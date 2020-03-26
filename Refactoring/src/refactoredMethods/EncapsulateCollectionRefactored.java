package refactoredMethods;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EncapsulateCollectionRefactored {
    class Course{
        private final String _name;
        private final boolean _advanced;
        
        public Course (String name, boolean isAdvanced) {
            _name = name;
            _advanced = isAdvanced;
        }
    
        public boolean isAdvanced() {
            return _advanced;
        }
        
        public String getName() {
            return _name;
        }
    }
    
    class Person{
        private final Set _courses =  new HashSet();
    
        public Set getCourses() {
            return Collections.unmodifiableSet(_courses);
        }
    
        public void initialiyeCourses(Set arg) {
            assertTrue(_courses.isEmpty());
            _courses.addAll(arg);
        }
        
        public void addCourse (Course arg) {
            _courses.add(arg);
        }
        
        public void removeCourse (Course arg) {
            _courses.remove(arg);
        }
    }
}
