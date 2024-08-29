package model;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI extends BorderPane {
    
    private Graph<Location> graphDurban, graphCapeTown, graphJohannesburg;

    public GUI(Stage primaryStage) {
        setGUI(primaryStage);
    }

    private void setGUI(Stage stage) {
    	 Button proceedButton = new Button("Proceed");

    	    // Create layouts for the scenes
    	    BorderPane firstSceneLayout = new BorderPane();
    	    VBox mainLayout = new VBox(20);

    	    // Add townships to the main layout
    	    VBox durbanBox = createTownshipBox("Durban", "Inanda", "./data/locations.txt");
    	    VBox capeTownBox = createTownshipBox("Cape Town", "Gugulethu", "./data/locations.txt");
    	    VBox johannesburgBox = createTownshipBox("Johannesburg", "Soweto", "./data/locations.txt");
    	    mainLayout.getChildren().addAll(durbanBox, capeTownBox, johannesburgBox);
    	    HBox hb = new HBox(20);
    	    hb.getChildren().add(mainLayout);

    	    // Create and configure the canvas
    	    Canvas canvas = new Canvas(500, 500);
    	    GraphicsContext gc = canvas.getGraphicsContext2D();
    	    gc.setLineWidth(3);
    	    gc.setFont(Font.font(30));
    	    gc.setStroke(Color.RED);
    	    String msg = ">>Please Press the 'Proceed' button to search\n for the nearest Hospital in your township<<";
    	    // Animation for moving text
    	   
    	    AnimationTimer timer = new AnimationTimer() {    
    	    	int x = 50;
				int y = 67;

    	        @Override
    	        public void handle(long now) {
    	            gc.strokeText(msg, --x, y);
    	     	    
    	            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	            
    	            if(x < -340)
					{ 
						x = 516; 
					}
    	            
    	            gc.strokeText(msg, --x, y);
    	        }
    	    };
    	    timer.start();

    	    // Load background images
    	    Image backgroundImage = new Image("file:data/image2.jpg"); // Update the path as needed
    	    BackgroundImage background = new BackgroundImage(
    	        backgroundImage,
    	        BackgroundRepeat.REPEAT,
    	        BackgroundRepeat.REPEAT,
    	        BackgroundPosition.DEFAULT,
    	        BackgroundSize.DEFAULT
    	    );
    	    
    	    Image backgroundImage2 = new Image("file:data/image.jpg"); // Update the path as needed
    	    BackgroundImage background2 = new BackgroundImage(
    	        backgroundImage2,
    	        BackgroundRepeat.REPEAT,
    	        BackgroundRepeat.REPEAT,
    	        BackgroundPosition.DEFAULT,
    	        BackgroundSize.DEFAULT
    	    );

    	    // Set background for the first scene
    	    firstSceneLayout.setBackground(new Background(background));
    	    firstSceneLayout.setTop(canvas);
    	    firstSceneLayout.setCenter(proceedButton);

    	    // Create the scenes
    	    Scene firstScene = new Scene(firstSceneLayout, 500, 525);
    	    Scene mainScene = new Scene(hb, 900, 550);

    	    // Set background for the main scene
    	    mainLayout.setBackground(new Background(background2));

    	    // Set up button action to load hospitals and switch to the main scene
    	    proceedButton.setOnAction(e -> {
    	        loadHospitalsForAllCities();
    	        stage.setScene(mainScene);
    	    });

    	    // Set the initial scene
    	    stage.setScene(firstScene);
    	    stage.setTitle("Hospital Finder");
    	    stage.show();
    	}

    private void loadHospitalsForAllCities() {
        try {
            graphDurban = GraphUtil.createGraph("./data/locations.txt");
            graphCapeTown = GraphUtil.createGraph("./data/locations.txt");
            graphJohannesburg = GraphUtil.createGraph("./data/locations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VBox createTownshipBox(String cityName, String townshipName, String filePath) {
    	VBox townshipBox = new VBox(10);

        // VBox for label, buttons, and result field
        VBox controlBox = new VBox(10);
        
        Label label = new Label(cityName.toUpperCase() + " - " + townshipName.toUpperCase());
        label.setFont(Font.font("system", FontWeight.EXTRA_BOLD, 17));
        Button loadHospitalsButton = new Button("Load Hospitals");
        Button searchButton = new Button("Search");
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPrefWidth(190);
       

        // Add controls to controlBox
        controlBox.getChildren().addAll(label, loadHospitalsButton, searchButton, resultField);

        // ListView for displaying hospitals
        ListView<String> listView = new ListView<>();

        // HBox to arrange controlBox and listView horizontally
        HBox layout = new HBox(200);
        layout.getChildren().addAll(controlBox, listView);

        // Load hospitals into the ListView when the "Load Hospitals" button is clicked
        loadHospitalsButton.setOnAction(e -> {
            listView.getItems().clear();
            Graph<Location> graph = getGraphForCity(cityName);

            if (graph == null) {
                System.out.println("Graph is null for city: " + cityName);
                return;
            }

            for (Graph.Vertex<Location> vertex : graph.getVertices()) {
                // Check if the vertex is a hospital and within the same township
                if (vertex.getValue().getName().contains("Hospital")) {
                    System.out.println("Hospital found in township: " + vertex.getValue().getName());
                    listView.getItems().add(vertex.getValue().getName());
                }
            }
        });

        // Search for the nearest hospital when the "Search" button is clicked
        searchButton.setOnAction(e -> {
            Graph<Location> graph = getGraphForCity(cityName);
            if (graph != null) {
                Location nearestHospital = GraphUtil.findNearestHospital(GraphUtil.findVertexByName(graph, townshipName));
                if (nearestHospital != null) {
                    resultField.setText(nearestHospital.getName());
                } else {
                    resultField.setText("No hospitals found.");
                }
            }
        });

        // Add the HBox to the townshipBox
        townshipBox.getChildren().add(layout);
        return townshipBox;
    }
    

    private Graph<Location> getGraphForCity(String cityName) {
        switch (cityName) {
            case "Durban":
                return graphDurban;
            case "Cape Town":
                return graphCapeTown;
            case "Johannesburg":
                return graphJohannesburg;
            default:
                return null;
        }
    }
}