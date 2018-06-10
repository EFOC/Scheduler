package com.example.eosorio1.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xdtruong on 4/23/2018.
 */

public class SigninActivity extends AppCompatActivity {
    Button button1, button2; // Buttons have style implemented with it.
    TextView text1, text2;
    EditText ed1, ed2;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signin );	//inflate XML layout in activity_signin.xml
    }


    public void signin( View v ) {
        // Retrieve username and password
        EditText usernameEditText = ( EditText) findViewById( R.id.input_username );
        EditText passwordEditText = ( EditText) findViewById( R.id.input_password );
        String usernameString = usernameEditText.getText( ).toString( );
        String passwordString = passwordEditText.getText( ).toString( );

        if(usernameString.equals("users") && passwordString.equals("users"))
        {
            Toast.makeText(getApplicationContext(),"Right Password!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong Password! | For testing purposes, the username and password are both 'users'", Toast.LENGTH_SHORT).show();
            // clear data
            usernameEditText.setText( "" );
            passwordEditText.setText( "" );
        }
    }

    public void goBack( View v ) {	//runs when cancel button clicked. Pops current activity off activity stack so app goes to previous activity.
        this.finish( );
        overridePendingTransition( R.anim.fade_in_and_scale, 0 );
    }

}
