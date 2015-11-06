import java.util.concurrent.locks.Lock;

public class Counter implements Runnable {

    private LockName lockName;
    private int threadCount;
    private int maxNumber;
    private int value;
    private Lock lock;

    /**
     * Counter Constructor
     * @param value starting value for the counter
     * @param lockName name of the lock were going to use for this counter
     * @param threadCount Number of threads competing for the counter
     * @param maxNumber Max Number of increments we want to allow
     */
    public Counter(int value, LockName lockName, int threadCount, int maxNumber) {
        this.value = value;
        this.lockName = lockName;
        this.threadCount = threadCount;
        this.maxNumber = maxNumber;

        //Determine which lock to create
        switch (this.lockName) {
            case BAKERY_LOCK:
                lock = new Bakery(threadCount);
                break;
            case ARRAY_LOCK:
                //Change to array lock
               // lock = new Bakery(threadCount);
                break;
            case TTAS_LOCK:
                //Change to TTAS lock
                //lock = new TtasLock(threadCount);
            default:

        }
    }

    /**
     * Gets the value of the counter and increments it by one
     * Needs to return the counter value so that the thread
     * continues to try and increment it
     * @return the value of the counter
     */
    public int getAndIncrement() {

        lock.lock();
        try {
            //If we reach the end return our max value
            if (value >= maxNumber) {
                return value;
            }
            value++;
        } finally {
            lock.unlock();
        }
        return value;
    }

    public void getHistory() {
        //Logging goes here
        for (int i=0;i < ((Bakery) lock).getThreadHistory().length;i++) {
            System.out.println("Thread Count: " + i + " Incremented: " + ((Bakery) lock).getThreadHistory()[i] + " Times");
        }
    }

    @Override
    public void run() {

        //Check that we are not higher than the max number of increments
        while (getAndIncrement() < maxNumber) {
            //Once we hit max this will complete
        }
    }
}
