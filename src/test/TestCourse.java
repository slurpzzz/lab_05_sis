package test;

import org.junit.jupiter.api.*;
import sis.course.Course;
import sis.course.CourseComparator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit unit test for Course
 *
 * @author RIT CS
 */
public class TestCourse {

    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /** assortment of Course objects used on the test methods */
    private static Course cs1;
    private static Course cs2;
    private static Course mop;
    private static Course algo;
    private static Course plc;

    @BeforeAll
    public static void initCourses(){
        // initializing an assortment of Course objects
        cs1 = new Course(1, "Computer_Science_1", 100);
        cs2 = new Course(2, "Computer_Science_2", 100);
        mop = new Course(3, "Mechanics_of_Programming", 200);
        algo = new Course(4, "Analysis_Of_Algorithms", 200);
        plc = new Course(5, "Programming_Language_Concepts", 300);
    }

    @BeforeEach
    public void setUp() {
        // changing the default standard output for our stream captor
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test Course equality implementation */
    @Test
    public void testEquality() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(cs1);
        courseList.add(cs2);
        courseList.add(mop);
        courseList.add(algo);
        assertTrue(courseList.contains(mop));
        assertFalse(courseList.contains(plc));
        // copy of an existing course in the list
        assertTrue(courseList.contains(new Course(4, "Analysis_Of_Algorithms", 200)));
    }

    /**
     * Test Course natural order
     */
    @Test
    public void testNaturalOrder() {
        Set<Course> courseTree1 = new TreeSet<>();
        courseTree1.add(algo);
        courseTree1.add(mop);
        courseTree1.add(cs2);
        courseTree1.add(plc);
        courseTree1.add(cs1);
        courseTree1.add(mop);  // intentionally add a duplicate
        courseTree1.add(new Course(4, "Analysis_Of_Algorithms", 200)); // adding another duplicate

        String expected = "Course{id=1, name='Computer_Science_1', level=100, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=2, name='Computer_Science_2', level=100, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=3, name='Mechanics_of_Programming', level=200, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=4, name='Analysis_Of_Algorithms', level=200, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=5, name='Programming_Language_Concepts', level=300, professor=null, students=[]}";

        for (Course course : courseTree1) {
            System.out.println(course);
        }
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    /**
     * Test Course alternative sorting defined in CourseComparator class
     */
    @Test
    public void testAlternativeOrder() {
        Set<Course> courseTree2 = new TreeSet<>(new CourseComparator());
        courseTree2.add(cs1);
        courseTree2.add(cs2);
        courseTree2.add(mop);
        courseTree2.add(algo);
        courseTree2.add(plc);
        for (Course course : courseTree2) {
            System.out.println(course);
        }

        String expected = "Course{id=4, name='Analysis_Of_Algorithms', level=200, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=1, name='Computer_Science_1', level=100, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=2, name='Computer_Science_2', level=100, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=3, name='Mechanics_of_Programming', level=200, professor=null, students=[]}" + System.lineSeparator();
        expected += "Course{id=5, name='Programming_Language_Concepts', level=300, professor=null, students=[]}";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    /**
     * Test Course hash code implementation
     */
    @Test
    public void testHashCode(){
        // using a LinkedHashMap to preserve insertion order
        // otherwise we won't be able to match the order of the expected output
        Map<Course, String> courseMap = new LinkedHashMap<>();
        courseMap.put(cs1, "A");
        courseMap.put(cs2, "A");
        courseMap.put(mop, "B-");
        courseMap.put(algo, "A");
        courseMap.put(plc, "B+");
        courseMap.put(new Course(1, "Computer_Science_1", 100), "A-");  // change cs1 grade to A-

        String expected = "Computer_Science_2" + System.lineSeparator();
        expected += "Analysis_Of_Algorithms";

        // loop over the map and print out the matching ones here
        // TODO

        // TODO Uncomment the following assertEquals to verify the correctness of this test
        //assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}