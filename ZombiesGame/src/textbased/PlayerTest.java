import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
	
	// Defining test interfaces 
	
	private String name;
	
	public Player testInterfaceScout() {
		name = "TestScoutPlayer";
		Player scoutPlayer = new Player(name, "the Scout", 25, 15, 25, 6);
		return scoutPlayer;
	}
	
	public Player testInterfaceBruiser() {
		name = "TestBruiserPlayer";
		Player bruiserPlayer = new Player(name, "the Bruiser", 65, 5, 12, 12);
		return bruiserPlayer;
	}
	
	public Player testInterfaceSurvivalist() {
		name = "TestSurvivalistPlayer";
		Player survPlayer = new Player(name, "the Survivalist", 40, 6, 17, 9);
		return survPlayer;
	}
	
	// Testing getStatus method
	
	@Test
	public void scoutGetStatusTest() {
		Player scoutPlayer = testInterfaceScout();
		
		int actualHealth = scoutPlayer.getHitPoints();
		int expectedHealth = 25;
		
	    assertEquals("The health of the player(a scout) should be 25.", actualHealth, expectedHealth);
	}

	@Test
	public void bruiserHealthGetStatusTest() {
		Player bruiserPlayer = testInterfaceBruiser();
		
		int actualHealth = bruiserPlayer.getHitPoints();
		int expectedHealth = 65;
		
	    assertEquals("The health of the player(a bruiser) should be 65.", actualHealth, expectedHealth);
	}
	
	@Test
	public void survivalistHealthGetStatusTest() {
		Player survPlayer = testInterfaceSurvivalist();
		
		int actualHealth = survPlayer.getHitPoints();
		int expectedHealth = 40;
		
	    assertEquals("The health of the player(a survivalist) should be 40.", actualHealth, expectedHealth);
	}
	
	// Testing heal method
	
	@Test
	public void scoutHealFullHealthTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.heal();
		
		int actualHealth = scoutPlayer.getHitPoints();
		int expectedHealth = 25;
		
	    assertEquals("HitPoints should not exceed the maximum value.", actualHealth, expectedHealth);
	}
	
	@Test
	public void healDamagedTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.setStatus(1);
		scoutPlayer.heal();
		
		int actualHealth = scoutPlayer.getHitPoints();
		int expectedHealth = 21;
		
	    assertEquals("HitPoints should increase when heal is applied to player.", actualHealth, expectedHealth);
	}
	
	// Testing numBandages 
	
	@Test
	public void healUseBandagesTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();

		assertEquals("There should be 3 bandages left in the player's inventory.", 3, scoutPlayer.getNumBandages());
	}
	
	@Test
	public void healUseBandagesTest2() {
		Player survPlayer = testInterfaceSurvivalist();
		survPlayer.heal();
		survPlayer.heal();
		survPlayer.heal();

		assertEquals("There should be 6 bandages left in the player's inventory.", 6, survPlayer.getNumBandages());
	}
	
	@Test
	public void healUseBandagesTest3() {
		Player bruiserPlayer = testInterfaceBruiser();
		bruiserPlayer.heal();
		bruiserPlayer.heal();
		bruiserPlayer.heal();
		bruiserPlayer.heal();
		bruiserPlayer.heal();

		assertEquals("There should be 7 bandages left in the player's inventory.", 7, bruiserPlayer.getNumBandages());
	}
	
	@Test
	public void healUseAllBandagesTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();

		assertEquals("There should be no bandages left in the player's inventory.", 0, scoutPlayer.getNumBandages());
	}
	
	@Test
	public void healExceedBandageAmountTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();
		scoutPlayer.heal();

		assertEquals("There should not be a negative amount of bandages left.", 0, scoutPlayer.getNumBandages());
	}
	
	// Testing isAlive method
	
	@Test
	public void isAliveNewPlayerTest() {
		Player scoutPlayer = testInterfaceScout();
		
		assertTrue("The player should be alive, there has been no combat.",true == scoutPlayer.isAlive());
	}
	
	@Test
	public void isAliveDeadPlayerTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.setStatus(-1);
		
		assertTrue("The player's health fell below zero, and should not be alive.",false == scoutPlayer.isAlive());
	}
	
	@Test
	public void isAliveZeroHitPointsTest() {
		Player scoutPlayer = testInterfaceScout();
		scoutPlayer.setStatus(0);
		
		assertTrue("The player's health is zero, and should be dead.",false == scoutPlayer.isAlive());
	}
	
	// Testing attack methods for player
	@Test
	public void attackBruiserTest() {
		Player bruiserPlayer = testInterfaceBruiser();
		int attackValue = bruiserPlayer.attack();
		int minDamage = 5;
		int maxDamage = 12;
		
		assertTrue("The attack value should be greater than or equal to the minimum damage that a bruiser can inflict.", attackValue >= minDamage);
		assertTrue("The attack value should be lesser than or equal to the maximum damage that a bruiser can inflict.", attackValue <= maxDamage);
	}
	
	@Test
	public void attackSurvivalistTest() {
		Player survPlayer = testInterfaceSurvivalist();
		int attackValue = survPlayer.attack();
		int minDamage = 6;
		int maxDamage = 17;
		
		assertTrue("The attack value should be greater than or equal to the minimum damage that a survivalist can inflict.", attackValue >= minDamage);
		assertTrue("The attack value should be lesser than or equal to the maximum damage that a survivalist can inflict.", attackValue <= maxDamage);
	}
	
	@Test
	public void attackScoutTest() {
		Player scoutPlayer = testInterfaceScout();
		int attackValue = scoutPlayer.attack();
		int minDamage = 15;
		int maxDamage = 25;
		
		assertTrue("The attack value should be greater than or equal to the minimum damage that a scout can inflict.", attackValue >= minDamage);
		assertTrue("The attack value should be lesser than or equal to the maximum damage that a scout can inflict.", attackValue <= maxDamage);
	}
	
	// Testing defend methods
	@Test
	public void combatPlayerVersusZombieTest() {
		Zombie testZombie = new Zombie("Creeper", 40, 8, 12);
		Player bruiserPlayer = testInterfaceBruiser();
		
		bruiserPlayer.defend(testZombie);
		testZombie.defend(bruiserPlayer);
		
		assertTrue("Player health should be reduced in combat.", bruiserPlayer.getHitPoints() < 65);
		assertTrue("Zombie health should be reduced in combat.", testZombie.getHitPoints() < 40);
	}
	
	@Test
	public void combatPlayerVersusZombie2Test() {
		Zombie testZombie = new Zombie("Tainter", 30, 3, 5);
		Player bruiserPlayer = testInterfaceBruiser();
		
		bruiserPlayer.defend(testZombie);
		testZombie.defend(bruiserPlayer);
		
		assertTrue("Player health should be reduced in combat.", bruiserPlayer.getHitPoints() < 65);
		assertTrue("Zombie health should be reduced in combat.", testZombie.getHitPoints() < 30);
	}
	
	@Test
	public void combatPlayerVersusZombie3Test() {
		Zombie testZombie = new Zombie("Gnawer", 18, 1, 2);
		Player bruiserPlayer = testInterfaceBruiser();
		
		bruiserPlayer.defend(testZombie);
		testZombie.defend(bruiserPlayer);
		
		assertTrue("Player health should be reduced in combat.", bruiserPlayer.getHitPoints() < 65);
		assertTrue("Zombie health should be reduced in combat.", testZombie.getHitPoints() < 18);
	}
	
	@Test
	public void healAfterCombatTest() {
		Zombie testZombie = new Zombie("Tainter", 30, 3, 5);
		Player scoutPlayer = testInterfaceScout();
		
		int initialHealth = scoutPlayer.getHitPoints();
		
		scoutPlayer.defend(testZombie);
		testZombie.defend(scoutPlayer);
		
		int afterAttackHealth = scoutPlayer.getHitPoints();
		int difference = initialHealth - afterAttackHealth;
		
		scoutPlayer.heal();
		int afterHealHealth = scoutPlayer.getHitPoints();
		
		int difference2 = afterHealHealth - afterAttackHealth;
		
	    assertTrue("HitPoints should increase when heal is applied to player.", difference2 >= difference);
	}
}
