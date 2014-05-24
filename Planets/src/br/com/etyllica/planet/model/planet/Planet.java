package br.com.etyllica.planet.model.planet;

public abstract class Planet {

	//Mass in kg
	protected String mass;
	
	//Radius in miles
	protected String radius;
	
	//Distance from the Sun
	protected String distance;
	
	public Planet() {
		super();
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
	
}
