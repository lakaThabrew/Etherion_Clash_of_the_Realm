import java.io.Serializable;

public class Mission implements Serializable
{
    private String id;
    private String name;
    private String kingdom;
    private String difficulty;
    private String villainId;

    public Mission(String id, String name, String kingdom, String difficulty, String villainId) 
    {
        this.id = id;
        this.name = name;
        this.kingdom = kingdom;
        this.difficulty = difficulty;
        this.villainId = villainId;
    }

    // Getters
    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public String getKingdom() 
    {
        return kingdom;
    }

    public String getDifficulty() 
    {
        return difficulty;
    }

    public String getVillain() 
    {
        return villainId;
    }

    // Method to display mission details
    public String getMissionDetails() 
    {
        return "Mission " + id + ": " + name + " in " + kingdom + " (Difficulty: " + difficulty + ") - Villain: " + villainId;
    }
}