package view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PLAYER;
import model.SmallLifeLabel;



public class GameView {
 //create a separate stage, scene and pane for our game view
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;

	//define stage width and height
	
	private static final int GAME_WIDTH = 900;
	private static final int GAME_HEIGHT = 800;

	
	//create a field for menu stage and image view for ship
	private Stage menuStage;
	private ImageView player;
	
	//animation - 4 booleans that will be controlled by left, right, up and down arrow keys
	 private boolean isLeftKeyPressed;
	 private boolean isRightKeyPressed;
	 private boolean isUpKeyPressed;
	 private boolean isDownKeyPressed;
	 private int angle;
	 private AnimationTimer gameTimer;
	 
	 //create a grid pane that will allow us to store our small image as the entire background
	 private GridPane gridPane1;
	 private GridPane gridPane2;
	 
	 //private string for background image URL
	 private final static String BACKGROUND_IMAGE = "view/resources/grass2.png";
	 
	 private final static String ZOMBIE_IMAGE = "view/resources/zoimbie1_hold.png";
	 
	 private ImageView[] zombie;
	 
	 Random randomPositionGenerator;
	 
	 private SmallLifeLabel pointsLabel;
	 private ImageView[] playerLives;
	 private int playerLife;
	 private int points;
	 
	//define constructor
	public GameView() {
		InitializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
	}
	//create key listeners to see which keys are pressed or released

	private void createKeyListeners() {
		
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				//check which key was pressed or released
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
				} else if (event.getCode() ==KeyCode.RIGHT) {
					isRightKeyPressed = true;
				} else if (event.getCode() ==KeyCode.UP) {
					isUpKeyPressed = true;
				} else if (event.getCode() ==KeyCode.DOWN) {
					isDownKeyPressed = true;
				}
				
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() ==KeyCode.RIGHT) {
					isRightKeyPressed = false;
				} else if (event.getCode() ==KeyCode.UP) {
					isUpKeyPressed = false;
				} else if (event.getCode() ==KeyCode.DOWN) {
					isDownKeyPressed = false;
				}
				
			}
			
		});
		
	}

	private void InitializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		//initialize game stage
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
	
		
	}
	//method that hides the menu window and shows the game window
	public void createNewGame(Stage menuStage, PLAYER chosenPlayer) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createPlayer(chosenPlayer);
		createGameElements(chosenPlayer);
		createGameLoop();
		gameStage.show();
	}
	private void createGameElements(PLAYER chosenPlayer) {
		playerLife = 2;
		pointsLabel = new SmallLifeLabel("POINTS: 00");
		pointsLabel.setLayoutX(760);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		playerLives = new ImageView[3];
		
		for(int i = 0; i < playerLives.length; i++) {
			playerLives[i] = new ImageView(chosenPlayer.getUrlLife());
			playerLives[i].setLayoutX(765 + (i * 50));;
			playerLives[i].setLayoutY(80);;
			gamePane.getChildren().add(playerLives[i]);
		}
	}
	
	private void createPlayer(PLAYER chosenPlayer) {
		player = new ImageView(chosenPlayer.getUrl());
		//starting position for player
		player.setLayoutX(GAME_WIDTH/2);
		player.setLayoutY(GAME_HEIGHT - 150 );
		gamePane.getChildren().add(player);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				movePlayer();
				
			}
			
		};
		
		gameTimer.start();
	}
	
	private void movePlayer() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(player.getLayoutX() > -20) {
				player.setLayoutX(player.getLayoutX() -4);
			}
		}
			
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(player.getLayoutX() < 859) {
			player.setLayoutX(player.getLayoutX() +4);
			}
		}
		
		if(isUpKeyPressed) {
			if(player.getLayoutY() >0)
			player.setLayoutY(player.getLayoutY() -4);
		}
		if(isDownKeyPressed) {
			if(player.getLayoutY() <760)
			player.setLayoutY(player.getLayoutY() +4);
		}
	}
	
	//method that will create background 
	private void createBackground() {
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		//each grid pane will contain 12 images to cover entire background 
		for(int i = 0; i < 12; i++) {
			ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
			ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
			GridPane.setConstraints(backgroundImage1, i% 3, i / 3);
			GridPane.setConstraints(backgroundImage2, i% 3, i / 3);
			gridPane1.getChildren().add(backgroundImage1);
			gridPane2.getChildren().add(backgroundImage2);
			}
		
		gridPane2.setLayoutY(-1024);
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}
}
	

