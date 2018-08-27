package hw3;

/**
 * The Request class simulates implements a request object which 
 * defines an elevator request to go from the source floor to the destination floor. 
 * 
 * @author Samuel Sundararaman 
 * 		e-mail: samuel.sundararaman@stonybrook.edu 
 * 		Stony Brook ID: 111352739
 */
public class Request {
	
	private int sourceFloor; // The floor which the passenger is currently located at. 
	private int destinationFloor; // The floor which the passenger must be taken to.
	private int timeEntered; // The time unit in which the request was placed. 
	
	/**
	 * Creates an instance of the Request class. 
	 * @param numFloors The number of floors in the building.
	 */
	public Request(int numFloors) {
		
		sourceFloor = (int)((Math.random()*numFloors)+1);
		destinationFloor = (int)((Math.random()*numFloors)+1);
		
	}
	
	public int getSource() {
		return sourceFloor;
	}
	
	public int getDestination() {
		return destinationFloor;
	}
	
	public int getTime() {
		return timeEntered;
	}
	
	public void setSource(int floor) {
		sourceFloor = floor;
	}
	
	public void setDestination(int floor) {
		destinationFloor = floor;
	}
	
	public void setTime(int time) {
		timeEntered = time;
	}

}
