import java.util.function.Predicate;
import java.util.Comparator;
import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;

/**
 * Constructs an abstract class {@code CustomLinkedUrgencyQueue} that extends
 * {@code BaseLinkedUrgencyQueue} and fully implements the {@code UrgencyQueue} interface.
 */
public class CustomLinkedUrgencyQueue<Type> extends BaseLinkedUrgencyQueue<Type> {

    java.util.Comparator<Type> comparator;

    /**
     * Construct a {@code CustomLinkedUrgencyQueue}.
     *
     * @param cmp a function that lets you determine the urgency order
     *     between two items
     * @throws NullPointerException if {@code cmp} is {@code null}
     */
    public CustomLinkedUrgencyQueue(Comparator<Type> cmp) {
        super();
        if (cmp == null) {
            throw new NullPointerException();
        } // if
        this.comparator = cmp;
    } // CustomLinkedUrgencyQueue

    @Override
    public boolean enqueue(Type item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        } // if
        Node<Type> newNode = new Node<Type>(item);
        if (head == null || comparator.compare(item, head.getItem()) >= 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return true;
        } // if
        Node<Type> current = head;
        while (current.getNext() != null) {
            Node<Type> next = current.getNext();
            if (comparator.compare(item, next.getItem()) >= 0) {
                newNode.setNext(next);
                current.setNext(newNode);
                size++;
                return true;
            } // if
            current = current.getNext();
        } // while
        current.setNext(newNode);
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
        UrgencyQueue<Type> newQueue = new CustomLinkedUrgencyQueue<>(comparator);
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
        Node<Type> current = head;
        UrgencyQueue<Type> newQueue = new CustomLinkedUrgencyQueue<>(comparator);
        for (int i = 0; i < size(); i++) {
            Type item = current.getItem();
            if (cond.test(item)) {
                newQueue.enqueue(item);
            } // if
            current = current.getNext();
        } // for
        return newQueue;
    } // filter

} // CustomLinkedUrgencyQueue<Type>
