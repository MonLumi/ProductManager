import java.io.*;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    static Scanner keyboard = new Scanner(System.in);
    static LinkedList linkedList = new LinkedList();

    //Variable for data
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



    //Variable for export console screen to txt file
    static PrintStream fileOut;
    static {
        try {
            fileOut = new PrintStream("src/console_output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        
        //Change system default console-out to both console and file out
        MultiOut mo = new MultiOut(System.out, fileOut);
        System.setOut(mo);

        int choice;
        do {
            menu();
            choice = Integer.parseInt(keyboard.nextLine());
            fileOut.append(String.valueOf(choice)).append("(user input)\n\n");
            switch (choice) {
                case 0 : break;

                //Import data to linked list and display
                case 1 :  {
                    linkedList.clear();

                    importData(linkedList, true);
                    resultHeader();
                    linkedList.display();
                    lineBreak();
                    System.out.println("Import data successful");
                    System.out.println();
                    break;
                }

                //Add a new product to tail
                case 2 : {
                    int isRepeat;
                    do {
                        System.out.println("Adding new product");
                        lineBreak();
                        System.out.print("ID: ");
                        int id = Integer.parseInt(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(id)).append("(user input)\n\n");
                        System.out.print("Title: ");
                        String title = Main.keyboard.nextLine();
                        fileOut.append(title.concat("(user input)\n\n"));
                        System.out.print("Quantity: ");
                        int quantity = Integer.parseInt(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(quantity)).append("(user input)\n\n");
                        System.out.print("Price: ");
                        double price = Double.parseDouble(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(price)).append("(user input)\n\n");

                        Product newProduct = new Product(id, title, quantity, price);

                        linkedList.addTail(newProduct);
                        exportProduct(newProduct);
                        System.out.println();
                        System.out.println("Add new product successfully");
                        resultHeader();
                        newProduct.display();
                        System.out.println();

                        System.out.print("Do you want to add another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(isRepeat)).append("(user input)\n\n");
                        System.out.println();
                    } while (1 == isRepeat);
                    break;
                }

                //Visit all product and display info
                case 3 : {
                    resultHeader();
                    linkedList.display();
                    lineBreak();
                    break;
                }

                //Export data to file
                case 4 : {
                    if (linkedList.isEmpty()) {
                        System.out.println("There are no data to export! Please import data first");
                    } else {
                        exportAll();
                        resultHeader();
                        linkedList.display();
                        lineBreak();
                        System.out.println("Export data successful");
                        System.out.println();
                    }
                    break;
                }

                //Search product by ID
                case 5 :  {
                    int isRepeat;
                    do {
                        Tools.search();
                        System.out.print("Do you want to search another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(isRepeat)).append("(user input)\n\n");
                    } while (1 == isRepeat);
                    break;
                }

                //Delete product by ID
                case 6 :  {
                    int isRepeat;
                    do {
                        Tools.delete();
                        System.out.print("Do you want to delete another product? (\"1\" for yes): ");
                        isRepeat = Integer.parseInt(Main.keyboard.nextLine());
                        fileOut.append(String.valueOf(isRepeat)).append("(user input)\n\n");
                    } while (1 == isRepeat);
                    break;
                }

                //Sort by ID
                case 7 :  {
                    Tools.quickSort(linkedList.getHead(), linkedList.getTail());

                    resultHeader();
                    linkedList.display();
                    lineBreak();
                    System.out.println("Data is successfully sorted!");
                    System.out.println();
                    break;
                }

                //Convert to binary
                case 8 :  {
                    System.out.println("The first product: ");
                    Product product = linkedList.getHead().getProduct();
                    resultHeader();
                    product.display();
                    lineBreak();
                    System.out.print("The quantity in the Binary Number: ");
                    System.out.println(Tools.convertToBinary(product.getQuantity()));
                    System.out.println();
                    break;
                }

                //Import to stack
                case 9 :  {
                    LinkedList linkedStack = new LinkedList();
                    importData(linkedStack, false);

                    resultHeader();
                    //Pop data, not peek
                    Node top = linkedStack.getHead();
                    while (top != null) {
                        top.display();
                        top = top.getNext();
                        linkedStack.setHead(top);
                    }
                    lineBreak();
                    break;
                }

                //Import to queue
                case 10 : {
                    LinkedList linkedQueue = new LinkedList();
                    importData(linkedQueue, true);

                    resultHeader();
                    Node deQueue = linkedQueue.getHead();
                    while (deQueue != linkedQueue.getTail().getNext()) {
                        deQueue.display();
                        deQueue = deQueue.getNext();
                        linkedQueue.setHead(deQueue);
                    }
                    lineBreak();
                    break;
                }
                default : {
                    System.out.println("Your choice is not correct, please try again!");
                    System.out.println();
                }
            }

            if (choice == 2 || choice == 5 || choice == 6 || choice >10) {
                choice = 1;
            } else if (choice != 0){
                System.out.print("Press Enter to go to Main Menu");
                Main.keyboard.nextLine();
                fileOut.append("\n\n");
                choice = 1;
            }
        } while (choice != 0);


        lineBreak();
        System.out.println("Good bye");
    }

    static void menu() {
        System.out.printf("%-25s","");
        System.out.println("What do you want to do?");
        System.out.printf("%-25s","");
        lineBreak();

        System.out.println("0. Exit");
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
        System.out.print("Your choice: ");
    }

    static void lineBreak() {
        System.out.println("-----------------------");
    }
    static void columnsSplit (String s1, String s2) {
        System.out.printf("%-50s%s%n", s1, s2);
    }

    static void resultHeader() {
        System.out.printf("%-10s%-20s%-20s%-20s%n", "ID", "TITLE", "QUANTITY", "PRICE");
        lineBreak();
    }

}
