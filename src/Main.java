public class Main {
    public static final long SLEEP = 1000;

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        Showroom showroom = new Showroom();
        Buyer buyer = new Buyer(showroom);
        Runnable buying = buyer::buy;

        new Thread(null, () -> producer.produce(showroom),"BMW").start();
        new Thread(null, buying, "Покупатель1").start();
        Thread.sleep(SLEEP);
        new Thread(null, buying, "Покупатель2").start();
        Thread.sleep(SLEEP);
        new Thread(null, buying, "Покупатель3").start();
    }
}
