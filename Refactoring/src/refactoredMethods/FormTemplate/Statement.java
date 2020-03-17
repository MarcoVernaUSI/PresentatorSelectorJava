package refactoredMethods.FormTemplate;

import java.util.Enumeration;

public abstract class Statement {

    public String value(Customer aCustomer) {
        Enumeration rentals = aCustomer.getRentals();
        String result = headerString(aCustomer);
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            
            //show figures for this rental
            result += eachRentalString(each);
        }
        //add footer lines
        result += footerString(aCustomer);
        return result;
    }

    abstract String footerString(Customer aCustomer);

    abstract String eachRentalString(Rental each);

    abstract String headerString(Customer aCustomer);

}
