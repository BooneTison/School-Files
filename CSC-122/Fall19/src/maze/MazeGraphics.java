package maze;

import maze.GridPoint.GridType;
import maze.MazeRunnerNick;
import maze.MazeRunnerNick.Option;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MazeGraphics extends Application {
	private static MazeRunnerNick maze;
	private int gridSize = 50;
	GridPoint[][] grid;
	Font font1 = new Font("SansSerif", 8);

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane initializeMaze = new Pane();
		Pane buttons = new Pane();
		
		grid = maze.getGrid();
		for (int i = 0; i < maze.getRowCount(); i++) {
			for (int j = 0; j < maze.getColumnCount(); j++) {
				Rectangle r = new Rectangle(gridSize*i,gridSize*j,gridSize,gridSize); 
				
				if (grid[i][j].getTileType() == GridType.WALL) {
					r.setStroke(Color.BLACK);
					r.setFill(Color.BLACK);
				} else if (grid[i][j].getTileType() == GridType.HALL) {
					r.setStroke(Color.WHITE);
					r.setFill(Color.WHITE);
				} else if (grid[i][j].getTileType() == GridType.START) {
					r.setStroke(Color.GREEN);
					r.setFill(Color.GREEN);
				} else if (grid[i][j].getTileType() == GridType.END) {
					r.setStroke(Color.RED);
					r.setFill(Color.RED);
				}
				initializeMaze.getChildren().add(r);
			}
		}
		
		drawCost(initializeMaze);
		
		Circle cursor = new Circle(gridSize*maze.getStart().ROW+25,gridSize*maze.getStart().COL+25,10);
		cursor.setStroke(Color.ORANGE);
		cursor.setFill(Color.ORANGE);
        cursor.setOpacity(0);
        initializeMaze.getChildren().add(cursor);
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), cursor);
	    fadeTransition.setFromValue(5.0);
	    fadeTransition.setToValue(0.0);
	    fadeTransition.setCycleCount(Animation.INDEFINITE);
        
        Button playGame = new Button("Play");
		playGameClass handler1 = new playGameClass(fadeTransition);
		playGame.setOnAction(handler1);
		buttons.getChildren().add(playGame);
		
		Button cancelGame = new Button("Stop");
		cancelGame.setLayoutX(50);
		cancelGameClass handler2 = new cancelGameClass(fadeTransition);
		cancelGame.setOnAction(handler2);
		buttons.getChildren().add(cancelGame);
		
		Button showBestPath = new Button("Best Path");
		showBestPath.setLayoutY(35);
		showBestPathClass handler3 = new showBestPathClass(initializeMaze);
		showBestPath.setOnAction(handler3);
		buttons.getChildren().add(showBestPath);
		
		Button hideBestPath = new Button("Hide Path");
		hideBestPath.setLayoutY(35);
		hideBestPath.setLayoutX(85);
		hideBestPathClass handler4 = new hideBestPathClass(initializeMaze);
		hideBestPath.setOnAction(handler4);
		buttons.getChildren().add(hideBestPath);
		
		Scene scene = new Scene(initializeMaze,gridSize*maze.getRowCount(),gridSize*maze.getColumnCount());
		Scene scene2 = new Scene(buttons,200,300);
		primaryStage.setTitle("MazeRunner");
		primaryStage.setScene(scene);
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
		Stage secondStage = new Stage();
		secondStage.setTitle("Buttons");
		secondStage.setScene(scene2);
		secondStage.setX(gridSize*maze.getRowCount()+5);
		secondStage.setY(0);
		secondStage.show();
	}
	
	class playGameClass implements EventHandler<ActionEvent> {
		FadeTransition f = new FadeTransition();
		public playGameClass(FadeTransition ft) { f = ft; }
		@Override
		public void handle(ActionEvent e) {
			f.play();
		}
	}
	
	class cancelGameClass implements EventHandler<ActionEvent> {
		FadeTransition f = new FadeTransition();
		public cancelGameClass(FadeTransition ft) { f = ft; }
		@Override
		public void handle(ActionEvent e) {
			f.jumpTo(Duration.seconds(1));
			f.stop();
		}
	}
	
	class showBestPathClass implements EventHandler<ActionEvent> {
		Pane p = new Pane();
		public showBestPathClass(Pane pane) { p = pane; }
		@Override
		public void handle(ActionEvent e) {
			Option o = maze.getBestPath().getVia();
			while (o.getVia() != null) {
				int x = gridSize*o.getCoordinate().ROW+10; int y = gridSize*o.getCoordinate().COL+10;
				Rectangle path = new Rectangle(x,y,30,30);
				path.setStroke(Color.ORANGE);
				path.setFill(Color.ORANGE);
				p.getChildren().add(path);
				o = o.getVia();
			}
			drawCost(p);
		}
	}
	
	class hideBestPathClass implements EventHandler<ActionEvent> {
		Pane p = new Pane();
		public hideBestPathClass(Pane pane) { p = pane; }
		@Override
		public void handle(ActionEvent e) {
			Option o = maze.getBestPath().getVia();
			while (o.getVia() != null) {
				int x = gridSize*o.getCoordinate().ROW+10; int y = gridSize*o.getCoordinate().COL+10;
				Rectangle path = new Rectangle(x,y,30,30);
				path.setStroke(Color.WHITE);
				path.setFill(Color.WHITE);
				p.getChildren().add(path);
				o = o.getVia();
			}
			drawCost(p);
		}
	}
	
	public void drawCost(Pane pane) {
		for (int i = 0; i < maze.getRowCount(); i++) {
			for (int j = 0; j < maze.getColumnCount(); j++) {
				if (grid[i][j].getTileType() == GridType.HALL) {
					Text t = new Text(gridSize*i+20,gridSize*j+30,String.valueOf(grid[i][j].getCost()));
					t.setStroke(Color.BLACK);
					t.setFont(font1);
					t.setFill(Color.BLACK);
					pane.getChildren().add(t);
				} 
			}
		}
	}
	
	public static void main(String[] args) {
		maze = new MazeRunnerNick("maze2.txt");
		maze.solve();
		Application.launch(args);
	}

}
