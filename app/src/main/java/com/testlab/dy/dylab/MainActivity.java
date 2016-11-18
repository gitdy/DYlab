package com.testlab.dy.dylab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.testlab.dy.dylab.item1.RecycleTest1Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//public class MainActivity extends Activity {
    ListView mLv;
    ListView lv2;
    MainListAdapter mAdapter;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.main_lv);
        mAdapter = new MainListAdapter();
        mList=new ArrayList<String>();
        //添加item
        mList.add("RecycleViewTest");



        mAdapter.setList(mList);
        mLv.setAdapter(mAdapter);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        MainActivity.this.startActivity(new Intent(MainActivity.this,RecycleTest1Activity.class));
                    break;
                }
            }
        });
    }

    public class MainListAdapter extends BaseAdapter {
        List<String> list;

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null) {
                view= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main_list,null);
            }
            TextView tv = (TextView) view.findViewById(R.id.text);
            tv.setText(mList.get(i));


            return view;
        }
    }
}
