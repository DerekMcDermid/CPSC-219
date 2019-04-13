package view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PLAYER;
import model.SmallLifeLabel;


//set a radius around elements for collision 

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
	 private boolean isAKeyPressed;
	 private boolean isDKeyPressed;
	 private boolean isWKeyPressed;
	 private boolean isSKeyPressed;
	 private boolean isJKeyPressed;
	 private boolean isLKeyPressed;
	 private boolean isKKeyPressed;
	 private int angle;
	 private AnimationTimer gameTimer;
	 
	 //create a grid pane that will allow us to store our small image as the entire background
	 private GridPane gridPane1;
	 private GridPane gridPane2;
	 
	 //private string for background image URL
	 private final static String BACKGROUND_IMAGE = "view/resources/grass2.png";
	 
	 private final static String ZOMBIE_IMAGE = "view/resources/zoimbie1_hold.png";
	
	
	 private Image backgroundImage, zombieImage, bulletImage;
	 private ArrayList<Node> bullet = new ArrayList<Node>();
	 private Group board;
	 Text livesText;
	 private ImageView[] zombie;
	 Random randomPositionGenerator;
	//create throwing variable so that player can't hold down k to shoot
	 boolean throwing;
	 

	
	//create star for bonus points
	private ImageView star;
	 
	private SmallLifeLabel pointsLabel;	 
	private ImageView[] playerLives;
	private int playerLife;
	private int points;
	private final static String STAR_IMAGE = "view/resources/star_PNG41493.png";
	
	private final static int STAR_RADIUS = 15; 
	private final static int PLAYER_RADIUS = 23;
	private final static int ZOMBIE_RADIUS = 15;
	 
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
				if(event.getCode() == KeyCode.A) {
					isAKeyPressed = true;
				} else if (event.getCode() ==KeyCode.D) {
					isDKeyPressed = true;
				} else if (event.getCode() ==KeyCode.W) {
					isWKeyPressed = true;
				} else if (event.getCode() ==KeyCode.S) {
					isSKeyPressed = true;
				} else if(event.getCode() ==KeyCode.J) {
					isJKeyPressed = true;
				} else if(event.getCode() ==KeyCode.L) {
					isLKeyPressed = true;
				}
				else if(event.getCode() == KeyCode.K) {
					isKKeyPressed = true;
					if (!throwing) {
					ImageView aBullet = new ImageView(bulletImage);
			 		Node newBullet = aBullet;
			 		newBullet.relocate(player.getLayoutX() + player.getBoundsInLocal().getWidth(), player.getLayoutY());
			 		bullet.add(newBullet);
			 		board.getChildren().add(newBullet);
			 		throwing = true;
					}
			}

				
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.A) {
					isAKeyPressed = false;
				} else if (event.getCode() ==KeyCode.D) {
					isDKeyPressed = false;
				} else if (event.getCode() ==KeyCode.W) {
					isWKeyPressed = false;
				} else if (event.getCode() ==KeyCode.S) {
					isSKeyPressed = false;
				} else if(event.getCode() ==KeyCode.J) {
					isJKeyPressed = false;
				} else if(event.getCode() ==KeyCode.L) {
					isLKeyPressed = false;
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
		createGameElements(chosenPlayer);
		createGameLoop();
		gameStage.show();
	}
	
	private void moveGameElements() {
		//make star move 
		star.setLayoutY(star.getLayoutY() + 5);
		//make zombies move
		for(int i = 0; i < zombie.length; i++) {
			zombie[i].setLayoutY(zombie[i].getLayoutY()+3); 
		}
	}
	
	//check if zombies are below screen. if so, locate them over the top
	
	private void checkIfElementsBehindPlayerAndRelocate() {
		//relocate star
		if(star.getLayoutY() > 900) {
			setNewElementposition(star);
		}
		for(int i = 0; i < zombie.length; i++) {
			if(zombie[i].getLayoutY() > 800) {
				setNewElementposition(zombie[i]);
			}
		}
	}
	private void setNewElementposition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(875));;
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}
	private void createGameElements(PLAYER chosenPlayer) {
		playerLife = 2;
		star = new ImageView(STAR_IMAGE);
		setNewElementposition(star);
		gamePane.getChildren().add(star);
		pointsLabel = new SmallLifeLabel("POINTS: 00");
		pointsLabel.setLayoutX(760);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		playerLives = new ImageView[3];
		//create area for lives
		for(int i = 0; i < playerLives.length; i++) {
			playerLives[i] = new ImageView(chosenPlayer.getUrlLife());
			playerLives[i].setLayoutX(765 + (i * 50));
			playerLives[i].setLayoutY(80);
			gamePane.getChildren().add(playerLives[i]);
		}
		
		zombie = new ImageView[35];
		for(int i = 0; i < zombie.length; i++) {
			zombie[i] = new ImageView(ZOMBIE_IMAGE);
			setNewElementposition(zombie[i]);
			gamePane.getChildren().add(zombie[i]);
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
			int dx =0, dy =0;

			@Override
			public void handle(long now) {
				moveBackground();
				moveGameElements();
				checkIfElementsBehindPlayerAndRelocate();
				checkIfElementsCollide();
				movePlayer();
		

			}
			
		};
		
		gameTimer.start();
	}
	
	
	private void movePlayer() {
		if(isAKeyPressed && !isDKeyPressed) {
			if(player.getLayoutX() > -20) {
				player.setLayoutX(player.getLayoutX() -4);
			}
		}
			
		if(isDKeyPressed && !isAKeyPressed) {
			if(player.getLayoutX() < 859) {
			player.setLayoutX(player.getLayoutX() +4);
			}
		}
		
		if(isWKeyPressed) {
			if(player.getLayoutY() >0)
			player.setLayoutY(player.getLayoutY() -4);
		}
		if(isSKeyPressed) {
			if(player.getLayoutY() <760)
			player.setLayoutY(player.getLayoutY() +4);
		} 
		if(isJKeyPressed) {
			angle -=5;
			player.setRotate(angle);
		}
		if(isLKeyPressed) {
			angle +=5;
			player.setRotate(angle);
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
	//making the background move to give the effect of walking across field
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 1);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 1);;
		
		if(gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		if(gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
		
		
	}
	private void checkIfElementsCollide() {
		if(PLAYER_RADIUS + STAR_RADIUS > calculatedDistance(player.getLayoutX() + 60, star.getLayoutX() + 20, player.getLayoutY() + 37, star.getLayoutY() + 15)) {
			setNewElementposition(star);
			
			points++;
			String textToSet = "POINTS : ";
			if (points < 10) {
				textToSet = textToSet + "0";
			}
			pointsLabel.setText(textToSet + points);
		}
		
		
		for(int i = 0; i < zombie.length; i++) {
			if(ZOMBIE_RADIUS + PLAYER_RADIUS > calculatedDistance(player.getLayoutX() + 49, zombie[i].getLayoutX() + 30, player.getLayoutY() + 37, zombie[i].getLayoutY() + 20)) {
			removeLife();
			setNewElementposition(zombie[i]);
			}
		}
	}

	
	private void removeLife() {
		
		gamePane.getChildren().remove(playerLives[playerLife]);
		playerLife --;
		if(playerLife < 0) {
			gameStage.close();
			gameTimer.stop();
			menuStage.show();
			}
	}
	//calculate the distance between two points
	
	private double calculatedDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1-y2, 2));
	}
	
}
