public class StudentHashTable {
    private static class Entry {
        Student student;
        Entry next;
        Entry(Student student, Entry next) {
            this.student = student;
            this.next = next;
        }
    }

    private final Entry[] buckets;
    private int size;

    public StudentHashTable() { this(31); }

    public StudentHashTable(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be positive.");
        buckets = new Entry[capacity];
    }

    // hashCode() can come back negative, so use floorMod to keep this in range
    private int bucketIndex(String key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public boolean put(Student student) {
        if (student == null || get(student.getStudentId()) != null) return false;
        int index = bucketIndex(student.getStudentId());
        buckets[index] = new Entry(student, buckets[index]);
        size++;
        return true;
    }

    public Student get(String id) {
        String key = Student.normalizeId(id);
        Entry current = buckets[bucketIndex(key)];
        while (current != null) {
            if (current.student.getStudentId().equals(key)) return current.student;
            current = current.next;
        }
        return null;
    }

    public boolean remove(String id) {
        String key = Student.normalizeId(id);
        int index = bucketIndex(key);
        Entry previous = null;
        Entry current = buckets[index];
        while (current != null) {
            if (current.student.getStudentId().equals(key)) {
                if (previous == null) buckets[index] = current.next;
                else previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() { return size; }
}
