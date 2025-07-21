// Hero class (player-controlled)

public class Hero extends Charact
{
    private int price;
    
    public Hero(String id, String name, String element, int attack, int defense, float speed) 
    {
        super(id, name, element, attack, defense, speed);
    }
    
    public Hero(String id, String name, String element, int attack, int defense, float speed, int price) 
    {
        super(id, name, element, attack, defense, speed);
        this.price = price;
    }
    
    public int getPrice() 
    { 
        return price; 
    }

    public String toString() 
    {
        return "Hero{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", element='" + getElement() + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                ", speed=" + getSpeed() +
                ", price=" + price +
                '}';
    }
}
