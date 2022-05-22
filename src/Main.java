public class Main {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Showroom showroom = new Showroom();
        Buyer buyer = new Buyer(showroom);
        Runnable buying = buyer::buy;

        new Thread(null, () -> producer.produce(showroom),"BMW").start();
        new Thread(null, buying, "Покупатель1").start();
        new Thread(null, buying, "Покупатель2").start();
        new Thread(null, buying, "Покупатель3").start();
    }
}
