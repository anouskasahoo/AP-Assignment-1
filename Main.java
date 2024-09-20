package com.iiitd.registration;
import java.util.*;

import static com.iiitd.registration.User.findUser;

public class Main {
    static ArrayList<Complaint> complaints = new ArrayList<>();
    static Map<String, User> userMap = new HashMap<>();
    static final ArrayList<Course> courseCatalog = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();

        while (true) {
            System.out.println("\n");
            System.out.println("Indraprastha Institute of Information Technology, Delhi");
            System.out.println("-------------------------------------------------------");
            System.out.println("\n     Welcome to the New IIITD Registration System!\n");
            System.out.println("Enter Your Choice-");
            System.out.println("Press 1 to Enter the system\nPress 2 to exit the system\nPress 3 to see a cat\n");
            System.out.println("-------------------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thank You for visiting our system!");
                    System.out.println("Exiting...");
                    return;
                case 3:
                    System.out.println(" /\\_/\\");
                    System.out.println("( o.o ) helo!");
                    System.out.println(" > ^ <\n");
                    System.out.println("Thank you for checking out the cat :)\nNow back to the system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }

    private static void login() {
        System.out.println("-------------------------------------------------------\n");
        System.out.println("Login Here");
        System.out.println("Enter Your Role-");
        System.out.println("Press 1 for Student\nPress 2 for Professor\nPress 3 for Administrator");
        System.out.println("\n\nEnter 4 to Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                studentEnter();
                break;
            case 2:
                professorEnter();
                break;
            case 3:
                adminEnter();
                break;
            case 4:
                System.out.println("Exiting the application.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void initialize() {
        //hardcoding courses
        //sem 1
        Course ip = new Course("Introduction to Programming", "ProfA", 1, 4, "CSE101");
        Course hci = new Course("Human Computer Interaction", "ProfB", 1, 4, "DES101");
        Course m1 = new Course("Linear Algebra", "ProfC", 1, 2, "MTH101");
        //sem 2
        Course dsa = new Course("Data Structures and Algorithms", "ProfD", 2, 4, "CSE102", "CSE101");
        Course ddv = new Course("Design Drawing and Visualization", "ProfE", 2, 4, "DES102", "DES101");
        Course vdc = new Course("Visual Design and Communication", "ProfF", 2, 4, "DES103", "DES101");
        Course m2 = new Course("Probability and Statistics", "ProfG", 2, 2, "MTH102", "MTH101");
        //sem 3
        Course ap = new Course("Advanced Programming", "ProfH", 3, 4, "CSE201", "CSE101", "CSE102");
        Course dpp = new Course("Design Processes and Perspectives", "ProfI", 3, 4, "DES201", "DES101", "DES103", "CSE101");
        Course m3 = new Course("Multivariate Calculus", "ProfJ", 4, 2, "MTH201", "MTH101", "MTH102");

        //hardcoding students
        Student student1 = new Student("anouska@mail.com", "ab", 1, "anouska");  // Email, Password, Semester
        Student student2 = new Student("somya@mail.com", "bc", 2, "somya");
        Student student3 = new Student("bhoomi@mail.com", "cd", 3, "bhoomi");
        Student student4 = new Student("vanshi@mail.com", "de", 1, "vanshi");  // Email, Password, Semester
        Student student5 = new Student("anishka@mail.com", "ef", 2, "anishka");
        Student student6 = new Student("ambar@mail.com", "fg", 3, "ambar");

        //hardcoding profs
        Professor profW = new Professor("ProfW@mail.com", "passProfW", "ProfW");
        Professor profX = new Professor("ProfX@mail.com", "passProfX", "ProfX");
        Professor profY = new Professor("ProfY@mail.com", "passProfY", "ProfY");
        Professor profZ = new Professor("ProfZ@mail.com", "passProfZ", "ProfZ");

        //hardcoding timings
        ip.setTimings("Monday", "10:00-11:30");
        ip.setTimings("Wednesday", "10:00-11:30");
        hci.setTimings("Tuesday", "13:00-14:30");
        hci.setTimings("Thursday", "13:00-14:30");
        m1.setTimings("Monday", "09:00-10:30");
        m1.setTimings("Wednesday", "09:00-10:30");
        dsa.setTimings("Tuesday", "11:00-12:30");
        dsa.setTimings("Thursday", "11:00-12:30");
        ddv.setTimings("Monday", "14:00-15:30");
        ddv.setTimings("Wednesday", "14:00-15:30");
        vdc.setTimings("Tuesday", "15:00-16:30");
        vdc.setTimings("Thursday", "15:00-16:30");
        m2.setTimings("Monday", "11:00-12:00");
        m2.setTimings("Wednesday", "11:00-12:00");
        ap.setTimings("Tuesday", "09:00-10:30");
        ap.setTimings("Thursday", "09:00-10:30");
        dpp.setTimings("Monday", "13:00-14:30");
        dpp.setTimings("Wednesday", "13:00-14:30");

        //hardcoding admin
        Admin adm = new Admin("idk123");
        //admin.assignProf("DES101","Prof W");
        adm.assignProf("CSE101", "ProfX");
        adm.assignProf("DES101", "ProfY");
        adm.assignProf("MTH101", "ProfZ");

        //hard coding complaints
        Complaint complaint1 = new Complaint("Issue with course registration system", student1);
        Complaint complaint2 = new Complaint("Grade discrepancies in recent exam", student2);
        Complaint complaint3 = new Complaint("Inadequate course materials provided", student3);
        adm.changeStatus(1,student1.name);

        //hardcoding previous and current courses of some students
        student1.registerForCourse("CSE101");
        student2.registerForCourse("CSE101");
        student2.addCourseScore("CSE101", 9.0);
        adm.completeStudentCourse("anouska@mail.com","CSE101");
        student2.registerForCourse("CSE102");
    }

    private static void studentEnter() {
        System.out.println("Welcome student!");
        System.out.println("Press 1 to Sign In\nPress 2 to Log In (if you have previously signed in)\nPress 3 to go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                studentSignIn();
                break;
            case 2:
                studentLogin();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void studentSignIn() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Student student = (Student) findUser(email);
        if (student!=null) {
            System.out.println("You have already signed in. Please log in with your password");
            studentEnter();
            return;
        }
        System.out.println("Set your password:");
        String password = scanner.nextLine();
        System.out.println("Enter your Name:");
        String name = scanner.nextLine();
        System.out.println("Enter your semester:");
        int sem = Integer.parseInt(scanner.nextLine());
        if (sem<1 || sem>8) {
            System.out.println("Semester is out of range (1-8), try again!");
            return;
        }
        Student st = new Student(email,password,sem,name);

        System.out.println("Signed In successfully, Kindly Log In now to use the registration system");
        studentEnter();
    }

    private static void studentLogin() {

        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Student student = (Student) findUser(email);
        if (student==null) {
            System.out.println("Email not found. Please Sign In before Logging In");
            studentEnter();
        }
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (student!=null && student.login(email, password)) {
            System.out.println("-----------------------------------------");
            System.out.println("Successfully logged in!");
            System.out.println("Hello "+student.name+"!");

            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("\nStudent Menu:");
                System.out.println("-----------------------------------------");
                System.out.println("1. View Available Courses");
                System.out.println("2. Register for Courses");
                System.out.println("3. View Schedule");
                System.out.println("4. Track Academic Progress");
                System.out.println("5. Drop Courses");
                System.out.println("6. Manage your complaints");
                System.out.println("7. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 7) {
                    System.out.println("-----------------------------------------");
                    System.out.println("Logging out");
                    return;
                }

                switch (choice) {
                    case 1:
                        System.out.println("-----------------------------------------\n");
                        System.out.println("Semester " + student.getSemester() + " courses- \n");
                        Student.viewAvailableCourses(student);
                        break;
                    case 2:
                        System.out.println("-----------------------------------------");
                        System.out.println("Enter Course Code of the course you want to register: ");
                        String course = scanner.nextLine();
                        student.registerForCourse(course);
                        break;
                    case 3:
                        System.out.println("-----------------------------------------");
                        student.viewSchedule();
                        break;
                    case 4:
                        System.out.println("-----------------------------------------");
                        System.out.println("Your current CGPA is " + student.getCGPA() + ".");
                        System.out.println("Your current SGPA is " + student.getSGPA() + ".");
                        System.out.println("You have " + student.getCredits() + " credits till now.");
                        break;
                    case 5:
                        System.out.println("-----------------------------------------");
                        System.out.println("Enter Course Code of the course you want to drop: ");
                        String code = scanner.nextLine();
                        student.dropCourse(code);
                        break;
                    case 6:
                        while (true) {
                            System.out.println("-----------------------------------------");
                            System.out.println("1. View your complaints and their statuses");
                            System.out.println("2. Submit a new complaint");
                            System.out.println("3. Edit a complaint");
                            System.out.println("4. Go back");

                            int pick = scanner.nextInt();
                            scanner.nextLine();

                            if (pick == 4){
                                break;
                            }

                            switch (pick) {
                                case 1:
                                    System.out.println("Your complaints: ");
                                    student.checkComplaints();
                                    break;
                                case 2:
                                    System.out.println("Enter your complaint: ");
                                    String comp = scanner.nextLine();
                                    student.submitComplaint(comp);
                                    break;
                                case 3:
                                    System.out.println("Enter your complaint number: ");
                                    int num = scanner.nextInt();
                                    scanner.nextLine();
                                    Complaint complaintToEdit = null;
                                    for (Complaint c : complaints) {
                                        if (c.getFiledBy() == student && c.getComplaintNumber() == num) {
                                            complaintToEdit = c;
                                            break;
                                        }
                                    }
                                    if (complaintToEdit == null) {
                                        System.out.println("Complaint not found");
                                    } else {
                                        System.out.println("Enter your modified complaint: ");
                                        String newComp = scanner.nextLine();
                                        complaintToEdit.setComplaint(newComp);
                                        System.out.println("Complaint number " + complaintToEdit.getComplaintNumber() + " has been edited.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid choice please try again");
                                    break;
                            }
                        }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid email or password. Try again");
        }
    }

    private static void professorEnter() {
        System.out.println("Welcome professor!");
        System.out.println("Press 1 to Sign In\nPress 2 to Log In (if you have previously signed in)\nPress 3 to go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                professorSignIn();
                break;
            case 2:
                professorLogin();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void professorSignIn() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Professor professor = (Professor) findUser(email);
        if (professor != null) {
            System.out.println("You have already signed in. Please log in with your password.");
            professorEnter();
            return;
        }
        System.out.println("Set your password:");
        String password = scanner.nextLine();
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        Professor prof = new Professor(email, password, name);

        System.out.println("Signed In successfully, kindly Log In now to use the registration system.");
        professorEnter();
    }

    private static void professorLogin() {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Professor prof = (Professor) findUser(email);
        if (prof==null) {
            System.out.println("Email not found. Please Sign In before Logging In");
            professorEnter();
        }

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        assert prof != null;
        if (prof.login(email, password)) {
            System.out.println("-----------------------------------------");
            System.out.println("Successfully logged in!");
            System.out.println("Hello "+prof.name+"!");

            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("\nProfessor Menu:");
                System.out.println("-----------------------------------------");
                System.out.println("1. View your Course details");
                System.out.println("2. View Students in a Course");
                System.out.println("3. Manage Course details");
                System.out.println("4. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 4){
                    System.out.println("-----------------------------------------");
                    System.out.println("Logging out");
                    return;
                }

                switch (choice) {
                    case 1:
                        System.out.println("-----------------------------------------");
                        prof.getCourseDetails(prof.name);
                        if (prof.course != null) {
                            System.out.println("Syllabus: " + prof.course.getSyllabus());
                        }
                        break;
                    case 2:
                        System.out.println("-----------------------------------------");
                        if (prof.course == null) {
                            System.out.println("You don't have a course yet");
                        } else if (prof.students == null){
                            System.out.println("No students in your course yet");
                        } else {
                            System.out.println("Details of Students in course " + prof.course.getCourseCode() + "-");
                            prof.viewStudentsInCourse(prof.course.getCourseCode());
                        }
                        break;
                    case 3:
                        if (prof.course != null) {
                            System.out.println("Select a detail to modify");
                            System.out.println("1. Add prerequisite");
                            System.out.println("2. Edit course credits");
                            System.out.println("3. Edit syllabus");
                            System.out.println("4. Edit class timings");
                            System.out.println("5. Assign grades");
                            System.out.println("6. Add student to course");

                            int pick = scanner.nextInt();
                            scanner.nextLine();

                            switch (pick) {
                                case 1:
                                    System.out.println("Enter course code to add as your course's prerequisite: ");
                                    String prereq = scanner.nextLine();
                                    prof.course.addPrerequisite(prereq);
                                    System.out.println("Prerequisite added.");
                                    break;
                                case 2:
                                    System.out.println("Enter course credits for your course: ");
                                    prof.course.credits = scanner.nextInt();
                                    System.out.println("Credits updated.");
                                    break;
                                case 3:
                                    prof.course.getSyllabus();
                                    System.out.println("To add to syllabus, press 1");
                                    int ans = scanner.nextInt();
                                    if (ans==1) {
                                        System.out.println("Enter topic to add: ");
                                        String topic = scanner.nextLine();
                                        prof.course.addToSyllabus(topic);
                                    }
                                    break;
                                case 4:
                                    System.out.println("1. Add timings");
                                    System.out.println("2. Remove timings");

                                    int ar = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (ar) {
                                        case 1:
                                            System.out.println("Enter day: ");
                                            String day = scanner.nextLine();
                                            System.out.println("Enter time slot: ");
                                            String ts = scanner.nextLine();
                                            prof.course.addTiming(day,ts);
                                            break;
                                        case 2:
                                            System.out.println("Enter day to remove: ");
                                            String d = scanner.nextLine();
                                            prof.course.removeTiming(d);
                                            break;
                                    }
                                    System.out.println("Timing updated");

                                    break;
                                case 5:
                                    System.out.println("Enter course code to assign grades for: ");
                                    String code = scanner.nextLine();
                                    System.out.println("Enter student's email id: ");
                                    String mail = scanner.nextLine();
                                    System.out.println("Enter score: ");
                                    int score = scanner.nextInt();
                                    prof.uploadScore(mail, code, score);
                                    System.out.println("Grade updated.");
                                    break;
                                case 6:
                                    System.out.println("Enter student's email id: ");
                                    String st = scanner.nextLine();
                                    prof.addStudent((Student) userMap.get(st));
                                    System.out.println("Student added.");
                                    break;
                                default:
                                    System.out.println("Invalid Input");
                                    break;
                            }
                        }
                        else{
                            System.out.println("You have not been assigned a course yet");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid email or password. Try again.");
        }
    }

    private static void adminEnter() {
        System.out.println("Welcome administrator!");
        System.out.println("Press 1 to Sign In\nPress 2 to Log In (if you have previously signed in)\nPress 3 to go back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                adminSignIn();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void adminSignIn() {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        Admin adm = (Admin) findUser(email);
        if (adm != null) {
            System.out.println("You have already signed in. Please log in with your password.");
            adminEnter();
            return;
        }
        Admin admin = new Admin(email);
        System.out.println("Signed In successfully, kindly Log In now to use the registration system.");
        adminEnter();
    }

    private static void adminLogin() {
        System.out.println("-----------------------------------------\n");
        System.out.println("Welcome Admin!");
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Admin admin = (Admin) findUser(email);
        if (admin==null) {
            System.out.println("Email not found, please sign in first");
            adminEnter();
            return;
        }
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        if (!Objects.equals(password, Admin.adminPassword)){
            System.out.println("Incorrect Password.");
            return;
        }
        if (admin.adminLog(password)) {
            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("\nAdministrator Menu:");
                System.out.println("1. Manage Course Catalog");
                System.out.println("2. Manage Student Records");
                System.out.println("3. Manage Professor Records");
                System.out.println("4. Handle Complaints");
                System.out.println("5. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("-----------------------------------------");
                        System.out.println("Course Catalog Functions");
                        System.out.println("1. View Courses");
                        System.out.println("2. Add Course");
                        System.out.println("3. Delete Course");
                        System.out.println("4. Complete a student's course");
                        int pick = scanner.nextInt();
                        scanner.nextLine();
                        switch (pick) {
                            case 1:
                                System.out.println("\n");
                                admin.viewCourses();
                                break;
                            case 2:
                                System.out.println("\n");
                                System.out.println("Enter course title: ");
                                String title = scanner.nextLine();
                                System.out.println("Enter professor name: ");
                                String prof = scanner.nextLine();
                                System.out.println("Enter Semester: ");
                                int sem = scanner.nextInt();
                                System.out.println("Enter course credits: ");
                                int cred = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter Course code: ");
                                String coursecode = scanner.nextLine();
                                System.out.println("Enter prerequisite: ");
                                String prerequisite = scanner.nextLine();

                                admin.addCourse(title, prof, sem, cred, coursecode, prerequisite);
                                break;
                            case 3:
                                System.out.println("\n");
                                System.out.println("Enter Course code");
                                String codecourse = scanner.nextLine();
                                admin.deleteCourse(codecourse);
                                break;
                            case 4:
                                System.out.println("Enter student email: ");
                                String mail = scanner.nextLine();
                                System.out.println("Enter course code: ");
                                String code = scanner.nextLine();
                                admin.completeStudentCourse(mail,code);
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("-----------------------------------------");
                        System.out.println("Student Records Functions");
                        System.out.println("1. View Records");
                        System.out.println("2. Update Grade");
                        System.out.println("3. Update student semester");
                        int pick1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (pick1) {
                            case 1:
                                System.out.println("\n");
                                admin.viewStudentRecords();
                                break;
                            case 2:
                                System.out.println("Enter student's email: ");
                                String mail = scanner.nextLine();
                                System.out.println("Enter course code: ");
                                String code = scanner.nextLine();
                                System.out.println("Enter score: ");
                                int score = scanner.nextInt();
                                admin.uploadScore(mail, code, score);
                                break;
                            case 3:
                                System.out.println("Enter student's email: ");
                                String studentMail = scanner.nextLine();
                                System.out.println("Enter new semester: ");
                                int semester = scanner.nextInt();
                                admin.updateSemester(studentMail, semester);
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("-----------------------------------------");
                        System.out.println("Student Records Functions");
                        System.out.println("1. View Records");
                        System.out.println("2. Assign Professor to Course");
                        int pick2 = scanner.nextInt();
                        scanner.nextLine();
                        switch (pick2) {
                            case 1:
                                admin.viewProfRecords();
                                break;
                            case 2:
                                System.out.println("Enter course code to assign a professor to: ");
                                String code = scanner.nextLine();
                                System.out.println("Enter professor name: ");
                                String name = scanner.nextLine();
                                admin.assignProf(code, name);
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Student Records Functions");
                        System.out.println("1. View Complaints");
                        System.out.println("2. Filter complaints according to resolution status");
                        System.out.println("3. Change status of a complaint");
                        int pick3 = scanner.nextInt();
                        scanner.nextLine();
                        switch (pick3) {
                            case 1:
                                if (complaints.isEmpty()) {
                                    System.out.println("No complaints filed yet");
                                } else {
                                    System.out.println("Here are the current complaints: ");
                                    admin.viewComplaints();
                                }
                                break;
                            case 2:
                                if (complaints.isEmpty()) {
                                    System.out.println("No complaints filed yet");
                                } else {
                                    System.out.println("Enter U to check unresolved complaints and R to check resolved complaints: ");
                                    String res = scanner.nextLine();
                                    if (Objects.equals(res, "U")) {
                                        admin.viewComplaintsAccToResolution(false);
                                    } else if (Objects.equals(res, "R")) {
                                        admin.viewComplaintsAccToResolution(true);
                                    } else {
                                        System.out.println("Invalid input try again");
                                    }
                                }
                                break;
                            case 3:
                                if (complaints.isEmpty()) {
                                    System.out.println("No complaints filed yet");
                                } else {
                                    System.out.println("Enter complainant name: ");
                                    String complaintName = scanner.nextLine();
                                    System.out.println("Here are complaints filed by "+complaintName+" :");
                                    admin.viewComplaintsIndividual(complaintName);
                                    System.out.println("Enter complaint number to change status of: ");
                                    int num = scanner.nextInt();
                                    admin.changeStatus(num, complaintName);
                                    System.out.println("Successfully changed status of complaint.");
                                }
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("-----------------------------------------");
                        System.out.println("Logging out");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
}
