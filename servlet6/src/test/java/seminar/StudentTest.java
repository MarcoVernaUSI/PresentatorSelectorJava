package seminar;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.model.Student;

public class StudentTest {

    @Test
    public void checkStudent() {
        Student student = new Student(1, "Anna", "Marchi");
        
        assertThat(student.getName(), is("Anna"));
        assertThat(student.getLastName(), is("Marchi"));
        assertThat(student.getFullName(), is("Anna Marchi"));
    }
}
