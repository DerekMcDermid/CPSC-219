package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PLAYER;

public class GameView {
 //create a separate stage, scene and pane for our game view
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	//define stage width and height
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	//create a field for menu stage and image view for ship
	private Stage menuStage;
	private ImageView player;
	
	//animation - 4 booleans that will be controlled by left, right, up and down arrow keys
	 private boolean isLeftKeyPressed;
	 private boolean isRightKeyPressed;
	 private boolean isUpKeyPressed;
	 private boolean isDownKeyPressed;
	 private AnimationTimer gameTimer;
	 
	//define constructor
	public GameView() {
		InitializeStage();
		createKeyListeners();
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
		createPlayer(chosenPlayer);
		gameStage.show();
	}
	
	private void createPlayer(PLAYER chosenPlayer) {
		player = new ImageView(chosenPlayer.getUrl());
		//starting position for player
		player.setLayoutX(GAME_WIDTH/2);
		player.setLayoutY(GAME_HEIGHT - 150 );
		gamePane.getChildren().add(player);
	}
	
	
	
}
