package collections;

/**
 * A Generic collection of type TValue.
 * <p>
 * The TValue at the top is declaring it, every other item is using it.
 *
 * @param <TValue> The type of nodes that can be stored
 */
public class Collection<TValue> {

    // a protected node isn't available from outside the collections package
    // the node has to be of the same TValue!
    protected Node<TValue> head;

    /**
     * Adds the value to the Linked List
     *
     * @param value the value of the item
     */
    public void add(TValue value) {
        // TValue is inferred
        // (It'll tell you it's unnecessary if you use it on the right)
        Node<TValue> node = new Node<>(value);
        node.next = head;
        this.head = node;
    }

}
