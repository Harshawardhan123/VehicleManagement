package com.harsh.first;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

//Car entity 

@XmlRootElement
public class Car 
{
	@XmlTransient
	public int Id;
	@XmlTransient
	public int Year;
	@XmlTransient
	public String Make;
	@XmlTransient
	public String Model;
	
	Car()
	{
		
	}
	
	Car(int Id, int Year, String Make, String Model)
	{
		this.Id = Id;
		this.Year = Year;
		this.Make = Make;
		this.Model = Model;
	}
	
	public int getYear() {
		return Year;
	}
	
	@XmlElement
	public void setYear(int year) {
		Year = year;
	}
	public int getId() {
		return Id;
	}
	
	@XmlElement
	public void setId(int id) {
		Id = id;
	}
	public String getMake() {
		return Make;
	}
	
	@XmlElement
	public void setMake(String make) {
		Make = make;
	}
	public String getModel() {
		return Model;
	}
	
	@XmlElement
	public void setModel(String model) {
		Model = model;
	}
	
}

