package com.uoc.pra1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.uoc.datalevel.DataException;
import com.uoc.datalevel.DataObject;
import com.uoc.datalevel.DataQuery;
import com.uoc.datalevel.FindCallback;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements  ListView.OnItemClickListener {

    private View mProgressView;
    private ListView mListView;

    public static final int INSERT_REQUEST = 100;

    static final int INSERT_ITEM_REQUEST = 1;

    static final int RESULT_OK = 1;

    public ResultListAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String user_email = getIntent().getStringExtra("user_email");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR2 :: Results");

        mListView = (ListView) findViewById(R.id.listView);
        mProgressView = findViewById(R.id.progress);


        mListView.setOnItemClickListener(this);

        showProgress(true);

        // ************************************************************************
        // UOC - BEGIN - CODE3
        //
        DataQuery query = DataQuery.get("item");



        query.findInBackground("", "", DataQuery.OPERATOR_ALL, new FindCallback<DataObject>() {
            @Override
            public void done(ArrayList<DataObject> dataObjects, DataException e) {
                if (e == null) {
                    if (dataObjects.size() != 0) {
                        m_adapter = new ResultListAdapter(ResultsActivity.this, null);

                        m_adapter.m_array = dataObjects;
                        m_adapter.mActivity = ResultsActivity.this;

                        showProgress(false);
                        mListView.setAdapter(m_adapter);
                    }
                } else {
                    // Error

                }
            }
        });

        // UOC - END - CODE3
        // ************************************************************************


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                //metodoAdd()
                addItem();
                return true;
      /*      case R.id.edit:
                //metodoEdit()
                return true;
            case R.id.delete:
                //metodoDelete()
                return true;
        */    default:
                return super.onOptionsItemSelected(item);
        }


    }


    private void addItem() {
      //  Intent insertItemIntent = new Intent(ResultsActivity.this, InsertActivity.class);
      //  startActivityForResult(insertItemIntent, INSERT_ITEM_REQUEST );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to

        switch(requestCode) {
            case RESULT_OK:
                // Resultado correcto.
                // El usuario ha insertado un nuevo item.

                DataQuery query = DataQuery.get("item");

                query.findInBackground("", "", DataQuery.OPERATOR_ALL, new FindCallback<DataObject>() {
                    @Override
                    public void done(ArrayList<DataObject> dataObjects, DataException e) {
                        if (e == null) {
                            if (dataObjects.size() != 0) {
                                m_adapter = new ResultListAdapter(ResultsActivity.this, null);

                                m_adapter.m_array = dataObjects;
                                m_adapter.mActivity = ResultsActivity.this;

                                showProgress(false);
                                mListView.setAdapter(m_adapter);
                            }
                        } else {
                            // Error

                        }
                    }
                });
                break;
            case RESULT_CANCELED:
                // Cancelación o cualquier situación de error.
                // El usuario ha cancelado la operación de insertar un nuevo item.
                Toast.makeText(this, "El usuario no inserto ningún ítem: ", Toast.LENGTH_LONG).show();
                break;

        }

    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        // ************************************************************************
        // UOC - BEGIN - CODE5
        //
        DataObject object = (DataObject) m_adapter.m_array.get(position);

        // Send m_objectId to DetailACtivity

        Intent intent;
        intent = new Intent(this, DetailActivity.class);
        intent.putExtra("object_id", object.m_objectId);

        // UOC - END - CODE5
        // ************************************************************************


        startActivity(intent);

    }

    private void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mListView.setVisibility(show ? View.GONE : View.VISIBLE);

    }


}
