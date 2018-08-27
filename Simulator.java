package hw3;

import java.util.ArrayList;

/**
 * The Simulator class simulates a building with elevators and uses the classes
 * Request, RequestQueue, Elevator, and BooleanSource.
 * Called from the Analyzer class.
 * 
 * @author Samuel Sundararaman 
 * 		e-mail: samuel.sundararaman@stonybrook.edu 
 * 		Stony Brook ID: 111352739
 */
public class Simulator {
	
	/**
	 * Simulates the handling of Requests by Elevators in a building. 
	 * 
	 * @param probability The probability that a new request is placed during a given time unit.
	 * @param numFloors The number of floors in the building. 
	 * @param numElevators The number of elevators in the building. 
	 * @param time The amount of time units the simulation runs. 
	 */
	public static void simulate(double probability, int numFloors, int numElevators, int time) {
		int numRequests = 0;
		int totalWait = 0;
		double avgWait = 0.0;
		ArrayList<Elevator> elevators = new ArrayList<Elevator>(numElevators);
		RequestQueue requests = new RequestQueue();
		BooleanSource chance = new BooleanSource(probability);
		for (int i = 0; i < numElevators; i++) {
			Elevator newElevator = new Elevator();
			elevators.add(newElevator);
		}
		for (int i = 0; i < time; i++) {
			
			if (chance.requestArrived()) {
				Request newReq = new Request(numFloors);
				newReq.setTime(i + 1);
				requests.enqueue(newReq);
				
			}
			for (int x = 0; x < numElevators; x++) {
				if (elevators.get(x).getRequest() != null && elevators.get(x).getState()!=0) {
						
					if (elevators.get(x).getState() == 1) {
						elevators.get(x).moveSource();
					}
					if (elevators.get(x).getState() == 2) {
						elevators.get(x).moveDestination();
					}
					if (elevators.get(x).getCurrent() == elevators.get(x).getRequest().getSource() && elevators.get(x).getState()==1) {
						elevators.get(x).setState(2);
						totalWait = totalWait + ((i+1)-elevators.get(x).getRequest().getTime());
						numRequests++;
						if(elevators.get(x).getRequest().getSource() == elevators.get(x).getRequest().getDestination()) {
							elevators.get(x).setState(0);
						}
					}
					if (elevators.get(x).getCurrent() == elevators.get(x).getRequest().getDestination()&&
							elevators.get(x).getState()==2) {
						elevators.get(x).setState(0);
						
					}
					
				}
				if (elevators.get(x).getState() == 0) {
					if (!requests.isEmpty()) {
						Request req = requests.dequeue();
						elevators.get(x).setRequest(req);
						elevators.get(x).setState(1);
						if (elevators.get(x).getCurrent() == req.getSource()) {
							elevators.get(x).setState(2);
							elevators.get(x).moveDestination();
							numRequests++;
						} 

					}
				}
				

			}

		}
		avgWait = Math.round(((double) totalWait / numRequests) * 100);
		avgWait = avgWait / 100.0;
		System.out.println("Total Wait Time: " + totalWait);
		System.out.println("Total Requests: " + numRequests);
		System.out.println("Average Wait Time: " + avgWait);

	}

}
