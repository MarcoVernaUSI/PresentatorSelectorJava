package refactoredMethods;

import java.util.Date;

public class IntroduceForeignMethodRefactored {
    private Date previousEnd;
    
    Date newStart = nextDay(previousEnd);

    private static Date nextDay(Date arg) {    
        return new Date (arg.getYear(),arg.getMonth(), arg.getDate() +1);
    }
}