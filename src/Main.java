import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner keyboard = new Scanner(System.in);
    static LinkedList linkedList = new LinkedList();

    static File file = new File("src/data.txt");

    public static void importData(LinkedList destination, boolean isQueue) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        reader.readLine();
        reader.readLine();
        String rawLine = reader.readLine();
        while (rawLine != null) {
            //convert lines of String to product field
            String[] data = rawLine.split(";");
            int id = Integer.parseInt(data[0].strip());
            String title = data[1].strip();
            int quantity = Integer.parseInt(data[2].strip());
            double price = Double.parseDouble(data[3].strip());

            if (isQueue) { // queue
                destination.addTail(new Product(id,title,quantity,price));
            } else { // stack
                destination.addHead(new Product(id, title, quantity, price));
            }
            rawLine = reader.readLine();
        }

        reader.close();
    }

    static void exportProduct(Product product) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        int id = product.getId();
        String title = product.getTitle();
        int quantity = product.getQuantity();
        double price = product.getPrice();

        //Convert to one line and write to file
        String txtFormat = String.format("%-10s%-20s%-20s%-20s", id + ";", title + ";", quantity +";", price);
        writer.append(txtFormat);
        writer.newLine();

        writer.close();
    }

    static void exportAll() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(String.format("%-10s%-20s%-20s%-20s", "ID", "TITLE", "QUANTITY", "PRICE"));
        writer.newLine();
        writer.append("---------------------");
        writer.newLine();
        Node node = linkedList.getHead();
        while (node != null) {
            int id = node.getProduct().getId();
            String title = node.getProduct().getTitle();
            int quantity = node.getProduct().getQuantity();
            double price = node.getProduct().getPrice();

            //Convert to one line and write to file
            String txtFormat = String.format("%-10s%-20s%-20s%-20s", id + ";", title + ";", quantity +";", price);
            writer.append(txtFormat);
            writer.newLine();

            node = node.getNext();
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        int choice;
        do {
            menu();
            choice = Integer.parseInt(keyboard.nextLine());
            switch (choice) {
                case 0 -> {}

                //Import data to linked list and display
                case 1 -> {
                    linkedList.clear();

                    importData(linkedList, true);
                    resultHeader();
                    linkedList.display();
                }

                //Add a new product to tail
                case 2 -> {
                    int isRepeat;
                    do {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(keyboard.nextLine());
                        System.out.print("Title: ");
                        String title = keyboard.nextLine();
                        System.out.print("Quantity: ");
                        int quantity = Integer.parseInt(keyboard.nextLine());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(keyboard.nextLine());

                        Product newProduct = new Product(id, title, quantity, price);

                        linkedList.addTail(newProduct);
                        exportProduct(newProduct);
                        lineBreak();
                        System.out.println("Add new product successfully");
                        resultHeader();
                        newProduct.display();
                        System.out.println();

                        System.out.print("Do you want to add another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(keyboard.nextLine());
                    } while (1 == isRepeat);
                }

                //Visit all product and display info
                case 3 -> linkedList.display();

                //Export data to file
                case 4 -> exportAll();

                //Search product by ID
                case 5 -> {
                    int isRepeat;
                    do {
                        Tools.search();
                        System.out.print("Do you want to search another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(keyboard.nextLine());
                    } while (1 == isRepeat);
                }

                //Delete product by ID
                case 6 -> {
                    int isRepeat;
                    do {
                        Tools.delete();
                        System.out.print("Do you want to delete another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(keyboard.nextLine());
                    } while (1 == isRepeat);
                }

                //Sort by ID
                case 7 -> {

                }

                //Convert to binary
                case 8 -> {
                    System.out.println("The first product: ");
                    Product product = linkedList.getHead().getProduct();
                    resultHeader();
                    product.display();
                    System.out.print("The quantity in the Binary Number: ");
                    System.out.println(Tools.convertToBinary(product.getQuantity()));
                }

                //Import to stack
                case 9 -> {
                    LinkedList linkedStack = new LinkedList();
                    importData(linkedStack, false);

                    linkedStack.display();
                }

                //Import to queue
                case 10 -> {
                    LinkedList linkedQueue = new LinkedList();
                    importData(linkedQueue, true);

                    linkedQueue.display();
                }
                default -> System.out.println("Your choice is not correct, please try again!");
            }

            if (0 == choice) break;
            if (choice <= 10) {
                System.out.print("Back to main menu? (\"1\" for yes): ");
                choice = Integer.parseInt(keyboard.nextLine());
            } else choice = 1;
        } while (choice == 1);
        lineBreak();
        System.out.println("Good bye");
    }

    static void menu() {
        System.out.printf("%-25s","");
        System.out.println("What do you want to do?");
        System.out.printf("%-25s","");
        lineBreak();

        columnsSplit("1. Input data",
                "6. Delete a product via ID");
        columnsSplit("2. Add a new product",
                "7. Sort all products by IDs");
        columnsSplit("3. Display all products",
                "8. Convert first quantity to Binary number");
        columnsSplit("4. Output data",
                "9. Import data to stack");
        columnsSplit("5. Search a product via ID",
                "10. Import data to queue");

        System.out.printf("%-25s","");
        lineBreak();
        System.out.print("Your choice (\"0\" for exit): ");
    }

    static void lineBreak() {
        System.out.println("-----------------------");
    }
    static void columnsSplit (String s1, String s2) {
        System.out.printf("%-50s%s%n", s1, s2);
    }

    static void resultHeader() {
        System.out.printf("%-10s%-20s%-20s%-20s%n", "ID", "TITLE", "QUANTITY", "PRICE");
    }

}
