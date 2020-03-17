package refactoredMethods.FormTemplate;

public class HtmlStatement extends Statement {
    @Override
    public String footerString(Customer aCostumer) {
        return "<P>You owe <EM>" + String.valueOf(aCostumer.getTotalCharge()) + "</EM><P>\n"
         + "On this rental you earned <EM>" + 
        String.valueOf(aCostumer.getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
    }

    @Override
    public String eachRentalString(Rental each) {
        return each.getMovie().getTitle()+ ": " +
            String.valueOf(each.getCharge()) + "<BR>\n";
    }

    @Override
    public String headerString(Customer aCostumer) {
        return "<H1>Rentals for <EM>" + aCostumer.getName() + "</EM></H1><P>\n";
    }
   

}
