
import java.io.*;

public class Employee implements Serializable
{
	private String name;
	private String surname;
	private Job job;  //this is from Job class
	private int staffNr;
	
	private static final long serialVersionUID = 478921440099314794L;
	
	//creating a constructor
	public Employee(String name, String surname, Job job, int staffNr)
	{
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.staffNr = staffNr;		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setJob(Job job)
	{
		this.job = job;
	}
	
	public Job getJob()
	{
		return job;
	}
	
	public void setStaffNr(int staffNr)
	{
		this.staffNr = staffNr;
	}
	
	public int getStaffNr()
	{
		return staffNr;
	}

	

}
