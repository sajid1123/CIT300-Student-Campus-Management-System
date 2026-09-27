import java.util.Locale;

/** Represents one student. The student ID is immutable after registration. */
public class Student {
    private final String studentId;
    private String name;
    private String programme;
    private double marks;

    public Student(String studentId, String name, String programme, double marks) {
        this.studentId = normalizeId(studentId);
        setDetails(name, programme, marks);
    }

    public static String normalizeId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        return id.trim().toUpperCase(Locale.ROOT);
    }

    private static String requiredText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public static boolean validMarks(double value) {
        return Double.isFinite(value) && value >= 0 && value <= 100;
    }

    public void setDetails(String name, String programme, double marks) {
        String checkedName = requiredText(name, "Name");
        String checkedProgramme = requiredText(programme, "Programme");
        if (!validMarks(marks)) {
            throw new IllegalArgumentException("Marks must be a number from 0 to 100.");
        }
        this.name = checkedName;
        this.programme = checkedProgramme;
        this.marks = marks;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getProgramme() { return programme; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "ID: %-12s | Name: %-20s | Programme: %-18s | Marks: %.2f",
                studentId, name, programme, marks);
    }
}
