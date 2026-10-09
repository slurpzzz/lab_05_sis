package sis.course;

import java.util.Comparator;

/**
 * A class that overrides the natural order comparison of courses to order
 * them alphabetically by course name.
 *
 * @author JUSTIN SPADONE
 */
public class CourseComparator implements Comparator<Course> {
    @Override
    public int compare(Course c1, Course c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
