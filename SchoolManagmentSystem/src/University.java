import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

public class University implements Serializable {
    private Repository<Student> students;
    private Repository<Teacher> teachers;
    private Repository<Course> courses;
    private Repository<AdministrativeStaff> staff;

    public University() {
        this.students = new Repository<>();
        this.teachers = new Repository<>();
        this.courses = new Repository<>();
        this.staff = new Repository<>();
    }

    public Repository<Student> getStudents() {
        return students;
    }

    public Repository<Teacher> getTeachers() {
        return teachers;
    }

    public Repository<Course> getCourses() {
        return courses;
    }

    public Repository<AdministrativeStaff> getStaff() {
        return staff;
    }
    //                                            GENERIC BASED ADD & REMOVE FUNCTIONS

    public <T> void addToUniversity(T item){
        if(item instanceof Student){
            if(!students.getItems().contains((Student)item)) {
                students.add((Student) item);
                System.out.println("Student " + ((Student) item).getStudentID() + "With Name:" + ((Student) item).getName() + "Added to University");
            }else{
                System.out.println("Student already exists in University");
            }
        }else if(item instanceof Teacher) {
            if (!teachers.getItems().contains((Teacher) item)) {
                teachers.add((Teacher) item);
                System.out.println("Teacher " + ((Teacher) item).getTeacherID() + "With Name:" + ((Teacher) item).getName() + "Added to University");
            }else{
                System.out.println("Teacher already exists in University");
            }
        } else if (item instanceof Course) {
            if (!courses.getItems().contains((Course) item)) {
                courses.add((Course) item);
                System.out.println("Course " + ((Course) item).getCourseID() + "With Name:" + ((Course) item).getCourseTitle() + "Added to University");
            }else{
                System.out.println("Course already exists in University");
            }
        }else if(item instanceof AdministrativeStaff){
            if (!staff.getItems().contains((AdministrativeStaff) item)) {
                staff.add((AdministrativeStaff) item);
                System.out.println("AdministrativeStaff " + ((AdministrativeStaff) item).getStaffID() + "With Name:" + ((AdministrativeStaff) item).getName() + "Added to University");
            }else{
                System.out.println("Staff already exists in University");
            }
        }else{
            System.out.println("Unknown Object Type, Cannot Add");
        }
    }

    public <T> void removeFromUniversity(T item){
        if(item instanceof Student) {
            if (!students.getItems().contains((Student) item)) {
                System.out.println("Student Does not exist in University");
            } else {
                students.remove((Student) item);
                System.out.println("Student " + ((Student) item).getStudentID() + "With Name:" + ((Student) item).getName() + "Removed From University");
            }
        }else if(item instanceof Teacher) {
            if (!teachers.getItems().contains((Teacher) item)) {
                System.out.println("Teacher Does not exist in University");
            } else {
                teachers.remove((Teacher) item);
                System.out.println("Teacher " + ((Teacher) item).getTeacherID() + " With Name: " + ((Teacher) item).getName() + " Removed From University");
            }
        }else if(item instanceof AdministrativeStaff) {
            if (!staff.getItems().contains((AdministrativeStaff) item)) {
                System.out.println("Staff Does not exist in University");
            } else {
                staff.remove((AdministrativeStaff) item);
                System.out.println("Staff " + ((AdministrativeStaff) item).getStaffID() + " With Name: " + ((AdministrativeStaff) item).getName() + " Removed From University");
            }
        }else if(item instanceof Course) {
            if (!courses.getItems().contains((Course) item)) {
                System.out.println("Course Does not exist in University");
            } else {
                courses.remove((Course) item);
                System.out.println("Course " + ((Course) item).getCourseID() + " With Name: " + ((Course) item).getCourseTitle() + " Removed From University");
            }
        }else{
            System.out.println("Unknown Object Type, Cannot Remove");
        }
    }

    public void searchStudentByName(String name){
        boolean found = false;
        if(students.getItems().isEmpty()){
            System.out.println("There is not Student Enrolled at the moment");
        }else{
            for(Student student:students.getItems()){
                if(student.getName().equals(name)){
                    System.out.println("STUDENT FOUND");
                    student.displayDetails();
                    found = true;
                }
            }
            if(!found){
                System.out.println("No Record found matching the name "+name);
            }
        }
    }

    public void filterCoursesByCredits(int minCredits){
        boolean found = false;
        if(courses.getItems().isEmpty()){
            System.out.println("There is no Course Added at the moment");
        }else{
            for(Course course:courses.getItems()){
                if(course.getCredit()>=minCredits){
                    found = true;
                    System.out.println(course.toString());
                }
            }
            if(!found){
                System.out.println("No Course Matches the Filter Criteria");
            }
        }
    }

   public void showSystemStats() {
        if(!students.getItems().isEmpty()){
            System.out.println("Students: "+Student.getNoOfStudents());
        }else{
            System.out.println("Students: No Students Enrolled at the Moment");
        }

       if(!teachers.getItems().isEmpty()){
           System.out.println("Teachers: "+ Teacher.getNoOfTeachers());
       }else{
           System.out.println("Teachers: No Teachers Assigned at the Moment");
       }

       if(!courses.getItems().isEmpty()){
           System.out.println("Courses: "+Course.getNoOfCourses());
       }else{
           System.out.println("Course: No Course Enrolled at the Moment");
       }

       if(!staff.getItems().isEmpty()){
           System.out.println("Staff: "+AdministrativeStaff.getNoOfStaff());
       }else{
           System.out.println("Staff: No Staff Employed at the Moment");
       }
   }

                                                // FILE HANDLING

    public void saveData(String fileName){
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))){
            writer.writeObject(this);
            System.out.println("Data Succesfully saved to "+fileName);
        }catch(IOException e){
            System.out.println("An error occured while saving data");
            e.printStackTrace();
        }
    }
    public static University loadData(String fileName){
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))){
            University university = (University)reader.readObject();
            System.out.println("Data Loaded Succesfully From "+fileName);
            return university;
        }catch(IOException | ClassNotFoundException e){
            System.out.println("An Error occured while saving data");
            e.printStackTrace();
        }
        return null;
    }

}
