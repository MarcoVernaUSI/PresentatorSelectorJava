package refactoredMethods;

public class ReplaceTpeCodeWithSubclassRefactored {
    static abstract class Employee{
        protected final int _type;
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
    
        abstract int getType();
        
        static Employee create(int type) {
            switch (type) {
            case ENGINEER:
                return new Engineer();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
        default:
        throw new IllegalArgumentException("Incorrect type code value");}}
            
        private Employee (int type) {
            _type = type;
        }
    }
    
    
    static class Engineer extends Employee {
        public Engineer() {
            super(0);
        }

        @Override
        int getType() {
            return Employee.ENGINEER;
        }
    }
    

    static class Salesman extends Employee {
        public Salesman() {
            super(1);
        }

        @Override
        int getType() {
            return Employee.SALESMAN;
        }
    }
    
    

    static class Manager extends Employee {
        public Manager() {
            super(0);
        }

        @Override
        int getType() {
            return Employee.MANAGER;
        }
    }
}
