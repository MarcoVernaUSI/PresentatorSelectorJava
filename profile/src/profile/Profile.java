package profile;

public class Profile {


	public int sumTo(int limit) {
	
		int sum = 0;
		Calc calc = new Calc();
		
		for (int value = 0; value <= limit; value++) {
			sum = calc.accumulate(sum, value);
			// Before was
			// sum = new Calc().accumulate(sum, value);
		}
		
		return sum;
	}
}
