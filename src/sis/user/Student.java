package sis.user;

import sis.course.Course;

import java.util.Comparator;

public class Student extends User implements Comparable<User> {
    public Student(String username) {
        super(username, UserType.STUDENT, Comparator.comparing(Course::getName));
    }
}
