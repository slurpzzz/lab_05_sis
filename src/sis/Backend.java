package sis;

import sis.course.Course;
import sis.course.CourseDB;
import sis.user.Professor;
import sis.user.Student;
import sis.user.User;
import sis.user.UserDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Backend {
    private CourseDB courseDB = new CourseDB();
    private UserDB userDB = new UserDB();

    public Backend(String courseFile, String professorFile, String studentFile) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(courseFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int level = Integer.parseInt(fields[2]);
                courseDB.addValue(new Course(id, name, level));
            }
        }
        try (Scanner in = new Scanner(new File(professorFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                String name = fields[0];
                Professor prof = new Professor(name);
                int[] ids = Arrays.stream(fields, 1, fields.length).mapToInt(Integer::parseInt).toArray();
                for (int id : ids) {
                    prof.addCourse(courseDB.getValue(id));
                }
                userDB.addValue(prof);
            }
        }
        try (Scanner in = new Scanner(new File(studentFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                String name = fields[0];
                Student stu = new Student(name);
                int[] ids = Arrays.stream(fields, 1, fields.length).mapToInt(Integer::parseInt).toArray();
                for (int id : ids) {
                    stu.addCourse(courseDB.getValue(id));
                }
                userDB.addValue(stu);
            }
        }
    }

    private void addCourses(User user, String[] courseIDs) {

    }

    boolean courseExists(int id) {
        return courseDB.hasKey(id);
    }

    boolean enrollStudent(String username, int courseId) {
        return false;
    }

    Collection<Course> getAllCourses() {
        return courseDB.getAllValues();
    }

    Collection<User> getAllUsers() {
        return List.of();
    }

    Course getCourse(int id) {
        return courseDB.getValue(id);
    }

    Collection<Course> getCoursesUser(String username) {
        return List.of();
    }

    private void initializeCourseDB(String courseFile) {
    }

    private void initializeUserDB(String professorFile, String studentFile) {
    }

    boolean isStudent(String username) {
        return userDB.hasKey(username) && userDB.getValue(username).getType() == User.UserType.STUDENT;
    }

    boolean unenrollStudent(String username, int courseId) {
        return false;
    }

}
