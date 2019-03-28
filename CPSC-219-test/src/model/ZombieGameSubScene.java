package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class ZombieGameSubScene extends SubScene {
	
	//create the constant background image
	private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "model/resources/green_button12.png";
	
	//create logic that makes the subscene go away after the button is clicked again
	//field that will tell is if subscene is hidden
	private boolean isHidden;
	

	public ZombieGameSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		
		//set background
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		
		//get achnorpane
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(1024);
		setLayoutY(180);
		
		// TODO Auto-generated constructor stub
	}
	//a method that write the logic in order to move the subscenes
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		
		//decides which element should be moved
		transition.setNode(this);
		
		if(isHidden) {
		//method that defines how a particular element will change its position on the x axis
		 transition.setToX(-676);
		isHidden = false;
		} else {
			//if it is shown, we want it to hide again
			transition.setToX(0);
			isHidden = true;
		}
		 //play the transition
		 transition.play();
		
	}
	//method that returns the pain of the subscene
	public AnchorPane getPane() {
		return (AnchorPane)this.getRoot();
	}

}
