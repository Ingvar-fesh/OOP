import java.util.*;

public class Tree<T> implements Iterable<T> {

    private Node<T> root = null;
    private int size = 0;
    private int modCount = 0;

    /**
     * Adds the specified element into this tree. If the tree is empty, then
     * the added element is the new root node. Otherwise, it is a child node of
     * the root.
     * @param element the element to add
     * @return the added node of this tree
     */
    public Node<T> add(T element) {
        ++modCount;
        ++size;
        if (size == 1) {
            root = new Node<>(element);
            return root;
        } else return root.addChild(element);
    }

    /**
     * Adds the specified element into this tree as a child of the given node.
     * @param node the node to add a child element to
     * @param element the element to add
     * @return the added node of this tree
     */
    public Node<T> add(Node<T> node, T element) {
        ++modCount;
        ++size;
        return node.addChild(element);
    }

    /**
     * Removes the first (according to the tree's iterator) occurence of the
     * specified element from this tree, if it is present. All its children
     * nodes are also removed.
     * @param element the element to add
     * @return <code>true</code> if this tree was modified
     */
    public boolean remove(T element) {
        modCount++;
        if (Objects.equals(root.getValue(), element)) {
            root = null;
            size = 0;
            return true;
        } else {
            int sizeRemoved = root.removeChild(element);
            size -= sizeRemoved;
            return sizeRemoved > 0;
        }
    }

    /**
     * Removes all elements from this tree.
     */
    public void clear() {
        modCount++;
        root = null;
        size = 0;
    }

    /**
     * Checks if this tree contains the specified element.
     * @param data the element to search for
     * @return <code>true</code> if this tree contains <code>e</code>
     */
    public boolean contains(T data) {
        for (var i : this) {
            if (i.equals(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this tree is empty.
     * @return <code>true</code> if this tree is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this tree.
     * @return the size of this tree
     */
    public int size() {
        return size;
    }

    private abstract class TreeIterator<S> implements Iterator<S> {
        Deque<Node<T>> deque;
        int expectedModCount = modCount;

        public TreeIterator() {
            deque = new ArrayDeque<>();
            if (root != null) {
                deque.add(root);
            }
        }

        void checkModCount() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }
    }

    /**
     * Returns an iterator over the elements of this tree, traversing the tree
     * in depth-first order.
     * @return the DFS iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<T>() {
            @Override
            public T next() {
                checkModCount();
                Node<T> topNode = deque.removeFirst();
                var it = topNode.getChildren().listIterator(topNode.getChildren().size());
                while (it.hasPrevious())
                    deque.addFirst(it.previous());
                return (T) topNode.getValue();
            }
        };
    }

    /**
     * Returns an iterator over the elements of this tree, traversing the tree
     * in breadth-first order.
     * @return the BFS iterator
     */
    public Iterator<T> bfsIterator() {
        return new TreeIterator<T>() {
            @Override
            public T next() {
                checkModCount();
                Node<T> topNode = deque.removeFirst();
                deque.addAll(topNode.getChildren());
                return topNode.getValue();
            }
        };
    }
}