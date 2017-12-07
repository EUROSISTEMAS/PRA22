package com.uoc.pra1;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.uoc.datalevel.DataException;
import com.uoc.datalevel.DataObject;
import com.uoc.datalevel.DataQuery;
import com.uoc.datalevel.FindCallback;
import com.uoc.datalevel.GetCallback;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR2 :: Detail");



        // ************************************************************************
        // UOC - BEGIN - CODE6
        //
        String object_id = getIntent().getStringExtra("object_id");

        DataQuery query = DataQuery.get("item");
        query.getInBackground(object_id, new GetCallback<DataObject>() {
            @Override
            public void done(DataObject object, DataException e) {
                if (e == null) {



                } else {
                    // Error

                }
            }
        });
        // UOC - END - CODE6
        // ************************************************************************

    }
}
