package com.hyojin.assignment;

import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class area_fragment extends Fragment {

    private EditText description;
    private TextView location;
    private ToggleButton star;
    private String str;
    private GameData gameData;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
         View v = inflater.inflate(R.layout.fragment_area, container, false);

        gameData = (GameData) getArguments().getSerializable("asdf");


        description = (EditText) v.findViewById(R.id.area_description);
        description.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if((event.getAction() == KeyEvent.ACTION_DOWN)&&(keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).setDescription(str);
                    description.setText(null);
                    Toast.makeText(getActivity(),"entered",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        location = v.findViewById(R.id.location);
        if(gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).isTown()==true)
        {
            location.setText(R.string.twon);
        }
        else
        {
            location.setText(R.string.wild);
        }

        star = v.findViewById(R.id.star_toggle);
        star.setTextOn("Starred");
        star.setTextOff("Not Starred");
        star.setText("Not Starred");
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(star.isChecked())
                {
                    gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).setStarred(true);
                }
                else
                {
                    gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).setStarred(false);

                }
            }
        });
        return v;

    }
    public void update()
    {
        gameData = (GameData) getArguments().getSerializable("asdf");
        if(gameData.getArea(gameData.getPlayer().getRowLocation(),gameData.getPlayer().getColLocation()).isTown()==true)
        {
            location.setText(R.string.twon);
        }
        else
        {
            location.setText(R.string.wild);
        }

    }

}

