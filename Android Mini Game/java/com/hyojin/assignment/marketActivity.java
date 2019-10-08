package com.hyojin.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class marketActivity extends AppCompatActivity {


    private RecyclerView mItemRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemAdapter mAdapter;
    private TextView mTitleTextView;
    private TextView mCostTextView;
    private Button mBuyButton;
    private Button mSellButton;
    private Button mUse;
    private Button mLeaveButton;
    private GameData gmd;
    public String[] gg;


    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recycler_view);

        gmd = (GameData) getIntent().getSerializableExtra("value1");

        mItemRecyclerView = findViewById(R.id.item_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mItemRecyclerView.setLayoutManager(mLayoutManager);


        mLeaveButton =  findViewById(R.id.leave_Button);
        mLeaveButton.setText(R.string.leave);
        mLeaveButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent rtn2 = new Intent();
                rtn2.putExtra("returnValue1",gmd);
                setResult(RESULT_OK,rtn2);
                Toast.makeText(marketActivity.this, "back to navigation from market", Toast.LENGTH_SHORT).show();
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


        List<Item> items = gmd.getItems();

        mAdapter = new ItemAdapter(items);
        mItemRecyclerView.setAdapter(mAdapter);



    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private Item mItems;

        public ItemHolder(View view) {
            super(view);

            mTitleTextView = (TextView) view.findViewById(R.id.item_name);
            mCostTextView = (TextView) view.findViewById(R.id.item_cost);
            mBuyButton = (Button) view.findViewById(R.id.button_1_option);
            mBuyButton.setText(R.string.bought);
            mBuyButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(gmd.getPlayer().getCash() >= mItems.getValue()) {
                        Toast.makeText(marketActivity.this, "complete:"+R.string.bought, Toast.LENGTH_SHORT).show();

                        // add the item function here
                    }
                    else
                    {
                        Toast.makeText(marketActivity.this, R.string.notenough, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mSellButton = (Button) view.findViewById(R.id.button_2_option);
            mSellButton.setText(R.string.sold);
            mSellButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(marketActivity.this, "complete" + R.string.sold, Toast.LENGTH_SHORT).show();        // function for sell item
                    if(gmd.getPlayer().getEquipment().contains(mItems.getDescription()) == true) {
                        gmd.getPlayer().setCash((mItems.getValue() * 3 / 4));
                    }
                }
            });
            mUse = (Button) view.findViewById(R.id.button_3_option);
            mUse.setText(R.string.use);
            mUse.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(marketActivity.this, R.string.use, Toast.LENGTH_SHORT).show();
                }
            });
        }


        public void bind(Item item) {
            mItems = item;
            mTitleTextView.setText(mItems.getDescription());
            mCostTextView.setText(String.valueOf(mItems.getValue()));
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<Item> items;

        public ItemAdapter(List<Item> item) {
            items = item;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holdingview,parent,false);

            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}

