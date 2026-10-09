package sis.user;

import sis.course.Course;

import java.util.*;

public class User implements Comparable<User> {
    private final TreeSet<Course> courses;
    private final UserType type;
    private final String username;

    public User(String username, UserType type, Comparator<Course> comp) {
        this.username = username;
        this.type = type;
        courses = new TreeSet<>(comp);
    }

    public boolean addCourse(Course course) {
        return courses.add(course);
    }

    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User u) return Objects.equals(this.username, u.username);
        return false;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public String toString() {
        return "User{username='%s', type=%s, courses=%s}".formatted(username, type, courses);
    }

    public enum UserType {
        PROFESSOR, STUDENT
    }
}
