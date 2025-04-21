import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseTitle;
    private int credit;
    private Teacher assignedTeacher;

    private static int noOfCourses = 0;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<Double> grades;

    public Course(String courseID, String courseTitle, int credit, Teacher assignedTeacher) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.credit = credit;
        this.assignedTeacher = assignedTeacher;
        this.enrolledStudents = new ArrayList<Student>();
        this.grades = new ArrayList<Double>();
        noOfCourses++;
    }

    public static int getNoOfCourses() {
        return noOfCourses;
    }

    public ArrayList<Double> getGrades(){
        return grades;
    }

    public int getCredit() {
        return credit;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return "courseID='" + courseID + '\'' +
                ", courseTitle='" + courseTitle + '\'';
    }

    public void addGrade(Student student, Double grade) {
        int index = enrolledStudents.indexOf(student);
        grades.add(index, grade);
    }

    //    METHODS
    public void addStudent(Student student) {
        addStudent(student, true);
    }

    public void addStudent(Student student, boolean updateStudent) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            grades.add(0.0);
            if (updateStudent) {
                student.enrollInCourse(this);
            }
            System.out.printf("Student %s added to the course: %s%n", student.getStudentID(), courseTitle);
        } else {
            System.out.printf("Student %s is already in the course: %s%n", student.getStudentID(), courseTitle);
        }
    }

    public void removeStudent(Student student) {
        removeStudent(student, true);
    }

    public void removeStudent(Student student, boolean updateStudent) {
        if (enrolledStudents.contains(student)) {
            int index = enrolledStudents.indexOf(student);
            grades.remove(index);
            enrolledStudents.remove(student);
            student.removeFromCourse(this);
            System.out.println(student.toString());
            System.out.println("Removed from the course:" + courseTitle);
        } else {
            System.out.println("Student is not enrolled in the course");
        }
    }


    public double averageGrade() {
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in the course: " + courseTitle);
            return 0.0; // Avoid division by zero
        }

        double totalGrade = 0.0;
        for (Double grade : grades) {
            totalGrade += grade;
        }

        double averageGrade = totalGrade / enrolledStudents.size();
        System.out.printf("Average Grade for the Course %s: %.2f%n", courseTitle, averageGrade);
        return averageGrade;
    }

}
