package view;


import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InformationLabel;
import model.PLAYER;
import model.PlayerPicker;
import model.ZombieGameButton;
import model.ZombieGameSubScene;

public class Viewer {
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	
	//initiate the 3 views we will be using
	private AnchorPane mainPane; //helps organize GUI
	private Scene mainScene;
	private Stage mainStage;
	
	//set the position of the first button
	
	private final static int MENU_BUTTONS_START_X = 100; 
	private final static int MENU_BUTTONS_START_Y = 300; 
	
	//credits subscene

	//help subscene
	private ZombieGameSubScene helpSubScene;
	//score subscene

	//player chooser subscene
	private ZombieGameSubScene playerChooserSubScene;
	
	//ensure that after another button is clicked, the previous clicked subscene will hide
	private ZombieGameSubScene sceneToHide; 
	
	//define a list in which we store the buttons
	List<ZombieGameButton> menuButton;
	
	List<PlayerPicker> playersList;
	private PLAYER chosenPlayer;
	
 //create a constructor in which we initialize our fields 
 public Viewer() {
	 menuButton = new ArrayList<>();
	 mainPane = new AnchorPane();
	 //set default width and height 
	 mainScene = new Scene(mainPane, WIDTH, HEIGHT);
	 //initialize our stage
	 mainStage = new Stage();
	 //set our scene
	 mainStage.setScene(mainScene);
	 createSubScene();
	 createButtons();
	 createBackground();
	 createLogo();

	 
 }
//ensure that after another button is clicked, the previous clicked subscene will hide
 
 private void showSubScene(ZombieGameSubScene subScene) {
	 if(sceneToHide!= null) {
		 sceneToHide.moveSubScene(); 
	 }
	 
	 //show new subscene
	 subScene.moveSubScene();
	 sceneToHide = subScene;
 }
 
 //a method that initializes our subscenes
 
 private void createSubScene() {
	
	 helpSubScene = new ZombieGameSubScene();
	 mainPane.getChildren().add(helpSubScene);
	 
	
	 
	 
	 createPlayerChooserSubScene();
 }
 
 private void createPlayerChooserSubScene() {
	 playerChooserSubScene = new ZombieGameSubScene();
	 //add to main pane
	 mainPane.getChildren().add(playerChooserSubScene);
	 
	 //create information label
	 
	 InformationLabel choosePlayerLabel = new InformationLabel("CHOOSE YOUR PLAYER");
	 choosePlayerLabel.setLayoutX(110);
	 choosePlayerLabel.setLayoutY(25);;
	 playerChooserSubScene.getPane().getChildren().add(choosePlayerLabel);
	 playerChooserSubScene.getPane().getChildren().add(createPlayerToChoose());
	 playerChooserSubScene.getPane().getChildren().add(createButtonToStart());
 }
 
 //create the ships that are able to be chosen
 private HBox createPlayerToChoose() {
	 HBox box = new HBox();
	 box.setSpacing(20);
	 //initialize the player picker list
	 playersList = new ArrayList<>();
	 for(PLAYER player : PLAYER.values()) {
		 PlayerPicker playerToPick = new PlayerPicker(player);
		 //add each ship picker to list
		 playersList.add(playerToPick);
		 //add plater to box
		 box.getChildren().add(playerToPick);
		 //set an action for mouse click action
		 playerToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for(PlayerPicker player : playersList) {
					player.setIsCircleChosen(false);
				}
				playerToPick.setIsCircleChosen(true);
				chosenPlayer = playerToPick.getPlayer();
			}
			 
		 });
		 
		 
	 }
	 //set box layout x and y positions
	 box.setLayoutX(175);
	 box.setLayoutY(115);
	 return box;
 }
 private ZombieGameButton createButtonToStart() {
	 ZombieGameButton startButton = new ZombieGameButton("START");
	 startButton.setLayoutX(300);
	 startButton.setLayoutY(300);
	 
	 //begin game view after clicking start button
	 startButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			if(chosenPlayer != null) {
				GameView game = new GameView();
				game.createNewGame(mainStage, chosenPlayer);
			}
			
		}
		 
	 });
	 return startButton; 
 }
 	
 
 //a method that returns our main stage
 public Stage getMainStage() {
	 return mainStage;
 }
 
 //method that will help us to add menu buttons
 
 private void addMenuButton(ZombieGameButton button) {
	 button.setLayoutX(MENU_BUTTONS_START_X);
	 button.setLayoutY(MENU_BUTTONS_START_Y + menuButton.size() * 100);
	 menuButton.add(button);
	 mainPane.getChildren().add(button);
 }
 
 
 //create button
 private void createButtons() {
	 //call all 5 menu buttons
	 createStartButton();
	 createExitButton();
	
 }
 //method for start button
 private void createStartButton() {
	 ZombieGameButton startButton = new ZombieGameButton("PLAY");
	 addMenuButton(startButton);
	 
	 //add subscene to corresponding button
	 startButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			showSubScene(playerChooserSubScene);
			
		}
		 
	 });
 }
 
 
 //method for exit button
 private void createExitButton() {
	 ZombieGameButton exitButton = new ZombieGameButton("EXIT");
	 addMenuButton(exitButton);
	 
	 //implement exit logic
	 
	 exitButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			//Close main stage
			mainStage.close();
			
		}
		 
	 });
 }
 
 //a method to set the background image
 private void createBackground() {
	 Image backgroundImage = new Image("view/resources/Once-upon-a-Zombie-image-once-upon-a-zombie-36574221-1000-845.png",1100,900,false,true);
	 BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
	 mainPane.setBackground(new Background(background));
 }
 
 //create logo
 private void createLogo() {
	 ImageView logo = new ImageView("view/resources/picturetopeople.org-65706887442032c1a1761762cc4336f4bfab715d66cf71f529.png");
	 logo.setLayoutX(80);
	 logo.setLayoutY(10);
	 
	 //add logo to main pane
	 mainPane.getChildren().add(logo);
 }

}