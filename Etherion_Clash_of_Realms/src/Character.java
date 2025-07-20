// Base Character class

import java.io.Serializable;

public class Character implements Serializable
{
    protected String id;
    protected String name;
    protected String element;
    protected int attack;
    protected int defense;
    protected float speed;

    public Character(String id, String name, String element, int attack, int defense, float speed) 
    {
        this.id = id;
        this.name = name;
        this.element = element;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // Getters
    public String getName() 
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getElement() 
    {
        return element;
    }

    public int getAttack() 
    {
        return attack;
    }

    public int getDefense() 
    {
        return defense;
    }

    public float getSpeed() 
    {
        return speed;
    }
}
