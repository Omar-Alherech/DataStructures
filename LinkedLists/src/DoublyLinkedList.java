import java.util.NoSuchElementException;

/**
 * Implementation of a Doubly linked list based off a LinkedList Interface. Implements all functions that are
 * designated within the interface as the assignment states. The class is of generic type T, also Comparable to type T.
 * This allows the doubly-linked list to accept many different types of variables and also allows the use of the method
 * compareTo, useful for sorting and node placement.
 * <p>
 * This linked list can be seen as a singly-linked list that does not only have a singular link variable, both a head
 * and a tail node to allow movement in both directions.
 *
 * @param <T>
 * @author  Omar (Porapat) Alherech
 */
public class DoublyLinkedList<T extends Comparable<T>> implements LinkedList<T> {
    // Initialization of the Class Global Variables
    private Node<T> head;
    private Node<T> tail;
    //Cursor was not used in this implementation, but I have kept the variable here anyways
    private Node cursor;

    /**
     * Constructs a default implementation of this DoublyLinkedList, with all variables defaulting to null
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Sets the Class's global head variable to the specified Node Variable. If the head is still null, the head is
     * simply initialized as newHead. Otherwise, the head is added on top of the currently existing head node, using
     * logic similar to addHead()
     *
     * @param newHead The Node that is to be attached at the start of the linked list.
     */
    public void setHead(Node<T> newHead) {
        if (head == null) {
            head = newHead;
            head.setHead(null);
        } else {
            head.getNext().setHead(newHead);
            newHead.setTail(head.getNext());
            head = newHead;
        }
    }

    /**
     * Sets the Class's global tail variable to the specified Node Variable. If the tail is still null, the tail is
     * simply initialized as newTail. Otherwise, the tail is added on top of of the currently existing tail node, using
     * logic similar to addHead()
     *
     * @param newTail The newtail node that is to be used to attach it to the end of the linked list.
     */
    public void setTail(Node<T> newTail) {
        if (tail == null) {
            tail = newTail;
            if (head == null) {
                head = tail;
            }
        } else {
            tail.setTail(newTail);
            newTail.setHead(tail);
            tail = tail.getNext();
        }
    }

    public static void main(String[] args) {
        //This was used for self testing
        DoublyLinkedList boop = new DoublyLinkedList();
        boop.addHead(4);
        boop.addTail(5);
        boop.addTail(6);
        DoublyLinkedList beep = new DoublyLinkedList();
        beep.addHead(1);
        beep.addTail(2);
        beep.addTail(3);
        System.out.println(boop.merge(beep));
    }

    /**
     * Returns the generic type variable head.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Returns the generic type variable tail
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * Takes an element and creates and initialized a LinkedList node. The node is then stacked at the start of the
     * linked list as the head. If the head is null, the head is set to the newly created newHead node. If the head
     * is not null, links are created between the previous head node, and the head of the newly created head node is
     * set to null.
     *
     * @param element The element used to create the new Head Node.
     */
    public void addHead(T element) {
        Node<T> newHead = new Node<>(element);
        if (head != null) {
            newHead.setTail(head);
            head.setHead(newHead);
        }
        if (tail == null) {
            tail = newHead;
        }
        head = newHead;
        cursor = head;
    }

    /**
     * Takes an element and creates and initialized a LinkedList node. The node is then stacked at the bottom of the
     * linked list as the tail. If the tail is null, the tail is set to the newly created newTail node. If the head
     * is not null, links are created between the previous head node, and the tail of the newly created tail node is
     * set to null.
     *
     * @param element The element used to create the new Tail node.
     */
    public void addTail(T element) {
        Node<T> newTail = new Node<T>(element);
        // If the tail is not null, preparations are made to link the node in both directions to the end of the linked
        // list
        if (tail != null) {
            tail.setTail(newTail);
            newTail.setHead(tail);
            newTail.setTail(null);
        }
        if (head == null) {
            head = newTail;
        }
        tail = newTail;
    }

