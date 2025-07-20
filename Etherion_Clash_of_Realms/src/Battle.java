import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.Serializable;
import java.util.HashMap;

public class Battle implements Serializable{
    private Player player;
    private GameDataLoader dataLoader;

    public Battle(Player player, GameDataLoader dataLoader) {
        this.player = player;
        this.dataLoader = dataLoader;
    }

    protected void start() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Battle ===");

        // Get current mission based on player level
        int level = (player.getLevel());
        String missionIndex = "M" + String.valueOf(level);
        String villainIndex = "V" + String.valueOf(level);

        if (level >= dataLoader.getMissions().size()) {
            System.out.println("All missions completed! No more battles.");
            scanner.close();
            return;
        }

        Mission mission = dataLoader.getMissions().get(missionIndex);
        Villain villain = dataLoader.getVillains().get(villainIndex);
        Character hero = player.getCurrentHero();

        double playerHealth = 100.0;
        double villainHealth = 100.0;

        System.out.println("Mission: " + mission.getName());
        System.out.println("Enemy: " + villain.getName());

        while (playerHealth > 0 && villainHealth > 0) {
            // Player turn
            System.out.println("\nYour turn:");
            System.out.println("1. Normal Punch (+50)");
            System.out.println("2. Use Special Power");
            System.out.print("Choice: ");
            int move = scanner.nextInt();
            //scanner.nextLine();

            Power power;
            if (move == 2) {
                power = choosePower();
                if (power == null) {
                    System.out.println("Falling back to normal punch.");
                    power = new Power("P0","Normal Punch", "Neutral", 50, 0, 999);
                }
            } else {
                power = new Power("P0","Normal Punch", "Neutral", 50, 0, 999);
            }

            Shield V_shield = dataLoader.searchShieldById(villain.getShieldId());

            double damageToVillain = calculateDamage(hero, villain, power,V_shield);
            villainHealth -= damageToVillain;
            System.out.printf("You dealt %.2f damage. Villain HP: %.2f\n", damageToVillain, Math.max(0, villainHealth));

            if (villainHealth <= 0)
                break;

            // Villain turn
            System.out.println("\nVillain's turn...");
            List<String> villainPowerIds = villain.getPowerIds();

            Random rand = new Random();
            Power randomPower = dataLoader.searchPowerById(villainPowerIds.get(rand.nextInt(villainPowerIds.size())));
            System.out.println("Randomly selected power: " + randomPower.getName());

            //Power villainPower = dataLoader.searchPowerById(villainPowerIds.get(0)); // You can randomize later
            
            double damageToPlayer = calculateDamage(villain, hero, randomPower, player.getCurrentShield());
            playerHealth -= damageToPlayer;
            System.out.printf("Villain dealt %.2f damage. Your HP: %.2f\n", damageToPlayer, Math.max(0, playerHealth));
        }

        if (playerHealth > 0) {
            System.out.println("\n✅ You WON!");
            player.levelUp();
            player.addCoins(500); // or based on mission
            System.out.println("You leveled up to " + player.getLevel() + " and earned 500 coins!");
        } else {
            System.out.println("\n❌ You LOST...");
            player.spendCoins(100); // 20% logic can be improved
            System.out.println("You lost 100 coins.");
        }
        System.out.println("Battle ended. Returning to main menu.");
        return;
    }

    private Power choosePower() {
        HashMap<String, Power> powers = player.getPowers();
        if (powers.isEmpty()) {
            System.out.println("No powers available.");
            return null;
        }

        System.out.println("Choose a power id:");
        for (Map.Entry<String, Power> entry : powers.entrySet()) 
        {
            System.out.println(
                    entry.getKey() + ". " + entry.getValue().getName() + " (" + String.valueOf(entry.getValue().getUsesRemaining()) + " uses left)");
        }
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (powers.containsKey(choice)) 
        {
            Power selected = powers.get(choice);
            if (selected.getUsesRemaining() > 0) 
            {
                selected.use(); // reduce use
                return selected;
            } 
            else 
            {
                System.out.println("No uses left for this power!");
            }
        }
        scanner.close();
        return null;
    }

    private double calculateDamage(Character attacker, Character defender, Power power, Shield opponentShield)       
    {
        double attackPart = attacker.getAttack() * power.getAttackValue();
        double speedBoost = 1 + attacker.getSpeed();
        double numerator = attackPart * speedBoost;

        double shieldDefense = (opponentShield != null) ? opponentShield.getDefenseValue() : 0;
        double dodgeFactor = 10 * (1 - defender.getSpeed());
        double denominator = defender.getDefense() + shieldDefense + dodgeFactor;

        return numerator / Math.max(1.0, denominator);
    }

}
