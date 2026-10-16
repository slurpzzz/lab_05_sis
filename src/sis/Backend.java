package sis;

import sis.course.Course;
import sis.course.CourseDB;
import sis.user.Professor;
import sis.user.Student;
import sis.user.User;
import sis.user.UserDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Backend {
    private final CourseDB courseDB = new CourseDB();
    private final UserDB userDB = new UserDB();

    public Backend(String courseFile, String professorFile, String studentFile) throws FileNotFoundException {
        initializeCourseDB(courseFile);
        initializeUserDB(professorFile, studentFile);
    }

    private void addCourses(User user, String[] courseIDs) {
        for (String id : courseIDs) {
            Course c = courseDB.getValue(Integer.parseInt(id));
            user.addCourse(c);
            if (user.getType() == User.UserType.PROFESSOR) c.setProfessor(user.getUsername());
            else c.addStudent(user.getUsername());
        }
    }

    public boolean courseExists(int id) {
        return courseDB.hasKey(id);
    }

    public boolean enrollStudent(String username, int courseId) {
        if (!courseExists(courseId) || !userExists(username) || !(userDB.getValue(username) instanceof Student s))
            return false;
        Course c = courseDB.getValue(courseId);
        boolean courseAdded = s.addCourse(c);
        if (!courseAdded) return false;
        return c.addStudent(username);
    }

    public Collection<Course> getAllCourses() {
        return courseDB.getAllValues();
    }

    public Collection<User> getAllUsers() {
        return userDB.getAllValues().stream().sorted(Comparator.comparing(User::getUsername)).toList();
    }

    public Course getCourse(int id) {
        return courseDB.getValue(id);
    }

    public Collection<Course> getCoursesUser(String username) {
        return userDB.getValue(username).getCourses();
    }

    private void initializeCourseDB(String courseFile) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(courseFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int level = Integer.parseInt(fields[2]);
                courseDB.addValue(new Course(id, name, level));
            }
        }
    }

    private void initializeUserDB(String professorFile, String studentFile) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(professorFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                String username = fields[0];
                Professor prof = new Professor(username);
                String[] ids = Arrays.copyOfRange(fields, 1, fields.length);
                addCourses(prof, ids);
                userDB.addValue(prof);
            }
        }
        try (Scanner in = new Scanner(new File(studentFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                String username = fields[0];
                Student stu = new Student(username);
                String[] ids = Arrays.copyOfRange(fields, 1, fields.length);
                addCourses(stu, ids);
                userDB.addValue(stu);
            }
        }
    }

    public boolean isStudent(String username) {
        return userDB.hasKey(username) && userDB.getValue(username).getType() == User.UserType.STUDENT;
    }

    public boolean unenrollStudent(String username, int courseId) {
        if (!courseExists(courseId) || !userExists(username) || !(userDB.getValue(username) instanceof Student s))
            return false;
        Course c = courseDB.getValue(courseId);
        if (!c.removeStudent(username)) return false;
        return s.removeCourse(c);
    }

    public boolean userExists(String username) {
        return userDB.hasKey(username);
    }
}
