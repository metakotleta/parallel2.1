import java.util.LinkedList;
import java.util.Queue;

public class Producer {
    public static final long PRODUCE_TIME = 2000;
    public static int carsCount = 0;
    private Queue<Car> cars = new LinkedList<>();

    public void produce() {
        while (carsCount++ < 9) {
            try {
                Thread.sleep(PRODUCE_TIME);
                cars.offer(new Car());
                System.out.printf("Производитель %s выпустил 1 авто\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attentionWeHaveAACar();
        }
        attentionWeHaveAACar();
        Thread.currentThread().interrupt();
    }

    public synchronized void sell() {
        try {
            while (cars.peek() == null) {
                System.out.printf("Нет авто для %s\n", Thread.currentThread().getName());
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cars.poll();
        System.out.printf("Покупатель %s купил автомобиль\n", Thread.currentThread().getName());
    }

    public synchronized void attentionWeHaveAACar() {
        notify();
    }
}
