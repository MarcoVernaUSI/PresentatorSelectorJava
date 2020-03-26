package refactoredMethods;

public class RemoveMiddlemanRefactored {
    class Person{
        Department _department;
        
        public Department getDepartment() {
            return _department;
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
