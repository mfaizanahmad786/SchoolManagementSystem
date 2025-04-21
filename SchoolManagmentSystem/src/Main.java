
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

class UniversityManagementGUI extends JFrame {
    private University university;
    private JTabbedPane tabbedPane;

    // Panels for different operations
    private JPanel studentsPanel, teachersPanel, coursesPanel, staffPanel;

    // Input fields
    private JTextField studentNameField, studentEmailField, studentDOBField, studentIDField, studentAddressField;
    private JTextField teacherNameField, teacherEmailField, teacherDOBField, teacherIDField, teacherSpecializationField;
    private JTextField courseIDField, courseTitleField, courseCreditField;
    private JTextField staffNameField, staffEmailField, staffDOBField, staffIDField, staffRoleField, staffDepartmentField;

    // Tables to display data
    private JTable studentsTable, teachersTable, coursesTable, staffTable;

    public UniversityManagementGUI() {
        // Initialize University
        university = new University();

        // Set up the main frame
        setTitle("University Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Initialize panels
        createStudentsPanel();
        createTeachersPanel();
        createCoursesPanel();
        createStaffPanel();


        // Add panels to tabbed pane
        tabbedPane.addTab("Students", studentsPanel);
        tabbedPane.addTab("Teachers", teachersPanel);
        tabbedPane.addTab("Courses", coursesPanel);
        tabbedPane.addTab("Staff", staffPanel);

        // Add tabbed pane to frame
        add(tabbedPane);
    }

    private void createStudentsPanel() {
        studentsPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));

        inputPanel.add(new JLabel("Name:"));
        studentNameField = new JTextField();
        inputPanel.add(studentNameField);

        inputPanel.add(new JLabel("Email:"));
        studentEmailField = new JTextField();
        inputPanel.add(studentEmailField);

