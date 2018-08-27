package hw3;

import java.util.Vector;

/**
 * The RequestQueue class implements a queue of Request objects.
 * 
 * @author Samuel Sundararaman 
 * 		e-mail: samuel.sundararaman@stonybrook.edu 
 * 		Stony Brook ID: 111352739
 */
public class RequestQueue {
	
	private Vector<Request> queue; // 
	/**
	 * Creates an instance of the RequestQueue class with no Request objects.
	 */
	public RequestQueue() {
		
		queue = new Vector<Request>();

	}
	
	/**
	 * Adds a Request object to the front of the queue.
	 * @param add The Request object to be added.
	 */
	public void enqueue(Request add) {
		queue.addElement(add);
		
	}
	
	/**
	 * Removes the first Request object in the queue.
	 * @return The first Request object in the queue. 
	 */
	public Request dequeue() {
		if(!queue.isEmpty()) {
			return queue.remove(0);
		}
		else {
			return null;
		}
	}

	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
