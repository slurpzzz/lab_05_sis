package sis.user;

import sis.course.Course;

import java.util.Comparator;

public class Professor extends User implements Comparable<User> {
    public Professor(String username) {
        super(username, UserType.PROFESSOR, Comparator.comparingInt(Course::getLevel).thenComparing(Course::getName));
    }
}
