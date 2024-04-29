import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.CalcModel;
import model.Operation;

public class CalculatorTests
{
	CalcModel model;
	
	DoubleProperty num1 = new SimpleDoubleProperty();
	DoubleProperty num2 = new SimpleDoubleProperty();
	DoubleProperty result = new SimpleDoubleProperty();
	
	@BeforeEach
	void setUp() throws Exception
	{
		model = new CalcModel();
		num1.set(6);
		num2.set(3);;
	}

	@Test
	void test()
	{
		testSetNums();
		testAddition();
		testSubtraction();
		testMultiplication();
		testDivision();
		testOperations();
	}

	private void testSetNums()
	{
		model.setNum1(num1);
		model.setNum2(num2);
		assertEquals(model.getNum1().get(), 6);
		assertEquals(model.getNum2().get(), 3);
	}

	private void testAddition()
	{
		model.add();
		assertEquals(model.getResult().get(), 9);
	}

	private void testSubtraction()
	{
		model.subtract();
		assertEquals(model.getResult().get(), 3);
	}

	private void testMultiplication()
	{
		model.multiply();
		assertEquals(model.getResult().get(), 18);
	}

	private void testDivision()
	{
		model.divide();
		assertEquals(model.getResult().get(), 2);
	}
	
	private void testOperations()
	{
		assertEquals(model.getOperations().get(0).toString(), new Operation(6, " + ", 3, 9).toString());
		assertEquals(model.getOperations().get(1).toString(), new Operation(6, " - ", 3, 3).toString());
		assertEquals(model.getOperations().get(2).toString(), new Operation(6, " * ", 3, 18).toString());
		assertEquals(model.getOperations().get(3).toString(), new Operation(6, " / ", 3, 2).toString());
	}
}
