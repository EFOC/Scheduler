package com.example.eosorio1.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    ScrollView scrollView;
    Calendar cal;
    CheckedButton cb = new CheckedButton();
    String eventName;
    String eventDesc;
    EditText nameET;
    EditText descET;
    Event event;
    RadioGroup amOrPm;
    NumberPicker startMinutePicker;
    NumberPicker startHourPicker;
    NumberPicker endHourPicker;
    NumberPicker endMinPicker;
    Button saveButton;
    LocalTime startTime;
    LocalTime endTime;
    int eventId;
    int day;
    int startHour;
    int startMins;
    int month;

    RadioGroup yearRG;
    RadioGroup dayRG;
    RadioGroup monthRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setButtons();
        setFrags();
    }

    public void setFrags(){
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        int width = size.x;
        yearRG = (RadioGroup) findViewById(R.id.yearRG);
        dayRG = (RadioGroup) findViewById(R.id.dayRG);
        monthRG = (RadioGroup) findViewById(R.id.monthRG);
        ValidateRadioGroup vrg = new ValidateRadioGroup();
        yearRG.setOnCheckedChangeListener(vrg);
        dayRG.setOnCheckedChangeListener(vrg);

        NestedScrollView yearNSV = (NestedScrollView) findViewById(R.id.yearScroll);
        NestedScrollView dayNSV = (NestedScrollView) findViewById(R.id.dayScroll);
        NestedScrollView monthNSV = (NestedScrollView) findViewById(R.id.monthScroll);
        dayNSV.getLayoutParams().width = width/3;
        monthNSV.getLayoutParams().width = width/3;
        yearNSV.getLayoutParams().width = width/3;
        boolean firstButton = true;
        for(int i = 1; i <= 31; i++){
            RadioButton rb = new RadioButton(this);
            rb.setOnCheckedChangeListener(cb);
//            if(firstButton)
//                rb.setChecked(true);
//            firstButton = false;
            rb.setButtonDrawable(0);
            rb.setText(i+"");
            rb.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dayRG.addView(rb);
        }
        for(int i = 0; i < monthRG.getChildCount(); i++){
            View button = monthRG.getChildAt(i);
            if(button instanceof RadioButton)
                ((RadioButton) button).setOnCheckedChangeListener(cb);
        }


        monthRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                cal = Calendar.getInstance();
                dayRG.removeAllViews();
                // Added a current year and first day just to get the month that was selected
                // The selected month will determine the number of days on the list
                if(monthRG.getCheckedRadioButtonId() == R.id.januaryRB)
                    month = 1;
                else if(monthRG.getCheckedRadioButtonId() == R.id.februaryRB)
                    month = 2;
                else if(monthRG.getCheckedRadioButtonId() == R.id.marchRB)
                    month = 3;
                else if(monthRG.getCheckedRadioButtonId() == R.id.aprilRB)
                    month = 4;
                else if(monthRG.getCheckedRadioButtonId() == R.id.mayRB)
                    month = 5;
                else if(monthRG.getCheckedRadioButtonId() == R.id.juneRB)
                    month = 6;
                else if(monthRG.getCheckedRadioButtonId() == R.id.julyRB)
                    month = 7;
                else if(monthRG.getCheckedRadioButtonId() == R.id.auguestRB)
                    month = 8;
                else if(monthRG.getCheckedRadioButtonId() == R.id.septemberRB)
                    month = 9;
                else if(monthRG.getCheckedRadioButtonId() == R.id.octoberRB)
                    month = 10;
                else if(monthRG.getCheckedRadioButtonId() == R.id.novemberRB)
                    month = 11;
                else if(monthRG.getCheckedRadioButtonId() == R.id.decemberRB)
                    month = 12;
                cal.set(2018,month,1);
                day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for(int i = 1; i <= day; i++){
                    RadioButton rb = new RadioButton(group.getContext());
                    rb.setOnCheckedChangeListener(cb);
                    rb.setButtonDrawable(0);
                    rb.setText(i+"");
                    rb.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    dayRG.addView(rb);
                }
                validate();
            }
        });

        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        firstButton = true;
        for(int i = currentYear; i <= currentYear+5; i++){
            RadioButton rb = new RadioButton(this);
            rb.setOnCheckedChangeListener(cb);
            rb.setButtonDrawable(0);
            rb.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rb.setText(i+"");
            yearRG.addView(rb);
        }
    }

    public void setButtons(){


        amOrPm = (RadioGroup) findViewById(R.id.amOrPm);
        nameET = (EditText) findViewById(R.id.nameOfEventEdit);
        descET = (EditText) findViewById(R.id.descriptionEdit);
        ValidateTextBoxes vtb = new ValidateTextBoxes();
        nameET.addTextChangedListener(vtb);
        descET.addTextChangedListener(vtb);

        startHourPicker      = (NumberPicker) findViewById(R.id.hourPicker);
        startMinutePicker    = (NumberPicker) findViewById(R.id.minutePicker);
        endHourPicker        = (NumberPicker) findViewById(R.id.endHour);
        endMinPicker         = (NumberPicker) findViewById(R.id.endMin);
        endHourPicker.setMaxValue(12);
        endHourPicker.setMinValue(1);
        startHourPicker.setMinValue(1);
        startHourPicker.setMaxValue(12);
        endMinPicker.setMaxValue(59);
        endMinPicker.setMinValue(0);
        startMinutePicker.setMinValue(0);
        startMinutePicker.setMaxValue(59);

        Button goToMap = (Button) findViewById(R.id.goMapButton);
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

        Button goBack = (Button) findViewById(R.id.back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.finish();
                overridePendingTransition(R.anim.side_from_right,0);
            }
        });
        SaveHandler saveHandler = new SaveHandler();
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setEnabled(false); // Information needs to be validated before they can add it
        saveButton.setOnClickListener(saveHandler);
    }
    public boolean validateTimes(){

        startHour = 0;
        startMins = 0;
        int endHour = 0;
        int endMins = 0;
        RadioButton startAmRB = (RadioButton) findViewById(R.id.amRB);
        RadioButton startPmRB = (RadioButton) findViewById(R.id.pmRB);
        RadioButton endAmRB = (RadioButton) findViewById(R.id.endAm);
        RadioButton endPmRB = (RadioButton) findViewById(R.id.endPm);
        // Converting the 12 0'clock time to 24 time
        if(startHourPicker.getValue() == 12 && startAmRB.isChecked())
            startHour = 0;
        else if(startHourPicker.getValue() == 12 && startPmRB.isChecked())
            startHour = 12;
        else if(startAmRB.isChecked())
            startHour = startHourPicker.getValue();
        else if(startPmRB.isChecked())
            startHour = startHourPicker.getValue()+12;
        // Doing the same thing but for PM
        if(endHourPicker.getValue() == 12 && endAmRB.isChecked())
            endHour = 0;
        else if(endHourPicker.getValue() == 12 && endPmRB.isChecked())
            endHour = 12;
        else if(endAmRB.isChecked())
            endHour = endHourPicker.getValue();
        else if(endPmRB.isChecked())
            endHour = endHourPicker.getValue()+12;
        // Minutes don't need to be converted
        startMins = startMinutePicker.getValue();
        endMins = endMinPicker.getMaxValue();
        startTime = LocalTime.of(startHour, startMins);
        endTime = LocalTime.of(endHour,endMins);
        if(startTime.isAfter(endTime) || startTime == endTime){
            Toast.makeText(this, "Error: Can not have start time coming after or equaling end time", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    class SaveHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(!validateTimes())
                return;
            // USING PREFERENCES HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // Using preferences to store and get the most recent ID value so that we don't over lap IDs when setting IDs for events
            SharedPreferences settings = getPreferences(MODE_PRIVATE);
            eventId = settings.getInt("eventId", 0);

            int yearChecked = Integer.parseInt(((RadioButton) findViewById(yearRG.getCheckedRadioButtonId())).getText().toString());
            int dayChecked = Integer.parseInt(((RadioButton) findViewById(dayRG.getCheckedRadioButtonId())).getText().toString());

            // Get text from the EditText views
            eventName = nameET.getText().toString();
            eventDesc = descET.getText().toString();

            LocalDateTime localDateTime = LocalDateTime.of(yearChecked,(month),dayChecked,startHour,startMins);

            event = new Event(eventId, eventName, eventDesc, localDateTime, endTime);
            ReadEvents.events.add(event);
            ReadEvents.saveFile(v.getContext());
            ReadEvents.readEvent(v.getContext()); // Read in the new data from the file

            // Start service for the notification
            Intent intent = new Intent(AddActivity.this, NotifService.class);
            intent.putExtra("eventId", eventId); // Send event's id to know which event gets the notification
            startService(intent);

            // Saving the id so new events don't get the same id
            SharedPreferences.Editor editor = settings.edit();
            eventId++;
            editor.putInt("eventId", eventId);
            editor.apply();
            // Clean the screen afterwards
            clean();
        }
    }

    class ValidateTextBoxes implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            validate();
        }
    }
    public void validate(){
        if(nameET.getText().toString().trim().length() > 0 && descET.getText().toString().trim().length() > 0 &&
                yearRG.getCheckedRadioButtonId() != -1 && monthRG.getCheckedRadioButtonId() != -1 && dayRG.getCheckedRadioButtonId() != -1)
            saveButton.setEnabled(true);
        else
            saveButton.setEnabled(false);
    }
    public void clean(){
        nameET.setText("");
        descET.setText("");
        startHourPicker.setValue(1);
        startMinutePicker.setValue(0);
        endMinPicker.setValue(0);
        endHourPicker.setValue(1);
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    class ValidateRadioGroup implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            validate();
        }
    }
}