package seminar;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.model.Course;
import com.app.seminar.model.Student;

public class SeminarTest {
    String courseDate = "28.03.2020";

    @Test
    public void checkSeminar() {
        Course course1 = new Course(1, "English", "A1", "", 2, courseDate);
        Course course2 = new Course(1, "Deutsch", "B1", "", 2, courseDate);
        Course course3 = new Course(1, "Espagnolo", "C1", "", 2, courseDate);
      
        Student student1 = new Student(1, "Anna", "Marchi");
        Student student2 = new Student(1, "Marco", "Ruggieri");
        Student student3 = new Student(1, "Ivan", "Bernasconi");
        Student student4 = new Student(1, "Raf", "Bosch");
        Student student5 = new Student(1, "Pillar", "Moura");
        Student student6 = new Student(1, "Rosane", "Miguel");
        
        course1.addStudent(student1);
        course1.addStudent(student2);
        
        course2.addStudent(student3);
        course2.addStudent(student4);
        
        course3.addStudent(student5);
        course3.addStudent(student6);

        assertThat(course1.getTotalSeats(), is(8));
        assertThat(course2.getName(), is("Deutsch"));
        assertThat(course3.getDescription(), is("C1"));
        assertThat(course3.getLocation(), is("Lugano"));
       
    }
}
