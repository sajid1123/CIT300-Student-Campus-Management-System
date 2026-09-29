import java.util.Scanner;

/** Entry point. All sixteen required menu options are available here. */
public class Main {
    private static void printMenu() {
        System.out.println("\n==============================================");
        System.out.println(" UNIVERSITY STUDENT & CAMPUS MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records using Linked List");
        System.out.println(" 5. Add Service Request to Queue");
        System.out.println(" 6. Process Next Service Request");
        System.out.println(" 7. Display Recent Actions using Stack");
        System.out.println(" 8. Display Students using BST");
        System.out.println(" 9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-16): ");
            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid menu input. Enter a number from 1 to 16.");
                continue;
            }
            switch (choice) {
                case 1: system.menuAddStudent(scanner); break;
                case 2: system.menuUpdateStudent(scanner); break;
                case 3: system.menuDeleteStudent(scanner); break;
                case 4: System.out.println("\n" + system.listStudents()); break;
                case 5: system.menuAddRequest(scanner); break;
                case 6: system.menuProcessRequest(); break;
                case 7: System.out.println("\n" + system.recentActions()); break;
                case 8: System.out.println("\n" + system.orderedStudents()); break;
                case 9: system.menuSearchByHash(scanner); break;
                case 10: system.menuAddLocation(scanner); break;
                case 11: system.menuRemoveLocation(scanner); break;
                case 12: system.menuAddRoad(scanner); break;
                case 13: system.menuRemoveRoad(scanner); break;
                case 14: System.out.println("\n" + system.campus().displayNetwork()); break;
                case 15: system.menuBfs(scanner); break;
                case 16:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Enter a number from 1 to 16.");
            }
        }
        scanner.close();
    }
}
