package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ZombieGameButton extends Button {
 //create a constant variable for the path of our font and one to define a style for our buttons when they are pressed and released
	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/green_button00.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/green_button01.png');";

	//create constructor that passes a string that will be shown
	public ZombieGameButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}
	
	//method that sets our font
	
	private void setButtonFont() {
	
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
	
		} catch (FileNotFoundException e) {
		setFont(Font.font("Verdana", 23));
	}
}
	//create a method of setting pressed type and one for released type
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		//make the pressed button height 4 pixels smaller than the released one
		setLayoutY(getLayoutY() - 4);
	}
	
	//create listeners: when mouse is pressed, released, enters and leaves
	private void initializeButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
				
			}
		});
		
		//create drop shadow effect when mouse enters
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
				
			}
	});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}
	});
	}
		
}

