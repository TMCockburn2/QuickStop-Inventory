package com.example.tyler.shoppinglistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Tyler on 11/10/2017.
 */

public class title_page_activity extends AppCompatActivity {
    EditText radius;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);
        Button button = (Button)findViewById(R.id.start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        //on button click, ultimately we want it to be able to pull the number from the editText
        //box, so that could be used in the maps activity class. This is done by utilizing the
        //putExtra class, which would hold the value and transfer it from class to the next class

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(title_page_activity.this, MapsActivity.class);
                //myIntent.putExtra("mapRadius", getRadius());
                title_page_activity.this.startActivity(myIntent);

            }
        });




    }

    public int getRadius(){
        radius = (EditText)findViewById(R.id.editText);
        int mapRadius = Integer.parseInt(radius.toString());
        return mapRadius;
    }




}