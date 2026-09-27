
public class ServiceQueue {
    public static class Request {
        private final String studentId;
        private final String description;

        public Request(String studentId, String description) {
            this.studentId = Student.normalizeId(studentId);
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Request description cannot be empty.");
            }
            this.description = description.trim();
        }

        public String getStudentId() { return studentId; }
        public String getDescription() { return description; }

        @Override
        public String toString() {
            return studentId + " - " + description;
        }
    }

    private static class Node {
        Request request;
        Node next;
        Node(Request request) { this.request = request; }
    }

    private Node front;
    private Node rear;
    private int size;

    public void enqueue(Request request) {
        if (request == null) throw new IllegalArgumentException("Request is required.");
        Node node = new Node(request);
        if (rear == null) front = node;
        else rear.next = node;
        rear = node;
        size++;
    }

    public Request dequeue() {
        if (front == null) return null;
        Request first = front.request;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return first;
    }

    public int size() { return size; }
}
