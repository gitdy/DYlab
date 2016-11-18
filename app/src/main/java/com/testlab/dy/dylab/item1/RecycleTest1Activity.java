package com.testlab.dy.dylab.item1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.testlab.dy.dylab.R;

public class RecycleTest1Activity extends Activity {
RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_test1);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

    }
}
