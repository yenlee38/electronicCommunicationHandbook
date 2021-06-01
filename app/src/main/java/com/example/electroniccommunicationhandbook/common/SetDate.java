package com.example.electroniccommunicationhandbook.common;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetDate {

    public static void setDate(EditText tvDate, Context context){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();
        DateFormat formater= new SimpleDateFormat("E, MMM dd yyyy");
        //if the date is selected
        if (!tvDate.getText().toString().isEmpty())
        {
            SimpleDateFormat simpFormat = new SimpleDateFormat("E, MMM dd yyyy");
            //Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(simpFormat.parse(tvDate.getText().toString()));
                year = (cal.get(Calendar.YEAR));
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
            }
            catch(Exception e)
            {}
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                cal.set(Calendar.YEAR,year);
                cal.set(Calendar.DAY_OF_MONTH,day);
                cal.set(Calendar.MONTH,month);
                tvDate.setText(formater.format(cal.getTime()));
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}
