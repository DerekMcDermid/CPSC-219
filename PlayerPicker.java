package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PlayerPicker extends VBox {
	
	//create tqo imagery fields
	private ImageView circleImage;
	private ImageView playerImage;

	//create 2 strings - each one will contain url for player image and empty circle image
	private String circleNotChosen= "view/resources/playerChooser/grey_circle.png";
	private String circleChosen = "view/resources/playerChooser/green_boxTick.png";
	
	private PLAYER player;
	
	//boolean that tells us if the player was selected
	private boolean isCircleChosen;
	
	public PlayerPicker(PLAYER player) {
		circleImage = new ImageView(circleNotChosen);
		playerImage = new ImageView(player.getUrl());
		this.player = player;
		//by default, the circle will not be chosen so set this to false
		isCircleChosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		//add images to the vbox
		this.getChildren().add(circleImage);
		this.getChildren().add(playerImage);
	}
	
	public PLAYER getPlayer() {
		return player;
	}
	
	public boolean getIsCircleChosen() {
		return isCircleChosen;
	}
	
	//method which will set our circle if it is chosen or not chosen
	
	public void setIsCircleChosen(boolean isCircleChosen) {
		this.isCircleChosen = isCircleChosen;
		String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
		circleImage.setImage(new Image(imageToSet));
	}
	
}
