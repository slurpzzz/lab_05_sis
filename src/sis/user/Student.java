package sis.user;

import sis.course.Course;

import java.util.Comparator;

public class Student extends User implements Comparable<User> {
    public Student(String username) {
        super(username, UserType.STUDENT, (c1, c2) -> c1.getName().compareTo(c2.getName()));
    }
}
