public class Product implements IDisplay{
    private int id;
    private String title;
    private int quantity;
    private double price;

    public Product() {
    }

    public Product(int id, String title, int quantity, double price) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void display() {
        System.out.printf("%-10s%-20s%-20s%-20s%n", id, title, quantity, price);
    }
}
