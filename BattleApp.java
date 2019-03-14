package Project;
import java.awt.Canvas;
import java.awt.Insets;

import javax.swing.JTextField;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 
//this code is inspired by the code written for individual assignment 5

public class BattleApp extends Application 
{
	
	public static void main(String[] args)
	{
		launch(args);
	}
	public void start(Stage primaryStage) 
	{
		//a 'parent' branch/root that all other branches can reference
		VBox root = new VBox();
		
		//hbox that displays all player information at the top of the screen
		HBox playerInfo = new HBox();

		//add labels for all player information
		Label playerName = new Label("Player Name:			" );
		Label playerMinAttackVal = new Label ("Min Attack:			");
		Label playerMaxAttackVal = new Label ("Max Attack:			");
		Label playerHP = new Label ("HP:			");
		Label playerBandage = new Label ("Bandages remaining:			");

		//retrieve all player information and add it to the label from HBox
		playerInfo.getChildren().add(playerName);
		playerInfo.getChildren().add(playerMinAttackVal);
		playerInfo.getChildren().add(playerMaxAttackVal);
		playerInfo.getChildren().add(playerHP);
		playerInfo.getChildren().add(playerBandage);
		root.getChildren().add(playerInfo);
		
		//create a blank vbox that adds a bunch of blank labels in order to make room in the middle of the window`
	    VBox branchNodeBlank = new VBox();
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        branchNodeBlank.getChildren().add(new Label (""));
        
        //add this hbox to the 'parent' vbox
        
        root.getChildren().add(branchNodeBlank);

        //create an hbox that has all zombie information laid out (horizontally)

		HBox zombieInfo = new HBox();
		playerInfo.getChildren().add(zombieInfo);
		
		Label zombieName = new Label("Zombie Name:			");
		zombieInfo.getChildren().add(zombieName);

		Label zombieMinDamage = new Label("Min Damage:			");
		zombieInfo.getChildren().add(zombieMinDamage);

		Label zombieMaxDamage = new Label("Max Damage:			");
		zombieInfo.getChildren().add(zombieMaxDamage);

		Label zombieHP = new Label("HP:			");
		zombieInfo.getChildren().add(zombieHP);
		
		//add this hbox to the 'parent' vbox
		
		root.getChildren().add(zombieInfo);

		//create an hbox for the heal and attack button options 

		HBox attackHealButton = new HBox();
		playerInfo.getChildren().add(attackHealButton);

		Button attack = new Button("Attack");
		attackHealButton.getChildren().add(attack);
		
		//code used to center the buttons
		
		attackHealButton.setMaxWidth(Double.MAX_VALUE);
	    AnchorPane.setLeftAnchor(attackHealButton, 0.0);
	    AnchorPane.setRightAnchor(attackHealButton, 0.0);
	    attackHealButton.setAlignment(Pos.CENTER);

		Button heal = new Button("Heal");
		attackHealButton.getChildren().add(heal);
		root.getChildren().add(attackHealButton);


		Scene scene = new Scene(root, 700, 300);
		primaryStage.setTitle("Battle");
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	//how to add photo to label http://tutorials.jenkov.com/javafx/label.html


}

