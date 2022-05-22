import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Showroom {
    private Queue<Car> cars = new LinkedList<>();
    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    public Queue<Car> getCars() {
        return cars;
    }

    public void sell() {
        lock.lock();
        try {
            while (cars.peek() == null) {
                System.out.printf("Нет авто для %s\n", Thread.currentThread().getName());
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        cars.poll();
        System.out.printf("Покупатель %s купил автомобиль\n", Thread.currentThread().getName());
    }

    public void newCarAttention() {
        lock.lock();
        try {
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
