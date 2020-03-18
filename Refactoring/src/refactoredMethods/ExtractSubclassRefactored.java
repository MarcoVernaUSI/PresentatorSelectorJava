package refactoredMethods;

public class ExtractSubclassRefactored {

    class JobItem {
        protected JobItem (int unitPrice, int quantity) {
            _unitPrice = unitPrice;
            _quantity = quantity;
        }
        
        public int getTotalPrice() {
            return getUnitPrice() * _quantity;
        }
    
        public int getUnitPrice(){
            return _unitPrice;
        }
    
        public int getQuantity(){
            return _quantity;
        }
        
        protected boolean isLabor() {
            return false;
            }
    
        private final int _unitPrice;
        private final int _quantity;
    }
    
    
    class Employee{
        public Employee (int rate) {
            _rate = rate;
        }
    
        public int getRate() {
            return _rate;
        }
    
        private final int _rate;
    }
    
    
    class LaborItem extends JobItem{

        public LaborItem(int quantity, Employee employee) {
            super(0, quantity);
            _employee = employee;
        }

        public Employee getEmployee() {
            return _employee;
        }
        
        @Override
        protected boolean isLabor() {
            return true;
            }
        
        @Override
        public int getUnitPrice(){
            return _employee.getRate();
            }
        
        private final Employee _employee;
        
    }
}
