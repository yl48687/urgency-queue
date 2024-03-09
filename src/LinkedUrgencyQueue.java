import java.lang.Comparable;
import java.util.function.Predicate;
import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;
import cs1302.p3.BaseLinkedUrgencyQueue;

/**
 * Constructs an abstract class {@code LinkedUrgencyQueue} that extends
 * {@code BaseLinkedUrgencyQueue} and {@code Comparable} and fully implements the
 * {@code UrgencyQueue} interface.
 */
public class LinkedUrgencyQueue<Type extends Comparable<Type>>
    extends BaseLinkedUrgencyQueue<Type> {

    /**
     * Construct a {@code LinkedUrgencyQueue}.
     */
    public LinkedUrgencyQueue() {
        super();
    } // LinkedUrgencyQueue

    @Override
    public boolean enqueue(Type item) {
        if (item == null) {
            throw new NullPointerException();
        } // if
        Node<Type> newNode = new Node<Type>(item);
        if (head == null || item.compareTo(head.getItem()) >= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<Type> current = head;
            while (current.hasNext() && item.compareTo(current.getNext().getItem()) < 0) {
                current = current.getNext();
            } // while
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        } // if
        size++;
        return true;
    } // enqueue

    @Override
    public UrgencyQueue<Type> dequeueMany(int num) {
        if (num < 0) {
            throw new IllegalArgumentException();
        } // if
        if (num > size()) {
            throw new IllegalStateException();
        } // if
        UrgencyQueue<Type> newQueue = new LinkedUrgencyQueue<>();
        for (int i = 0; i < num; i++) {
            newQueue.enqueue(dequeue());
        } // for
        return newQueue;
    } // dequeueMany

    @Override
    public UrgencyQueue<Type> filter(Predicate<Type> cond) {
        if (cond == null) {
            throw new NullPointerException();
        } // if
        LinkedUrgencyQueue<Type> newQueue = new LinkedUrgencyQueue<>();
        Node<Type> current = head;
        while (current != null) {
            Type item = current.getItem();
            if (cond.test(item)) {
                newQueue.enqueue(item);
            } // if
            current = current.getNext();
        } // while
        return newQueue;
    } // filter

} // LinkedUrgencyQueue<Type extends Comparable<Type>>
