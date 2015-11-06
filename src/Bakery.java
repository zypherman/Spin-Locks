import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * Bakery lock relies upon first in first out order
 * If a thread has a lower label than you let it go first
 * on the off chance that you both have the same label
 * we just take the one with the lower thread id to break the tie
 */
public class Bakery extends BaseLock implements Lock {

    //Using atomic variables because we can
    private AtomicBoolean[] flag;
    private AtomicInteger[] label;
    private int[] threadHistory;

    private int threads;

    /**
     * Constructor for Bakery lock
     *
     * @param threads thread count
     */
    public Bakery(int threads) {
        this.threads = threads;
        //Create a new array of booleans size equal to the number of threads we have running
        flag = new AtomicBoolean[threads];
        //Do the same for labels
        label = new AtomicInteger[threads];

        //Create a new Atomic Boolean and Atomic Integer for each item
        for (int i = 0; i < threads; i++) {
            flag[i] = new AtomicBoolean();
            label[i] = new AtomicInteger();
        }

        threadHistory = new int[threads];
    }

    /**
     * Acquire the lock
     */
    @Override
    public void lock() {
        //Get id of the current thread
        int threadId = getCurrentThreadId();
        //Raise the flag for our thread
        flag[threadId].set(true);
        //Make our label to be 1 more than the highest label
        label[threadId].set(findMaximumElement(label) + 1);

        //For all the threads we have
        for (int j = 0; j < threads; j++) {
            //While not on the current thread and our thread has its flag raised and (our iterator label is less than current thread
            // or (iterator label is equal to current label as well as being less than current thread
            while ((j != threadId) && flag[j].get() &&
                    ((label[j].get() < label[threadId].get())
                            || ((label[threadId].get() == label[j].get())
                            && j < threadId))) {
                //Spin lock will wait until conditions are met
                //We don't actually have to do anything in here
                threadHistory[getCurrentThreadId()]++;
            }
        }
    }

    /**
     * Release lock
     * lowers the threads flag in the flag array
     */
    @Override
    public void unlock() {
        flag[getCurrentThreadId()].set(false);
    }

    /**
     * Find the max element in the array of thread labels
     * @param labelArray label array
     * @return maximum maxLabel
     */
    private int findMaximumElement(AtomicInteger[] labelArray) {
        int maxLabel = 0;
        //Find the max label
        for (AtomicInteger label : labelArray) {
            if (label.get() > maxLabel) {
                maxLabel = label.get();
            }
        }
        return maxLabel;
    }

    public int[] getThreadHistory() {
        return threadHistory;
    }

}
