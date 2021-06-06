public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
    }

    public LinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addHead(Product product) {
        Node node = new Node(product);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(this.head);
            head = node;
        }
    }

    public void addTail(Product product) {
        Node node = new Node(product);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void display() {
        Node current = head;

        while (current != null) {
            current.display();
            current = current.getNext();
        }
    }
}
