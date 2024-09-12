
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 */

/**
 * @author Cebo Sithole 218035887
 *
 */
public class MainPane extends BorderPane {


	//Gui variables and others
	private static Graph<Town> graph;
	private Label lblLogin;
	private TextField username;
	private PasswordField password;
	private MenuBar menubar;
	private Menu menu;
	private Menu menuFile;
	private Label lblusername;
	private Label lblpassword;
	private Button btnLogin;
	private ComboBox<String> combo0;
	private ComboBox<String> combo1;
	private ComboBox<String> combo2;
	private ListView<String> vertListview;
	private ListView<String> edgeListView;
	private static int totalUsage;
	private int secPassed;
	private int secPassed2;
	private int alertValue;
	private int once;
	private int once2;
	private boolean increm;
	private AnimationTimer timer;

	private List<Graph.Vertex<Town>> vertices = new ArrayList<Graph.Vertex<Town>>();
	private List<Graph.Edge<Town>> edges = new ArrayList<Graph.Edge<Town>>();

	/**
	 * @param stage
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainPane(Stage stage)
	{
		//GUI nodes and local variables 
		menubar = new MenuBar();
		menu = new Menu("Simulation");
		menuFile = new Menu("File");
		MenuItem mi0 = new MenuItem("Home Screen");
		MenuItem mi1 = new MenuItem("Dashboard");
		MenuItem mi2 = new MenuItem("Simulation in real-time");
		MenuItem mi3 = new MenuItem("Exit");
		MenuItem mi4 = new MenuItem("Export Data");
		MenuItem mi5 = new MenuItem("Import Data");
		menu.getItems().addAll(mi0,mi1, mi2);
		menuFile.getItems().addAll(mi5, mi4 , mi3);
		menubar.getMenus().addAll( menuFile, menu);

		//local variables
		totalUsage = 0;
		secPassed =0;
		secPassed2 =60;
		alertValue=0;
		once =-1;
		once =-1;
		increm = false;

		//Gui components
		lblLogin = new Label("Welcome back, Admin");
		lblLogin.setFont(Font.font(30));
		username = new TextField("Admin");
		lblusername = new Label("Username:");
		lblpassword = new Label("Password:");
		password = new PasswordField();
		password.setText("Admin");
		btnLogin = new Button("Login");

		btnLogin.setMaxWidth(280);
		password.setMaxSize(280,10);
		username.setMaxSize(280, 10);


		//Vertex values
		Label lblAddTown = new Label("Add Town");
		lblAddTown.setFont(Font.font(30));
		Label lblTownName = new Label("Town Name:");

		Label lblHouseHold = new Label("Number of households");
		TextField txtHouseHolds = new TextField();
		txtHouseHolds.setMaxWidth(200);
		TextField txtAlert = new TextField();

		Label lblapplianceRateSum = new Label("Appliances Power Rating Sum in Watts(default:32367)");
		TextField txtappRSum = new TextField();
		txtappRSum.setMaxWidth(200);

		//Edge values
		Label lblAddPL = new Label("Add Power Line");
		lblAddPL.setFont(Font.font(30));
		Label lblPLCost = new Label("Max Transfer Rate in kV(recommended max:123kV):");
		TextField txtPLCost = new TextField();
		Label lblFromTown = new Label("From:");
		Label lblToTown = new Label("To:");
		Label lblFor = new Label("When city total power usage is:");

		Button btnAddTown = new Button("Add Town");
		Button btnRemoveTown = new Button("Remove Town");
		btnAddTown.setMaxWidth(200);
		btnRemoveTown.setMaxWidth(200);
		Button btnAddPL = new Button("Add Power Line");
		Button btnRemovePL = new Button("Remove Power Line");
		btnAddPL.setMaxWidth(200);
		btnRemovePL.setMaxWidth(200);

		Button setAlertButton = new Button("Set Alert");
		Button stopSimulationButton = new Button("Stop simulation");
		Button startSimulationButton = new Button("Run Simulation");
		Button pauseSimulationButton = new Button("Pause Simulation");

		stopSimulationButton.setMaxWidth(200);
		startSimulationButton.setMaxWidth(200);
		pauseSimulationButton.setMaxWidth(200);

		combo1 = new ComboBox<>();
		combo1.setEditable(false);
		combo1.setMaxWidth(400);

		combo2 = new ComboBox<>();
		combo2.setEditable(false);
		combo2.setMaxWidth(400);

		combo0 = new ComboBox<>();
		combo0.setEditable(true);
		combo0.setMaxWidth(300);

		vertListview = new ListView<String>();
		edgeListView = new ListView<String>();

		Label lblListView = new Label("Towns");
		lblListView.setFont(Font.font(30));
		Label lblListView2 = new Label("Power-lines");
		lblListView2.setFont(Font.font(30));
		Label lblkW = new Label("kW");

		VBox vbLogin = new VBox();
		vbLogin.setMaxSize(300, 400);
		vbLogin.setSpacing(20);

		VBox ltBox = new VBox();
		VBox ltBox2 = new VBox();
		ltBox.getChildren().addAll( lblusername, username);
		ltBox2.getChildren().addAll(lblpassword, password);


		vbLogin.getChildren().addAll(lblLogin,ltBox ,ltBox2,btnLogin);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setCenter(vbLogin);
		this.setWidth(800);
		this.setHeight(600);


		//MenuItem for the HOMESCREEN
		mi0.setOnAction(h->{

			timer.stop();

			BorderPane homePane = new BorderPane();
			Image image = null;
			try {
				//Reference: https://energycouncil.com/wp-content/uploads/Eskom-1.png
				image =new Image(new FileInputStream(new File("./dist/Eskom-1.png")));
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}
			final Image image2 = image;
			Canvas canvas = new Canvas(500, 500);

			GraphicsContext gc = canvas.getGraphicsContext2D();

			gc.drawImage(image2, 0, 0);
			gc.setLineWidth(1);
			gc.setFont(Font.font(17));
			gc.setStroke(Color.MEDIUMBLUE);

			//Animation for moving the text
			timer = new AnimationTimer() {	
				int x = 525;
				int y = 67;


				@Override
				public void handle(long now) {

					gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
					gc.strokeText("Important: Import data first to use the simulator", --x, y);
					gc.drawImage(image2, 0, 0);
					gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

					gc.drawImage(image2, 0, 0);

					if(x < -340)
					{ 
						x = 516; 
					}
					gc.strokeText("Important: Import data first to use the simulator", --x, y);

				}
			};timer.start();

			homePane.setTop(menubar);
			homePane.setCenter(canvas);
			Scene scene = new Scene(homePane, 800, 600);
			stage.setScene(scene);
			stage.show();
		});

		//MenuItem for saving or exporting data..
		mi4.setOnAction(s->{

			final FileChooser fsChooser = new FileChooser();
			File file = null;
			fsChooser.setTitle("Select file or create new file to save");

			fsChooser.setInitialDirectory(new File("./dist/Data"));

			file = fsChooser.showSaveDialog(null);

			boolean success = FileHandlerClass.saveAll(graph, file);

			Alert alert = new Alert(AlertType.INFORMATION);
			if(success)
			{

				alert.setTitle("Notification");
				alert.setHeaderText("Success");
				alert.setContentText("Data saved");
				alert.showAndWait();

			}else if (success == false) {

				alert.setTitle("Notification");
				alert.setHeaderText("Error");
				alert.setContentText("Oops something went wrong, data not saved");
				alert.showAndWait();
			}
		});

		//MenuItem for selecting a file to read or import..
		mi5.setOnAction(r->{

			File file = null;
			final FileChooser fsChooser = new FileChooser();

			fsChooser.setTitle("Select file to read");

			fsChooser.setInitialDirectory(new File("./dist/Data"));
			file = fsChooser.showOpenDialog(null);

			graph = FileHandlerClass.readAll(file);

			Alert alert = new Alert(AlertType.INFORMATION);
			if(graph != null) {

				alert.setTitle("Notification");
				alert.setHeaderText("Success");
				alert.setContentText("Data imported"); 
				alert.showAndWait();

			}else if (graph == null) 
			{
				alert.setTitle("Notification"); 
				alert.setHeaderText("Error");
				alert.setContentText("Oops something went wrong, data corrupted");
				alert.showAndWait(); 
			}
		});

		//Button for the basic login capability
		btnLogin.setOnAction(e->{

			String strUsername = username.getText();
			String strPassword = password.getText();

			if(FileHandlerClass.readTextFile(strUsername, strPassword)) {

				Image image = null;
				try {
					//Reference: https://energycouncil.com/wp-content/uploads/Eskom-1.png
					image =new Image(new FileInputStream(new File("./dist/Eskom-1.png")));
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
				final Image image2 = image;
				Canvas canvas = new Canvas(500, 500);

				GraphicsContext gc = canvas.getGraphicsContext2D();
				gc.drawImage(image2, 0, 0);
				gc.setLineWidth(1);
				gc.setFont(Font.font(17));
				gc.setStroke(Color.MEDIUMBLUE);

				//Animation for moving the text
				timer = new AnimationTimer() {	
					int x = 525;
					int y = 67;


					@Override
					public void handle(long now) {

						gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
						gc.strokeText("Important: Import data first to use the simulator", --x, y);
						gc.drawImage(image2, 0, 0);
						gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

						gc.drawImage(image2, 0, 0);

						if(x < -340)
						{ 
							x = 516; 
						}
						gc.strokeText("Important: Import data first to use the simulator", --x, y);

					}
				}
				;timer.start();


				BorderPane afterLoginPane = new BorderPane();
				afterLoginPane.setTop(menubar);
				afterLoginPane.setCenter(canvas);
				Scene scene = new Scene(afterLoginPane, 800, 600);

				stage.setScene(scene);
				stage.show();

			}else
			{
				Alert error = new Alert(Alert.AlertType.ERROR);
				error.setTitle("Error");
				error.setContentText("Incorrect username or password");
				error.show();
			}

		});
		//Button for adding a vertex
		btnAddTown.setOnAction(e2->{

			//setting everything up before
			edges = graph.getEdges();
			vertices = graph.getVertices();

			boolean exist = false;
			if(graph != null && (combo0.getValue() != null) && !(txtHouseHolds.getText().equals("")))
			{
				//checking if the same town name exist in a same city
				for(Graph.Vertex<Town> vertex : graph.getVertices())
				{
					if(combo0.getValue().equals(vertex.getValue().getName()))
					{

						exist = true;
					}
				}

				if(exist == false) {
					if(txtappRSum.getText().equals("")) {
						vertices.add(new Graph.Vertex<Town>(new Town(combo0.getValue().toString(),Integer.parseInt(txtHouseHolds.getText()))));
					}else if(!txtappRSum.getText().equals(""))
					{
						int ratingsum = Integer.parseInt(txtappRSum.getText());
						System.out.println(ratingsum);
						vertices.add(new Graph.Vertex<Town>(new Town(combo0.getValue().toString(),Integer.parseInt(txtHouseHolds.getText()), ratingsum)));
					}
					graph.setAllVertices(vertices);

					List<String> names = new ArrayList<String>();
					List<String> names2 = new ArrayList<String>();
					for(Graph.Vertex<Town> v : graph.getVertices())
					{
						names.add(v.getValue().getName());
					}
					combo0.setItems(FXCollections.observableArrayList(names));
					combo1.setItems(FXCollections.observableArrayList(names));
					combo2.setItems(FXCollections.observableArrayList(names));
					vertListview.setItems(FXCollections.observableArrayList(names));

					//list view of the edges
					for(Graph.Edge<Town> edge : graph.getEdges())
					{
						names2.add("From: "+ edge.getFromVertex().getValue().getName() + " To:-> " + edge.getToVertex().getValue().getName());
					}
					edgeListView.setItems(FXCollections.observableArrayList(names2));

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Notification");
					alert.setContentText("Town added successfully");
					System.out.println(graph.toString());
					alert.showAndWait();
				}else if(exist == true)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Notification");
					alert.setContentText("Town already exist");
					alert.showAndWait();
				}



			}else if ( combo0.getValue() == null || txtHouseHolds.getText().equals(""))
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);

				alert.setTitle("Error");
				alert.setContentText("Field is empty");
				alert.showAndWait();
			}

		});
		//Button for removing a vertex
		btnRemoveTown.setOnAction(eR->{

			//setting everything up before
			edges = graph.getEdges();
			vertices = graph.getVertices();

			Graph.Vertex<Town> delVertex =null;
			List<Graph.Edge<Town>> tempList = new ArrayList<>();

			//find the vertex with the given name
			if(graph != null && (combo0.getValue() != null))
			{
				for(Graph.Vertex<Town> ver: graph.getVertices())
				{
					if(ver.getValue().getName().equals(combo0.getValue().toString()))
					{
						delVertex = ver;
						break;
					}
				}


				//check if the vertex exist
				if(delVertex != null) {

					//copying the edges that the vertex to be deleted has
					for(Graph.Edge<Town> copy: delVertex.getEdges())
					{
						tempList.add(copy);
					}

					//getting the edges that have been reversed but the same
					for(Graph.Edge<Town> edge : graph.getEdges())
					{

						if(edge.getTo().equals(delVertex) || edge.getFrom().equals(delVertex))
						{
							tempList.add(edge);

						}
					}

					//then remove its edges, get the list of edges that it had
					//Look for vertices that are linked to this vertex and delete the link between them and remove their link edge
					for(Graph.Vertex<Town> ver: graph.getVertices())
					{
						for(Graph.Edge<Town> edge1 : tempList)
						{

							ver.removeEdge(edge1);

						}

					}

					//remove them in the edge list of the graph
					graph.getEdges().removeAll(tempList);

					//Finally remove them in the list of the graph
					graph.getVertices().remove(delVertex);


					vertices = graph.getVertices();
					edges = graph.getEdges();
					List<String> names = new ArrayList<String>();
					List<String> names2 = new ArrayList<String>();
					for(Graph.Vertex<Town> v : graph.getVertices())
					{
						names.add(v.getValue().getName());
					}
					combo0.setItems(FXCollections.observableArrayList(names));
					combo1.setItems(FXCollections.observableArrayList(names));
					combo2.setItems(FXCollections.observableArrayList(names));
					vertListview.setItems(FXCollections.observableArrayList(names));

					//list view of the edges
					for(Graph.Edge<Town> edge : edges)
					{

						names2.add("From: "+edge.getFromVertex().getValue().getName() + " To:-> " + edge.getToVertex().getValue().getName());
					}
					edgeListView.setItems(FXCollections.observableArrayList(names2));

					System.out.println(graph.toString());
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Town does not exist");
					alert.showAndWait();
				}

			}else if(graph == null || combo0.getValue() == null)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Field is empty");
				alert.showAndWait();
			}



		});
		//Button for adding an edge
		btnAddPL.setOnAction(e2->{

			//setting everything up before
			edges = graph.getEdges();
			vertices = graph.getVertices();
			//for the edge list view
			List<String> listEdges = new ArrayList<String>();
			if(graph != null && !(txtPLCost.getText().equals("")))
			{

				//what if I'm adding a new one and what if it is existing
				Graph.Edge<Town> newEdge = new Graph.Edge<Town>(Integer.parseInt(txtPLCost.getText()), null, null);
				//The reverse
				Graph.Edge<Town> newEdge2 = new Graph.Edge<Town>(Integer.parseInt(txtPLCost.getText()), null, null);
				//create the edge base on the selected towns
				for(Graph.Vertex<Town> ver: graph.getVertices())
				{
					if(ver.getValue().getName().equalsIgnoreCase(combo1.getValue().toString()))
					{
						newEdge.setFrom(ver);
						newEdge2.setTo(ver);

					}else if(ver.getValue().getName().equalsIgnoreCase(combo2.getValue().toString()))
					{
						newEdge.setTo(ver);
						newEdge2.setFrom(ver);
					}
				}

				boolean exist = false;
				//loop through to check if the edge exist regardless of the transfer rate
				for(Graph.Edge<Town> edge : graph.getEdges()) //here
				{
					if((edge.getFrom().equals(newEdge.getFrom()) && edge.getTo().equals(newEdge.getTo())) ||  (edge.getFrom().equals(newEdge.getTo()) && edge.getTo().equals(newEdge.getFrom())))
					{
						exist = true;
						break;
					}
				}


				if(exist == false)
				{
					//loop through to add an edge to both vertices involved
					for(Graph.Vertex<Town> ver: graph.getVertices())
					{
						if(ver.getValue().getName().equalsIgnoreCase(combo1.getValue().toString()))
						{
							ver.addEdge(newEdge);
						}else if(ver.getValue().getName().equalsIgnoreCase(combo2.getValue().toString()))
						{
							ver.addEdge(newEdge2);
						}
					}
					edges.add(newEdge);
					edges.add(newEdge2);

					graph.setAllEdges(edges);

					//list view of the edges
					for(Graph.Edge<Town> edge : graph.getEdges())
					{

						listEdges.add("From: "+edge.getFromVertex().getValue().getName() + " To:-> " + edge.getToVertex().getValue().getName());
					}
					//updating the edge list view
					edgeListView.setItems(FXCollections.observableArrayList(listEdges));
					System.out.println(graph.toString());
				}else 
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Edge already exist");
					alert.showAndWait();
				}
			}else if(graph == null || txtPLCost.getText().equals(""))
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Field is empty");
				alert.showAndWait();
			}
		});
		//MenuItem for opening up the management of the edges and vertices
		mi1.setOnAction(e->{

			if(graph != null) {
				//setting everything up before
				edges = graph.getEdges();
				vertices = graph.getVertices();
				//for storing the list of the towns existing
				List<String> listVertices = new ArrayList<String>();
				List<String> listEdges = new ArrayList<String>();
				for(Graph.Vertex<Town> v : graph.getVertices())
				{
					listVertices.add(v.getValue().getName());
				}
				combo0.setItems(FXCollections.observableArrayList(listVertices));
				combo1.setItems(FXCollections.observableArrayList(listVertices));
				combo2.setItems(FXCollections.observableArrayList(listVertices));
				vertListview.setItems(FXCollections.observableArrayList(listVertices));

				//list view of the edges
				for(Graph.Edge<Town> edge : graph.getEdges())
				{
					listEdges.add("From: "+edge.getFromVertex().getValue().getName() + " To:-> "+ edge.getToVertex().getValue().getName());
				}
				edgeListView.setItems(FXCollections.observableArrayList(listEdges));


			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Error");
				alert.setContentText("Please import data first and refresh the dashboard");
				alert.showAndWait();
			}
			//VBox for the list view and label 
			VBox vbListBox = new VBox();
			vbListBox.getChildren().addAll(lblListView, vertListview);
			vbListBox.setSpacing(5);
			vbListBox.setPadding(new Insets(5, 5, 5, 5));

			//VBox for the list view and label 
			VBox vbListBox2 = new VBox();
			vbListBox2.getChildren().addAll(lblListView2, edgeListView);
			vbListBox2.setSpacing(5);
			vbListBox2.setPadding(new Insets(5, 5, 5, 5));

			//HBox for both VBox defined below and above
			HBox combinedBox = new HBox();
			combinedBox.setSpacing(20);
			combinedBox.setMaxSize(850,500);

			//VBox for the left nodes
			VBox vb = new VBox();
			vb.setMaxWidth(500);
			vb.setPadding(new Insets(5, 5, 5, 5));
			vb.setSpacing(5);
			HBox hBox = new HBox();
			HBox hBox2 = new HBox();
			hBox.setSpacing(20);
			hBox2.setSpacing(20);
			hBox.getChildren().addAll(btnAddPL, btnRemovePL);
			hBox2.getChildren().addAll(combo0,btnRemoveTown);
			vb.getChildren().addAll(lblAddTown, lblTownName,hBox2 ,lblHouseHold,txtHouseHolds,lblapplianceRateSum,txtappRSum,btnAddTown ,lblAddPL, lblPLCost, txtPLCost, lblFromTown,combo1, lblToTown,combo2, hBox);

			//adding the two VBoxes 
			combinedBox.getChildren().addAll(vb, vbListBox, vbListBox2);
			BorderPane bPane = new BorderPane(); //root
			bPane.setTop(menubar);
			bPane.setCenter(combinedBox);

			Scene scene = new Scene(bPane, 1000, 600);
			stage.setScene(scene);
			stage.show();

		});
		//Button for removing an edge
		btnRemovePL.setOnAction(eRPL->{

			//setting everything up before
			edges = graph.getEdges();
			vertices = graph.getVertices();

			Graph.Vertex<Town> fromVertex = null;
			Graph.Vertex<Town> toVertex = null;
			List<Graph.Edge<Town>> deletedEdges = new ArrayList<>();
			//for the list view
			List<String> listEdges = new ArrayList<String>();
			//for removing an edge
			if(combo2.getValue().toString() != null && combo1.getValue().toString() != null)
			{

				//Finding selected vertices
				for(Graph.Vertex<Town> vert : graph.getVertices())
				{
					if(vert.getValue().getName().equals(combo1.getValue().toString()))
					{
						System.out.println("found 1");
						fromVertex = vert;
					}else if(vert.getValue().getName().equals(combo2.getValue().toString())) 
					{
						System.out.println("found 2");
						toVertex = vert;
					}
				}

				boolean found = false;
				//temp object to avoid concurrent exception
				List<Graph.Edge<Town>> tempEdges = new ArrayList<>();
				for(Graph.Edge<Town> edge : graph.getEdges())
				{
					tempEdges.add(edge);
				}


				//first go through a list of edges in the graph and add the ones that are to be deleted
				for(Graph.Edge<Town> edge : tempEdges)
				{

					if((edge.getFrom().equals(fromVertex) && edge.getTo().equals(toVertex)) || (edge.getFrom().equals(toVertex) && edge.getTo().equals(fromVertex)) )
					{
						deletedEdges.add(edge); //reference of the deleted edges
						found = true;
						edges.remove(edge); //remove it from this list
						System.out.println("Found one and added");
					}
				}

				if(found == true) {
					//removing the deleted edges in each vertex
					for(Graph.Vertex<Town> vert : graph.getVertices())
					{
						for(Graph.Edge<Town> delEdge : deletedEdges)
						{
							vert.removeEdge(delEdge);
							System.out.println("Vert deleted in the vertex");
						}
					}

					//finally set the new list of edges and vertices
					graph.setAllEdges(edges);
					vertices = graph.getVertices(); //updating the reference list in this class

					//list view of the edges
					for(Graph.Edge<Town> edge : edges)
					{

						listEdges.add("From: "+edge.getFromVertex().getValue().getName() + " To:-> " + edge.getToVertex().getValue().getName());
					}
					//updating the edge list view
					edgeListView.setItems(FXCollections.observableArrayList(listEdges));

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Notification");
					alert.setHeaderText("Success");
					alert.setContentText("Removed the power line succefully");
					alert.showAndWait();
				}else {
					//an edge not found
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Oops powerline not found");
					alert.showAndWait();
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Operation failed");
				alert.showAndWait();
			}
		}); 

		//MenuItem for the real time analysis of the power usages used by towns or city
		mi2.setOnAction(e->{

			if(graph != null) {
				//setting everything up before
				edges = graph.getEdges();
				vertices = graph.getVertices();

				//transversal data structures
				Map<Graph.Vertex<Town>, Graph.Edge<Town>> forest = new HashMap<Graph.Vertex<Town>, Graph.Edge<Town>>();
				Set<Graph.Vertex<Town>> knownSet = new HashSet<Graph.Vertex<Town>>();


				Label trendLabel = new Label("Power Consumption Trend");
				trendLabel.setFont(Font.font(30));

				//draw a line chart
				NumberAxis xAxis = new NumberAxis();
				xAxis.setLabel("Time(per second)");

				NumberAxis yAxis = new NumberAxis();
				yAxis.setLabel("Total Power Consumption by City(per kW/sec)");

				//for the bar chart
				NumberAxis byAxis =new NumberAxis();
				byAxis.setLabel("Power Consumption(per kW/sec)");

				//For the bar chart
				CategoryAxis catAxis = new CategoryAxis();
				catAxis.setLabel("Townships");

				//creating the main chart
				LineChart trendChart = new LineChart<>(xAxis, yAxis);
				BarChart barChart = new BarChart<>(catAxis,byAxis);



				//data series to add data on
				XYChart.Series dataseries = new XYChart.Series<>();
				XYChart.Series dataseries2 = new XYChart.Series<>();

				dataseries.setName("2023");//setting the bottom name
				dataseries2.setName("2023");

				//adding data and points
				dataseries.getData().add(new XYChart.Data<>(0, 0));
				//dataseries2.getData().add(new XYChart.Data<>("",0));

				trendChart.getData().add(dataseries); //adding data to the line chart
				barChart.getData().add(dataseries2);

				//time line of the graph
				Timeline timeline = new Timeline();

				timeline.setCycleCount(Timeline.INDEFINITE);
				//The button that starts the simulation
				startSimulationButton.setOnAction(es->{
					timeline.play();
				});
				//The button that paused the simulation
				pauseSimulationButton.setOnAction(ep->{
					timeline.pause();
				});
				//The button that stops the simulation
				stopSimulationButton.setOnAction(est->{
					timeline.stop();
				});

				//The time line runs every 10 seconds
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(10), eTL-> {

					secPassed +=10; //increase timer each cycle


					for(Graph.Vertex<Town> vc : graph.getVertices())
					{ 
						//checking to see if a town does get power supply through edges if not there will be no usage
						if(getTotalList(vc.getEdges()) != 0)
						{
							if(secPassed == secPassed2)
							{
								increm = true;
							
								//calculating the power usage and decrease it at certain times
								vc.setpowerUsage(Double.parseDouble(String.format("%.2f",(vc.getpowerUsage() - vc.getValue().calculateConsumption()*2))));

								//plotting the graph using the name and the power usage
								dataseries2.getData().add(new XYChart.Data<>(vc.getValue().getName(),vc.getpowerUsage()));
							}else 
							{
								//calculating the power usage and increasing it very 10 seconds
								vc.setpowerUsage(Double.parseDouble(String.format("%.2f",(vc.getpowerUsage() + vc.getValue().calculateConsumption()))));

								//plotting the graph using the name and the power usage
								dataseries2.getData().add(new XYChart.Data<>(vc.getValue().getName(),vc.getpowerUsage()));
							}

						}else {
							if(once2==-1) {
								once2++;
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Notification");
								alert.setContentText(vc.getValue().getName()+" does not have any power supply coming in from other towns");
								alert.show();
							}
						}

					}
					if(increm == true)
					{
						secPassed2+=60;
						increm = false;
					}
					totalUsage = 0;
					//Add other points in real time every specified second to show the total usage of towns in a city each 10 seconds
					dataseries.getData().add(new XYChart.Data(secPassed,DFS(graph, graph.getVertices().get(0), knownSet, forest)));
					//displays this every second
					if(once == 0) {
						if(alertValue <= totalUsage)
						{
							once++;
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Notification");
							alert.setContentText("Alert: The total usage of the city is now  at : " + totalUsage +" kW");
							alert.show();
						}

					}
					
					System.out.println(graph.toString());
				}));



				BorderPane pane = new BorderPane(); //root
				pane.setTop(menubar);
				HBox hBox = new HBox(startSimulationButton, pauseSimulationButton, stopSimulationButton);
				hBox.setSpacing(10);
				hBox.setPadding(new Insets(5,5,5,5));

				HBox hBox0 = new HBox(setAlertButton, lblFor,txtAlert, lblkW);
				hBox0.setSpacing(10);
				hBox0.setPadding(new Insets(5,5,5,5));

				VBox vBox = new VBox(trendLabel,trendChart,hBox0, hBox);//adding the chart to the vbox		
				vBox.setSpacing(10);
				vBox.setPadding(new Insets(5,5,5,5));

				pane.setLeft(vBox);
				pane.setCenter(barChart);
				Scene scene = new Scene(pane, 1920, 1000);

				stage.setScene(scene);
				System.out.println(graph.toString());
				stage.show();
			}else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Error");
				alert.setContentText("Please import data first..");
				alert.showAndWait();
			}

		});

		//MenuItem for exiting
		mi3.setOnAction(e->{

			//Changing chart and changing bar graph
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure you want to exit?");
			alert.showAndWait();

			if(alert.getResult().getText().equals("OK")){
				//close the primary stage if OK
				stage.close();

			}
			else
			{
				//do nothing
			}

		});
		setAlertButton.setOnAction(a->{
			//Set the alert here 
			alertValue = Integer.parseInt(txtAlert.getText());
			once = 0;
		});

	}

	//Graph Traversal to visit all the nodes and get their values and return a total 
	//Reference Textbook : Data Structures and Algorithms in Java, 6th Edition
	//Visiting the edges and vertices does not happen in terms of weight or cost, it is irrelevant to the problem being solved
	public static int DFS(Graph<Town> g, Graph.Vertex<Town> u, Set<Graph.Vertex<Town>> known, Map<Graph.Vertex<Town>, Graph.Edge<Town>> forest)
	{
		//The value return to help draw up a real time graph
		totalUsage += u.getpowerUsage();
		//System.out.println(u.toString());
		System.out.println(totalUsage);
		known.add(u);
		for(Graph.Edge<Town> e: u.getEdges())
		{
			Graph.Vertex<Town> vertex = e.getTo();
			if(!known.contains(vertex))
			{
				forest.put(vertex, e);
				DFS(g, vertex, known, forest);
			}
		}
		return totalUsage;
	}
	/**
	 * @param edges, edges passed by a vertex
	 * @return returns the total cost of the edges linked with the current vertex, if zero it means the town does not have a power supply coming in
	 */
	private double getTotalList(List<Graph.Edge<Town>> edges)
	{
		double totalrate = 0.0;

		for(Graph.Edge<Town> edge: edges)
		{
			totalrate += edge.getCost();
		}
		return totalrate;
	}
}
