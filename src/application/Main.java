package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Viewer;
//All code for constructing the main menu of our game was inspired by a series of youtube tutorials Website: https://www.youtube.com/watch?v=6BKI26gxK2Q&t=23s Author: javacraving

public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		try {
			Viewer view = new Viewer();
			primaryStage = view.getMainStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	{
}
	

}
