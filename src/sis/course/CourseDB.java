package sis.course;

import sis.DB;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CourseDB implements DB<Integer, Course> {
    private final HashMap<Integer, Course> courses;

    public CourseDB() {
        this.courses = new HashMap<>();
    }

    @Override
    public Course addValue(Course value) {
        Course c = courses.get(value.getId());
        courses.put(value.getId(), value);
        return c;
    }

    @Override
    public Course getValue(Integer key) {
        return courses.get(key);
    }

    @Override
    public boolean hasKey(Integer key) {
        return courses.containsKey(key);
    }

    @Override
    public Collection<Course> getAllValues() {
        return courses.values();
    }
}
