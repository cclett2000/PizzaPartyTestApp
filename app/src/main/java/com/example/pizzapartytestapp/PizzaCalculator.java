package com.example.pizzapartytestapp;

public class PizzaCalculator {
    public enum HungerLevel{
        LIGHT,MEDIUM,RAVENOUS
    }

    public final static int SLICES_PER_PIZZA = 8;

    private HungerLevel mHungerlevel;
    private int mPartySize;

    public PizzaCalculator(int partySize, HungerLevel level){
        setHungerLevel(level);
        setPartySize(partySize);
    }

    public HungerLevel getHungerLevel(){
        return mHungerlevel;
    }

    public void setHungerLevel(HungerLevel level){
        mHungerlevel = level;
    }

    public int getPartySize(){
        return mPartySize;
    }

    public void setPartySize(int partySize){
        if(partySize >= 0){
            mPartySize = partySize;
        }
    }

    public int getTotalPizzas(){
        int slicesPerPerson = 0;

        if(mHungerlevel == HungerLevel.LIGHT){
            slicesPerPerson = 2;
        }
        else if(mHungerlevel == HungerLevel.MEDIUM){
            slicesPerPerson = 3;
        }
        else if(mHungerlevel == HungerLevel.RAVENOUS){
            slicesPerPerson = 4;
        }

        return (int) Math.ceil(mPartySize * slicesPerPerson / (double) SLICES_PER_PIZZA);
    }
}
