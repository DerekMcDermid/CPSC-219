package model;
//All code for constructing the main menu of our game was inspired by a series of youtube tutorials Website: https://www.youtube.com/watch?v=6BKI26gxK2Q&t=23s Author: javacraving
//the enum type stores a url for each player image

public enum PLAYER {
	SCOUT("view/resources/playerChooser/female_stand.png"),
	BRUISER("view/resources/playerChooser/soldier_stand.png"),
	SURVIVALIST("view/resources/playerChooser/player_stand.png"),
	ADVENTURER("view/resources/playerChooser/adventurer_stand.png");

	private String urlPlayer;
	
	private PLAYER(String urlPlayer) {
		this.urlPlayer = urlPlayer;
	}
	
	//return the url
	public String getUrl() {
		return this.urlPlayer;
	}
}
