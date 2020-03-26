package originalMethods;

public class ExtractSubclassOriginal {

    class JobItem {
        public JobItem (int unitPrice, int quantity, boolean isLabor, Employee employee) {
            _unitPrice = unitPrice;
            _quantity = quantity;
            _isLabor = isLabor;
            _employee = employee;
        }
    
        public int getTotalPrice() {
            return getUnitPrice() * _quantity;
        }
    
        public int getUnitPrice(){
            return (_isLabor) ?
                _employee.getRate():
                    _unitPrice;
        }
    
        public int getQuantity(){
            return _quantity;
        }
    
        public Employee getEmployee() {
            return _employee;
        }
    
        private final int _unitPrice;
        private final int _quantity;
        private final Employee _employee;
        private final boolean _isLabor;
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
    
}
