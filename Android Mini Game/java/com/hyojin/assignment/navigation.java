package com.hyojin.assignment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class navigation extends AppCompatActivity {

    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button overviewButton;
    private Button optionButton;

    private  GameData gameData;
    private int limit;
    private int limit2;
    private String aaaaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        gameData = GameData.get();
        limit = (gameData.getPlayer().getColLocation()*gameData.getHeight()) + gameData.getPlayer().getRowLocation();  // current player location size
        limit2 = (gameData.getHeight() * gameData.getWidth() +1); // maximum size of player move area
        //aaaaa = "height is "+String.valueOf(gameData.getPlayer().getRowLocation())+"width is"+String.valueOf(gameData.getPlayer().getColLocation())+"limit is"+String.valueOf(limit);
        //===============================================================north button=================================================================
        northButton = findViewById(R.id.north_button);
        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(navigation.this, R.string.north,Toast.LENGTH_SHORT).show();
                if(gameData.getPlayer().getRowLocation() == 1&& (limit < limit2))
                {
                    Toast.makeText(navigation.this, R.string.cannotmovethere,Toast.LENGTH_SHORT).show();
                }
                else if(limit < limit2){
                    gameData.getPlayer().setHealth(Math.max(0.0, gameData.getPlayer().getHealth() - 5 - (gameData.getPlayer().getEquipmentMass() / 2.0)));
                    gameData.getPlayer().setRowLocation(gameData.getPlayer().getRowLocation()-1);
                    gameData.getArea(gameData.getPlayer().getRowLocation()-1,gameData.getPlayer().getColLocation()-1).setExplored(true);
                    status_change();
                }
            }
        });

        //===============================================================south button=================================================================
        southButton = findViewById(R.id.south_button);
        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(navigation.this, R.string.south,Toast.LENGTH_SHORT).show();
                if(gameData.getPlayer().getRowLocation() == gameData.getHeight() && (limit < limit2)){
                    Toast.makeText(navigation.this, R.string.cannotmovethere,Toast.LENGTH_SHORT).show();
                }
                else if(limit < limit2){
                    gameData.getPlayer().setHealth(Math.max(0.0, gameData.getPlayer().getHealth() - 5 - (gameData.getPlayer().getEquipmentMass() / 2.0)));
                    gameData.getPlayer().setRowLocation(gameData.getPlayer().getRowLocation()+1);
                    gameData.getArea(gameData.getPlayer().getRowLocation()-1,gameData.getPlayer().getColLocation()-1).setExplored(true);
                    status_change();

                }
            }
        });

        //===============================================================east button=================================================================
        eastButton = findViewById(R.id.east_button);
        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(navigation.this, R.string.east,Toast.LENGTH_SHORT).show();
                if(gameData.getPlayer().getColLocation() == gameData.getWidth() && (limit < limit2) ){
                    Toast.makeText(navigation.this, R.string.cannotmovethere,Toast.LENGTH_SHORT).show();
                }
                else if(limit < limit2){
                    gameData.getPlayer().setHealth(Math.max(0.0, gameData.getPlayer().getHealth() - 5 - (gameData.getPlayer().getEquipmentMass() / 2.0)));
                    gameData.getPlayer().setColLocation(gameData.getPlayer().getColLocation()+1);
                    gameData.getArea(gameData.getPlayer().getRowLocation()-1,gameData.getPlayer().getColLocation()-1).setExplored(true);
                    status_change();

                    //Toast.makeText(navigation.this,String.valueOf(gameData.getPlayer().getColLocation()), Toast.LENGTH_SHORT ).show();
                }
            }
        });

        //===============================================================west button=================================================================
        westButton = findViewById(R.id.west_button);
        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(navigation.this, R.string.west,Toast.LENGTH_SHORT).show();
                if(gameData.getPlayer().getColLocation() == 1 && (limit < limit2)){
                    Toast.makeText(navigation.this, R.string.cannotmovethere,Toast.LENGTH_SHORT).show();
                }
                else if(limit < limit2){
                    gameData.getPlayer().setHealth(Math.max(0.0, gameData.getPlayer().getHealth() - 5 - (gameData.getPlayer().getEquipmentMass() / 2.0)));
                    gameData.getPlayer().setColLocation(gameData.getPlayer().getColLocation()-1);
                    gameData.getArea(gameData.getPlayer().getRowLocation()-1,gameData.getPlayer().getColLocation()-1).setExplored(true);
                    status_change();

                    //Toast.makeText(navigation.this,String.valueOf(gameData.getPlayer().getColLocation()), Toast.LENGTH_SHORT ).show();


                }
            }
        });

        //===============================================================Overview button=================================================================
        overviewButton = findViewById(R.id.overview_button);
        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(navigation.this, R.string.overviewButton,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(navigation.this, overviewActivity.class);
                intent.putExtra("value0",gameData);
                startActivityForResult(intent,0);
            }
        });

        //===============================================================Option button=================================================================
        optionButton = findViewById(R.id.option_button);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean condition = gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).isTown();

                if(condition == true) {
                    Toast.makeText(navigation.this, "market value1", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(navigation.this, marketActivity.class);
                    intent.putExtra("value1", gameData);
                    startActivityForResult(intent,1);
                }
                else{
                    Toast.makeText(navigation.this, "wild activity", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(navigation.this, wildernessActivity.class);

                    intent.putExtra("value2",gameData);
                    startActivityForResult(intent,2);
                }
            }
        });



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.area_fragment);
        if(fragment == null){
            fragment = new area_fragment();
            fm.beginTransaction()
                    .add(R.id.area_fragment,fragment)
                    .commit();
        }
        Bundle bundleData = new Bundle();
        bundleData.putSerializable("asdf", gameData);
        fragment.setArguments(bundleData);

        FragmentManager fm2 = getSupportFragmentManager();
        Fragment fragment2 = fm2.findFragmentById(R.id.status_recycler);
        if(fragment2 == null){
            fragment2 = new status_fragment();
            fm2.beginTransaction()
                    .add(R.id.status_fragment,fragment2)
                    .commit();
        }
        //Bundle bundleData = new Bundle();
        bundleData.putSerializable("qwert", gameData);
        fragment2.setArguments(bundleData);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        Toast.makeText(this, "Workign well", Toast.LENGTH_LONG);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    gameData = (GameData) data.getSerializableExtra("returnValue0");
                    status_change();
                    break;
                case 1:
                    gameData = (GameData) data.getSerializableExtra("returnValue1");
                    status_change();
                    break;
                case 2:
                    gameData = (GameData) data.getSerializableExtra("returnValue2");
                    status_change();
                    break;
            }
        }
    }

    public void resetGame(GameData gmdata)
    {
        gameData = gmdata;
        status_change();

    }
    public void status_change() {
        status_fragment mStatus_fragment = (status_fragment) getSupportFragmentManager().findFragmentById(R.id.status_fragment);

        mStatus_fragment.update();
    }
    public void area_change() {
        status_fragment mArea_fragment = (status_fragment) getSupportFragmentManager().findFragmentById(R.id.area_fragment);

        mArea_fragment.update();
    }

}