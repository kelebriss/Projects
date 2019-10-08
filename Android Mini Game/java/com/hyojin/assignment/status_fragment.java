package com.hyojin.assignment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class status_fragment extends Fragment {

    private TextView cash;
    private TextView health;
    private TextView equipment;
    private TextView result;
    private Button reset;
    private GameData gameData;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_status, container, false);

        reset = (Button) v.findViewById(R.id.reset_button);
        reset.setText("Reset");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),R.string.reset,Toast.LENGTH_SHORT).show();

                GameData newGame = new GameData().get();

                navigation nav = (navigation)getActivity();
                nav.resetGame(newGame);

            }
        });

        gameData = (GameData) getArguments().getSerializable("qwert");

        cash = v.findViewById(R.id.cash_text);
        health = v.findViewById(R.id.health_text);
        equipment = v.findViewById(R.id.equipment_text);
        result = v.findViewById(R.id.result_text);
        update();


        return v;
    }

    private String result()
    {
        if(gameData.getPlayer().haswon()== true)
        {
            return "Win";
        }
        else if(gameData.getPlayer().getHealth() <= 0.0)
        {
            return "Lose";
        }
        return "Playing";
    }

    public void update()
    {
        gameData = (GameData)getArguments().getSerializable("qwert");
        cash.setText(String.valueOf(gameData.getPlayer().getCash()));
        health.setText(String.valueOf(gameData.getPlayer().getHealth()));
        equipment.setText(String.valueOf(gameData.getPlayer().getEquipmentMass()));
        result.setText(result());
    }
}

