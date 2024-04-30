package model;

public class Operation
{
	double num1;
	double num2;
	
	String op;
	double result;
	public Operation(double num1, String op, double num2,double result)
	{
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.op = op;
		this.result = result;
	}
	@Override
	public String toString()
	{
		return num1 + op+ num2+" = "+ result;
	}
	
	
	
	
	
	
}
