public class Node implements IDisplay{
    private Product product;
    private Node next;

    public Node (Product product) {
        this.product = product;
        next = null;
    }

    public Node(Product product, Node next) {
        this.product = product;
        this.next = next;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public void display() {
        product.display();
    }
}
