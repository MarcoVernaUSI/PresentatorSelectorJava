package originalMethods;

import java.util.Set;

public class EncapsulateCollectionOriginal {
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
        private Set _courses;
    
        public Set getCourses() {
            return _courses;
        }
    
        public void setCourses(Set arg) {
            _courses = arg;
        }
    }
}
