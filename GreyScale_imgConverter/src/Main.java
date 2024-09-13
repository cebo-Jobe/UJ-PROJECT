
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ClientPane root = new ClientPane();
		Scene scene = new Scene(root,700,550);
		primaryStage.setTitle(STYLESHEET_CASPIAN);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
