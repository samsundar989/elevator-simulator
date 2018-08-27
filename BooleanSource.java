 package hw3;

 /**
  * The BooleanSource class implements a BooleanSource which is used to 
  * determine whether a Request has arrived. 
  * 
  * @author Samuel Sundararaman 
  * 		e-mail: samuel.sundararaman@stonybrook.edu 
  * 		Stony Brook ID: 111352739
  */
public class BooleanSource {

	double probability; // The probability of a Request being placed during a given time unit. 
	
	/**
	 * Constructs an instance of the BooleanSource class.
	 * @param p The probability of a Request being placed during a given time unit. 
	 * @exception IllegalArgumentException
	 */
	public BooleanSource(double p) {
		if(p<0.0 || p>1.0) {
			throw new IllegalArgumentException();
		}
		probability = p;
		
	}
	
	/**
	 * Returns a boolean to indicate whether a Request has arrived. 
	 * @return True if a Request has arrived, false if no Request has arrived
	 */
	public boolean requestArrived() {
		return (Math.random()<probability);
	}
}
