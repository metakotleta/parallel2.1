public class Buyer {

    public static final long BUY_TIMER = 1000;
    public static final int MAX_CARS = 3;
    private Producer producer;

    public Buyer(Producer producer) {
        this.producer = producer;
    }

    public void buy() {
        int haveMoney = 0;
        while (haveMoney++ < MAX_CARS) {
            try {
                Thread.sleep(BUY_TIMER);
                System.out.printf("%s идет за новой машинкой\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Производитель прекратил продажи в стране покупателя");
                e.printStackTrace();
            }
            producer.sell();
        }
        Thread.currentThread().interrupt();
    }
}
