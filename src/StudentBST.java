public class StudentBST {
    private static class Node {
        Student student;
        Node left;
        Node right;
        Node(Student student) { this.student = student; }
    }

    private Node root;
    private int size;

    public boolean insert(Student student) {
        if (student == null || find(student.getStudentId()) != null) return false;
        root = insertNode(root, student);
        size++;
        return true;
    }

    private Node insertNode(Node node, Student student) {
        if (node == null) return new Node(student);
        if (student.getStudentId().compareTo(node.student.getStudentId()) < 0) {
            node.left = insertNode(node.left, student);
        } else {
            // duplicate ids are rejected in insert(), so equality never lands here
            node.right = insertNode(node.right, student);
        }
        return node;
    }

    public Student find(String id) {
        String key = Student.normalizeId(id);
        Node current = root;
        while (current != null) {
            int comparison = key.compareTo(current.student.getStudentId());
            if (comparison == 0) return current.student;
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    public boolean remove(String id) {
        String key = Student.normalizeId(id);
        if (find(key) == null) return false;
        root = deleteNode(root, key);
        size--;
        return true;
    }

    private Node deleteNode(Node node, String key) {
        if (node == null) return null;
        int comparison = key.compareTo(node.student.getStudentId());
        if (comparison < 0) node.left = deleteNode(node.left, key);
        else if (comparison > 0) node.right = deleteNode(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // two children: move the inorder successor up, then delete it from the right subtree
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.student = successor.student;
            node.right = deleteNode(node.right, successor.student.getStudentId());
        }
        return node;
    }

    public String displayInOrder() {
        if (root == null) return "No student records available.";
        StringBuilder output = new StringBuilder();
        appendInOrder(root, output);
        return output.toString();
    }

    private void appendInOrder(Node node, StringBuilder output) {
        if (node == null) return;
        appendInOrder(node.left, output);
        output.append(node.student).append(System.lineSeparator());
        appendInOrder(node.right, output);
    }

    public int size() { return size; }
}