    /**
     * Takes the current head node of the linked list. If the head is not null, links are created to replace the next
     * node as the head node. If the head at any point is null, an error is thrown.
     *
     * @throws java.util.NoSuchElementException
     */
    public T removeHead() throws java.util.NoSuchElementException {
        // If the head is not null, the node is then saved temporarily to be returned, as the previous second Node is
        // now set to the head of the linked list.
        if (head != null) {
            T temp = head.getData();
            head.getNext().setHead(null);
            head = head.getNext();
            if (head == null) {
                throw new java.util.NoSuchElementException();
            }
            cursor = head;
            return temp;
        } else {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     * Takes the current tail node of the linked list. If the tail is not null, links are created to replace the
     * previous node as the tail node. If the tail is null at any point, an error is thrown.
     *
     * @throws java.util.NoSuchElementException
     */
    public T removeTail() throws java.util.NoSuchElementException {
        //
        if (tail != null) {
            T temp = tail.getData();
            tail = tail.getPrev();
            if (tail == null) {
                throw new java.util.NoSuchElementException();
            }
            tail.setTail(null);
            return temp;
        } else {
            tail = head;
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     * Takes an integer index value, and traverses the array using a nodePtr in order to return the Node specified using
     * the index.
     *
     * @param index The index at which the node is to be retrieved within the linked list.
     * @throws java.util.NoSuchElementException
     */
    public Node<T> get(int index) throws java.util.NoSuchElementException {
        Node<T> nodePtr = head;
        // If the tail is not null, the node is then saved temporarily to be returned, as the previous second to last
        //Node is now set to the tail of the linked list.
        if (head == null) throw new java.util.NoSuchElementException();
        if (index < 0) throw new java.util.NoSuchElementException();
        int i = 0;
        while (i < index) {
            if (nodePtr == null) throw new java.util.NoSuchElementException();
            nodePtr = nodePtr.getNext();
            i++;
        }
        if (nodePtr != null) {
            return nodePtr;
        } else {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     * Takes an integer index value, as well as a T element variable and creates a new Node. Using a nodePtr variable
     * the linked list is traversed and the element is then placed at the specified index joining and linking the
     * adjacent nodes.
     *
     * @param index   The index at which the new Node is to be inserted.
     * @param element The element used to create a new node to be inserted
     * @throws java.util.NoSuchElementException
     */
    public void add(int index, T element) throws java.util.NoSuchElementException {
        Node<T> nodePtr = head;
        Node<T> newNode = new Node<T>(element);
        int i = 0;
        if (index < 0) throw new java.util.NoSuchElementException();
        //While loop used to traverse the array, as well as to count and keep track of the indexes.
        while (i < index) {
            if (nodePtr == null) throw new java.util.NoSuchElementException();
            nodePtr = nodePtr.getNext();
            i++;
        }
        // If the pointer is not at the specified index, the new Node is placed in its specific position and then linked
        // to the adjacent nodes.
        if (nodePtr != null) {
            nodePtr.getPrev().setTail(newNode);
            newNode.setHead(nodePtr.getPrev());
            newNode.setTail(nodePtr);
            nodePtr.setHead(newNode);
        }
    }

    /**
     * Takes an integer index value, and traverses the array using a nodePtr in order to delete the specified node.
     * the node is then set null, as links are created with the previously adjacent nodes.
     *
     * @param index The index of the node that is to be removed within the linked list.
     * @throws java.util.NoSuchElementException
     */
    public T remove(int index) throws java.util.NoSuchElementException {
        Node<T> nodePtr = head;
        int i = 0;
        if (index < 0) throw new java.util.NoSuchElementException();
        while (i < index) {
            if (nodePtr == null) throw new java.util.NoSuchElementException();
            nodePtr = nodePtr.getNext();
            i++;
        }
        // If the node that is set to be removed is not null, adjacent nodes are linked, and the node set to be removed
        // is set to be null.
        if (nodePtr != null) {
            nodePtr.getPrev().setTail(nodePtr.getNext());
            nodePtr.getNext().setHead(nodePtr.getPrev());
            nodePtr.setTail(null);
            nodePtr.setHead(null);
            return nodePtr.getData();
        } else {
            throw new java.util.NoSuchElementException();
        }
    }

    /**
     * Returns a new list that is a copy of the linked list.
     */
    @Override
    public LinkedList<T> clone() {
        DoublyLinkedList<T> create_copy = new DoublyLinkedList<>();
        Node<T> nodePtr = head;
        if (nodePtr == null) throw new NoSuchElementException();
        //While loop in order to the traverse the linked list, nodePtr used to copy and create separate nodes.
        while (nodePtr != null) {
            if (create_copy.getHead() == null) {
                create_copy.addHead(nodePtr.getData());
            } else {
                create_copy.addTail(nodePtr.getData());
            }
            nodePtr = nodePtr.getNext();
        }
        return create_copy;
    }

    /**
     * Takes in a second Linked list and joins it to the tail of the original. Both linked lists are then joined and
     * The head and tail of the second list are then set to null.
     *
     * @param list The second list which is to be joined onto the original.
     */
    public void concatenate(LinkedList<T> list) {
        list.setHead(tail);
        tail.setTail(list.getHead());
        list.setHead(null);
        list.setTail(null);
    }

    /**
     * Traverses the entire linked list and removes out any elements that are "larger" than the T parameter target.
     *
     * @param target The target node that is to be compared to.
     */
    public void filter(T target) {
        Node<T> nodePtr = head;
        int i = 0;
        while (nodePtr != null) {
            // If the node is larger than the target parameter, it is removed using the same steps in remove(), adjacent
            //nodes are linked before the node is removed. The node is then set to be null.
            if ((nodePtr.getData().compareTo(target)) > 0) {
                if (nodePtr.getNext() != null) {
                    nodePtr.getNext().setHead(nodePtr.getPrev());
                    nodePtr.getPrev().setTail(nodePtr.getNext());
                    nodePtr = nodePtr.getPrev();
                } else {
                    nodePtr.getPrev().setTail(null);
                    tail = nodePtr.getPrev();
                    break;
                }
                i--;
            }
            nodePtr = nodePtr.getNext();
            i++;
        }
    }

    /**
     * Takes in a specified element, and adds the element to the current linked list in ascending order.
     *
     * @param element The element used to create the new node to be added in ascending order.
     */
    public void sortedAdd(T element) {
        Node<T> nodePtr = head;
        Node<T> insert = new Node<>(element);
        while (nodePtr != null) {
            // If the new element is the largest element, it is set to the tail.
            if (element.compareTo(tail.getData()) > 0) {
                tail.setTail(insert);
                insert.setHead(tail);
                tail = insert;
                break;
            }
            // If the nodePtr is pointing to a smaller value than that of the element, the nodePtr is then incremented.
            if ((nodePtr.getData().compareTo(element)) < 0) {
                nodePtr = nodePtr.getNext();
                continue;
            }
            // Element should be placed here, at the same value or before a higher value.
            if (nodePtr.getData().compareTo(element) >= 0) {
                // If the nodePtr is not currently on the tail, the node is added to the left of the nodeptr.
                if (nodePtr != tail) {
                    System.out.println("Not Tail");
                    System.out.println(element);
                    System.out.println(nodePtr.getData());
                    insert.setTail(nodePtr);
                    insert.setHead(nodePtr.getPrev());
                    nodePtr.getPrev().setTail(insert);
                    nodePtr.setHead(insert);
                } else {
                    // If the nodePtr is pointing at the tail, the node is then placed before the element if it is of
                    //smaller value
                    insert.setHead(nodePtr.getPrev());
                    insert.setTail(nodePtr);
                    nodePtr.getPrev().setTail(insert);
                    nodePtr.setHead(insert);
                    nodePtr.setTail(null);
                }
                break;
            }
        }
    }

    /**
     * Sorts the current Linked list in ascending order using the sortedAdd() method previously used.
     */
    public LinkedList<T> sort() {
        // Set the tail and head to avoid any mismatch
        LinkedList new_list = new DoublyLinkedList();
        new_list.addHead(head.getData());
        new_list.addTail(tail.getData());
        Node<T> nodePtr = this.head.getNext();
        // use Sortedadd() to place the elements in sorted ascending order between the head and tail node.
        while (nodePtr != null) {
            if (nodePtr == tail) {
                break;
            }
            new_list.sortedAdd(nodePtr.getData());
            nodePtr = nodePtr.getNext();
            System.out.println("Loop***********");
            System.out.println(new_list);
        }
        return new_list;
    }

    /**
     * Removes all repeating elements, creating a unique Linked List
     */
    public void removeDuplicates() {
        Node<T> nodePtr = head;
        while (nodePtr != null) {
            if (nodePtr.getPrev() != null) {
                if (nodePtr.getPrev().getData().compareTo(nodePtr.getData()) == 0) {
                    nodePtr.getNext().setHead(nodePtr.getPrev());
                    nodePtr.getPrev().setTail(nodePtr.getNext());
                }
            }
            nodePtr = nodePtr.getNext();
        }
    }

    /**
     * Puts the parameter Linked list at the start of the original Linked list.
     *
     * @params list The list that is to be merged at the start of the original list.
     */
    public LinkedList<T> merge(LinkedList<T> list) {
        LinkedList return_list = new DoublyLinkedList<>();
        Node<T> nodePtr = list.getHead();
        while (nodePtr != null) {
            if (return_list.getHead() == null) {
                return_list.addHead(nodePtr.getData());
            } else {
                return_list.addTail(nodePtr.getData());
            }
            nodePtr = nodePtr.getNext();
        }
        nodePtr = this.getHead();
        while (nodePtr != null) {
            return_list.addTail(nodePtr.getData());
            nodePtr = nodePtr.getNext();
        }
        return return_list;
    }


    public String toString() {
        String s = "";

        s += "head=" + (head == null ? "null" : head.getData()) + " ";
        s += "tail=" + (tail == null ? "null" : tail.getData());
        s += "\n";
        Node<T> nodePtr = head;
        while (nodePtr != null) {
            s += nodePtr.getData() + "->";
            nodePtr = nodePtr.getNext();
        }
        s += "null";
        s += "\n";
        nodePtr = tail;
        while (nodePtr != null) {
            s += nodePtr.getData() + "->";
            nodePtr = nodePtr.getPrev();
        }
        s += "null";
        return s;
    }

}