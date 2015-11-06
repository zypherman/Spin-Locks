import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Base Lock that we can extend to handle our 3 different types of locks
 */
public abstract class BaseLock implements Lock {

    private static String NOT_IMPLEMENTED = "Not Yet Implemented";

    /**
     * Base Lock
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Base Lock
     * @return returns nothing
     */
    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Base Lock
     * @param time time
     * @param unit unit
     * @return returns nothing
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    /**
     * Base Lock
     * @return returns nothing
     */
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    //Utility Methods
    /**
     * @param capacity thread count
     * @return thread id
     * @see #getCurrentThreadId()
     * @deprecated
     */
    public static int getCurrentThreadId(int capacity) {
        return (int) (Thread.currentThread().getId() % capacity);
    }

    /**
     * @return thread id which is set to thread's name
     */
    public static int getCurrentThreadId() {
        return Integer.parseInt(Thread.currentThread().getName());
    }
}
