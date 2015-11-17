package com.example.marianne.ejendomsselskabetbmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    MyDBHandler myDBHandler;
    TextView txt;
    EditText taskDescrip, roomNr, address;
    CheckBox acquisi, scheduled;
    DatePicker date;
    Button btngemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHandler = new MyDBHandler(this, null, null, 1);
        txt = (TextView) findViewById(R.id.txtTaskDescription);
        taskDescrip = (EditText)findViewById(R.id.etxTaskDescription);
        roomNr = (EditText)findViewById(R.id.etxRoom);
        address = (EditText)findViewById(R.id.etxAddress);
        acquisi = (CheckBox)findViewById(R.id.chbAcquisition);
        scheduled = (CheckBox)findViewById(R.id.chbScheduled);
        date = (DatePicker)findViewById(R.id.dtpDate);
        btngemData = (Button)findViewById(R.id.btnSaveTask);

//        printDatabase();
        addData();

    }

    public void addData(){
        btngemData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Date dato = new Date(date.getYear()-1900,date.getMonth(),date.getDayOfMonth());
                        //String selectedDate = DateFormat.getDateInstance().format(date.getYear());
                        String selectedDate = date.getDayOfMonth() + "/" + (date.getMonth()+1) + "/" + date.getYear();
                        Log.d("Valgte år er: ", selectedDate +" "+ dato.toString());
                        Inspectionlist myList = new Inspectionlist(selectedDate,
                                roomNr.getText().toString(),
                                address.getText().toString(),
                                (acquisi.isChecked() ? 1 : 0),
                                taskDescrip.getText().toString(),
                                (scheduled.isChecked() ? 1 : 0)
                        );
                        boolean isInserted = myDBHandler.GemData(myList);

                        if (isInserted = true) {
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            taskDescrip.setText("");
                            roomNr.setText("");
                            address.setText("");
                            acquisi.setChecked(false);
                            scheduled.setChecked(false);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );}
    public void printDatabase(){
        String dbString = myDBHandler.databaseToString();
        txt.setText(dbString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
