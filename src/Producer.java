public class Producer {
    public static final long PRODUCE_TIME = 3000;
    public static int carsCount = 0;

    public void produce(Showroom showroom) {
        while (carsCount++ < 9) {
            try {
                Thread.sleep(PRODUCE_TIME);
                showroom.getCars().offer(new Car());
                showroom.newCarAttention();
                System.out.printf("Производитель %s выпустил 1 авто\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }
}
