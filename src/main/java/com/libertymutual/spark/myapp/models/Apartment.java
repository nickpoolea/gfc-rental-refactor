package com.libertymutual.spark.myapp.models;

import java.util.ArrayList;
import java.util.List;

public class Apartment {

	private int id;
	private int rent;
	private int numberOfBedrooms;
	private double numberOfBathrooms;
	private int squareFootage;
	private String address;
	private String city;
	private String state;
	private int zip;

	public Apartment() {
	}

	public Apartment(int id, int rent, int numberOfBedrooms, double numberOfBathrooms, int squareFootage,
			String address, String city, String state, int zip) {
		this.id = id;
		this.rent = rent;
		this.numberOfBedrooms = numberOfBedrooms;
		this.numberOfBathrooms = numberOfBathrooms;
		this.squareFootage = squareFootage;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public double getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public static List<Apartment> findAll() {
		List<Apartment> apartments = new ArrayList<Apartment>();
		apartments.add(new Apartment(1, 2000, 3, 1, 1000, "123 Main", "Honolulu", "Hi", 96825));
		apartments.add(new Apartment(2, 1500, 2, 2, 750, "123 Koko Isle", "Honolulu", "Hi", 96825));
		return apartments;
	}

	public static Apartment findById(int id) {
		if (id == 1 || id == 2) {
			return findAll().get(id - 1);
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setNumberOfBathrooms(double numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

}
