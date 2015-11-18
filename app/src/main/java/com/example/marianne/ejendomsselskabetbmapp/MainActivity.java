package com.example.marianne.ejendomsselskabetbmapp;

import android.app.DatePickerDialog;
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
import java.util.Calendar;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDBHandler myDBHandler;
    TextView txt;
    EditText taskDescrip, roomNr, address, Donedate;
    CheckBox acquisi, scheduled;
    DatePicker date;
    Button btngemData;
    //datepickerdialog
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;

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
        btngemData = (Button)findViewById(R.id.btnSaveTask);
        Donedate = (EditText) findViewById(R.id.EtxfromDate);
        Donedate.requestFocus();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        setDateTimeField();
//        printDatabase();
        addData();

    }

    //Datepickerdialog start

    private void setDateTimeField() {

        //sæt onclicklistener, så man kan fange "klikket"
        Donedate.setOnClickListener(this);

        //lav en calendar instance, så vi dafs dato til at sætte datepickerdialog til ved åbning
        Calendar newCalendar = Calendar.getInstance();
        //opret en ny datepickerdialog med en OndateSetListener (kalder tilbage når man vælger en dato)
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            //modtager callback fra datePickerDialog
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
//                henter dato/tid fra calendar objektet og formaterer det til tekst og sætter det ind i vores editText
                Donedate.setText(dateFormatter.format(newDate.getTime()));
            }
            //sætter startdato for datepickerdialog(hvilken dato en viser når man åbner den)
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void addData(){
        btngemData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Date dato = new Date(date.getYear() - 1900, date.getMonth(), date.getDayOfMonth());
                        //String selectedDate = DateFormat.getDateInstance().format(date.getYear());
//                        String selectedDate = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
//                        Log.d("Valgte år er: ", selectedDate + " " + dato.toString());
                        String selectedDate = Donedate.getText().toString();
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
                        } else
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


    @Override
    public void onClick(View v) {
        fromDatePickerDialog.show();
    }
}
