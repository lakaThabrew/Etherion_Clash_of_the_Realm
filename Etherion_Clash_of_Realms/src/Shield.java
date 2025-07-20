import java.io.Serializable;

public class Shield implements Serializable
{
    private String id;
    private String name;
    private int defenseValue;
    private int unlockMission;

    public Shield(String id, String name, int defenseValue, int unlockMission) 
    {
        this.id = id;
        this.name = name;
        this.defenseValue = defenseValue;
        this.unlockMission = unlockMission;
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

    public int getDefenseValue() 
    {
        return defenseValue;
    }

    public int getUnlockMission() 
    {
        return unlockMission;
    }

    public String toString() 
    {
        return "Shield{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", defenseValue=" + defenseValue +
                ", unlockMission=" + unlockMission +
                '}';
    }
}