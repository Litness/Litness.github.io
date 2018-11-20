package com.example.litness.litness;

import java.util.ArrayList;
import java.util.List;

public class Bar {
    public String barName = "";
    public String coverOver = "0";
    public String coverUnder = "0";
    public String wait = "";
    public String litness = "1";
    public String phone = "420-867-5309";
    public String address = "1600 Pennsylvania Avenue NW Washington, DC 20500";
    public String description = "";
    public List<Integer> photos= new ArrayList<>();
    public List<Integer> livePhotos= new ArrayList<>();
    public List<String> tags = new ArrayList<>();
    public String rating = "3.5";
    public List<Review> reviews = new ArrayList<>();

    //will store from Sun-Sat
    public List<Day> days = new ArrayList<>();

    public Menu menu = new Menu();

    //list will store event
    public static class Day {
        public String day = "";
        public List<String> events = new ArrayList<>();
        public List<String> specials = new ArrayList<>();
    }

    public static class Review {
        public String user = "";
        public String rating = "";
        public String text = "";
        public long timestamp = System.currentTimeMillis();
    }

    public static class Menu {
        public List<Item> food = new ArrayList<>();
        public List<Item> drinks = new ArrayList<>();
    }

    public static class Item {

        public String item;
        public int price;

        Item(String item, int price) {
            this.item = item;
            this.price = price;
        }
    }
}
