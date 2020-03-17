package refactoredMethods;


import java.util.Enumeration;
import java.util.Vector;

public class ExtractMethodRefactored {
    private final Vector _orders = new Vector();
    private String _name;
    
    
    void printOwing(double previousAmount) {
        printBanner();
        double outstanding = getOutstanding(previousAmount * 1.2);
        printDetails(outstanding);
        }

    
    public double getOutstanding(double initialValue) {
        double result = initialValue;
        Enumeration e = _orders.elements();
        while (e.hasMoreElements()) {
            Order each = (Order) e.nextElement();
            result += each.getAmount();
        }
        return result;
    }

    
    public void printDetails(double outstanding) {
        //print details
        System.out.println ("name:" + _name);
        System.out.println ("amount" + outstanding);
    }

    
    public void printBanner() {
        // print banner
        System.out.println ("**************************");
        System.out.println ("***** Customer Owes ******");
        System.out.println ("**************************");
    }

}

class Order {
    private int _amount;

    public double getAmount() {
        return _amount;
    }

}
