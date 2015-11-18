package com.example.marianne.ejendomsselskabetbmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;

public class Forside extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);

//        Inspectionlist inspectionlist = new Inspectionlist("carl");
//
//        myDBHandler.addProduct(inspectionlist);
    }


// test
    public void addButtonOnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void listOfTaskButtonOnClick(View view){
        Intent intent = new Intent(this,TaskListe.class);
        startActivity(intent);
    }
    public void NewlistOfTaskButtonOnClick(View view){
        Intent intent = new Intent(this,NewTaskList.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
