import java.util.Scanner;

public class StudentManagementSystem {
    private final StudentLinkedList list = new StudentLinkedList();
    private final ActionStack actions = new ActionStack();
    private final ServiceQueue requests = new ServiceQueue();
    private final StudentBST bst = new StudentBST();
    private final StudentHashTable hashTable = new StudentHashTable();
    private final CampusGraph campus = new CampusGraph();

    // These public methods are also used by the automated integration tests.
    public boolean registerStudent(Student student) {
        if (student == null || hashTable.get(student.getStudentId()) != null) return false;
        if (!list.add(student)) return false;
        bst.insert(student);
        hashTable.put(student);
        actions.push("Added student " + student.getStudentId());
        return true;
    }

    public boolean updateStudentRecord(String id, String name, String programme, double marks) {
        String key = Student.normalizeId(id);
        if (!list.update(key, name, programme, marks)) return false;
        // BST and hash table contain the same Student object; details are now updated there too.
        actions.push("Updated student " + key);
        return true;
    }

    public boolean deleteStudentRecord(String id) {
        String key = Student.normalizeId(id);
        Student removed = list.remove(key);
        if (removed == null) return false;
        bst.remove(key);
        hashTable.remove(key);
        actions.push("Deleted student " + key);
        return true;
    }

    public Student findStudentByHash(String id) { return hashTable.get(id); }
    public String listStudents() { return list.displayAll(); }
    public String orderedStudents() { return bst.displayInOrder(); }
    public String recentActions() { return actions.displayRecent(); }
    public int studentCount() { return list.size(); }
    public int bstCount() { return bst.size(); }
    public int hashCount() { return hashTable.size(); }
    public CampusGraph campus() { return campus; }

    public boolean addServiceRequest(String id, String description) {
        String key = Student.normalizeId(id);
        if (hashTable.get(key) == null) return false;
        requests.enqueue(new ServiceQueue.Request(key, description));
        actions.push("Queued service request for " + key);
        return true;
    }

    public ServiceQueue.Request processNextRequest() {
        ServiceQueue.Request next = requests.dequeue();
        if (next != null) actions.push("Processed service request for " + next.getStudentId());
        return next;
    }

    public int pendingRequests() { return requests.size(); }

    private static String readRequired(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) return text;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static double readMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter marks (0-100): ");
            try {
                double marks = Double.parseDouble(scanner.nextLine().trim());
                if (Student.validMarks(marks)) return marks;
            } catch (NumberFormatException ignored) {
                // Print one clear message for all invalid numerical inputs.
            }
            System.out.println("Invalid marks. Enter a number between 0 and 100.");
        }
    }

    public void menuAddStudent(Scanner scanner) {
        System.out.println("\n--- ADD STUDENT ---");
        String id = readRequired(scanner, "Student ID: ");
        if (hashTable.get(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        String name = readRequired(scanner, "Student name: ");
        String programme = readRequired(scanner, "Programme: ");
        double marks = readMarks(scanner);
        registerStudent(new Student(id, name, programme, marks));
        System.out.println("Student added successfully.");
    }

    public void menuUpdateStudent(Scanner scanner) {
        System.out.println("\n--- UPDATE STUDENT ---");
        String id = readRequired(scanner, "Student ID: ");
        Student existing = hashTable.get(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Current: " + existing);
        String name = readRequired(scanner, "New name: ");
        String programme = readRequired(scanner, "New programme: ");
        double marks = readMarks(scanner);
        updateStudentRecord(id, name, programme, marks);
        System.out.println("Student updated successfully.");
    }

    public void menuDeleteStudent(Scanner scanner) {
        System.out.println("\n--- DELETE STUDENT ---");
        String id = readRequired(scanner, "Student ID: ");
        System.out.println(deleteStudentRecord(id)
                ? "Student deleted successfully."
                : "Student not found.");
    }

    public void menuAddRequest(Scanner scanner) {
        System.out.println("\n--- ADD SERVICE REQUEST ---");
        String id = readRequired(scanner, "Student ID: ");
        if (hashTable.get(id) == null) {
            System.out.println("Student not found. Register the student first.");
            return;
        }
        String details = readRequired(scanner, "Request description: ");
        addServiceRequest(id, details);
        System.out.println("Request added. Pending requests: " + pendingRequests());
    }

    public void menuProcessRequest() {
        System.out.println("\n--- PROCESS NEXT REQUEST ---");
        ServiceQueue.Request request = processNextRequest();
        if (request == null) {
            System.out.println("No pending requests.");
            return;
        }
        System.out.println("Processed: " + request);
        if (hashTable.get(request.getStudentId()) == null) {
            System.out.println("Note: Student record has since been deleted.");
        }
        System.out.println("Pending requests: " + pendingRequests());
    }

    public void menuSearchByHash(Scanner scanner) {
        System.out.println("\n--- HASH TABLE SEARCH ---");
        String id = readRequired(scanner, "Student ID: ");
        Student result = findStudentByHash(id);
        System.out.println(result == null ? "Student not found." : result);
    }

    public void menuAddLocation(Scanner scanner) {
        String name = readRequired(scanner, "New campus location: ");
        if (campus.addLocation(name)) {
            actions.push("Added campus location " + name);
            System.out.println("Location added successfully.");
        } else System.out.println("Location already exists.");
    }

    public void menuRemoveLocation(Scanner scanner) {
        String name = readRequired(scanner, "Campus location to remove: ");
        if (campus.removeLocation(name)) {
            actions.push("Removed campus location " + name);
            System.out.println("Location and its connected roads removed.");
        } else System.out.println("Location not found.");
    }

    public void menuAddRoad(Scanner scanner) {
        String from = readRequired(scanner, "From location: ");
        String to = readRequired(scanner, "To location: ");
        if (!campus.hasLocation(from) || !campus.hasLocation(to)) {
            System.out.println("Both locations must exist before adding a road.");
        } else if (from.equalsIgnoreCase(to)) {
            System.out.println("A location cannot be connected to itself.");
        } else if (campus.addRoad(from, to)) {
            actions.push("Added campus road " + from + " <-> " + to);
            System.out.println("Two-way road added successfully.");
        } else System.out.println("This connection already exists.");
    }

    public void menuRemoveRoad(Scanner scanner) {
        String from = readRequired(scanner, "From location: ");
        String to = readRequired(scanner, "To location: ");
        if (campus.removeRoad(from, to)) {
            actions.push("Removed campus road " + from + " <-> " + to);
            System.out.println("Road removed successfully.");
        } else System.out.println("Connection not found or locations are unavailable.");
    }

    public void menuBfs(Scanner scanner) {
        String start = readRequired(scanner, "Starting campus location: ");
        System.out.println(campus.bfs(start));
    }
}
