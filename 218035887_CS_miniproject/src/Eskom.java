import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author Cebo Sithole 218035887
 * Eskom Problem Solution
 *
 */
public class Eskom extends Application{	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		launch(args);

	}
	

	@Override
	public void start(Stage stage) throws Exception {
		
		MainPane pane = new MainPane(stage);

		Scene scene = new Scene(pane, 800, 600);

		stage.setScene(scene);
		stage.setTitle("The Power Consumption Simulator");
		stage.show();

	}


}
