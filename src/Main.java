public class Main {

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        Buyer buyer = new Buyer(producer);
        Runnable buying = buyer::buy;
        Runnable producing = producer::produce;

        new Thread(null, producing, "BMW").start();
        new Thread(null, buying, "Покупатель1").start();
        new Thread(null, buying, "Покупатель2").start();
        new Thread(null, buying, "Покупатель3").start();
    }
}
