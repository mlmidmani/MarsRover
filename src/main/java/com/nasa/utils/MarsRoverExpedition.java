package com.nasa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Mars expedition main class
 * @author mohammed lmidmani
 *
 */
public class MarsRoverExpedition {

	/**
	 * plateau's height
	 */
	private int plateauHeight;
	/**
	 * plateau(s width
	 */
	private int plateauWidth;
	/**
	 * list of rovers
	 */
	List<Rover> rovers = new ArrayList<Rover>();
	/**
	 * getter for plateau's height
	 * @return plateau's height
	 */
	public int getPlateauHeight() {
		return plateauHeight;
	}
	/**
	 * getter for plateau's width
	 * @return plateau's width
	 */
	public int getPlateauWidth() {
		return plateauWidth;
	}

	/**
	 * runs the expedition
	 */
	public void runExpedition() {
		// create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);
	    //outputs welcome text
	    outputWellcomeText();
	    //  prompt for the plateau's size
	    System.out.print(Messages.getString("MarsRoverExpedition.0")); 
	    boolean plateauSizeOK = false;
	    //while not entring correct size
	    while(!plateauSizeOK) {
		    // get the plateau's size as a String
		    String plateauS = scanner.nextLine();
		    
		    //parse the string for effective height and width
		    try {
		    	parsePlateauSize(plateauS);
		    	//move to the next step
		    	plateauSizeOK  =true;
		    } catch(IllegalArgumentException e) {
		    	System.out.println(e.getMessage());
		    	System.out.print(Messages.getString("MarsRoverExpedition.0")); 
		    }
	    }
	    
	    System.out.println(Messages.getString("MarsRoverExpedition.1")); 
	    //take on rovers coordinates and instruction until user enters empty rover coordinates
	    while (true) {
	    	boolean roverCoordinatesOK = false;
	    	boolean lastRover = false;
	    	Rover rover = null;
		    //Retry taking rover coordinates until correct coordinates or empty value 
	    	while(!roverCoordinatesOK)
	    	{
	    		//  prompt for the next rover landing location
			    System.out.print(Messages.getString("MarsRoverExpedition.2")+(rovers.size()+1)+Messages.getString("MarsRoverExpedition.3"));  //$NON-NLS-2$
			    // get the rover's landing as a String
			    String roverLanding = scanner.nextLine();
			    //parse the string for effective landing coordinates and direction
			    try {
			    	if(roverLanding.isEmpty()) {
			    		lastRover=true;
			    		break;
			    	}
			    	rover = parseRoverLandingCoordinates(roverLanding);
			    	rovers.add(rover);
			    	//move on to the next step
			    	roverCoordinatesOK = true;
			    } catch(IllegalArgumentException e) {
			    	System.out.println(e.getMessage());
			    }
	    	}
	    	//stop requesting rover data
	    	if(lastRover) {
	    		break;
	    	}
	    	boolean roverInstructionsOK = false;
	    	//While not having correct rover instructions
	    	while(!roverInstructionsOK) {
	    		//  prompt for the rover's instructions
			    System.out.print(Messages.getString("MarsRoverExpedition.4")+rovers.size()+Messages.getString("MarsRoverExpedition.5"));  //$NON-NLS-2$
			    // get the rover's instructions as a String
			    String roverInstructions = scanner.nextLine();
			    //parse the string for effective instructions
			    try {
			    	List<InstructionEnum> instructions = parseRoverInstructions(roverInstructions);
			    	rover.move(instructions);
			    	//move to the next step
			    	roverInstructionsOK = true;
			    } catch(IllegalArgumentException e) {
			    	System.out.println(e.getMessage());
			    }
	    	}
	    }
	    scanner.close();
	    printRoversCoordinates();
	}
	
	/**
	 * Output welkome text
	 */
	private void outputWellcomeText() {
	    System.out.println(Messages.getString("MarsRoverExpedition.6")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.7")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.8")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.9")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.10")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.11")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.12")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.13")); 

	}
	/**
	 * prints results
	 */
	private void printRoversCoordinates() {
		System.out.println(Messages.getString("MarsRoverExpedition.14")); 
		System.out.println(Messages.getString("MarsRoverExpedition.15")); 
	    System.out.println(Messages.getString("MarsRoverExpedition.16")); 
		System.out.println(Messages.getString("MarsRoverExpedition.17")); 
		System.out.println(Messages.getString("MarsRoverExpedition.18")); 
		System.out.println(Messages.getString("MarsRoverExpedition.19")); 
		System.out.println(Messages.getString("MarsRoverExpedition.20")); 
		System.out.println(Messages.getString("MarsRoverExpedition.21")); 
			     
		 for(int i=0;i<rovers.size();i++) {
			Rover rover = rovers.get(i);
			System.out.println(Messages.getString("MarsRoverExpedition.22")+(i+1)+Messages.getString("MarsRoverExpedition.23")+rover.getX()+Messages.getString("MarsRoverExpedition.24")+rover.getY()+Messages.getString("MarsRoverExpedition.25")+rover.getDirection());  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		
	}

	/**
	 * Verify entered values for plateau's size
	 * @param plateauS entered string
	 */
	private void parsePlateauSize(String plateauS) {
		final String errorMessage = new String(Messages.getString("MarsRoverExpedition.26")); 
		//split string by space
		String[] coordinates = plateauS.split(Messages.getString("MarsRoverExpedition.27")); 
		//throw exception if the number of arguments is not correct
		if(coordinates.length!=2) {
			throw new IllegalArgumentException(errorMessage);
		}
		//parse enetred values correctness
		try {
			plateauWidth = Integer.parseInt(coordinates[0]);
			plateauHeight = Integer.parseInt(coordinates[1]);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	/**
	 * Verify entered values for rovers's instructions
	 * @param roverInstructions entered string
	 * @return list of instructions
	 */
	private List<InstructionEnum> parseRoverInstructions(String roverInstructions) {
		final String errorMessage = new String(Messages.getString("MarsRoverExpedition.28")); 
		//transform the string to a list of separate characters
		char[] roverInstructionsAsCharArray = roverInstructions.toCharArray();
		List<InstructionEnum> instructions = new ArrayList<InstructionEnum>();
		//Verify the correctness of each entered character
		try {
			for(int i=0;i<roverInstructionsAsCharArray.length;i++) {
				instructions.add(InstructionEnum.valueOf(String.valueOf(roverInstructionsAsCharArray[i])));
			}
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException(errorMessage);
		}
		return instructions;
	}
	/**
	 * Verify entered values for rovers's location
	 * @param roverLanding entered string
	 * @return the rover instance
	 */
	private Rover parseRoverLandingCoordinates(String roverLanding) {
		Rover rover = new Rover(MarsRoverExpedition.this);
		final String errorMessage = new String(Messages.getString("MarsRoverExpedition.29")); 
		//Split entered value by spaces
		String[] coordinates = roverLanding.split(Messages.getString("MarsRoverExpedition.30")); 
		//throw exception when invalid number of arguments
		if(coordinates.length!=3) {
			throw new IllegalArgumentException(errorMessage);
		}
		//Verify the correctness of entered values
		try {
			rover.setX(Integer.parseInt(coordinates[0]));
			rover.setY(Integer.parseInt(coordinates[1]));
			rover.setDirection(DirectionEnum.valueOf(coordinates[2]));
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException(errorMessage);
		}
		return rover;
	}

	
	public static void main(String[] args) {
		MarsRoverExpedition marsRoverExpedition = new MarsRoverExpedition();
		//launch the expedition
		marsRoverExpedition.runExpedition();
	}
}

