import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer {
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
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
        Thread.currentThread().interrupt();
    }

    public void sell() {
        lock.lock();
        try {
            while (cars.isEmpty()) {
                System.out.printf("Нет авто для %s\n", Thread.currentThread().getName());
                condition.await();
            }
            cars.poll();
            System.out.printf("Покупатель %s купил автомобиль\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void attentionWeHaveAACar() {
        condition.signal();
    }
}
