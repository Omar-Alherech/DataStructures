/**
 * Implementation of a node, intended to be used to build a Doubly-LinkedList.
 * <p>
 * Each node has it's own generic type data variable, a Node Linked as a head, and a Node linked as a tail. This allows
 * all the nodes in the Doubly-LinkedList to be connected, and allows for the ability to traverse back and forth, an
 * ability a singly-linkedList does not have.
 *
 * @param <T>
 * @author Omar (Porapat) Alherech
 */
public class Node<T> {

    // Variables
    private T data;
    private Node<T> head;
    private Node<T> tail;

    // Node Methods - Constructor
    /**
     * Constructs a default implementation of the Node class, with head and tail defaulting to None, and initializing
     * the data field.
     */
    public Node(T newData) {
        data = newData;
        head = null;
        tail = null;
    }

    // Node Methods - Getters
    /**
     *  Returns the node that is linked to the head of the current node.
     */
    public Node<T> getPrev() {
        return head;
    }
    /**
     *  Returns the node that is linked to the tail of the current node.
     */
    public Node<T> getNext() {
        return tail;
    }
    /**
     *  Returns T data variable value of the current node.
     */
    public T getData() {
        return data;
    }
    // Node Methods - Setters
    /**
     *  Sets the head of the current node
     * @param newHead The new head of the current node.
     */
    public Node<T> setHead(Node newHead) {
        head =  newHead;
        return head;
    }
    /**
     *  Sets the tail of the current node
     * @param newTail The new tail of the current node.
     */
    public Node<T> setTail(Node newTail) {
        tail = newTail;
        return tail;
    }
    /**
     *  Sets the T data value
     * @param newData The new data value for the current node.
     */
    public T setData(T newData) {
        data = newData;
        return data;
    }
}