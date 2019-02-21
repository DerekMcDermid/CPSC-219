public class Game{
  private final Player player = new Player; //check class.

  public void play(){
    System.out.println("You are an individual ??? kill zombies");
    Town.newInstance().startQuest(player);
  }

  public static void main(String args[]){
    Game game = new Game();
    game.play();
