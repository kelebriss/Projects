package com.hyojin.assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class overviewActivity extends AppCompatActivity {


    private RecyclerView mItemRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private ImageView mImageView;
    private areaAdpter mAdapter;
    private Button mLeaveButton;
    private GameData gmd;
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recycler_view);

        gmd = (GameData) getIntent().getSerializableExtra("value0");

        mItemRecyclerView = (RecyclerView)findViewById(R.id.item_recycler_view);
        mGridLayoutManager = new GridLayoutManager(this, gmd.getHeight(), GridLayoutManager.HORIZONTAL,false);
        mItemRecyclerView.setLayoutManager(mGridLayoutManager);


        mLeaveButton = (Button) findViewById(R.id.leave_Button);
        mLeaveButton.setText(R.string.leave);
        mLeaveButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent rtn1 = new Intent();
                rtn1.putExtra("returnValue0",gmd);
                setResult(RESULT_OK,rtn1);
                finish();
            }
        });

        FragmentManager fm2 = getSupportFragmentManager();
        Fragment fragment2 = fm2.findFragmentById(R.id.status_recycler);
        if(fragment2 == null){
            fragment2 = new status_fragment();
            fm2.beginTransaction()
                    .add(R.id.status_recycler,fragment2)
                    .commit();
        }
        Bundle bundleData = new Bundle();
        bundleData.putSerializable("qwert", gmd);
        fragment2.setArguments(bundleData);


        List<Area> areas = new ArrayList<>();
        for(int i = 0; i<gmd.getHeight(); i++)
        {
            for(int j = 0; j<gmd.getWidth(); j++)
            {
                areas.add(gmd.getArea(i,j));
            }
        }

       //List<Item> items = new ArrayList<Item>();
        mAdapter = new areaAdpter(areas);   //changed
        mItemRecyclerView.setAdapter(mAdapter);


    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private Area mArea;

        public ItemHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.cell);

            //mImageView.setImageResource(R.drawable.ic_tree1);
        }

        public void bind(Area area) {
            mArea = area;
            if(mArea.isExplored()==true) {
                if (mArea.isTown() == true) {
                    mImageView.setImageResource(R.drawable.ic_building1);
                } else {
                    mImageView.setImageResource(R.drawable.ic_grass1);
                }
            }
            else
            {

                mImageView.setImageResource(R.drawable.black);
            }
        }
    }

    private class areaAdpter extends RecyclerView.Adapter<ItemHolder> {
        private List<Area> areas;

        public areaAdpter(List<Area> area) {
            areas = area;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell,parent,false);

            ItemHolder holder = new ItemHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Area area = areas.get(position);
            holder.bind(area);
        }

        @Override
        public int getItemCount() {
            return areas.size();
        }
    }

}

