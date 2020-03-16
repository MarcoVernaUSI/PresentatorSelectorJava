package smell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class databaseMapper {

    /** This method saves the order to the database */
    public void saveOrder()  throws SQLException
    {
        //create connection
        Connection conn = setConnection();
        String sql =    "INSERT INTO T_ORDER " +
                        "(AUTHORIZATION_CODE, " +
                        "SHIPMETHOD_ID, USER_ID, ADDRESS_ID) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement orderStatement = conn.prepareStatement(sql);
        //set all parameters
       
        //execute statement
        orderStatement.executeUpdate();
    }

    private Connection setConnection() {
        return null;
    }

}
