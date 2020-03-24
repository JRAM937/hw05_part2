
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Demo extends Application {

	private ArrayList<Shape> shapes = new ArrayList<>();
	
	private Shape currentShape;     // the shape being drawn
	private ShapeFactory factory;   // for making a new Shape
	
	private Canvas canvas;    // drawing canvas
	private MenuBar menuBar;  
	private BorderPane root;  // window that contains menu bar and canvas
	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Drawing");
		canvas = new Canvas(800, 800);  // size 800 x 800 pixels

		// start of drawing
		canvas.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getX();  // get mouse position
				int y = (int) event.getY();
				System.out.printf("Drag Event Start: %d, %d\n", x, y);
				if (factory != null) {
					currentShape = factory.makeShape(new Point(x,y));
					shapes.add(currentShape);
				}
			}
		});

		// end drawing
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				System.out.printf("Mouse Released: %d, %d\n", x, y);
				if (currentShape != null) {
					currentShape.mouseMove(new Point(x,y));
					currentShape.drawEnd();
					currentShape = null;
					draw();
				}
			}
		});
		
		// during the draw 
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (currentShape != null) {
					currentShape.mouseMove(new Point((int) event.getX(), (int) event.getY()));
					draw();
				}
			}

		});

		// set up menu items for drawing a line

		MenuItem lineTool = new MenuItem("Line");
		lineTool.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Line Tool selected");
				factory = new LineFactory();

			}

		});

		// for drawing a scribble
		MenuItem scribbleTool = new MenuItem("Scribble");
		scribbleTool.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Sribble Tool selected");
				factory = new ScribbleFactory();

			}

		});
		
		//For drawing a circle
		MenuItem circleTool = new MenuItem("Circle");
		circleTool.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Circle Tool selected");
				factory = new CircleFactory();

			}

		});
		
		//For drawing a rectangle
		MenuItem rectangleTool = new MenuItem("Rectangle");
		rectangleTool.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Rectangle Tool selected");
				factory = new RectangleFactory();

			}

		});
		
		//For drawing a HappyFace
		MenuItem happyFaceTool = new MenuItem("Happy Face");
		happyFaceTool.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Happy Face Tool selected");
				factory = new HappyFaceFactory();

			}

		});
		

		Menu toolMenu = new Menu("Draw Tools");
		toolMenu.getItems().addAll(lineTool, scribbleTool, rectangleTool, circleTool, happyFaceTool);

//      Create MenuBar
		menuBar = new MenuBar();
		menuBar.getMenus().add(toolMenu);

		root = new BorderPane();
		root.setTop(menuBar); // arrange menu bar at time
		root.setBottom(canvas); // drawing canvas at below menu
		
		scene = new Scene(root, 350, 200);  // size of window 350x200 pixels
		primaryStage.setScene(scene);
		primaryStage.show();
		draw();
	}

	public void draw() {

		GraphicsContext gc = canvas.getGraphicsContext2D();
		// erase current drawing
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);
		// draw each shape
		shapes.forEach((s) -> s.draw(gc));
	}

}
