package seminar;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.model.Course;

public class CourseTest {
    String courseDate = "28.03.2020";
    @Test
    public void checkCourse() {
        Course course = new Course(1, "English", "A1","",2, courseDate);

        assertThat(course.getName(), is("English"));
        assertThat(course.getId(), is(1));
        assertThat(course.getDescription(), is("A1"));
        assertThat(course.getStartDate(), is("28.03.2020"));
    }
}
