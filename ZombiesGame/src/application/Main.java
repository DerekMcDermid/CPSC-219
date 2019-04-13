package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Viewer;

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