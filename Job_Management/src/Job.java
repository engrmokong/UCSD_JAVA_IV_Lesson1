import java.io.*;


public class Job implements Serializable
{
	private double salary;
	private String nameOfJob;
	
	///Create a constructor for salary and nameOfJob
	
	public Job(double salary, String nameOfJob) {
		
		this.salary = salary;
		this.nameOfJob = nameOfJob;
		}
	
	public void setSalary(double salary) 
	{
		this.salary = salary;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setNameOfjob(String nameOfJob)
	{
		this.nameOfJob = nameOfJob;
	}
	
	public String getNameOfJob()
	{
		return nameOfJob;
	}
}
