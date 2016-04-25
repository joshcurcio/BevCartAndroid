package com.jcurcio.rorc.bevcart;

/**
 * Created by Josh on 4/22/2016.
 */
public class Order {
    int beer;
    int burger;
    int chips;
    boolean completed;
    int hole;
    int hot_dog;
    int price;
    String provider;
    String user;
    String key;

    public Order(){

    }

    public boolean isCompleted() {
        return completed;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getBeer() {
        return beer;
    }

    public int getBurger() {
        return burger;
    }

    public int getChips() {
        return chips;
    }

    public int getHole() {
        return hole;
    }

    public int getHot_dog() {
        return hot_dog;
    }

    public int getPrice() {
        return price;
    }

    public String getProvider() {
        return provider;
    }

    public String getUser() {
        return user;
    }

    public void setBeer(int beer) {
        this.beer = beer;
    }

    public void setBurger(int burger) {
        this.burger = burger;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setHole(int hole) {
        this.hole = hole;
    }

    public void setHot_dog(int hot_dog) {
        this.hot_dog = hot_dog;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setUser(String user) {
        this.user = user;
    }
}


