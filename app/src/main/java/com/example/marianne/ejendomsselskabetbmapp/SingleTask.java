package com.example.marianne.ejendomsselskabetbmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Log;

public class SingleTask extends AppCompatActivity {

    String passedVar = "";
    private TextView passedView = null;
    MyDBHandler db;
    Inspectionlist myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        Log.d("før task hentes", "super");

        passedVar = getIntent().getStringExtra(TaskListe.NAME_EXTRA);
        Log.d("Valgte TASK er: ","passedvar=  "+  passedVar);

//        String ulla = db.getTask(Integer.parseInt(passedVar));
        passedView = (TextView)findViewById(R.id.passedText);
        //passedView.setText("You chose " + String.valueOf(myTask._taskdescription));
        passedView.setText("You chose " + passedVar);
        Log.d("Passed var Task","passedvar=  "+ passedVar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_task, menu);
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
