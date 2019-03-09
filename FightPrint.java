package Project;
public class FightPrint{

  private static String finishingDescription;

  public static String printOutcome(int outcome, Player player, String name) {
    if (player.getDescription() == "the Scout"){
      if (outcome == 1){
          System.out.println(player + " lands a direct shot on the " + name + " with their shotgun blasting it apart \n\n");
      }
      if (outcome == 2){
          System.out.println(player + " hits the " + name + " with the butt end of their shotgun creating an audible crunch at impact\n\n");
      }
      if (outcome == 3){
          System.out.println(player + " Shoots the " + name + " in the head destroying its skull \n\n");
      }
  }

    if (player.getDescription() == "the Bruiser"){
      if (outcome == 1){
          System.out.println(player + " kicks the " + name + "hard in the chest sending the " + name + " across the room \n\n");
      }
      if (outcome == 2){
          System.out.println(player + " grabs the " + name + " and slams it onto the foor so hard that the boards crack beneath the zombie \n\n");
      }
      if (outcome == 3){
          System.out.println(player + " kicks the zombie's legs out causing the " + name + " to fall over then " + player + " stomps on the zombie \n\n");
      }
  }

    if (player.getDescription() == "the Survivalist"){
      if (outcome == 1){
          System.out.println(player + " swings their woodcutter's axe over their head then brings it down on the zombie's head \n\n");
      }
      if (outcome == 2){
          System.out.println(player + " swings their axe hiting the " + name + " in the side and chopping it in half \n\n");
      }
      if (outcome == 3){
          System.out.println(player + " hits the zombie in the jaw with the backside of their axe breifly launching it in the air \n\n");
      }
  }
    if (player.getDescription() == "a musclebound hulk intent on crushing all evil in his way"){
        finishingDescription = player + " incinerates the zombie with a lightning strike";
    }
    return finishingDescription;
  }
}
