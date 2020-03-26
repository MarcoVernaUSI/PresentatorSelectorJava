package originalMethods;

public class ReplaceDelegationWIthInheritanceOriginal {
    class Employee {
        Person _person = new Person();
        
        public String getName() {
            return _person.getName();
        }
        
        public void setName(String arg) {
            _person.setName(arg);
        }
        
        @Override
        public String toString () {
            return "Emp: " + _person.getLastName();
        }
        
    }
    
    class Person {
        String _name;
        
        public String getName() {
            return _name;
        }
        
        public void setName(String arg) {
            _name = arg;
        }
        
        public String getLastName() {
            return _name.substring(_name.lastIndexOf(' ')+1);
        }
    }
}
