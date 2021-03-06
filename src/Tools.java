import java.util.Scanner;

public class Tools {
    static LinkedList list = Main.linkedList;
    static Scanner keyboard = Main.keyboard;

    public static void search() {
        System.out.print("Type ID: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Main.fileOut.append(String.valueOf(id)).append("(user input)\n\n");
        System.out.println();
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
            Main.resultHeader();
            node.display();
        } else System.out.println("Product not found!");
        System.out.println();
    }

    public static void delete() {
        System.out.print("Type ID: ");
        int id = Integer.parseInt(keyboard.nextLine());
        Main.fileOut.append(String.valueOf(id)).append("(user input)\n\n");

        boolean isExist;
        Node key;
        Node report;

        //if the deleted product is head
        if (list.getHead().getID() == id) {
            isExist = true;
            report = list.getHead();
            list.setHead(list.getHead().getNext());
        }
        // if the deleted product is tail
        else if (list.getTail().getID() == id) {
            isExist = true;
            report = list.getTail();
            key = list.getHead();
            while (key.getNext() != list.getTail()) {
                key = key.getNext();
            }
            key.setNext(null);
            list.setTail(key);
        }
        // if the deleted product is middle list
        else {
            key = list.getHead();
            Node prev = list.getHead();

            //search for deleted product
            while (key.getID() != id && key != list.getTail()) {
                prev = key;
                key = key.getNext();
            }

            if (key == list.getTail()) isExist = false;
            else {
                isExist = true;
                prev.setNext(key.getNext());
            }
            report = key;
        }


        System.out.println();
        if (isExist) {
            System.out.println("Successfully deleted the product: ");
            Main.resultHeader();
            report.display();
        } else System.out.println("Product not found!");
        System.out.println();
    }

    public static String convertToBinary (int quantity) {
        if (1 == quantity) return "1";
        else return convertToBinary(quantity/2) + (quantity%2);
    }

    public static void quickSort(Node start, Node end) {
        if(start == null || start == end|| start == end.getNext()) return;

        Node pivot_prev = partitionNode(start, end);
        quickSort(start, pivot_prev);

        if (pivot_prev != null && pivot_prev == start) quickSort(pivot_prev.getNext(), end);
        else if (pivot_prev != null && pivot_prev.getNext() != null) quickSort(pivot_prev.getNext().getNext(), end);
    }

    public static Node partitionNode(Node start, Node end) {
        if (start == end || start == null || end == null) return start;

        Node pivot_prev = start;
        Node curr = start;
        int pivot = end.getID();

        while (start != end) {
            if (start.getID() < pivot) {
                pivot_prev = curr;
                Product temp = curr.getProduct();
                curr.setProduct(start.getProduct());
                start.setProduct(temp);
                curr = curr.getNext();
            }
            start = start.getNext();
        }

        Product temp = curr.getProduct();
        curr.setProduct(end.getProduct());
        end.setProduct(temp);

        return pivot_prev;
    }
}
