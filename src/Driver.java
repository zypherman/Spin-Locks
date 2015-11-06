/**
 * Main driver class
 */
public class Driver {

    public static void startThreads(Thread[] threads) throws InterruptedException {

        int numberOfThreads = 8;
        int threadCounter = 0;
        int t2 = 10000;
        int t4 = t2/2;
        int t8 = t4/2;
        int t16 = t8/2;

        //Create our counter we might
        // Have to pull this out into a factory so that we can iterate through the different types
        //Of locks
        Counter counter = new Counter(0, LockName.BAKERY_LOCK, numberOfThreads, t8);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(counter);
            //Set the name of the thread
            //Give it a unique name based off our thread counter
            threads[i].setName(String.valueOf(threadCounter++));
        }

        //For each thread start it
        for (Thread thread : threads) {
            thread.start();
        }

        //Join threads to current main thread to die when they do
        for (Thread thread : threads) {
            thread.join();
        }

        counter.getHistory();

    }

    public static void main(String[] args) {

        int numberOfThreads = 8;
       // int threadCounter = 0;
        Thread[] threads;

        //Array of threads we have running
        //Pull out into config
        threads = new Thread[numberOfThreads];

        try {
            startThreads(threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Instant starttime = Instant.now();

        //Create our counter we might
        // Have to pull this out into a factory so that we can iterate through the different types
        //Of locks
//        Counter counter = new Counter(0, LockName.BAKERY_LOCK, numberOfThreads, 10000);
//
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new Thread(counter);
//            //Set the name of the thread
//            //Give it a unique name based off our thread counter
//            threads[i].setName(String.valueOf(threadCounter++));
//        }
//
//        //For each thread start it
//        for (Thread thread : threads) {
//            thread.start();
//        }
//
//        try {
//            //Join threads to current main thread to die when they do
//            for (Thread thread : threads) {
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Instant endTime = Instant.now();
        //Duration duration = new Duration(starttime - endTime);
    }
}
