import java.util.Map;
import java.util.Scanner;
import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable
{
    private String name;
    private String username;
    private String password;
    private String element;
    private int level;
    private int coins;
    private Hero currentHero;
    private Shield currentShield;
    private HashMap<String, Hero> heroes; 
    private HashMap<String, Power> powers;
    private HashMap<String, Shield> shields;

    public Player(String name, String username, String password, String element) 
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.element = element;
        this.level = 1;
        this.coins = 1000;
        this.powers = new HashMap<>();
        this.shields = new HashMap<>();
        this.heroes = new HashMap<>();

        // Give starting hero (Kael Draven)
        this.currentHero = new Hero("H1", "Kael Draven", "Fire", 10, 10, 0.1f);
        this.addHero(currentHero);

        // Give starting power (Fire Burst)
        this.powers.put("P1", new Power("P1","Fire Burst", "Fire", 100,100, 6));
        this.currentShield = null;
    }

    // Getters and setters
    public String getName() 
    {
        return name;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getElement() 
    {
        return element;
    }

    public int getLevel() 
    {
        return level;
    }

    public int getCoins() 
    {
        return coins;
    }

    public Hero getCurrentHero() 
    {
        return currentHero;
    }

    public Shield getCurrentShield() 
    {
        return currentShield;
    }

    public void addCoins(int amount) 
    {
        coins += amount;
    }

    public boolean spendCoins(int amount) 
    {
        if (coins >= amount) 
        {
            coins -= amount;
            return true;
        }
        return false;
    }

    public void levelUp() 
    {
        level++;
    }

    public void addPower(Power power) 
    {
        powers.put(power.getId(), power);
    }

    public HashMap<String, Power> getPowers() 
    {
        return powers;
    }

    public void addShield(Shield shield) 
    {
        shields.put(shield.getId(), shield);
    }

    public HashMap<String, Shield> getShields() 
    {
        return shields;
    }

    public void addHero(Hero hero) 
    {
        heroes.put(hero.getId(), hero);
    }

    public HashMap<String, Hero> getHeroes() 
    {
        return heroes;
    }

    public void setCurrentHero() 
    {
        System.out.println("Select a hero to set as current:");
        if (heroes.isEmpty()) 
        {
            System.out.println("No heroes available.");
            return;
        }
        else 
        {
            for (Map.Entry<String, Hero> entry : heroes.entrySet()) 
            {
                System.out.println(entry.getKey() + ": " + entry.getValue().getName());
            }
        }
        System.out.print("Enter hero id: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (heroes.containsKey(choice))
        {
            this.currentHero = heroes.get(choice);
            System.out.println("Current hero set to: " + currentHero.getName());
            return;
        }
        else 
        {
            System.out.println("Invalid hero id.");
        }
    }

    public void setCurrentShield()
    {
        System.out.println("Select a shield to set as current:");
        if (shields.isEmpty()) 
        {
            System.out.println("No shields available.");
            return;
        }
        else 
        {
            for (Map.Entry<String, Shield> entry : shields.entrySet()) 
            {
                System.out.println(entry.getKey() + ": " + entry.getValue().getName());
            }
        }
        System.out.print("Enter shield id: ");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (shields.containsKey(choice))
        {
            this.currentShield = shields.get(choice);
            System.out.println("Current Shield set to: " + currentShield.getName());
            return;
        }
        else 
        {
            System.out.println("Invalid shield id.");
        }
    }
}
