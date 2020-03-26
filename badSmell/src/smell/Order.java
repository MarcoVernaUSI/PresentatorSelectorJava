package smell;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;
 
public class Order {
 
    private final Vector<LineItem> _lineItems;
 
    public Order(LineItem... lineItems) {
        _lineItems = new Vector<>(Arrays.asList(lineItems));
    }
 
    @Override
    public boolean equals(Object aThat) {
        if ( this == aThat ) return true;
        if ( !(aThat instanceof Order) ) return false;
        Order that = (Order)aThat;
        return this._lineItems.equals(that._lineItems);
    }
 
    // writes this order object to the specified print writer
    public void writeOrder(PrintWriter pw) {
       
        for (LineItem item: _lineItems) {
            item.writeItem(pw);
        }
        pw.println("Order total = " + getTotal());
    }
 
	public int getTotal() {
	    int total = 0;
        
	    for (LineItem item: _lineItems) {
	        total += item.getLineItemTotal();
	    }
	    return total;
    }
}
