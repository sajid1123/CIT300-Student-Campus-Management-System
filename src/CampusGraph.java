
public class CampusGraph {
    private static class Vertex {
        String name;
        Edge firstEdge;
        Vertex next;
        boolean visited;
        Vertex(String name) { this.name = name; }
    }

    private static class Edge {
        Vertex destination;
        Edge next;
        Edge(Vertex destination, Edge next) {
            this.destination = destination;
            this.next = next;
        }
    }

    private static class VertexQueue {
        private static class Node {
            Vertex vertex;
            Node next;
            Node(Vertex vertex) { this.vertex = vertex; }
        }
        private Node front;
        private Node rear;
        void offer(Vertex vertex) {
            Node node = new Node(vertex);
            if (rear == null) front = node;
            else rear.next = node;
            rear = node;
        }
        Vertex poll() {
            if (front == null) return null;
            Vertex value = front.vertex;
            front = front.next;
            if (front == null) rear = null;
            return value;
        }
        boolean isEmpty() { return front == null; }
    }

    private Vertex firstVertex;
    private int locationCount;

    private static String checkedName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be empty.");
        }
        return name.trim();
    }

    private Vertex find(String name) {
        String key = checkedName(name);
        Vertex current = firstVertex;
        while (current != null) {
            if (current.name.equalsIgnoreCase(key)) return current;
            current = current.next;
        }
        return null;
    }

    public boolean hasLocation(String name) { return find(name) != null; }

    public boolean addLocation(String name) {
        String checked = checkedName(name);
        if (find(checked) != null) return false;
        Vertex node = new Vertex(checked);
        if (firstVertex == null) firstVertex = node;
        else {
            Vertex current = firstVertex;
            while (current.next != null) current = current.next;
            current.next = node;
        }
        locationCount++;
        return true;
    }

    public boolean removeLocation(String name) {
        Vertex target = find(name);
        if (target == null) return false;
        Vertex current = firstVertex;
        while (current != null) {
            if (current != target) removeDirectedEdge(current, target);
            current = current.next;
        }
        if (firstVertex == target) firstVertex = target.next;
        else {
            Vertex previous = firstVertex;
            while (previous.next != target) previous = previous.next;
            previous.next = target.next;
        }
        locationCount--;
        return true;
    }

    private boolean hasDirectedEdge(Vertex from, Vertex to) {
        Edge current = from.firstEdge;
        while (current != null) {
            if (current.destination == to) return true;
            current = current.next;
        }
        return false;
    }

    private void addDirectedEdge(Vertex from, Vertex to) {
        from.firstEdge = new Edge(to, from.firstEdge);
    }

    private void removeDirectedEdge(Vertex from, Vertex to) {
        Edge previous = null;
        Edge current = from.firstEdge;
        while (current != null) {
            if (current.destination == to) {
                if (previous == null) from.firstEdge = current.next;
                else previous.next = current.next;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public boolean hasRoad(String fromName, String toName) {
        Vertex from = find(fromName);
        Vertex to = find(toName);
        return from != null && to != null && hasDirectedEdge(from, to);
    }

    public boolean addRoad(String fromName, String toName) {
        Vertex from = find(fromName);
        Vertex to = find(toName);
        if (from == null || to == null || from == to || hasDirectedEdge(from, to)) {
            return false;
        }
        addDirectedEdge(from, to);
        addDirectedEdge(to, from);
        return true;
    }

    public boolean removeRoad(String fromName, String toName) {
        Vertex from = find(fromName);
        Vertex to = find(toName);
        if (from == null || to == null || !hasDirectedEdge(from, to)) return false;
        removeDirectedEdge(from, to);
        removeDirectedEdge(to, from);
        return true;
    }

    public String displayNetwork() {
        if (firstVertex == null) return "Campus network is empty.";
        StringBuilder output = new StringBuilder();
        Vertex current = firstVertex;
        while (current != null) {
            output.append(current.name).append(" -> ");
            Edge edge = current.firstEdge;
            if (edge == null) output.append("(no direct connections)");
            while (edge != null) {
                output.append(edge.destination.name);
                if (edge.next != null) output.append(", ");
                edge = edge.next;
            }
            output.append(System.lineSeparator());
            current = current.next;
        }
        return output.toString();
    }

    public String bfs(String startName) {
        Vertex start = find(startName);
        if (start == null) return "Starting location not found.";
        Vertex current = firstVertex;
        while (current != null) {
            current.visited = false;
            current = current.next;
        }
        VertexQueue queue = new VertexQueue();
        start.visited = true;
        queue.offer(start);
        StringBuilder output = new StringBuilder("BFS traversal: ");
        boolean first = true;
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            if (!first) output.append(" -> ");
            output.append(vertex.name);
            first = false;
            Edge edge = vertex.firstEdge;
            while (edge != null) {
                Vertex neighbour = edge.destination;
                if (!neighbour.visited) {
                    neighbour.visited = true;
                    queue.offer(neighbour);
                }
                edge = edge.next;
            }
        }
        return output.toString();
    }

    public int locationCount() { return locationCount; }
}
