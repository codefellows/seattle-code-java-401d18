package collections;

/**
 * There's no accessor (public) on the class
 * So it's only available in the package. (Like protected)
 * <p>
 * Note:  TValue or TWhatever or just T is the standard naming convention.
 * <p>
 * Sometimes you will also see U, but it's always upper starting.
 *
 * @param <TValue> The type of value this node can store
 */
class Node<TValue> {

    // the value of this node
    public TValue value;

    // the next value
    // we want it to match this node! (otherwise your list could get weird)
    // note: "protected" doesn't matter here, because Node itself is already
    protected Node<TValue> next;

    // You can use generic values in constructors and methods of this class the same as any
    public Node(TValue value) {
        this.value = value;
    }
}
