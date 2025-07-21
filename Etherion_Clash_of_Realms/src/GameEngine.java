import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameEngine implements Serializable
{
    private Player player;
    private Scanner scanner;
    private boolean isRunning;
    private GameDataLoader dataLoader;
    private static final String FILE_PATH = "usernames.txt";
    
    public GameEngine() 
    {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.dataLoader = null;
    }
    
    public void startGame() 
    {
        System.out.println("\n\nLoading game data...");
        try 
        {
            dataLoader = new GameDataLoader("D:\\Games\\Etherion_Clash_of_Realms\\Data\\Data_Heros.csv", 
                                            "D:\\Games\\Etherion_Clash_of_Realms\\Data\\Data_Villians.csv", 
                                            "D:\\Games\\Etherion_Clash_of_Realms\\Data\\Data_Mission.csv", 
                                            "D:\\Games\\Etherion_Clash_of_Realms\\Data\\Data_Powers.csv", 
                                            "D:\\Games\\Etherion_Clash_of_Realms\\Data\\Data_Shields.csv");

            System.out.println("Heroes: " + dataLoader.getHeroes());
            System.out.println("Villains: " + dataLoader.getVillains());
            System.out.println("Missions: " + dataLoader.getMissions());
            System.out.println("Powers: " + dataLoader.getPowers());
            System.out.println("Shields: " + dataLoader.getShields());
            System.out.println("\n\nGame data loaded successfully!");
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading game data: " + e.getMessage());
            return;
        }
        System.out.println("=== Etherion: Clash of Realms ===");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch(choice) 
        {
            case 1:
                createNewPlayer();
                break;
            case 2:
                loadGame();
                break;
            case 3:
                isRunning = false;
                break;

            default:
                System.out.println("Invalid choice!");
        }
        
        while(isRunning) 
        {
            mainMenu();
        }
        
        System.out.println("Thanks for playing Etherion: Clash of Realms!");
    }
    
    private void createNewPlayer() 
    {
        System.out.println("\n=== Create New Player ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        
        while (true)
        {
            boolean addUsernameSuccess = addUsername(username);
            if (addUsernameSuccess)
            {
                break; 
            } 
            else 
            {
                System.out.print("Please enter a different username: ");
                username = scanner.nextLine();
            }
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        while (true)
        {
            if (isPasswordValid(password)) 
            {
                break; 
            } 
            else 
            {
                System.out.print("Password must be at least 8 characters long, contain uppercase, lowercase, digits, and symbols. Please enter a valid password: ");
                password = scanner.nextLine();
            }
        }
       
        System.out.println("\nChoose your elemental affinity:");
        System.out.println("1. Fire");
        System.out.println("2. Water");
        System.out.println("3. Earth");
        System.out.println("4. Air");
        System.out.print("Your choice: ");

        int elementChoice = scanner.nextInt();
        
        String element = "";

        switch(elementChoice) 
        {
            case 1: 
                element = "Fire";
                break;

            case 2: 
                element = "Water"; 
                break;

            case 3: 
                element = "Earth"; 
                break;

            case 4: 
                element = "Air";
                break;

            default: 
                element = "Fire"; 
        }
        
        this.player = new Player(name, username, password, element);

        System.out.println("\nWelcome, " + name + "! You have been granted 1000 coins to start your journey.");
    }
    
    private void mainMenu() 
    {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Battle");
        System.out.println("2. Shop");
        System.out.println("3. View Profile");
        System.out.println("4. Save Game");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch(choice) 
        {
            case 1:
                startBattle();
                break;
            case 2:
                Shop();
                break;
            case 3:
                viewProfile();
                break;
            case 4:
                saveGame();
                break;
            case 5:
                isRunning = false;
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void startBattle() 
    {
        Battle battle = new Battle(player, dataLoader);
        battle.start();
    }
    
    private void Shop() 
    {
        Shop shop =  new Shop(dataLoader, player);
        shop.openShop();
    }
    
    private void viewProfile() 
    {
        System.out.println("\n=== Player Profile ===");
        System.out.println("Name: " + player.getName());
        System.out.println("Username: " + player.getUsername());
        System.out.println("Element: " + player.getElement());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Coins: " + player.getCoins());
        System.out.println("Current Hero: " + player.getCurrentHero().getName());
        System.out.println("Heroes: ");
        for (Hero hero : player.getHeroes().values()) 
        {
            System.out.println(" - " + hero.toString());
        }
        System.out.println("Powers: ");
        for (Power power : player.getPowers().values())
        {
            System.out.println(" - " + power.toString());
        }
        System.out.println("Shields: ");
        for (Shield shield : player.getShields().values())
        {
            System.out.println(" - " + shield.toString());
        }
    }
    
    private void saveGame() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) 
        {
            oos.writeObject(player);
            System.out.println("Game saved successfully!");
        } 
        catch (IOException e) 
        {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    private void loadGame() 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame.dat"))) 
        {
            player = (Player) ois.readObject();
            System.out.println("Game loaded! Welcome back, " + player.getName() + ".");
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.err.println("Failed to load game: " + e.getMessage());
            return;
        }
    }

    public static boolean isUsernameUnique(String username) 
    {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return true; // No file = no usernames yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.trim().equalsIgnoreCase(username.trim())) 
                {
                    return false; // Username already exists
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        return true; 
    }

    public static boolean addUsername(String username) 
    {
        if (isUsernameUnique(username)) 
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) 
            {
                writer.write(username);
                writer.newLine();
                System.out.println("Username added successfully.");
                return true;
            } 
            catch (IOException e) 
            {
                System.out.println("Error writing to the file.");
                e.printStackTrace();
                return false;
            }
        } 
        else 
        {
            System.out.println("Username already exists. Please choose another one.");
            return false;
        }
    }

    public static boolean isPasswordValid(String password) 
    {
        if (password.length() <= 8) 
        {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (char c : password.toCharArray()) 
        {
            if (Character.isUpperCase(c)) 
            {
                hasUpper = true;
            } 
            else if (Character.isLowerCase(c)) 
            {
                hasLower = true;
            } 
            else if (Character.isDigit(c)) 
            {
                hasDigit = true;
            } 
            else if (!Character.isLetterOrDigit(c)) 
            {
                hasSymbol = true;
            }
        }
        return hasUpper && hasLower && hasDigit && hasSymbol;
}

    
}