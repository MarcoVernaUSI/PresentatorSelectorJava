package originalMethods;

public class ReplaceConditionalWithPolymorphismOriginal {
    class Employee {
        private EmployeeType _type;
        
        Employee (int type) {
            setType(type);
        }
        
        public int getType() {
            return _type.getTypeCode();
        }
        
        void setType(int arg) {
            _type = EmployeeType.newType(arg);
            }

        int payAmount() {
            return _type.getSalary();
        }
    }
    
    abstract static class EmployeeType {
        
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
        
        int _monthlySalary;

        static EmployeeType newType(int code) {
            switch (code) {
                case ENGINEER:
                    return new Engineer();
                case SALESMAN:
                    return new Salesman();
                case MANAGER:
                    return new Manager();
                default:
                    throw new IllegalArgumentException("Incorrect Employee Code");
            }
        }
        
        abstract int getTypeCode();
        abstract int getSalary();
    }
    
    static class Engineer extends EmployeeType {
        @Override
        int getTypeCode () {
            return EmployeeType.ENGINEER;
        }

        @Override
        int getSalary() {
            return _monthlySalary;
        }
    }
    
    static class Manager extends EmployeeType {
        int _bonus;
        
        @Override
        int getTypeCode () {
            return EmployeeType.MANAGER;
        }
        @Override
        int getSalary() {
            return _monthlySalary + _bonus;
        }
    }
    
    static class Salesman extends EmployeeType {
        int _commission;
        
        @Override
        int getTypeCode () {
            return EmployeeType.SALESMAN;
        }
        @Override
        int getSalary() {
            return _monthlySalary + _commission;
        }
     }
}
