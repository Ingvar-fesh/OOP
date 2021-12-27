import java.util.*;

public class Node<T> {
    private final T value;
    private final List<Node<T>> children = new ArrayList<>();

    /**
     * Constructs a node containing the specified value.
     * @param value - data of node
     */
    public Node(T value) {
        this.value = value;
    }


    /**
     * Adds the specified value to the list of this node's children.
     * @param element the value to add
     * @return the added child node
     */
    public Node<T> addChild(T element) {
        Node<T> newNode = new Node<T>(element);
        children.add(newNode);
        return newNode;
    }

    /**
     * Removes the first occurence of the specified value from the sub-tree
     * of this node, if it is present. All its children are also removed.
     *
     * @param element the value to remove
     * @return the number of nodes removed
     */
    public int removeChild(T element) {
        int sizeRemoved = 0;
        for (int i = 0; i < children.size(); ++i) {
            Node<T> child = children.get(i);
            if (Objects.equals(child.value, element)) {
                sizeRemoved = child.size();
                children.remove(i);
            }
            else {
                sizeRemoved = child.removeChild(element);
            }
            if (sizeRemoved > 0) {
                return sizeRemoved;
            }
        }
        return sizeRemoved;
    }

    /**
     * Get size children
     * @return size
     */
    public int size() {
        return children.stream().mapToInt(Node::size).sum() + 1;
    }

    /**
     * Get value current node
     * @return value
     */
    public T getValue() {
        return value;
    }

    /**
     * Get list of cgildren current node
     * @return children
     */
    public List<Node<T>> getChildren() {
        return children;
    }
}