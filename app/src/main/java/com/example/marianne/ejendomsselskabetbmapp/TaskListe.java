package com.example.marianne.ejendomsselskabetbmapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TaskListe extends AppCompatActivity {

    MyDBHandler myDB;
    public final static String NAME_EXTRA = "com.example.marianne.ejendomsselskabetbmapp.COLUMN_ID";
    SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_liste);

        PopulateListViewDate();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_liste, menu);
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

    public void PopulateListViewDate(){

        myDB = new MyDBHandler(this, null, null, 1);

        Cursor cursor = myDB.GetAllRows();
        String[] fromFieldNames = new String[]{MyDBHandler.COLUMN_TASKDESCRIPTION, MyDBHandler.COLUMN_DATE, MyDBHandler.COLUMN_ROOM};
        int[] toViewID = new int[]{R.id.txtTask, R.id.txtdate, R.id.txtRoom};

        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout2, cursor, fromFieldNames, toViewID,0);

        ListView taskList = (ListView)findViewById(R.id.lsvTaskDate);

        taskList.setAdapter(myCursorAdapter);


        taskList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                        henter cursoren, så jeg kan bruge den
                        Cursor c = ((SimpleCursorAdapter)myCursorAdapter).getCursor();
                        //flytter cursor til den valgte position
                        c.moveToPosition(position);
//                        henter string fra column 5 i db
                        String taskdescription = c.getString(5);

                        Intent singleTask = new Intent(TaskListe.this, SingleTask.class);
                        Log.d("Sendt til singleTask ", taskdescription);
                        singleTask.putExtra(NAME_EXTRA, taskdescription);

                        startActivity(singleTask);
                        //String item = String.valueOf(parent.getItemAtPosition(position));
                        //Toast.makeText(TaskListe.this, item, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
