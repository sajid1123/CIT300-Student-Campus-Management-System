public class ActionStack {
    private static class Node {
        String action;
        Node next;
        Node(String action, Node next) {
            this.action = action;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public void push(String action) {
        if (action == null || action.trim().isEmpty()) {
            throw new IllegalArgumentException("Action cannot be empty.");
        }
        top = new Node(action.trim(), top);
        size++;
    }

    public String pop() {
        if (top == null) return null;
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    public String peek() { return top == null ? null : top.action; }
    public int size() { return size; }

    public String displayRecent() {
        if (top == null) return "No actions recorded yet.";
        StringBuilder output = new StringBuilder();
        Node current = top;
        int number = 1;
        while (current != null) {
            output.append(number++).append(". ").append(current.action)
                  .append(System.lineSeparator());
            current = current.next;
        }
        return output.toString();
    }
}
