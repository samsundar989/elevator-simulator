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
public class OptimalSimulator {

	/**
	 * A more optimal simulator of Elevators in a building.
	 * 
	 * @param probability The probability a new request will arrive in a given time unit. 
	 * @param numFloors The number of floors in the building. 
	 * @param numElevators The number of elevators in the building. 
	 * @param time The amount of time units the simulator runs. 
	 */
	public static void simulate(double probability, int numFloors, int numElevators, int time) {
		int numRequests = 0;
		int totalWait = 0;
		double avgWait = 0.0;
		int floor;
		ArrayList<Elevator> elevators = new ArrayList<Elevator>(numElevators);
		boolean[] direction = new boolean[numElevators]; //true is up, false is down
		RequestQueue requests = new RequestQueue();
		BooleanSource chance = new BooleanSource(probability);
		ArrayList<ArrayList<Request>> floors = new ArrayList<ArrayList<Request>>(numFloors);
		ArrayList<ArrayList<Request>> addOn = new ArrayList<ArrayList<Request>>(numElevators);
		for(int i=0;i<numFloors;i++) { // Creates a list of Requests for each floor
			ArrayList<Request> listReq = new ArrayList<Request>();
			floors.add(i,listReq);
		}
		for (int i = 0; i < numElevators; i++) {// Creates elevators and list of requests for each elevator
			ArrayList<Request> toAdd = new ArrayList<Request>();
			addOn.add(toAdd);
			Elevator newElevator = new Elevator();
			elevators.add(newElevator);
			
		}
		for (int i = 0; i < time; i++) {
	
			if (chance.requestArrived()) {
				Request newReq = new Request(numFloors);
				newReq.setTime(i + 1);
				requests.enqueue(newReq);
				floors.get(newReq.getSource()-1).add(newReq);
				
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
					// Checks to see if requests on current floor have destinations in the direction of current elevator 
					// If true, adds request to individual elevator's list of requests
					floor = elevators.get(x).getCurrent();
					for(int z=0;z<=floors.get(floor).size()-1;z++) {
						if(floors.get(floor).get(z).getDestination()>floor && direction[x]==true) {
							addOn.get(x).add(floors.get(floor).get(z));
							numRequests++;
							floors.get(floor).remove(z);
						}
						if(floors.get(floor).get(z).getDestination()<floor && direction[x] == false) {
							addOn.get(x).add(floors.get(floor).get(z));
							numRequests++;
							floors.get(floor).remove(z);
						}
						
					}
					//  Checks individual elevator's list of requests to see if extra requests have been handled
					// if handled, removes from the list
					for(int a=0;a<addOn.get(x).size();a++) {
						if(elevators.get(x).getCurrent()==addOn.get(x).get(a).getDestination()) {
							Request achieved = addOn.get(x).get(a);
							addOn.get(x).remove(achieved);
						}
					}
					
				}
				if (elevators.get(x).getState() == 0) {
					if (!requests.isEmpty()) {
						Request req = requests.dequeue();
						if(addOn.get(x).contains(req)) {
							while(addOn.get(x).contains(req)) {
								req = requests.dequeue();
							}
						}
						floors.get(req.getSource()-1).remove(req);
						elevators.get(x).setRequest(req);
						elevators.get(x).setState(1);
						if (elevators.get(x).getCurrent() == req.getSource()) {
							elevators.get(x).setState(2);
							direction[x] = elevators.get(x).moveDestination();
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
