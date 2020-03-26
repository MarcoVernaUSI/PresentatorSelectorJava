package originalMethods;

public class RemoveMiddlemanOriginal {
    class Person{
        Department _department;
        
        public Person getManager() {
            return _department.getManager();
        }
    }
    
    class Department{
        private final Person _manager;
    
    
        public Person getManager() {
            return _manager;
        }


        public Department (Person manager) {
            _manager = manager;
        }
    }
}
