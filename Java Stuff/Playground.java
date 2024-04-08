import java.util.concurrent.locks.*;
class Playground {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello world!");
        Lock lokk = new ReentrantLock();
        Thread crewMember = new Thread(new Sus());
        crewMember.start();
        crewMember.join();
        System.out.println("Imposter wins");
    }
    public static class Sus implements Runnable {
        @Override
        public void run() {
            System.out.println("amongus");
        }
    }
}