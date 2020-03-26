package originalMethods;

import java.util.Date;

public class IntroduceForeignMethodOriginal {
    private Date previousEnd;
    
    Date newStart = new Date (previousEnd.getYear(),
        previousEnd.getMonth(), previousEnd.getDate() + 1);
}
