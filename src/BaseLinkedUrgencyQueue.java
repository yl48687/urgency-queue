import java.lang.Iterable;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;                    
import cs1302.gen.UrgencyQueue;
import cs1302.gen.Node;

/**
 * Constructs an abstract class {@code BaseLinkedUrgencyQueue} that implements a subset of the
 * abstract methods in the {@code UrgencyQueue} interface.
 */
public abstract class BaseLinkedUrgencyQueue<Type> implements UrgencyQueue<Type> {

    int size;
    Node<Type> head;

    /**
     * Construct a {@code BaseLinkedUrgencyQueue}. This constructor is never
     * intended to be called directly via {@code new}; instead, it should only
     * be called in child class constructors using {@code super()}.
     */
    public BaseLinkedUrgencyQueue() {
        head = null;
        size = 0;
    } // BaseLinkedUrgencyQueue

    @Override
    public int size() {
        return size;
    } // size

    @Override
    public Type peek() {
        if (head == null) {
            throw new IllegalStateException();
        } // if
        return head.getItem();
    } // peek

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (size() > 0) {
            Node<Type> current = head;
            sb.append(Node.asString(current));
            current = current.getNext();
            while (current != null) {
                sb.append(", ");
                sb.append(Node.asString(current));
                current = current.getNext();
            } // while
        } // if
        sb.append("]");
        return sb.toString();
    } // toString

    @Override
    public Type dequeue() {
        if (head == null) {
            throw new IllegalStateException();
        } // if
        Type item = peek();
        head = head.getNext();
        size--;
        return item;
    } // dequeue

    @Override
    public void dequeue(Consumer<Type> action) {
        if (action == null) {
            throw new NullPointerException();
        } // if
        if (head == null) {
            throw new IllegalStateException();
        } // if
        Type item = dequeue();
        action.accept(item);
    } // dequeue

    @Override
    public void dequeueMany(int num, Consumer<Type> action) {
        if (action == null) {
            throw new NullPointerException();
        } // if
        if (num < 0) {
            throw new IllegalArgumentException();
        } // if
        if (num > size()) {
            throw new IllegalStateException();
        } // if
        for (int i = 0; i < num; i++) {
            dequeue(action);
        } // for
    } // dequeueMany

    @Override
    public void clear() {
        head = null;
        size = 0;
    } // clear

    @Override
    public <SubType extends Type> boolean enqueueAll(Iterable<SubType> items) {
        if (items == null) {
            throw new NullPointerException();
        } // if
        boolean added = false;
        for (SubType item : items) {
            if (item == null) {
                throw new IllegalArgumentException();
            } // if
            if (enqueue(item)) {
                added = true;
            } // if
        } // for
        return added;
    } // enqueueAll

    @Override
    public Type[] toArray(IntFunction<Type[]> generator) {
        if (generator == null) {
            throw new NullPointerException();
        } // if
        Type[] array = generator.apply(size());
        Node<Type> current = head;
        int i = 0;
        while (current.hasNext()) {
            array[i] = current.getItem();
            current = current.getNext();
            i++;
        } // while
        return array;
    } // toArray

} // BaseLinkedUrgencyQueue<Type>
