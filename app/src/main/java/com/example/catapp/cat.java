package com.example.catapp;


public class cat {
    private boolean register;
    private int exp;
    private int colour;
    private String name;
    private int age;
    private int hunger;
    private int hygiene;
    private int sleep;
    private boolean sick;
    private int money;

    public cat(){
    }
    public cat(int a){
        register=false;
        exp=a;
        colour=1;
        name=" ";
        age=1;
        hunger=100;
        hygiene=100;
        sleep=100;
        sick=false;
        money=1;
    }

    public boolean getSick(){return sick;}

    public int getMoney() {
        return money;
    }

    public int getExp() {return exp;}

    public int getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getSleep() {
        return sleep;
    }

    public void setExp(int exp) {this.exp = exp;}

    public void setColour(int colour) {
        this.colour = colour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    
    public void addExp(int a) {
        this.exp +=a;
    }

    public void addAge(int a) {
        this.age += a;
    }

    public void addHunger(int h) {
        this.hunger += h;
    }

    public void addHygiene(int h) {
        this.hygiene += h;
    }

    public void addSleep(int s) {
        this.sleep += s;
    }

    public void addMoney(int m) {
        this.money += m;
    }
        
}
