package com.example.eosorio1.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private ReadEvents eventsList;
    private ArrayList<Event> events;
    private Button create;
    private Button remove;
    private Button home;
    private RadioGroup radioGroup;
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        updateView( );								//creates the GUI
    }

    @Override
    public void onStart() {
        super.onStart();
        updateView();
    }
    public void updateView() {
        ReadEvents.readEvent(this);
        events = ReadEvents.events;
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        TableLayout eventListLayout = new TableLayout(this);

        // MAKING THE SCROLL VIEW
        radioGroup = new RadioGroup(this);
        CheckedButton cb = new CheckedButton();
        for (Event event : events) {
            RadioButton rb = new RadioButton(this);
            rb.setButtonDrawable(null);
            rb.setId(event.getId());
            rb.setPadding(15, 10, 0, 10);
            rb.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rb.setOnCheckedChangeListener(cb);
            rb.setText(event.display());
            radioGroup.addView(rb);
        }
        radioGroup.setGravity(Gravity.CENTER);
        eventListLayout.addView(radioGroup);
        scrollView.addView(eventListLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        RelativeLayout.LayoutParams params2
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        params2.addRule(RelativeLayout.ABOVE, R.id.buttonTableLayout); // Make the layout go above the buttons
        params.setMargins(0, 0, 0, 50);

        // Create the buttons
        create = new Button(this);
        remove = new Button(this);
        home = new Button(this);
        // Send to the home activity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.side_from_right,0);
            }
        });
        // Send to the create activity
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.side_from_left,0);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemoveDialog();
            }
        });
        // Set the text
        create.setText(R.string.create_btn);
        remove.setText(R.string.remove_btn);
        home.setText(R.string.home_btn);
        TableLayout buttonTableLayout = new TableLayout(this);
        buttonTableLayout.setId(R.id.buttonTableLayout);
        buttonTableLayout.addView(create);
        buttonTableLayout.addView(remove);
        buttonTableLayout.addView(home);
        relativeLayout.addView(scrollView, params2);
        relativeLayout.addView(buttonTableLayout, params);
        setContentView(relativeLayout); // Set the views
    }

    public void showRemoveDialog( ) {
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "Choose" );
        alert.setMessage( "Are you sure you want to remove?" );
        RemoveDialog removeDialog = new RemoveDialog( );
        alert.setPositiveButton( "YES", removeDialog );
        alert.setNegativeButton( "NO", removeDialog );
        alert.show( );
    }
    private class RemoveDialog implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id ) {
            if( id == -1 ) { // YES button
                events.removeIf(event -> event.getId() == radioGroup.getCheckedRadioButtonId());
                ReadEvents.saveFile(MainActivity.this);
                updateView();
            }
            else if( id == -2 ){return;} // NO button
        }
    }
}