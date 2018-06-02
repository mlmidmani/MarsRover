package com.nasa.utils;

import java.util.List;

/**
 * Rover class
 * @author mohammed lmidmani
 */
class Rover{
	/**
	 * The x coordinate
	 */
	private int x;
	/**
	 * The y coordinate
	 */
	private int y;
	/**
	 * The direction
	 */
	private DirectionEnum direction;
	/**
	 * Expedition instance
	 */
	private MarsRoverExpedition expedition;
	/**
	 * Constructor
	 * @param expedition mars expedition instance
	 */
	public Rover(MarsRoverExpedition expedition) {
		this.expedition = expedition;
	}
	/**
	 * getter for x coordinate
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * Moves the rover following a set of instruction
	 * @param instructions the set of instruction
	 */
	public void move(List<InstructionEnum> instructions) {
		for(InstructionEnum instruction: instructions) {
			switch(instruction) {
			case L: this.turnLeft(); break;
			case R: this.turnRight(); break;
			case M: this.moveForward(); break;
			default: break;
			}
		}
	}
	
	/**
	 * rover will not move if it is already at the plateau's edge
	 */
	private void moveForward() {
		switch(this.getDirection()) {
		case N:this.setY(this.getY()==expedition.getPlateauHeight()?this.getY():this.getY()+1);break;
		case E:this.setX(this.getX()==expedition.getPlateauWidth()?this.getX():this.getX()+1);break;
		case S:this.setY(this.getY()==0?0:this.getY()-1);break;
		case W:this.setX(this.getX()==0?0:this.getX()-1);break;
		default:break;
		}
	}
	/**
	 * turn left
	 */
	private void turnLeft() {
		switch(this.getDirection()) {
		case N:this.setDirection(DirectionEnum.W);break;
		case E:this.setDirection(DirectionEnum.N);break;
		case S:this.setDirection(DirectionEnum.E);break;
		case W:this.setDirection(DirectionEnum.S);break;
		default:break;
		}
	}
	/**
	 * turn right
	 */
	private void turnRight() {
		switch(this.getDirection()) {
		case N:this.setDirection(DirectionEnum.E);break;
		case E:this.setDirection(DirectionEnum.S);break;
		case S:this.setDirection(DirectionEnum.W);break;
		case W:this.setDirection(DirectionEnum.N);break;
		default:break;
		}
	}

	/**
	 * setter for x coordinate
	 * @param x new value
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * gettter for y coordinate
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * setter for y coordinate
	 * @param y new value
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * getter for direction
	 * @return direction
	 */
	public DirectionEnum getDirection() {
		return direction;
	}
	/**
	 * setter for direction
	 * @param direction new value
	 */
	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}
}
