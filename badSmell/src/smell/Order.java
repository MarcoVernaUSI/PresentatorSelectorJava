package smell;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 
    /** This method saves the order to the database */
    public void saveOrder()  throws SQLException
    {
        //create connection
        Connection conn = null;
 
        //java.sql.Date date = new java.sql.Date((new java.util.Date()).getTime());
        PreparedStatement orderStatement = null;
        //PreparedStatement getStatement = null;
        String sql = null;
        sql = new StringBuffer().append("INSERT INTO T_ORDER " )
            .append("(AUTHORIZATION_CODE, " )
            .append("SHIPMETHOD_ID, USER_ID, ADDRESS_ID) " )
            .append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ).toString();
        conn = setConnection();
        orderStatement = conn.prepareStatement(sql);
        //set all parameters
       
        //execute statement
        orderStatement.executeUpdate();
    }

	private Connection setConnection() {
		return null;
	}
}
