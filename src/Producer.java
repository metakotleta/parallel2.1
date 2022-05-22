public class Producer {
    public static final long PRODUCE_TIME = 3000;
    public static final long MAX_CARS = 9;
    public static int carsCount = 0;    

    public void produce(Showroom showroom) {
        while (carsCount++ < MAX_CARS) {
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
