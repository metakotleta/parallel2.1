import java.util.LinkedList;
import java.util.Queue;

public class Showroom {
    private Queue<Car> cars = new LinkedList<>();

    public Queue<Car> getCars() {
        return cars;
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

    public synchronized void newCarAttention() {
        notifyAll();
    }
}
