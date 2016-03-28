package com.harsh.first;

import java.util.List;
import java.util.StringTokenizer;
import javax.inject.Singleton;
import java.util.ArrayList;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/hi")
public class Service 
{
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	List<Car> carList=new ArrayList<Car>();
	int index=0;
	
	@GET
	@Path("/get/{Id}")
	@Produces(MediaType.APPLICATION_XML)
	public Car get(@PathParam("Id") int Id)
	{
		Car c1 = new Car();
		int flag = 0;
		
		flag = find(Id);
		
		if(flag == -1)
		{
			return c1;
		}
		else
		{
			
			c1=carList.get(flag);
			return c1;
			
		}
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_XML)
	public List<Car> getAll()
	{
		return(carList);
	}
	
	@POST
	@Path("/getByProperties")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Car> getByProperties(String data)
	{
		List<Car> carList2=new ArrayList<Car>();
		StringTokenizer st = new StringTokenizer(data, "=&");
		st.nextToken();
		int Id      = (Integer.parseInt(st.nextToken()));
		st.nextToken();
		int Year     = (Integer.parseInt(st.nextToken()));st.nextToken();
		String Model = st.nextToken();st.nextToken();
		String Make  = st.nextToken();
		
		carList2=findByProperties(Make);

		return(carList2);
	}
	
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)//_URLENCODED)
	public List<Car> update(String data)//, @Context HttpServletResponse servletResponse) throws IOException
	{
		
		Car c1 = new Car();
		int flag = 0;		
		StringTokenizer st = new StringTokenizer(data, "=&");
		st.nextToken();
		int Id      = (Integer.parseInt(st.nextToken()));
		st.nextToken();
		int Year     = (Integer.parseInt(st.nextToken()));st.nextToken();
		String Model = st.nextToken();st.nextToken();
		String Make  = st.nextToken();		
		flag=find(Id);	
		if(flag == -1)
		{
			//can be used for processing if element not found...
		}
		else
		{
			carList.remove(flag);
		//	System.out.println("found");
			c1.setId(Id);
			c1.setMake(Make);
			c1.setModel(Model);
			c1.setYear(Year);
			carList.add(c1);
		}
		return(carList);      
	 }
	
	@DELETE
	@Path("/delete/{Id}")
	@Produces(MediaType.APPLICATION_XML)
	public String delete(@PathParam("Id") int Id)
	{
		int flag=find(Id);
		if(flag == -1)
		{
			//can be used for processing if element not found...
		}
		else
		{
			carList.remove(flag);
		}
		return "test";
	}
	
	public int find(int Id)
	{
		Car c1=null;
		for(int i=0; i<carList.size(); i++)
		{
			c1=carList.get(i);
			if(Id == c1.getId())
			{
				return (i);
			}
			
		}
		return(-1);
	}
	
	public List<Car> findByProperties(String Make)
	{
		Car c1=new Car();	
		List<Car> carList2=new ArrayList<Car>();		
		index=0;
		for(int i=0; i<carList.size(); i++)
		{
			c1=carList.get(i);
			if(Make.equals(c1.getMake()))
			{
				carList2.add(c1);			
			}
		}
		return(carList2);
	}
	
	@POST
	@Path("/put")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Car> createUser(String data)
	{
		Car c1 = new Car();
		int flag = 0;
		
		StringTokenizer st = new StringTokenizer(data, "=&");
		st.nextToken();
		int Id       = (Integer.parseInt(st.nextToken()));st.nextToken();
		int Year     = (Integer.parseInt(st.nextToken()));st.nextToken();
		String Model = st.nextToken();st.nextToken();
		String Make  = st.nextToken();
		flag=find(Id);
		
		if(flag == -1)
		{
			c1.setId(Id);
			c1.setMake(Make);
			c1.setModel(Model);
			c1.setYear(Year);
			carList.add(c1);
		}
		else
		{
			//can be used for processing if element found...(Duplicate)
		}
		
	    return(carList);      
	 }
}
