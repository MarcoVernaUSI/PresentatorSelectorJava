package OriginalMethods;

public class ReplaceTypeCodeWithStateStrategyOriginal {

    class Employee {
        private final int _type;
        private int _monthlySalary;
        private int _commission;
        private int _bonus;
        static final int ENGINEER = 0;
        static final int SALESMAN = 1;
        static final int MANAGER = 2;
        Employee (int type) {
        _type = type;
        }
        
        int payAmount() {
            switch (_type) {
            case ENGINEER:
            return _monthlySalary;
            case SALESMAN:
            return _monthlySalary + _commission;
            case MANAGER:
            return _monthlySalary + _bonus;
            default:
            throw new RuntimeException("Incorrect Employee");
            }
            }
    }
}
