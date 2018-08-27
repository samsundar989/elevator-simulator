package hw3;

import java.util.Scanner;

/**
 * The Analyzer class takes in user input and uses it to run the simulation and print the results.
 * 
 * @author Samuel Sundararaman 
 * 		e-mail: samuel.sundararaman@stonybrook.edu 
 * 		Stony Brook ID: 111352739
 */
public class Analyzer {
	
	private static Scanner scanner; // Scanner object used to take in user input. 
	
	/**
	 * Main method which runs the simulation and takes input from the user. 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		System.out.println("Welcome to the Elevator simulator!");
		System.out.println("");
		System.out.println("If you would like to run the optimal simulator, enter 1. "
				+ "For the regular simulator, enter 0: ");
		int option = scanner.nextInt();
		if(option==1) {
			System.out.println("Running optimal simulator.");
		}
		else {
			System.out.println("Running regular simulator.");
		}
		System.out.println("Please enter the probability of arrival for Requests: ");
		double prob = scanner.nextDouble();
		if(prob<0 || prob>1) {
			System.out.println("Invalid input. Probability must be between 0 and 1. Please restart the program.");
			System.exit(0);
		}
		System.out.println("Please enter the number of floors: ");
		int numFloors = scanner.nextInt();
		if(numFloors<1) {
			System.out.println("Invalid input. Number of floors must be greater than 1. Please restart the program.");
			System.exit(0);
		}
		System.out.println("Please enter the number of elevators: ");
		int numElevators = scanner.nextInt();
		if(numElevators<1) {
			System.out.println("Invalid input. Number of elevators must be greater than 1. Please restart the program.");
			System.exit(0);
		}
		System.out.println("Please enter the length of the simulation (in time units): ");
		int time = scanner.nextInt();
		if(time<1) {
			System.out.println("Invalid input. Time must be greater than 1 time unit. Please restart the program.");
			System.exit(0);
		}
		if(option==1) {
			OptimalSimulator.simulate(prob, numFloors, numElevators, time);
		}
		else {
			Simulator.simulate(prob, numFloors,numElevators, time);
		}
		
		
		
		
	}

}
