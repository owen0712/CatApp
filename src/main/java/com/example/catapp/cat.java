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
    private int happy;
    private long time;

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

    public long getTime(){return time;}

    public int getHappy(){return happy;}

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

    public void setTime(long time){this.time=time;}

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

    public void setHappy(int happy){
        this.happy=happy;
    }
    
    public void addExp(int a) {
        this.exp +=a;
    }

    public void addHappy(int h){
        this.happy+=h;
        if(this.happy>100)
            this.happy=100;
    }

    public void addAge(int a) {
        this.age += a;
    }

    public void addHunger(int h) {
        this.hunger += h;
        if(this.hunger>100)
            this.hunger=100;
    }

    public void addHygiene(int h) {
        this.hygiene += h;
        if(this.hygiene>100)
            this.hygiene=100;
    }

    public void addSleep(int s) {
        this.sleep += s;
        if(this.sleep>100)
            this.sleep=100;
    }

    public void addMoney(int m) {
        this.money += m;
    }
        
}
