package model;


//the enum type stores a url for each player image

public enum PLAYER {
	SCOUT("view/resources/playerChooser/soldier1_machine.png","view/resources/playerChooser/soldier1_life.png"),
	BRUISER("view/resources/playerChooser/survivor1_machine.png","view/resources/playerChooser/survivor1_life.png"),
	SURVIVALIST("view/resources/playerChooser/manBlue_machine.png","view/resources/playerChooser/manBlue_life.png"),
	ADVENTURER("view/resources/playerChooser/womanGreen_machine.png","view/resources/playerChooser/womanGreen_life.png");

	private String urlPlayer;
	private String urlLife;
	
	private PLAYER(String urlPlayer, String urlLife) {
		this.urlPlayer = urlPlayer;
		this.urlLife = urlLife;
	}
	
	//return the url
	public String getUrl() {
		return this.urlPlayer;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
}
