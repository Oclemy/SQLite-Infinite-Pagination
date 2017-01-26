package com.tutorials.hp.sqliteinfinitepager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.srx.widget.PullToLoadView;
import com.tutorials.hp.sqliteinfinitepager.mData.Paginator;
import com.tutorials.hp.sqliteinfinitepager.mData.Spaceship;
import com.tutorials.hp.sqliteinfinitepager.mRecycler.MyAdapter;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    Button saveBtn;
    RecyclerView rv;
    PullToLoadView pullToLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pullToLoadView= (PullToLoadView) findViewById(R.id.pullToLoadView);


        //RECYCLERVIEW
        rv = pullToLoadView.getRecyclerView();
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        new Paginator(this,pullToLoadView,rv).initializePaginator();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
    }

    /*
   DISPLAY INPUT DIALOG
    */
    private void displayDialog()
    {
        final Dialog d=new Dialog(this);
        d.setTitle("SQLITE DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spaceship s=new Spaceship();
                save(s);
                nameEditText.setText("");

            }
        });

        d.show();

    }

    /*
    INSERT DATA
     */
    private void save(Spaceship s)
    {
        s.setName(nameEditText.getText().toString());
        s.save();

    }




}
