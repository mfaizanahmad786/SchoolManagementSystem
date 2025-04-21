
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

public class Person implements Serializable{
    private String name;
    private String email;
    private String dateOfBirth;

    public Person(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public Person(){
        super();
    }
    public void displayDetails(){
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Date of Birth: " + dateOfBirth);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}

class Student extends Person{
    private String studentID;
    private String Address;
    private static int noOfStudents=0;

    private double grade;
    private ArrayList<Course> enrolledCourses;


    public Student(String name, String email, String dateOfBirth, String studentID, String address) {
        super(name, email, dateOfBirth);
        this.studentID = studentID;
        Address = address;
        enrolledCourses = new ArrayList<Course>();
        grade = 0.0;
        noOfStudents++;
    }

    public Student(String studentID, String address) {
        this.studentID = studentID;
        Address = address;
        grade = 0.0;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getAddress() {
        return Address;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public static int getNoOfStudents() {
        return noOfStudents;
    }

    @Override
    public String toString() {
        return super.toString()+
                "studentID='" + studentID + '\'' +
                ", Address='" + Address + '\'';
    }


    void enrollInCourse(Course course){
        if(!enrolledCourses.contains(course)){
            enrolledCourses.add(course);
            course.addStudent(this,false);
            System.out.printf("%s Added To The Course: %s%n", studentID, course.getCourseTitle());
        }else{
            System.out.printf("%s Student %s is already enrolled in the course: %s%n", studentID, course.getCourseTitle());
        }

    }
    void removeFromCourse(Course course){
        if(enrolledCourses.contains(course)){
            enrolledCourses.remove(course);
            course.removeStudent(this,false);
        }else{
            System.out.println("Student is not enrolled in the course");
        }
    }

    void displayCourses(){
        if(enrolledCourses.isEmpty()){
            System.out.println("No courses have been registered");
        }else{
            for(Course course:enrolledCourses){
                System.out.println(course.toString());
            }
        }
    }



    public void displayDetails() {
        super.displayDetails();
        System.out.println("Student ID: " + studentID);
        System.out.println("Address: " + Address);
        System.out.println("Courses Registered: " + enrolledCourses.size());
    }
}

class Teacher extends Person implements Reportable{
    private String teacherID;
    private String specialization;
    private static int noOfTeachers=0;
    //Array List of Courses Taught will be added here
    private ArrayList<Course> assignedCourses;

    public Teacher(String name, String email, String dateOfBirth, String teacherID, String specialization) {
        super(name, email, dateOfBirth);
        this.teacherID = teacherID;
        this.specialization = specialization;
        noOfTeachers++;
        assignedCourses = new ArrayList<Course>();
    }

    public Teacher(String teacherID, String specialization) {
        this.teacherID = teacherID;
        this.specialization = specialization;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public static int getNoOfTeachers() {
        return noOfTeachers;
    }

    public ArrayList<Course> getAssignedCourses() {
        return assignedCourses;
    }

    //METHODS

    void assignCourse(Course course){
        if(!assignedCourses.contains(course)){
            assignedCourses.add(course);
            course.setAssignedTeacher(this);
            System.out.printf("Teacher %sAssigned to the course%s%n", teacherID, course.getCourseTitle());
        }else{
            System.out.println("Teacher is already assigned to the course:"+course.getCourseTitle());
        }
    }

    void displayCourses(){
        if(assignedCourses.isEmpty()){
            System.out.println("No courses have been assigned to the Teacher:"+teacherID);
        }else{
            for(Course course:assignedCourses){
                System.out.println(course.toString());
            }
        }
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Teacher ID: " + teacherID);
        System.out.println("Specialization: " + specialization);
        System.out.println("Courses Assigned: " + assignedCourses.size());
    }

    @Override
    public String generateReport() {
        return  "Teacher Report\n" +
                "Name: " + getName() + "\n" +
                "Teacher ID: " + teacherID + "\n" +
                "Specialization: " + specialization + "\n";
    }

    @Override
    public void exportToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("teacherReport.txt"))){
            writer.write(generateReport());
            System.out.println("Report exported to teacherReport.txt");
        }catch(IOException e){
            System.out.println("An Error occurred while writing to file");
            e.printStackTrace();
        }
    }
}

class AdministrativeStaff extends Person implements Reportable{
    private String staffID;
    private String role;
    private String department;
    private static int noOfStaff=0;
    public AdministrativeStaff(String name, String email, String dateOfBirth, String staffID, String role, String department) {
        super(name, email, dateOfBirth);
        this.staffID = staffID;
        this.role = role;
        this.department = department;
        noOfStaff++;
    }

    public static int getNoOfStaff() {
        return noOfStaff;
    }

    public AdministrativeStaff(String staffID, String role, String department) {
        this.staffID = staffID;
        this.role = role;
        this.department = department;
    }


    public void displayDetails() {
        super.displayDetails();
        System.out.println("Staff ID: " + staffID);
        System.out.println("Role: " + role);
        System.out.println("Department: " + department);
    }

    public String getStaffID() {
        return staffID;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public static String generateReport(ArrayList<? extends Person> people){

        StringBuilder report = new StringBuilder("REPORT:\n");
        for(Person person:people){
            if(person instanceof Student){
                Student student = (Student)person;
                report.append("Student ID: ").append(student.getStudentID())
                        .append(", Name: ").append(student.getName())
                        .append(", Enrolled Courses: ").append(student.getEnrolledCourses().size())
                        .append("\n");
            }else if(person instanceof Teacher){
                Teacher teacher = (Teacher)person;
                report.append("Teacher ID: ").append(teacher.getTeacherID())
                        .append(", Name: ").append(teacher.getName())
                        .append(", Assigned Courses: ").append(teacher.getAssignedCourses().size())
                        .append("\n");
            }else if(person instanceof AdministrativeStaff){
                AdministrativeStaff staff = (AdministrativeStaff)person;
                report.append("Staff ID: ").append(staff.staffID)
                        .append(", Name: ").append(staff.getName())
                        .append(", Role: ").append(staff.role)
                        .append("\n");
            }

        }
        return report.toString();
    }


    public String generateReport() {
        return  "Administrative Staff Report\n" +
                "Name: " + getName() + "\n" +
                "Staff ID: " + staffID + "\n" +
                "Role: " + role + "\n" +
                "Department: " + department + "\n";
    }

    @Override
    public void exportToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("staffReport.txt"))){
            writer.write(generateReport());
            System.out.println("Report exported to staffReport.txt");
        }catch(IOException e){
            System.out.println("An Error occured while writing to file");
            e.printStackTrace();
        }
    }
}
