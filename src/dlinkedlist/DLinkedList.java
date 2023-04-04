package dlinkedlist;

import java.lang.reflect.Array;
import java.util.*;

public class DLinkedList<E> implements List<E> {
    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        private Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    private final Node<E> frontCapNode;
    private final Node<E> tailCapNode;
    private int size;

    public DLinkedList() {
        frontCapNode = new Node<>(null, null, null);
        tailCapNode = new Node<>(null, null, null);
        startup();
    }

    public DLinkedList(Collection<? extends E> c) {
        this();
        for (E e : c) {
            insertBefore(tailCapNode, e);
        }
    }

    private void startup() {
        frontCapNode.next = tailCapNode;
        tailCapNode.prev = frontCapNode;
        size = 0;
    }


    private Node<E> getNode(Object obj) {
        Node<E> node = frontCapNode.next;
        while (!node.equals(tailCapNode)) {
            if (Objects.equals(node.element, obj)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private Node<E> getNode(int index) {
        if (index < (size / 2)) {
            Node<E> node = frontCapNode.next;
            for (int elementIndex = 0; elementIndex <= size / 2; elementIndex++) {
                if (index == elementIndex) {
                    return node;
                }
                node = node.next;
            }
        } else {
            Node<E> node = tailCapNode.prev;
            for (int elementIndex = size - 1; elementIndex >= size / 2; elementIndex--) {
                if (index == elementIndex) {
                    return node;
                }
                node = node.prev;
            }
        }
        return null;
    }

    private void insertBefore(Node<E> node, E element) {
        Node<E> previous = node.prev;
        Node<E> newNode = new Node<>(previous, element, node);
        previous.next = newNode;
        node.prev = newNode;
        size++;
    }

    private boolean delete(Node<E> node) {
        if (node != null) {
            Node<E> previousElement = node.prev;
            Node<E> nextElement = node.next;
            previousElement.next = nextElement;
            nextElement.prev = previousElement;
            size--;
        }
        return node != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return getNode(o) != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = frontCapNode;

            @Override
            public boolean hasNext() {
                return current.next != tailCapNode;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    Node<E> tempNode = current;
                    current = current.next;
                    return tempNode.element;
                }
                throw new NoSuchElementException();

            }

            @Override
            public void remove() {
                delete(current);

            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<E> node = frontCapNode.next;
        int i = 0;
        while (!node.equals(tailCapNode)) {
            result[i++] = node.element;
            node = node.next;
        }
        return result;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size || a.length > size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> node = frontCapNode.next; node != tailCapNode; node = node.next) {
            result[i++] = node.element;
        }
        a = (T[]) result;
        return a;
    }

    @Override
    public boolean add(E e) {
        insertBefore(tailCapNode, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> node = getNode(o);
        return delete(node);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (Objects.equals(c, null)) {
            throw new NullPointerException();
        }
        if (!(c instanceof DLinkedList<?>)) {
            throw new ClassCastException();
        }
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (Objects.equals(c, null)) {
            throw new NullPointerException();
        }
        for (E e : c) {
            insertBefore(tailCapNode, e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < size || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (c == null) {
            throw new NullPointerException();
        }
        Node<E> node = getNode(index);
        for (E e : c) {
            assert node != null;
            insertBefore(node, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (!(c instanceof DLinkedList<?>)) {
            throw new ClassCastException();
        }
        boolean result = false;
        Node<E> node = frontCapNode.next;
        while (!node.equals(tailCapNode)) {
            if (c.contains(node.element)) {
                result |= delete(node);
            }
            node = node.next;
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (!(c instanceof DLinkedList<?>)) {
            throw new ClassCastException();
        }
        boolean result = false;
        Node<E> node = frontCapNode.next;
        while (!node.equals(tailCapNode)) {
            if (!c.contains(node.element)) {
                result |= delete(node);
            }
            node = node.next;
        }
        return result;
    }

    @Override
    public void clear() {
        startup();
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = getNode(index);
        assert node != null;
        return node.element;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = getNode(index);
        assert node != null;
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = getNode(index);
        assert node != null;
        insertBefore(node, element);
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = getNode(index);
        assert node != null;
        E result = node.element;
        delete(node);
        return result;
    }

    @Override
    public int indexOf(Object o) {
        int nodeIndex = 0;
        Node<E> node = frontCapNode.next;
        while (!node.equals(tailCapNode)) {
            if (Objects.equals(node.element, o)) {
                return nodeIndex;
            }
            nodeIndex++;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int nodeIndex = size - 1;
        Node<E> node = tailCapNode.prev;
        while (!node.equals(frontCapNode)) {
            if (Objects.equals(node.element, o)) {
                return nodeIndex;
            }
            nodeIndex--;
            node = node.prev;
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<E> node = frontCapNode.next;
        while (!node.equals(tailCapNode)) {
            if (!node.equals(frontCapNode.next)) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(node.element);
            node = node.next;
        }
        return stringBuilder.toString();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            private Node<E> current = frontCapNode;

            @Override
            public boolean hasNext() {
                return current.next != tailCapNode;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    current = current.next;
                    return current.element;
                }
                throw new NoSuchElementException();
            }

            private Node<E> previousElement = tailCapNode;

            @Override
            public boolean hasPrevious() {
                return previousElement.prev != frontCapNode;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    previousElement = previousElement.prev;
                    return previousElement.element;
                }
                throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return indexOf(current.next.element);
            }

            @Override
            public int previousIndex() {
                return indexOf(previousElement.prev.element);
            }

            @Override
            public void remove() {
                if (current.prev == null) {
                    throw new IllegalStateException();
                }
                delete(current);

            }

            @Override
            public void set(E e) {
                current.element = e;
            }

            @Override
            public void add(E e) {
                insertBefore(tailCapNode, e);

            }

        };
    }


    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator<E>() {

            final Node<E> helper = getNode(index);
            Node<E> current;

            {
                assert helper != null;
                current = helper.prev;
            }

            @Override
            public boolean hasNext() {
                return current.next != tailCapNode;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    current = current.next;
                    return current.element;
                }
                throw new NoSuchElementException();
            }

            final Node<E> helperPrevius = getNode(index);
            Node<E> previousElement;

            {
                assert helperPrevius != null;
                previousElement = helperPrevius.prev;
            }

            @Override
            public boolean hasPrevious() {
                return previousElement.prev != frontCapNode;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    previousElement = previousElement.prev;
                    return previousElement.element;
                }
                throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return indexOf(current.next.element);
            }

            @Override
            public int previousIndex() {
                return indexOf(previousElement.prev.element);

            }

            @Override
            public void remove() {
                if (current.prev == null) {
                    throw new IllegalStateException();
                }
                delete(current);
            }

            @Override
            public void set(E e) {
                current.element = e;
            }

            @Override
            public void add(E e) {
                insertBefore(tailCapNode, e);

            }
        };

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Node<E> currentNode = getNode(fromIndex);
        List<E> subList = new DLinkedList<>();
        for (int index = fromIndex; index <= toIndex; index++) {
            assert currentNode != null;
            subList.add(currentNode.element);
            currentNode = currentNode.next;
        }
        return subList;
    }

}
