/** Custom singly linked list. This is the primary store for student records. */
public class StudentLinkedList {
    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public boolean add(Student student) {
        if (student == null || find(student.getStudentId()) != null) return false;
        Node node = new Node(student);
        if (head == null) head = node;
        else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = node;
        }
        size++;
        return true;
    }

    public Student find(String id) {
        String key = Student.normalizeId(id);
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equals(key)) return current.data;
            current = current.next;
        }
        return null;
    }

    /** Updates the same Student object that the BST and hash table reference. */
    public boolean update(String id, String name, String programme, double marks) {
        Student student = find(id);
        if (student == null) return false;
        student.setDetails(name, programme, marks);
        return true;
    }

    public Student remove(String id) {
        String key = Student.normalizeId(id);
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equals(key)) {
                if (previous == null) head = current.next;
                else previous.next = current.next;
                size--;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public int size() { return size; }

    public String displayAll() {
        if (head == null) return "No student records available.";
        StringBuilder output = new StringBuilder();
        Node current = head;
        while (current != null) {
            output.append(current.data).append(System.lineSeparator());
            current = current.next;
        }
        return output.toString();
    }
}
