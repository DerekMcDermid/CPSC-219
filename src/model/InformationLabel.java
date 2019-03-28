package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
//All code for constructing the main menu of our game was inspired by a series of youtube tutorials Website: https://www.youtube.com/watch?v=6BKI26gxK2Q&t=23s Author: javacraving

public class InformationLabel extends Label {
//store font url 
	public final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	
	public final static String BACKGROUND_IMAGE = "view/resources/playerChooser/green_button13.png";

	//constructor for text to be shown in this label
	public InformationLabel(String text) {
		setPrefWidth(380);
		setPrefHeight(49);
		setText(text);
		//make text wrap 
		setWrapText(true);
		setLabelFont();
		//center text to small label
		setAlignment(Pos.CENTER);
		
		//set background image
		BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,380, 49, false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
		setBackground(new Background(backgroundImage));
	}
	
	//set label font method
	private void setLabelFont() {
		
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
		} catch (FileNotFoundException e) {
		//if it can not find our font file, we set the font to regular font
			setFont(Font.font("Verdana",23));
			}
	}

}
