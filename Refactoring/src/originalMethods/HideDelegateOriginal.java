package originalMethods;

public class HideDelegateOriginal {
    class Person {
        Department _department;
        
        public Department getDepartment() {
            return _department;
        }
        
        public void setDepartment(Department arg) {
            _department = arg;
        }
    }
        
    class Department {
        private String _chargeCode;
        private final Person _manager;
        
        public Department (Person manager) {
            _manager = manager;
        }
        
        public Person getManager() {
            return _manager;
        }
        
        public String getChargeCode() {
            return _chargeCode;
        }
    }
}
