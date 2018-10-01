package Airplan;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CircularQueueImplementation {

    public static void main(String[] args) {

        CircularQueue<Luggage> circularQueue = new CircularQueue(8);
       /*
       SIMDI PERSONAL OBJESI  passenger alcam
       passengerler luggage leri
        Passengers
        */
        /*circularQueue.enqueue("Ali");
        circularQueue.enqueue("Burca");
        circularQueue.enqueue("Carder");
        circularQueue.enqueue("Damre");
        circularQueue.enqueue("zazaza");
        */
        circularQueue.enqueue(new Luggage(2018,13,"Classic"));

        System.out.println("Full Circular Queue" + circularQueue);

      /*  System.out.print("Dequeued following element from circular Queue ");
        System.out.println(circularQueue.dequeue() + " ");
        */

    }

}

//implementation of Circular Queue using Generics
class CircularQueue<E> {

    public int currentSize; //Current Circular Queue Size
    private E[] circularQueueElements;
    private int maxSize; //Circular Queue maximum size
    Queue<E> q = new LinkedList<>();

    private int rear;//rear position of Circular queue(new element enqueued at rear).
    private int front; //front position of Circular queue(element will be dequeued from front).      

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        circularQueueElements = (E[]) new Object[this.maxSize];
        currentSize = 0;
        front = -1;
        rear = -1;
    }

    public int size(){return maxSize;}
    /**
     * Enqueue elements to rear.
     */
    public void enqueue(E item) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("Circular Queue is full. Element cannot be added");
        }
        else {
            rear = (rear + 1) % circularQueueElements.length;
            circularQueueElements[rear] = item;
            currentSize++;

            if (front == -1) {
                front = rear;
            }
        }
    }

    /**
     * Dequeue element from Front.
     */
    public E dequeue() throws QueueEmptyException {
        E deQueuedElement;
        if (isEmpty()) {
            throw new QueueEmptyException("Circular Queue is empty. Element cannot be retrieved");
        }
        else {
            deQueuedElement = circularQueueElements[front];
            circularQueueElements[front] = null;
            front = (front + 1) % circularQueueElements.length;
            currentSize--;
        }
        return deQueuedElement;
    }

    /**
     * Check if queue is full.
     */
    public boolean isFull() {
        return (currentSize == circularQueueElements.length);
    }

    /**
     * Check if Queue is empty.
     */
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public String toString() {
        return "CircularQueue [" + Arrays.toString(circularQueueElements) + "]";
    }

}

class QueueFullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueFullException() {
        super();
    }

    public QueueFullException(String message) {
        super(message);
    }

}

class QueueEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueueEmptyException() {
        super();
    }

    public QueueEmptyException(String message) {
        super(message);
    }

}