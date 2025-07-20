import java.io.Serializable;

public class Power implements Serializable
{
    private String Id;
    private String name;
    private String element;
    private int attackValue;
    private int price;
    private int usesRemaining;
    private int uses = usesRemaining;

    public Power(String Id, String name, String element, int attackValue, int price, int usesRemaining) 
    {
        this.Id = Id;
        this.name = name;
        this.element = element;
        this.attackValue = attackValue;
        this.price = price;
        this.usesRemaining = usesRemaining;
    }

    // Getters
    public String getId() 
    {
        return Id;
    }

    public String getName() 
    {
        return name;
    }

    public String getElement() 
    {
        return element;
    }

    public int getAttackValue() 
    {
        return attackValue;
    }

    public int getUsesRemaining() 
    {
        return usesRemaining;
    }

    public int getPrice() 
    {
        return price;
    }

    public void use() 
    {
        if (usesRemaining > 0) {
            usesRemaining--;
        }
    }

    public void renew() 
    {
        usesRemaining = uses;
    }

    public String toString() 
    {
        return "Power{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", element='" + element + '\'' +
                ", attackValue=" + attackValue +
                ", price=" + price +
                ", uses" + uses +
                '}';
    }
}
