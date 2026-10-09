package sis.user;

import sis.course.Course;

import java.util.Comparator;

public class Professor extends User implements Comparable<User> {
    public Professor(String username) {
        super(username, UserType.PROFESSOR, (c1, c2) -> {
            int byLevel = Integer.compare(c1.getLevel(), c2.getLevel());
            if (byLevel != 0) return byLevel;
            return c1.getName().compareTo(c2.getName());
        });
    }
}
