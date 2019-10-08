package com.hyojin.assignment;

import android.content.Context;
import android.preference.PreferenceActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@SuppressWarnings("serial")
public class GameData implements Serializable{


    public static final int WIDTH =10;
    public static final int HEIGHT = 6;

    private  Area grid[][] = new Area[HEIGHT][WIDTH];
    private  Player mPlayer;
    private  ArrayList<Item> items = generateItem();

    private static GameData instance = null;


    public GameData() {
    }
    public GameData(GameData gameData)
    {
        instance = gameData;
    }

    public static GameData replaced(GameData gmd)
    {
        return gmd;
    }
    public static GameData get()
    {
        if(instance == null)
        {
            synchronized (GameData.class) {
                if(null==instance) {

                    instance = new GameData();
                    instance.reset();
                    //instance.items = generateItem();
                }
            }
        }
        return instance;
    }

    protected GameData(Area[][] grid)
    {
        this.grid = grid;
    }
    protected GameData(Player player)
    {
        mPlayer = player;
    }

    public void reset()
    {
       this.grid = generateGrid();
       this.mPlayer = generatePlayer();
    }

    public Area getArea(int i, int j)
    {
        return grid[i][j];
    }
    public Player getPlayer()
    {
        return mPlayer;
    }
    public int getHeight()
    {
        return HEIGHT;
    }
    public int getWidth()
    {
        return WIDTH;
    }
    public static ArrayList<Item> getItems(){return generateItem();}
    //============================================== something start from here ====================================


   public  void setTownLocation()
    {
        int size = WIDTH*HEIGHT;
        int settown = new Random().nextInt(size);

        if(grid[settown % HEIGHT][settown / HEIGHT].isTown() == false) {
            grid[settown % HEIGHT][settown / HEIGHT].setTown(true);
        }
        else{
            setTownLocation();
        }
    }

    //=====================================  generate the grid =============================================
     public Area[][] generateGrid()
    {
        int size = WIDTH*HEIGHT;
        int numoftown = new Random().nextInt(size/10);
        //Area[][] gnrGrid = new Area[HEIGHT][WIDTH];


        for(int i=0; i < HEIGHT; i++)
        {
            for(int j=0; j<WIDTH; j++)
            {
                grid[i][j] = new Area(false, new ArrayList<Item>(), null,false, i+j*HEIGHT,false);
            }
        }
        for(int i =0; i<numoftown; i++) {
            setTownLocation();
            }

       for(int i=0; i< items.size(); i++)
       {
            int quantity = new Random().nextInt(3);
            for(int j=0; j<quantity+1; j++)
           {
                int rn = new Random().nextInt(size);
                grid[rn%HEIGHT][rn/HEIGHT].setItems(items.get(i));
            }
        }
        return grid;
    }


       //=================================== character set up ===============================================
       public  Player generatePlayer()
       {
           int size = WIDTH*HEIGHT;
           int randomNumber = new Random().nextInt(size);

           Player player = new Player((randomNumber%HEIGHT)+1,(randomNumber/HEIGHT)+1,0,100.0, new ArrayList<Equipment>());

           grid[player.getRowLocation()][player.getColLocation()].setExplored(true);

           return player;
       }
    // =================================== hard coding Items============================================



    public static ArrayList<Item> generateItem()
    {
        ArrayList<Item> generated_item = new ArrayList<>();

        Equipment jadeMonkey = new Equipment();
        jadeMonkey.setMass(8.00);
        jadeMonkey.setValue(200);
        jadeMonkey.setDescription("JadeMonkey");
        generated_item.add(jadeMonkey);

        Equipment iceScraper = new Equipment();
        iceScraper.setMass(10.00);
        iceScraper.setValue(200);
        iceScraper.setDescription("Ice Scrapper");
        generated_item.add(iceScraper);

        Equipment roadMap = new Equipment();
        roadMap.setMass(2.00);
        roadMap.setValue(200);
        roadMap.setDescription("Roadmap");
        generated_item.add(roadMap);

        Equipment portable_smell_o_scope = new Equipment();
        portable_smell_o_scope.setMass(5.00);
        portable_smell_o_scope.setValue(500);
        portable_smell_o_scope.setDescription("portable smell-o-scope");
        generated_item.add(portable_smell_o_scope);

        Equipment ben_kenobi = new Equipment();
        ben_kenobi.setMass(0.00);
        ben_kenobi.setValue(9999);
        ben_kenobi.setDescription("ben_kenobi");
        generated_item.add(ben_kenobi);

        Equipment Improbability_drive = new Equipment();
        Improbability_drive.setMass(200.0);
        Improbability_drive.setValue(9999);
        Improbability_drive.setDescription("Improbability_drive");
        generated_item.add(Improbability_drive);

        Equipment computer = new Equipment();
        computer.setMass(4.00);
        computer.setValue(50);
        computer.setDescription("Computer");
        generated_item.add(computer);

        Equipment heavy_box = new Equipment();
        heavy_box.setMass(15.00);
        heavy_box.setValue(150);
        heavy_box.setDescription("heavy_box");
        generated_item.add(heavy_box);

        Equipment treasure_box = new Equipment();
        treasure_box.setMass(20.00);
        treasure_box.setValue(200);
        treasure_box.setDescription("treasure_box");
        generated_item.add(treasure_box);

        Equipment rubbish = new Equipment();
        rubbish.setMass(4.00);
        rubbish.setValue(20);
        rubbish.setDescription("rubbish");
        generated_item.add(rubbish);

        Food Hamburger = new Food();
        Hamburger.setHealth(20.00);
        Hamburger.setValue(7);
        Hamburger.setDescription("Hamburger");
        generated_item.add(rubbish);

        Food mushroom = new Food();
        mushroom.setHealth(-20.00);
        mushroom.setValue(20);
        mushroom.setDescription("mushroom");
        generated_item.add(mushroom);

        Food pizza = new Food();
        pizza.setHealth(15);
        pizza.setValue(5);
        pizza.setDescription("pizza");
        generated_item.add(rubbish);

        Food pie = new Food();
        pie.setHealth(10);
        pie.setValue(3);
        pie.setDescription("pie");
        generated_item.add(rubbish);

        Food doughnut = new Food();
        doughnut.setHealth(5);
        doughnut.setValue(2);
        doughnut.setDescription("doughnut");
        generated_item.add(rubbish);

        Food poison = new Food();
        poison.setHealth(-40.00);
        poison.setValue(100);
        poison.setDescription("poison");
        generated_item.add(rubbish);

        return generated_item;

    }
}
