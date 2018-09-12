/**
 * @AnitaLi (al367)
 * 
 * CS 201 Fall 2018
 * P1: NBody 
 * 
 * Due: Sept. 17
 */

public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	private final double G = 6.67*1e-11;
	
	//constructor in which values for each instance variable is given as a parameter
	/**
	 * constructor in which values for each instance variable is given as a parameter
	 */
	public Body (double xPos, double yPos, double xVel, double yVel, double mass, String name) {
		myXPos = xPos;
		myYPos = yPos;
		myXVel = xVel;
		myYVel = yVel;
		myMass = mass;
		myFileName = name;
	}
	
	//constructor that creates a Body by copying another
	/**
	 * constructor that creates a Body by copying another
	 */
	public Body (Body copy) {
		myXPos = copy.getX();
		myYPos = copy.getY();
		myXVel = copy.getXVel();
		myYVel = copy.getYVel();
		myMass = copy.getMass();
		myFileName = copy.getName();
	}
	
	//returns x position of body
	/**
	 * returns x position of body (a double)
	 */
	public double getX() {
		return myXPos;
	}
	
	//returns y position of body
	/**
	 * returns y position of body (a double)
	 */
	public double getY() {
		return myYPos;
	}
	
	//returns x velocity of body
	/**
	 * returns x velocity of body (a double)
	 */
	public double getXVel() {
		return myXVel;
	}
	
	//returns y velocity of body
	/**
	 * returns y velocity of body (a double)
	 */
	public double getYVel() {
		return myYVel;
	}

	//returns mass of body
	/**
	 * returns mass of body (a double)
	 */
	public double getMass() {
		return myMass;
	}
	
	//returns file name of body
	/**
	 * returns name of body (a String)
	 */
	public String getName() {
		return myFileName;
	}
	
	//returns the distance between two Body objects using the standard distance formula
	/**
	 * returns the distance between two Body objects using the standard distance formula
	 */
	public double calcDistance(Body b) {
		double changeX = myXPos - b.getX();
		double changeY = myYPos - b.getY();
		double distance = Math.sqrt((Math.pow(changeX, 2)) + (Math.pow(changeY, 2)));
		return distance;
	}
	
	//calculates and returns the force exerted on this body by the body given in parameter
	//       m1m2
	// F = G------
	//       r^2
	/**
	 * //calculates and returns the force exerted on this body by the body given in parameter
	 *       m1m2
	 * F = G------
	 *       r^2
	 */
	public double calcForceExertedBy(Body p) {
		double force = G * ((myMass * p.getMass()) / (Math.pow(this.calcDistance(p), 2)));
		return force;
	}
	
	//calculate and return the force exerted in the X direction 
	/**
	 * calculate and return the force exerted in the X direction 
	 */
	public double calcForceExertedByX(Body p) {
		double overallForce = this.calcForceExertedBy(p);
		double changeX = p.getX() - myXPos;
		double distance = this.calcDistance(p);
		double forceX = overallForce * (changeX / distance);
		return forceX;
	}
	
	//calculate and return the force exerted in the Y direction 
	/**
	 * calculate and return the force exerted in the Y direction 
	 */
	public double calcForceExertedByY(Body p) {
		double overallForce = this.calcForceExertedBy(p);
		double changeY = p.getY() - myYPos;
		double distance = this.calcDistance(p);
		double forceY = overallForce * (changeY / distance);
		return forceY;
	}
	
	//returns the total/net force exerted on this body (in X) by all the bodies in the array parameter
	/**
	 * returns the total/net force exerted on this body (in X direction) by all the bodies in the array parameter
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double netForceX = 0.0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}
	
	//returns the total/net force exerted on this body (in Y) by all the bodies in the array parameter
	/**
	 * returns the total/net force exerted on this body (in X direction) by all the bodies in the array parameter
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double netForceY = 0.0;
		for (Body b : bodies) {
			if (! this.equals(b)) {
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}
	
	//this mutator updates the instance variables of Body with the parameters 
	/**
	 * updates the instance variables of Body with the parameters (updates the position and velocities based on interval/dT and the x and y forces)
	 */
	public void update(double deltaT, double xForce, double yForce) {
		double accelX = xForce / myMass;
		double accelY = yForce / myMass;
		double nXVel = myXVel + (deltaT * accelX);
		double nYVel = myYVel + (deltaT * accelY);
		double nX = myXPos + (deltaT * nXVel);
		double nY = myYPos + (deltaT * nYVel);
		
		myXPos = nX;
		myYPos = nY;
		myXVel = nXVel;
		myYVel = nYVel;
	}
	
	//drawing the body
	/**
	 * draws the body to be viewed
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+ myFileName);
	}
}