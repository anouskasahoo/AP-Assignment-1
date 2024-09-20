package com.iiitd.registration;

import java.util.ArrayList;
import java.util.Objects;

import static com.iiitd.registration.Main.userMap;

public class Professor extends User {
    public ArrayList<Student> students = new ArrayList<>();
    public String name;
    public Course course;

    public Professor(String email, String password, String name) {
        super(email, password);
        this.name = name;
        userMap.put(email, this);
        course = null;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            if (course != null) {
                if (!student.getCurrCourses().contains(this.course))
                    student.registerForCourse(this.course.getCourseCode());
            }
        }
    }

    public static Professor findProfByName(String name){
        Professor professor = null;
        boolean profFound = false;
        for (User user : userMap.values()) {
            if (user instanceof Professor prof) {
                if (Objects.equals(prof.name, name)){
                    profFound = true;
                    professor = prof;
                }
            }
        }
        if (!profFound) {
            System.out.println("Invalid email, prof not found");
            return null;
        }
        return professor;
    }

    public void getCourseDetails(String name){
        for (Course c: Course.getCourseCatalog()){
            if (Objects.equals(c.getProfessor(), name)) {
                System.out.println(c.getTitle()+" | "+c.getCourseCode());
                System.out.println("Semester: "+c.getSemester()+" | "+"Credits offered: "+c.getCredits());
                return;
            }
        }
        System.out.println("No course assigned yet");
    }

    public void viewStudentsInCourse(String courseCode) {
        System.out.println("Students enrolled in course " + courseCode + ":");
        System.out.printf("%-20s | %-20s | %-5s%n", "Name", "Email", "CGPA");

        for (Student student : students) {
            for (Course course : student.getCurrCourses()) {
                if (course.getCourseCode().equals(courseCode)) {
                    System.out.printf("%-20s | %-20s | %-5.2f%n", student.name, student.getEmail(), student.getCGPA());
                }
            }
        }
    }

    public void uploadScore(String studentEmail, String courseCode, int score) {
        Student student = (Student) userMap.get(studentEmail);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        Course course = Course.findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (!student.getCurrCourses().contains(course)) {
            System.out.println("Student is not registered for this course.");
            return;
        }

        student.addCourseScore(courseCode, (double) score);
        System.out.println("Score uploaded successfully.");

    }
}
