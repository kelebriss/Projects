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

public class wildernessActivity extends AppCompatActivity {


    private RecyclerView mItemRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemAdapter mAdapter;
    private TextView mTitleTextView;
    private TextView mCostTextView;
    private Button mPickup;
    private Button mDrop;
    private Button mUse;
    private Button mLeaveButton;
    private GameData gmd;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recycler_view);

        gmd = (GameData) getIntent().getSerializableExtra("value2");

        mItemRecyclerView = findViewById(R.id.item_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mItemRecyclerView.setLayoutManager(mLayoutManager);


        mLeaveButton = findViewById(R.id.leave_Button);
        mLeaveButton.setText(R.string.leave);
        mLeaveButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent rtn3 = new Intent();
                rtn3.putExtra("returnValue2",gmd);
                setResult(RESULT_OK,rtn3);
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

        List<Item> items = new ArrayList<>();
        items = gmd.getItems();

        mAdapter = new ItemAdapter(items);
        mItemRecyclerView.setAdapter(mAdapter);

    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private Item mItems;

        public ItemHolder(View view) {
            super(view);

            mTitleTextView = (TextView) view.findViewById(R.id.item_name);
            mCostTextView = (TextView) view.findViewById(R.id.item_cost);
            mPickup = (Button) view.findViewById(R.id.button_1_option);
            mPickup.setText(R.string.pickup);
            mPickup.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(wildernessActivity.this, R.string.pickup, Toast.LENGTH_SHORT).show();
                }
            });
            mDrop = (Button) view.findViewById(R.id.button_2_option);
            mDrop.setText(R.string.drop);
            mDrop.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(wildernessActivity.this, R.string.drop, Toast.LENGTH_SHORT).show();        // function for sell item
                }
            });
            mUse = (Button) view.findViewById(R.id.button_3_option);
            mUse.setText(R.string.use);
            mUse.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(wildernessActivity.this, R.string.use, Toast.LENGTH_SHORT).show();
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

            ItemHolder holder = new ItemHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = items.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

   /* private void updateUI() {
        ItemLab itemLab = ItemLab.get(this);
        List<Item> items = itemLab.getItem();

        item_adapter = new ItemAdapter(items);
        itemRecyclerView.setAdapter(item_adapter);
    }
*/
}

