import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

public class GameDataLoader implements Serializable
{
    private HashMap<String, Hero> heroes = new HashMap<>();
    private HashMap<String, Villain> villains = new HashMap<>();
    private HashMap<String, Mission> missions = new HashMap<>();
    private HashMap<String, Power> powers = new HashMap<>();
    private HashMap<String, Shield> shields = new HashMap<>();

    public GameDataLoader(String heroesFile, String villainsFile, String missionsFile, String powersFile, String shieldsFile) throws IOException
    {
        loadHeroes(heroesFile);
        loadVillains(villainsFile);
        loadMissions(missionsFile); 
        loadPowers(powersFile);
        loadShields(shieldsFile);
    }

    public void loadHeroes(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = reader.readLine();
            if (header == null) return;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length < 7) continue;

                Hero p = new Hero(data[0], data[1], data[2], 
                                  Integer.parseInt(data[3]), 
                                  Integer.parseInt(data[4]), 
                                  Float.parseFloat(data[5]), 
                                  Integer.parseInt(data[6]));
                heroes.put(data[0], p);
            }
        }
    }

    public void loadMissions(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = reader.readLine();
            if (header == null) return;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                Mission m = new Mission(data[0], data[1], data[2], data[3], data[4]);
                missions.put(data[0], m);
            }
        }
    }

    public void loadVillains(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = reader.readLine();
            if (header == null) return;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length < 8) continue;

                Villain v = new Villain(data[0], data[1], data[2], 
                                        data[3], Integer.parseInt(data[4]), 
                                        Integer.parseInt(data[5]), 
                                        Float.parseFloat(data[6]), 
                                        List.of(data[7].split(";")), 
                                        data[8]);
                villains.put(data[0], v);
            }
        }
    }

    public void loadPowers(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = reader.readLine();
            if (header == null) return;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length < 6) continue;

                Power p = new Power(data[0], data[1], data[2], 
                                    Integer.parseInt(data[3]), 
                                    Integer.parseInt(data[4]),Integer.parseInt(data[5]));
                powers.put(data[0], p);
            }
        }
    }

    public void loadShields(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = reader.readLine();
            if (header == null) return;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length < 4) continue;

                Shield s = new Shield(data[0], data[1], 
                                      Integer.parseInt(data[2]), 
                                      Integer.parseInt(data[3]));

                shields.put(data[0], s);
            }
        }
    }

    public HashMap<String, Hero> getHeroes() 
    {
        for (Hero hero : heroes.values()) {
            System.out.println(hero.toString()); // Print each hero's details
        }
        return heroes;
    }

    public HashMap<String, Villain> getVillains() 
    {
        for (Villain villain : villains.values()) 
        {
            System.out.println(villain.toString()); // Print each villain's details
        }
        return villains;
    }

    public HashMap<String, Mission> getMissions() 
    {
        for (Mission mission : missions.values()) 
        {
            System.out.println(mission.getMissionDetails()); // Print each mission's details
        }

        return missions;
    }

    public HashMap<String, Power> getPowers() 
    {
        for (Power power : powers.values()) 
        {
            System.out.println(power.toString()); // Print each power's details
        }
        return powers;
    }

    public HashMap<String, Shield> getShields() 
    {
        for (Shield shield : shields.values()) 
        {
            System.out.println(shield.toString()); // Print each shield's details
        }
        return shields;
    }

    public Hero searchHeroById(String id) 
    {
        return heroes.get(id);
    }

    public Villain searchVillainById(String id) 
    {
        return villains.get(id);
    }

    public Mission searchMissionById(String id) 
    {
        return missions.get(id);
    }

    public Power searchPowerById(String id) 
    {
        return powers.get(id);
    }

    public Shield searchShieldById(String id) 
    {
        return shields.get(id);
    }
}
