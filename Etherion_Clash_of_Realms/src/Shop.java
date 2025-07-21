import java.util.Scanner;
import java.io.Serializable;
import java.util.HashMap;

public class Shop implements Serializable
{
    private GameDataLoader dataLoader;
    private Player player;
    private Scanner scanner;

    public Shop(GameDataLoader dataLoader, Player player, Scanner scanner)
    {
        this.dataLoader = dataLoader;
        this.player = player;
        this.scanner = scanner;
    }

    public void openShop() 
    {
        System.out.println("\n=== Shop ===");

        while (true) 
        {
            System.out.println("1. Buy Special Powers");
            System.out.println("2. Buy Shields");
            System.out.println("3. Buy Hero Characters");
            System.out.println("4. Return");
            System.out.print("Your choice: ");

            int shopChoice = scanner.nextInt();
            scanner.nextLine();

            switch (shopChoice) {
                case 1 -> {
                    HashMap<String, Power> powers = dataLoader.getPowers();
                    for (Power p: powers.values()) 
                    {
                        System.out.println(p.toString());
                    }

                    System.out.print("Enter power id to buy: ");
                    String index = scanner.next();
                    Power selected = dataLoader.searchPowerById(index);

                    if (selected == null) 
                    {
                        System.out.println("Power not found.");
                        continue;
                    }

                    if (player.getPowers().containsKey(selected.getId())) 
                    {
                        System.out.println("You already own this power.");
                        continue;
                    }

                    if (player.getCoins() >= selected.getPrice() && player.getPowers().size() < 4) 
                    {
                        boolean spend = player.spendCoins(selected.getPrice());
                        if (spend)
                        {
                            player.addPower(selected);
                            System.out.println("Power bought successfully.");
                        }
                        else 
                        {
                            System.out.println("Error Shopping.");
                        }
                        
                    } 
                    else 
                    {
                        System.out.println("Not enough coins or already 4 powers.");
                    }
                }

                case 2 -> {
                    HashMap<String, Shield> shields = dataLoader.getShields();
                    for (Shield s: shields.values()) 
                    {
                        System.out.println(s.toString());
                        
                    }
                    System.out.print("Enter shield id to buy: ");
                    String index = scanner.next();
                    Shield selected = dataLoader.searchShieldById(index);

                    if (player.getShields().containsKey(selected.getId())) 
                    {
                        System.out.println("You already own this shield.");
                        continue;
                    }

                    if (player.getLevel() >= selected.getUnlockMission()) 
                    {
                        player.addShield(selected);
                        System.out.println("Shield bought successfully.");
                        player.setCurrentShield();
                    } 
                    else 
                    {
                        System.out.println("Your level is not high enough to buy this shield.");
                    }
                    
                }

                case 3 -> {
                    HashMap<String, Hero> heroes = dataLoader.getHeroes();
                    for (Hero h: heroes.values()) 
                    {
                        System.out.println(h.toString());
                    }

                    System.out.print("Enter hero id to buy: ");
                    String index = scanner.next();

                    Hero selected = dataLoader.searchHeroById(index);

                    if (player.getHeroes().containsKey(selected.getId())) 
                    {
                        System.out.println("You already own this Character.");
                        continue;
                    }
                       
                    if (player.getCoins() >= selected.getPrice()) 
                    {
                        boolean spend = player.spendCoins(selected.getPrice());
                        if (spend)
                        {
                            player.addHero(selected);
                            System.out.println("Hero Character bought successfully.");
                            player.setCurrentHero();
                        }
                        else 
                        {
                            System.out.println("Error Shopping.");
                        }
                        
                    } else 
                    {
                        System.out.println("Not enough coins. You need " + (selected.getPrice() - player.getCoins()) + " more coins.");
                    }
                }

                case 4 -> {
                    System.out.println("Returning to menu.");
                    return;
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }

}
