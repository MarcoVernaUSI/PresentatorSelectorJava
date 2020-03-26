package originalMethods;

public class IntroduceAssertionOriginal {
    class Employee{
        private double NULL_EXPENSE;
        private final double _expenseLimit = NULL_EXPENSE;
        private Project _primaryProject;
        
        double getExpenseLimit() {
            return (_expenseLimit != NULL_EXPENSE) ? 
                _expenseLimit: _primaryProject.getMemberExpenseLimit();
        }
        boolean withinLimit (double expenseAmount) {
            return (expenseAmount <= getExpenseLimit());
        }    
    }
    
    
    class Project {
        public double getMemberExpenseLimit() {
            return 0;
        }
    }
}
