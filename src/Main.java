import java.util.Scanner;


//Dang lam do phan khoi tao LinkedList
public class Main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            menu();
            choice = Integer.parseInt(keyboard.nextLine());
            switch (choice) {
                case 0 -> {}

                //Import data to linked list and display
                case 1 -> {}

                //Add a new product to tail
                case 2 -> {}

                //Visit all product and display info
                case 3 -> {}

                //Export data to file
                case 4 -> {}

                //Search product by ID
                case 5 -> {}

                //Delete product by ID
                case 6 -> {}

                //Sort by ID
                case 7 -> {}

                //Convert to binary
                case 8 -> {}

                //Import to stack
                case 9 -> {}

                //Import to queue
                case 10 -> {}
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
        columnsSplit("5. Search a product via ID","10. Import data to queue");

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
}