        inputPanel.add(new JLabel("Date of Birth:"));
        studentDOBField = new JTextField();
        inputPanel.add(studentDOBField);

        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);

        inputPanel.add(new JLabel("Address:"));
        studentAddressField = new JTextField();
        inputPanel.add(studentAddressField);

        // Add Student Button
        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        inputPanel.add(addStudentButton);

        JButton removeStudentButton = new JButton("Remove Student");
        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        inputPanel.add(removeStudentButton);

        JButton enrollStudentInCourseButton = new JButton("Enroll In Course");
        enrollStudentInCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollStudentInCourse();
            }
        });
        inputPanel.add(enrollStudentInCourseButton);

        JButton displayStudentCoursesButton = new JButton("Display Student's Courses");
        displayStudentCoursesButton.addActionListener(e -> displayStudentCourses());
        inputPanel.add(displayStudentCoursesButton);

        JButton searchStudentButton = new JButton("Search Student");
        searchStudentButton.addActionListener(e -> searchStudent());
        inputPanel.add(searchStudentButton);

        // Table to display students
        String[] columnNames = {"Name", "Email", "DOB", "Student ID", "Address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        studentsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(studentsTable);

        // Layout
        studentsPanel.add(inputPanel, BorderLayout.NORTH);
        studentsPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createTeachersPanel() {
        // Similar structure to createStudentsPanel()
        // Implement input fields and table for Teachers
        teachersPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Teacher"));

        inputPanel.add(new JLabel("Name:"));
        teacherNameField = new JTextField();
        inputPanel.add(teacherNameField);

        inputPanel.add(new JLabel("Email:"));
        teacherEmailField = new JTextField();
        inputPanel.add(teacherEmailField);

        inputPanel.add(new JLabel("Date of Birth:"));
        teacherDOBField = new JTextField();
        inputPanel.add(teacherDOBField);

        inputPanel.add(new JLabel("Teacher ID:"));
        teacherIDField = new JTextField();
        inputPanel.add(teacherIDField);

        inputPanel.add(new JLabel("Specialization:"));
        teacherSpecializationField = new JTextField();
        inputPanel.add(teacherSpecializationField);

        JButton addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacher();
            }
        });
        inputPanel.add(addTeacherButton);

        JButton removeTeacherButton = new JButton("Remove Teacher");
        removeTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeacher();
            }
        });
        inputPanel.add(removeTeacherButton);

        JButton assignCourse = new JButton("Assign Course To Teacher");
        assignCourse.addActionListener(e -> openAssignCourseDialog());
        inputPanel.add(assignCourse);

        JButton displayTeacherCourses = new JButton("Display Assigned Courses");
        displayTeacherCourses.addActionListener(e -> displayCoursesByTeacher());
        inputPanel.add(displayTeacherCourses);

        JButton searchTeacher = new JButton("Search Teacher");
        searchTeacher.addActionListener(e -> searchTeacher());
        inputPanel.add(searchTeacher);

        String[] columnNames = {"Name", "Email", "DOB", "Teacher ID", "Specialization"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        teachersTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(teachersTable);


        teachersPanel.add(inputPanel, BorderLayout.NORTH);
        teachersPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createCoursesPanel() {
        coursesPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Course"));

        inputPanel.add(new JLabel("Course ID:"));
        courseIDField = new JTextField();
        inputPanel.add(courseIDField);

        inputPanel.add(new JLabel("Course Title:"));
        courseTitleField = new JTextField();
        inputPanel.add(courseTitleField);

        inputPanel.add(new JLabel("Credits:"));
        courseCreditField = new JTextField();
        inputPanel.add(courseCreditField);

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
        inputPanel.add(addCourseButton);

        JButton removeCourseButton = new JButton("Remove Course");
        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCourse();
            }
        });
        inputPanel.add(removeCourseButton);

        JButton addStudentToCourseButton = new JButton("Enroll Student In Course");
        addStudentToCourseButton.addActionListener(e -> addStudentToCourse());
        inputPanel.add(addStudentToCourseButton);

        JButton removeStudentFromCourseButton = new JButton("Remove Student From Course");
        removeStudentFromCourseButton.addActionListener(e -> removeStudentFromCourse());
        inputPanel.add(removeStudentFromCourseButton);

        JButton averageGradeButton = new JButton("Average Grade");
        averageGradeButton.addActionListener(e -> getAverageGrade());
        inputPanel.add(averageGradeButton);

        JButton searchCourse = new JButton("Search Course");
        searchCourse.addActionListener(e -> searchCourse());
        inputPanel.add(searchCourse);

        JButton addGrade = new JButton("Add Grade");
        addGrade.addActionListener(e -> setGrades());
        inputPanel.add(addGrade);

        JButton filterCourses = new JButton("Filter Courses");
        filterCourses.addActionListener(e -> filterCourses());
        inputPanel.add(filterCourses);



        String[] columnNames = {"Course ID", "Title", "Credits"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        coursesTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(coursesTable);

        coursesPanel.add(inputPanel, BorderLayout.NORTH);
        coursesPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createStaffPanel() {
        staffPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(9, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Staff"));

        inputPanel.add(new JLabel("Name:"));
        staffNameField = new JTextField();
        inputPanel.add(staffNameField);

        inputPanel.add(new JLabel("Email:"));
        staffEmailField = new JTextField();
        inputPanel.add(staffEmailField);

        inputPanel.add(new JLabel("Date of Birth:"));
        staffDOBField = new JTextField();
        inputPanel.add(staffDOBField);

        inputPanel.add(new JLabel("Staff ID:"));
        staffIDField = new JTextField();
        inputPanel.add(staffIDField);

        inputPanel.add(new JLabel("Role:"));
        staffRoleField = new JTextField();
        inputPanel.add(staffRoleField);

        inputPanel.add(new JLabel("Department:"));
        staffDepartmentField = new JTextField();
        inputPanel.add(staffDepartmentField);

        JButton addStaffButton = new JButton("Add Staff");
        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStaff();
            }
        });
        inputPanel.add(addStaffButton);

        JButton removeStaffButton = new JButton("Remove Staff");
        removeStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStaff();
            }
        });
        inputPanel.add(removeStaffButton);

        JButton generateReport = new JButton("Generate Report");
        generateReport.addActionListener(e -> generateReportForStaff());
        inputPanel.add(generateReport);

        JButton saveDataButton = new JButton("Save Data To File");
        saveDataButton.addActionListener(e -> saveUniversityData());
        inputPanel.add(saveDataButton);

        JButton loadUniversityDataButton = new JButton("Load Data From File");
        loadUniversityDataButton.addActionListener(e -> loadUniversityData());
        inputPanel.add(loadUniversityDataButton);

        JButton searchStaff = new JButton("Search Staff");
        searchStaff.addActionListener(e -> searchStaff());
        inputPanel.add(searchStaff);


        String[] columnNames = {"Name", "Email", "DOB", "Staff ID", "Role", "Department"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        staffTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(staffTable);

        staffPanel.add(inputPanel, BorderLayout.NORTH);
        staffPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void addStudent() {
        String name = studentNameField.getText();
        String email = studentEmailField.getText();
        String dob = studentDOBField.getText();
        String studentID = studentIDField.getText();
        String address = studentAddressField.getText();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || studentID.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create student and add to university
        Student student = new Student(name, email, dob, studentID, address);
        university.addToUniversity(student);

        // Add to table
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.addRow(new Object[]{name, email, dob, studentID, address});

        // Clear fields
        clearStudentFields();
    }
    private void enrollStudentInCourse(){

        int selectedRow = studentsTable.getSelectedRow();
        if(selectedRow >= 0){
            boolean found = false;
            String studentId = (String)studentsTable.getValueAt(selectedRow,3);

            for(Student student: university.getStudents().getItems()){
                if(student.getStudentID().equals(studentId)){
                    found = true;
                    String courseID = JOptionPane.showInputDialog("Enter Course ID: ");

                    Course courseToEnroll = null;
                    for(Course course:university.getCourses().getItems()){
                        if(course.getCourseID().equals(courseID)){
                            if(!course.getEnrolledStudents().contains(student)){
                                courseToEnroll = course;
                                break;
                            }else{
                                JOptionPane.showMessageDialog(this, "Student Already Enrolled in the Course", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        }
                    }

                    if(courseToEnroll!=null){
                        student.enrollInCourse(courseToEnroll);
                        JOptionPane.showMessageDialog(this, "Student " + student.getStudentID() +
                                " successfully enrolled in " + courseToEnroll.getCourseTitle());
                    }else{
                        JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
            if(!found) {
                JOptionPane.showMessageDialog(this, "Student not found in the university database!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please select a student to enroll in Course", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudentCourses(){
        int selectedRow = studentsTable.getSelectedRow();
        if(selectedRow >= 0){
            String ID = (String) studentsTable.getValueAt(selectedRow,3);
            for(Student student:university.getStudents().getItems()){
                if(student.getStudentID().equals(ID)){
                    ArrayList<Course> enrolledCourses = student.getEnrolledCourses();
                    if (enrolledCourses.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No courses enrolled for Student " + student.getStudentID() + ".");
                    } else {
                        StringBuilder coursesInfo = new StringBuilder("Courses Enrolled:\n");
                        for (Course course : enrolledCourses) {
                            coursesInfo.append(course.getCourseTitle()).append(" - ").append(course.getCredit()).append(" credits\n");
                        }
                        JOptionPane.showMessageDialog(this, coursesInfo.toString());
                    }return;
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please select a student to view enrolled courses.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudent(){
        String studentID = JOptionPane.showInputDialog(this,"Enter Student ID","FInd Student",JOptionPane.QUESTION_MESSAGE);
        if(studentID!=null && !studentID.trim().isEmpty()){
            boolean found = false;
            for(int row=0;row<studentsTable.getRowCount();row++){
                String ID = (String)studentsTable.getValueAt(row,3);

                if(studentID.equals(ID)){
                    studentsTable.setRowSelectionInterval(row,row);
                    studentsTable.scrollRectToVisible(studentsTable.getCellRect(row, 0, true));
                    found = true;
                    break;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(this, "Student not found: " + studentID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Student ID cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void removeStudent(){
        int selectedRow = studentsTable.getSelectedRow();
        boolean found = false;
        if(selectedRow >= 0){
            String ID = (String)studentsTable.getValueAt(selectedRow,3);

            for(Student student:university.getStudents().getItems()){
                if(student.getStudentID().equals(ID)){
                    university.removeFromUniversity(student);
                    ((DefaultTableModel)studentsTable.getModel()).removeRow(selectedRow);
                    found = true;
                    break;
                }
            }
            if(found){
                JOptionPane.showMessageDialog(this, "Student removed successfully.");
            }else{
                JOptionPane.showMessageDialog(this, "Student not found in the university database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please select a student to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void saveUniversityData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");


        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure file has .ser extension
            if (!fileToSave.getName().endsWith(".ser")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".ser");
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
                // Write number of sections (4 for students, teachers, courses, staff)
                oos.writeInt(4);

                // Write Students section
                oos.writeObject("STUDENTS");
                ArrayList<Student> students = new ArrayList<>(university.getStudents().getItems());
                oos.writeInt(students.size());
                for (Student student : students) {
                    oos.writeObject(student);
                }

                // Write Teachers section
                oos.writeObject("TEACHERS");
                ArrayList<Teacher> teachers = new ArrayList<>(university.getTeachers().getItems());
                oos.writeInt(teachers.size());
                for (Teacher teacher : teachers) {
                    oos.writeObject(teacher);
                }

                // Write Courses section
                oos.writeObject("COURSES");
                ArrayList<Course> courses = new ArrayList<>(university.getCourses().getItems());
                oos.writeInt(courses.size());
                for (Course course : courses) {
                    oos.writeObject(course);
                }

                // Write Staff section
                oos.writeObject("STAFF");
                ArrayList<AdministrativeStaff> staffList = new ArrayList<>(university.getStaff().getItems());
                oos.writeInt(staffList.size());
                for (AdministrativeStaff staff : staffList) {
                    oos.writeObject(staff);
                }

                JOptionPane.showMessageDialog(this,
                        "Data saved successfully to " + fileToSave.getName(),
                        "Save Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error saving data: " + ex.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addTeacher() {
        String name = teacherNameField.getText();
        String email = teacherEmailField.getText();
        String dob = teacherDOBField.getText();
        String teacherID = teacherIDField.getText();
        String specialization = teacherSpecializationField.getText();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || teacherID.isEmpty() || specialization.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create teacher and add to university
        Teacher teacher = new Teacher(name, email, dob, teacherID, specialization);
        university.addToUniversity(teacher);

        // Add to table
        DefaultTableModel model = (DefaultTableModel) teachersTable.getModel();
        model.addRow(new Object[]{name, email, dob, teacherID, specialization});

        // Clear fields
        clearTeacherFields();
    }

    public void openAssignCourseDialog(){
        JDialog assignCourseDialog = new JDialog();
        assignCourseDialog.setTitle("Assign Course To Teacher");
        assignCourseDialog.setSize(300,200);
        assignCourseDialog.setLocationRelativeTo(null);

        JComboBox<Course> coursesCombo = new JComboBox<>();
        for(Course course:university.getCourses().getItems()){
            coursesCombo.addItem(course);
        }

        JButton assignButton = new JButton("Assign Course");
        assignButton.addActionListener(e -> assignCourseToTeacher(coursesCombo,assignCourseDialog));

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Course:"));
        panel.add(coursesCombo);
        panel.add(assignButton);

        assignCourseDialog.add(panel);
        assignCourseDialog.setModal(true);
        assignCourseDialog.setVisible(true);
    }
    private void assignCourseToTeacher(JComboBox<Course> coursesCombo,JDialog dialog){
        int selectedRow = teachersTable.getSelectedRow();
        if(selectedRow>=0){
            Course course = (Course) coursesCombo.getSelectedItem();
            String ID = (String)teachersTable.getValueAt(selectedRow,3);
            for(Teacher teacher:university.getTeachers().getItems()){
                if(teacher.getTeacherID().equals(ID)){
                    teacher.assignCourse(course);
                    JOptionPane.showMessageDialog(dialog, "Course " + course.getCourseTitle() + " assigned to Teacher " + teacher.getTeacherID());
                    return;
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please select a Teacher to Assign a Course", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayCoursesByTeacher(){
        int selectedRow = teachersTable.getSelectedRow();
        if (selectedRow >= 0) {
        String ID = (String)teachersTable.getValueAt(selectedRow,3);
        JDialog dialog = new JDialog();
        dialog.setTitle("Assigned Courses for Teacher: " + ID);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);

        String[] columnNames = {"Course Title", "Course ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);


        for(Teacher teacher:university.getTeachers().getItems()){
            if(teacher.getTeacherID().equals(ID)){
                for(Course course: teacher.getAssignedCourses()){
                    String[] rows = {course.getCourseID(),course.getCourseTitle()};
                    tableModel.addRow(rows);
                }
                JTable table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);

                dialog.add(scrollPane, BorderLayout.CENTER);

                dialog.setVisible(true);
            }
        }
        }else{
            JOptionPane.showMessageDialog(this,"Please select a teacher to display courses");
        }
    }
    private void searchTeacher(){
        String teacherID = JOptionPane.showInputDialog(this,"Enter Teacher ID","FInd Teacher",JOptionPane.QUESTION_MESSAGE);
        if(teacherID!=null && !teacherID.trim().isEmpty()){
            boolean found = false;
            for(int row=0;row<teachersTable.getRowCount();row++){
                String ID = (String)teachersTable.getValueAt(row,3);

                if(teacherID.equals(ID)){
                    teachersTable.setRowSelectionInterval(row,row);
                    teachersTable.scrollRectToVisible(teachersTable.getCellRect(row, 0, true));
                    found = true;
                    break;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(this, "Teacher not found: " + teacherID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Teacher ID cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeTeacher(){
        int selectedRow = teachersTable.getSelectedRow();
        boolean found = false;
        if(selectedRow >= 0){
            String ID = (String)teachersTable.getValueAt(selectedRow,3);

            for(Teacher teacher:university.getTeachers().getItems()){
                if(teacher.getTeacherID().equals(ID)){
                    university.removeFromUniversity(teacher);
                    ((DefaultTableModel)teachersTable.getModel()).removeRow(selectedRow);
                    found = true;
                    break;
                }
            }
            if(found){
                JOptionPane.showMessageDialog(this, "Teacher removed successfully.");
            }else{
                JOptionPane.showMessageDialog(this, "Teacher not found in the university database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please select a Teacher to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCourse() {
        String courseID = courseIDField.getText();
        String courseTitle = courseTitleField.getText();
        String creditStr = courseCreditField.getText();

        // Validate input
        if (courseID.isEmpty() || courseTitle.isEmpty() || creditStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int credits = Integer.parseInt(creditStr);

            // Note: This requires a teacher to be pre-added, which might need modification
            Course course = new Course(courseID, courseTitle, credits, null);
            university.addToUniversity(course);

            // Add to table
            DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
            model.addRow(new Object[]{courseID, courseTitle, credits});

            // Clear fields
            clearCourseFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credits must be a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeCourse(){
        int selectedRow = coursesTable.getSelectedRow();
        boolean found = false;
        if(selectedRow >= 0){
            String ID = (String)coursesTable.getValueAt(selectedRow,0);

            for(Course course:university.getCourses().getItems()){
                if(course.getCourseID().equals(ID)){
                    university.removeFromUniversity(course);
                    ((DefaultTableModel)coursesTable.getModel()).removeRow(selectedRow);
                    found = true;
                    break;
                }
            }
            if(found){
                JOptionPane.showMessageDialog(this, "Course removed successfully.");
            }else{
                JOptionPane.showMessageDialog(this, "Course not found in the university database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please select a Course to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudentToCourse(){
        int selectedRow = coursesTable.getSelectedRow();
        if(selectedRow >= 0){
            String courseId = (String)coursesTable.getValueAt(selectedRow,0);

            for(Course course: university.getCourses().getItems()){
                if(course.getCourseID().equals(courseId)){
                    String StudentID = JOptionPane.showInputDialog("Enter Student ID: ");

                    Course courseToEnroll = course;
                    for(Student student:university.getStudents().getItems()){
                        if(student.getStudentID().equals(StudentID)){
                            if(!student.getEnrolledCourses().contains(courseToEnroll)){
                                courseToEnroll.addStudent(student);
                                break;
                            }else{
                                JOptionPane.showMessageDialog(this, "Student Already Enrolled in the Course", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        }
                    }

                    if(courseToEnroll!=null){
                        JOptionPane.showMessageDialog(this, "Student "  +
                                " successfully enrolled in " + courseToEnroll.getCourseTitle());
                    }else{
                        JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please select a student to enroll in Course", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeStudentFromCourse(){
        int selectedRow = coursesTable.getSelectedRow();
        boolean found = false;
        if(selectedRow >= 0){
            String ID = (String)coursesTable.getValueAt(selectedRow,0);
            String StudentID = JOptionPane.showInputDialog("Enter Student ID: ");
            Student studentToRemove = null;
            for(Student student:university.getStudents().getItems()){
                if(student.getStudentID().equals(StudentID)){
                    studentToRemove = student;
                    found = true;
                    break;
                }
            }
            if(found){
                for(Course course:university.getCourses().getItems()){
                    if(course.getCourseID().equals(ID)){
                        course.removeStudent(studentToRemove);
                        break;
                    }
                }
                JOptionPane.showMessageDialog(this, "Student removed successfully.");
            }else{
                JOptionPane.showMessageDialog(this, "Student not found in the university database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please select a student to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCourse(){
        String courseID = JOptionPane.showInputDialog(this,"Enter course ID","FInd Student",JOptionPane.QUESTION_MESSAGE);
        if(courseID!=null && !courseID.trim().isEmpty()){
            boolean found = false;
            for(int row=0;row<coursesTable.getRowCount();row++){
                String ID = (String)coursesTable.getValueAt(row,0);

                if(courseID.equals(ID)){
                    coursesTable.setRowSelectionInterval(row,row);
                    coursesTable.scrollRectToVisible(coursesTable.getCellRect(row, 0, true));
                    found = true;
                    break;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(this, "course not found: " + courseID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "course ID cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void setGrades() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Set Grades");
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Top Panel: Course selection
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel courseLabel = new JLabel("Select Course:");
        JComboBox<Course> courseCombo = new JComboBox<>();
        for (Course course : university.getCourses().getItems()) {
            courseCombo.addItem(course);
        }
        topPanel.add(courseLabel);
        topPanel.add(courseCombo);

        // Middle Panel: Student selection and grade input
        JPanel middlePanel = new JPanel(new FlowLayout());
        JLabel studentLabel = new JLabel("Select Student:");
        JComboBox<Student> studentCombo = new JComboBox<>();
        JLabel gradeLabel = new JLabel("Enter Grade:");
        JTextField gradeField = new JTextField(5);

        middlePanel.add(studentLabel);
        middlePanel.add(studentCombo);
        middlePanel.add(gradeLabel);
        middlePanel.add(gradeField);

        // Runnable to refresh student list based on selected course
        Runnable refreshStudents = () -> {
            studentCombo.removeAllItems();
            Course selectedCourse = (Course) courseCombo.getSelectedItem();
            if (selectedCourse != null) {
                for (Student student : selectedCourse.getEnrolledStudents()) {
                    studentCombo.addItem(student);
                }
            }
        };

        refreshStudents.run();
        courseCombo.addActionListener(e -> refreshStudents.run());

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton setGradeButton = new JButton("Set Grade");
        JButton cancelButton = new JButton("Cancel");

        bottomPanel.add(setGradeButton);
        bottomPanel.add(cancelButton);

        dialog.add(topPanel, BorderLayout.NORTH);
        dialog.add(middlePanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);


        cancelButton.addActionListener(e -> dialog.dispose());


        setGradeButton.addActionListener(e -> {
            Course selectedCourse = (Course) courseCombo.getSelectedItem();
            Student selectedStudent = (Student) studentCombo.getSelectedItem();
            String gradeText = gradeField.getText();

            if (selectedCourse != null && selectedStudent != null && !gradeText.isEmpty()) {
                try {
                    Double grade = Double.parseDouble(gradeText);

                    // Get the student's index and update the grades list
                    int studentIndex = selectedCourse.getEnrolledStudents().indexOf(selectedStudent);
                    if (studentIndex >= 0) {
                        selectedCourse.getGrades().set(studentIndex, grade);
                        JOptionPane.showMessageDialog(dialog, "Grade set successfully!");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Student not found in course.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid grade format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a course, student, and enter a valid grade.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void getAverageGrade() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseID = (String) coursesTable.getValueAt(selectedRow, 0);
            Course selectedCourse = null;

            for (Course course : university.getCourses().getItems()) {
                if (course.getCourseID().equals(courseID)) {
                    selectedCourse = course;
                    break;
                }
            }

            if (selectedCourse != null) {
                double averageGrade = selectedCourse.averageGrade();
                JOptionPane.showMessageDialog(this, "Average grade for " + selectedCourse.getCourseTitle() + ": " + averageGrade);
            } else {
                JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a Course to show Average Grade", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void filterCourses(){
        JDialog dialog = new JDialog();
        dialog.setTitle("Filter COurses By Credits");
        dialog.setSize(400,300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Enter Minimum Credits");
        JTextField textField = new JTextField(5);
        JButton filterButton = new JButton("Filter Courses");

        topPanel.add(label);
        topPanel.add(textField);
        topPanel.add(filterButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        dialog.add(topPanel,BorderLayout.NORTH);
        dialog.add(scrollPane,BorderLayout.CENTER);

        filterButton.addActionListener(e -> {
            String creditText = textField.getText();

            try{
                int minCredits = Integer.parseInt(creditText);
                StringBuilder result = new StringBuilder();

                for(Course course:university.getCourses().getItems()){
                    if(course.getCredit() > minCredits){
                        result.append(course.toString()).append("\n");
                    }
                }
                if(result.length() == 0){
                    result.append("No Courses Found with credits greater than ").append(minCredits);
                }

                resultArea.setText(result.toString());
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(dialog,"Please Enter a Valid Number","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.setVisible(true);
    }
    private void addStaff() {
        String name = staffNameField.getText();
        String email = staffEmailField.getText();
        String dob = staffDOBField.getText();
        String staffID = staffIDField.getText();
        String role = staffRoleField.getText();
        String department = staffDepartmentField.getText();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || staffID.isEmpty() || role.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create staff and add to university
        AdministrativeStaff staff = new AdministrativeStaff(name, email, dob, staffID, role, department);
        university.addToUniversity(staff);

        // Add to table
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.addRow(new Object[]{name, email, dob, staffID, role, department});

        // Clear fields
        clearStaffFields();
    }

    private void removeStaff(){
        int selectedRow = staffTable.getSelectedRow();
        boolean found = false;
        if(selectedRow >= 0){
            String ID = (String)staffTable.getValueAt(selectedRow,3);

            for(AdministrativeStaff staff:university.getStaff().getItems()){
                if(staff.getStaffID().equals(ID)){
                    university.removeFromUniversity(staff);
                    ((DefaultTableModel)staffTable.getModel()).removeRow(selectedRow);
                    found = true;
                    break;
                }
            }
            if(found){
                JOptionPane.showMessageDialog(this, "Staff removed successfully.");
            }else{
                JOptionPane.showMessageDialog(this, "Staff not found in the university database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Please select a Staff to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReportForStaff(){
        JDialog dialog = new JDialog();
        dialog.setTitle("Report");
        dialog.setSize(400,300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel reportLabel = new JLabel("Select Report Type");
        JComboBox<String> reportCombo = new JComboBox<>(new String[]{"Students","Teachers","Admininstrative Staff"});
        topPanel.add(reportLabel);
        topPanel.add(reportCombo);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        dialog.add(topPanel,BorderLayout.NORTH);
        dialog.add(scrollPane,BorderLayout.CENTER);

        Runnable refreshReport = () ->{
            String selectedType = (String)reportCombo.getSelectedItem();
            ArrayList<Person> persons = new ArrayList<>();
            if(selectedType.equals("Students")){
                persons.addAll(university.getStudents().getItems());
            }else if(selectedType.equals("Teachers")){
                persons.addAll(university.getTeachers().getItems());
            }else if(selectedType.equals("Administrative Staff")){
                persons.addAll(university.getStaff().getItems());
            }
            String report = AdministrativeStaff.generateReport(persons);
            reportArea.setText(report);
        };
        refreshReport.run();
        reportCombo.addActionListener(e -> refreshReport.run());
        dialog.setVisible(true);
    }


    // Clear methods
    private void clearStudentFields() {
        studentNameField.setText("");
        studentEmailField.setText("");
        studentDOBField.setText("");
        studentIDField.setText("");
        studentAddressField.setText("");
    }

    private void clearTeacherFields() {
        teacherNameField.setText("");
        teacherEmailField.setText("");
        teacherDOBField.setText("");
        teacherIDField.setText("");
        teacherSpecializationField.setText("");
    }

    private void clearCourseFields() {
        courseIDField.setText("");
        courseTitleField.setText("");
        courseCreditField.setText("");
    }

    private void clearStaffFields() {
        staffNameField.setText("");
        staffEmailField.setText("");
        staffDOBField.setText("");
        staffIDField.setText("");
        staffRoleField.setText("");
        staffDepartmentField.setText("");
    }

    private void searchStaff(){
        String staffID = JOptionPane.showInputDialog(this,"Enter staff ID","FInd Teacher",JOptionPane.QUESTION_MESSAGE);
        if(staffID!=null && !staffID.trim().isEmpty()){
            boolean found = false;
            for(int row=0;row<staffTable.getRowCount();row++){
                String ID = (String)staffTable.getValueAt(row,3);

                if(staffID.equals(ID)){
                    staffTable.setRowSelectionInterval(row,row);
                    staffTable.scrollRectToVisible(staffTable.getCellRect(row, 0, true));
                    found = true;
                    break;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(this, "staff not found: " + staffID, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "staff ID cannot be empty!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadUniversityData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to load");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad))) {

                int numSections = ois.readInt();

                for (int i = 0; i < numSections; i++) {
                    String sectionType = (String) ois.readObject();
                    int numItems = ois.readInt();

                    for (int j = 0; j < numItems; j++) {
                        switch (sectionType) {
                            case "STUDENTS":
                                Student student = (Student) ois.readObject();
                                university.addToUniversity(student);
                                addStudentToTable(student);
                                break;

                            case "TEACHERS":
                                Teacher teacher = (Teacher) ois.readObject();
                                university.addToUniversity(teacher);
                                addTeacherToTable(teacher);
                                break;

                            case "COURSES":
                                Course course = (Course) ois.readObject();
                                university.addToUniversity(course);
                                addCourseToTable(course);
                                break;

                            case "STAFF":
                                AdministrativeStaff staff = (AdministrativeStaff) ois.readObject();
                                university.addToUniversity(staff);
                                addStaffToTable(staff);
                                break;
                        }
                    }
                }

                JOptionPane.showMessageDialog(this,
                        "Data loaded successfully from " + fileToLoad.getName(),
                        "Load Successful",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading data: " + ex.getMessage(),
                        "Load Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void addStudentToTable(Student student) {
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
        model.addRow(new Object[]{
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getStudentID(),
                student.getAddress()
        });
    }

    private void addTeacherToTable(Teacher teacher) {
        DefaultTableModel model = (DefaultTableModel) teachersTable.getModel();
        model.addRow(new Object[]{
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDateOfBirth(),
                teacher.getTeacherID(),
                teacher.getSpecialization()
        });
    }

    private void addCourseToTable(Course course) {
        DefaultTableModel model = (DefaultTableModel) coursesTable.getModel();
        model.addRow(new Object[]{
                course.getCourseID(),
                course.getCourseTitle(),
                course.getCredit()
        });
    }

    private void addStaffToTable(AdministrativeStaff staff) {
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.addRow(new Object[]{
                staff.getName(),
                staff.getEmail(),
                staff.getDateOfBirth(),
                staff.getStaffID(),
                staff.getRole(),
                staff.getDepartment()
        });
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityManagementGUI().setVisible(true);
            }
        });
    }
}