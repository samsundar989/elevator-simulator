package hw3;

/**
 * The Elevator class implements the methods and variables that define an 
 * elevator within a building. 
 * 
 * @author Samuel Sundararaman 
 * 		e-mail: samuel.sundararaman@stonybrook.edu 
 * 		Stony Brook ID: 111352739
 */
public class Elevator {
	
	private int currentFloor; // The current floor the elevator is on. 
	private int elevatorState; // The current state of the elevator: 0 = IDLE, 1 = TO_SOURCE, 2 = TO_DESTINATION.
	private Request req; // The Request object that is currently assigned to this elevator. 
	
	/**
	 * Constructs and instance of the Elevator object that is 
	 * IDLE, has no Request, and is located on the 1st floor. 
	 */
	public Elevator() {
		req = null;
		elevatorState =0;
		currentFloor = 1;
		
	}
	
	public int getCurrent() {
		return currentFloor;
	}
	
	public int getState() {
		return elevatorState;
	}
	
	public Request getRequest() {
		return req;
	}
	
	public void setCurrent(int curr) {
		currentFloor = curr;
	}
	
	public void setState(int state) {
		elevatorState = state;
	}
	
	/**
	 * Moves the elevator towards the source floor. 
	 */
	public boolean moveSource() {
		if(req.getSource()>this.getCurrent()) {
			currentFloor++;
			return true;
		}
		else {
			currentFloor--;
			return false;
		}
	}
	
	/**
	 * Moves the elevator towards the destination floor. 
	 */
	public boolean moveDestination() {
		if(req.getDestination()>this.getCurrent()) {
			currentFloor++;
			return true;
		}
		else {
			currentFloor--;
			return false;
		}
	}
	
	public void setRequest(Request newReq) {
		req = newReq;
	}

}
