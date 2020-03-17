package refactoredMethods.FormTemplate;

import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private final String _name;
    private final Vector _rentals = new Vector();
    
    public Customer (String name){
        _name = name;
    }
    
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }
    
    public Enumeration getRentals() {
        return _rentals.elements();    
    }
    
    
    public String getName (){
        return _name;
    };

    public String statement() {
        return new TextStatement().value(this);
    }

  
    
    public String htmlStatement() {
        return new HtmlStatement().value(this);
        }


    
    
    public double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }
    
    public int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

   
}
