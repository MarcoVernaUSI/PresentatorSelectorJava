package refactoredMethods.FormTemplate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class statementTest {
    Customer customer1;
    Customer customer2;
    Customer customer3;

    @Before
    public void SetUp() {
        Movie movie1 = new Movie("Die Hard", 0);
        Movie movie2 = new Movie("Die Hard 2", 0);
        Movie movie3 = new Movie("Fast and furios", 1);
        Movie movie4 = new Movie("Alice in Wonderland", 2);
        
        customer1 = new Customer("Bob");
        customer2 = new Customer("George");
        customer3 = new Customer("Edward");
        
        customer1.addRental(new Rental(movie1, 10));
        customer1.addRental(new Rental(movie2, 10));
        
        customer2.addRental(new Rental(movie3, 5));
        customer2.addRental(new Rental(movie4, 10));
        
        customer3.addRental(new Rental(movie1, 5));
        customer3.addRental(new Rental(movie3, 5));
        customer3.addRental(new Rental(movie4, 10));
    }
     
    @Test
    public void testStatement() {
        
        String statement1 = customer1.statement();
        String statement2 = customer2.statement();
        String statement3 = customer3.statement();
        

        assertEquals("Rental Record for Bob\n" + 
            "\tDie Hard\t14.0\n" + 
            "\tDie Hard 2\t14.0\n" + 
            "Amount owed is 28.0\n" + 
            "You earned 2 frequent renter points", statement1);
        assertEquals("Rental Record for George\n" + 
            "\tFast and furios\t15.0\n" + 
            "\tAlice in Wonderland\t12.0\n" + 
            "Amount owed is 27.0\n" + 
            "You earned 3 frequent renter points", statement2);
        assertEquals("Rental Record for Edward\n" + 
            "\tDie Hard\t6.5\n" + 
            "\tFast and furios\t15.0\n" + 
            "\tAlice in Wonderland\t12.0\n" + 
            "Amount owed is 33.5\n" + 
            "You earned 4 frequent renter points", statement3);
    }

    @Test
    public void testHtmlStatement() {
        String htmlStatement1 = customer1.htmlStatement();
        String htmlStatement2 = customer2.htmlStatement();
        String htmlStatement3 = customer3.htmlStatement();
        
        assertEquals("<H1>Rentals for <EM>Bob</EM></H1><P>\n" + 
            "Die Hard: 14.0<BR>\n" + 
            "Die Hard 2: 14.0<BR>\n" + 
            "<P>You owe <EM>28.0</EM><P>\n" + 
            "On this rental you earned <EM>2</EM> frequent renter points<P>", htmlStatement1);
        assertEquals("<H1>Rentals for <EM>George</EM></H1><P>\n" + 
            "Fast and furios: 15.0<BR>\n" + 
            "Alice in Wonderland: 12.0<BR>\n" + 
            "<P>You owe <EM>27.0</EM><P>\n" + 
            "On this rental you earned <EM>3</EM> frequent renter points<P>", htmlStatement2);
        assertEquals("<H1>Rentals for <EM>Edward</EM></H1><P>\n" + 
            "Die Hard: 6.5<BR>\n" + 
            "Fast and furios: 15.0<BR>\n" + 
            "Alice in Wonderland: 12.0<BR>\n" + 
            "<P>You owe <EM>33.5</EM><P>\n" + 
            "On this rental you earned <EM>4</EM> frequent renter points<P>", htmlStatement3);
    }
}
