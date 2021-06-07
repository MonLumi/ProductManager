import java.util.Scanner;

public class Tools {
    static LinkedList list = Main.linkedList;
    static Scanner keyboard = Main.keyboard;

    public static void search() {
        System.out.print("Type ID: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Node node = list.getHead();
        boolean isExist = false;

        while (node != null) {
            if (node.getProduct().getId() == id) {
                isExist = true;
                break;
            }
            node = node.getNext();
        }

        if (isExist) {
            System.out.println("Found product: ");
            Main.lineBreak();
            Main.resultHeader();
            node.display();
        } else System.out.println("Product not found!");
    }

    public static void delete() {
        System.out.print("Type ID: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Node node = list.getHead();
        Node deletedNode = null;
        boolean isExist = false;

        while (node != null) {
            if (node.getNext().getProduct().getId() == id) {
                deletedNode = node.getNext();
                Node newNextNode = node.getNext().getNext();
                node.setNext(newNextNode);
                isExist = true;
                break;
            }

            node = node.getNext();
        }

        if (isExist) {
            System.out.println("Successfully deleted the product: ");
            deletedNode.display();
        } else System.out.println("Product not found!");
    }

    public static String convertToBinary (int quantity) {
        if (1 == quantity) return "1";
        else return convertToBinary(quantity/2) + (quantity%2);
    }
}
