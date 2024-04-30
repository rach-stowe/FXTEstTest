import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import app.CalculatorApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
import model.Operation;
import view.MainController;

@ExtendWith(ApplicationExtension.class)
public class FunctionalTests
{
	Operation [] operations = {
			new Operation(6, " + ", 3, 9),
			new Operation(6, " - ", 3, 3),
			new Operation(6, " * ", 3, 18),
			new Operation(6, " / ", 3, 2)};
	
	@Start
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CalculatorApp.class.getResource("../view/main.fxml"));

		BorderPane view = loader.load();

		MainController controller = loader.getController();
		CalcModel model = new CalcModel();
		controller.setModel(model);

		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	@Test
	public void testOperations(FxRobot robot) 
	{
		checkAdd(robot, "6", "3", "9");
		checkSubtract(robot, "6", "3", "3");	
		checkMultiply(robot, "6", "3", "18");	
		checkDivide(robot, "6", "3", "2");		
		checkOperations(robot);
	}
	
	private void checkAdd(FxRobot robot, String num1, String num2, String expected)
	{
		enterNum(robot, num1, "#num1");
		enterNum(robot, num2, "#num2");
		checkResult(robot, "#add", expected);
	}
	
	private void checkSubtract(FxRobot robot, String num1, String num2, String expected)
	{
		enterNum(robot, num1, "#num1");
		enterNum(robot, num2, "#num2");
		checkResult(robot, "#subtract", expected);
	}
	
	private void checkMultiply(FxRobot robot, String num1, String num2, String expected)
	{
		enterNum(robot, num1, "#num1");
		enterNum(robot, num2, "#num2");
		checkResult(robot, "#multiply", expected);
	}
	
	private void checkDivide(FxRobot robot, String num1, String num2, String expected)
	{
		enterNum(robot, num1, "#num1");
		enterNum(robot, num2, "#num2");
		checkResult(robot, "#divide", expected);
	}
	
	private void enterNum(FxRobot robot, String num, String target)
	{
		robot.clickOn(target);
		robot.write(num);
	}
	
	private void checkResult(FxRobot robot, String operationButton, String expected)
	{
		robot.clickOn(operationButton);
		Assertions.assertThat(robot.lookup("#result").queryAs(Label.class)).hasText(expected);
	}
	
	@SuppressWarnings("unchecked")
	private void checkOperations(FxRobot robot)
	{
		ListView<Operation> ops = (ListView<Operation>) robot.lookup("#operations").queryAll().iterator().next();
		Assertions.assertThat(ops).hasExactlyNumItems(operations.length);
		ObservableList<Operation> oList = ops.getItems();
		for(int i=0; i<operations.length; i++)
	    {
	     Assertions.assertThat(oList.get(i).toString()).isEqualTo(operations[i].toString()); 
	      
	    }
	}
}
