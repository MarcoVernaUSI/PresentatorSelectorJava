package refactoredMethods.FormTemplate;

public class TextStatement extends Statement {
    public String footerString(Customer aCustomer) {
        return "Amount owed is " + String.valueOf( aCustomer.getTotalCharge()) +"\n"
        + "You earned " + String.valueOf( aCustomer.getTotalFrequentRenterPoints()) +
        " frequent renter points";
    }

    public String eachRentalString(Rental each) {
        return "\t" + each.getMovie().getTitle()+ "\t" +
            String.valueOf(each.getCharge()) + "\n";
    }

    public String headerString(Customer aCustomer) {
        return "Rental Record for " +  aCustomer.getName() + "\n";
    }

}
