import java.util.List;
import java.util.ArrayList;

// Villain class (inherits from Character)
public class Villain extends Character {
    private String missionId;
    private List<String> powerIds; 
    private String shieldId;

    public Villain(String id, String name, String element, String missionId,
                   int attack, int defense, float speed, List<String> powerIds, String shieldId) 
    {
        super(id, name, element, attack, defense, speed);
        this.missionId = missionId;
        this.powerIds = new ArrayList<>(powerIds); // create a copy
        this.shieldId = shieldId;
        this.powerIds.add("P0"); // Add "Normal Punch" by default
    }

    // Getter for mission ID
    public String getMissionId() {
        return missionId;
    }

    // Getter for power IDs
    public List<String> getPowerIds() {
        return powerIds;
    }

    // Getter for shield ID
    public String getShieldId() {
        return shieldId;
    }

    // String representation
    @Override
    public String toString() {
        return "Villain{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", element='" + getElement() + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                ", speed=" + getSpeed() +
                ", missionId='" + missionId + '\'' +
                ", powerIds=" + powerIds +
                ", shieldId='" + shieldId + '\'' +
                '}';
    }
}
