import acsse.csc2a.model.CatchComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CatchComponent catchComponent = new CatchComponent();
        StackPane root = new StackPane();
        root.getChildren().add(catchComponent);
        
        Scene scene = new Scene(root, 250, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Play Catch");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
