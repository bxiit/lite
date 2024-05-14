import java.util.LinkedList;

public class YeraLinkedList<T> {
    Node<T> first;
    Node<T> last;
    int size;

    public void add(T o) {
        linkLast(o);
    }


    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(T e) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    public void print() {
        Node<T> t = first;
        while (t.next != null) {
            System.out.println(t.next.data);
            t = t.next;
        }
    }
}
