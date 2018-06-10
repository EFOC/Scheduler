package com.example.eosorio1.project;
/**
 * PROJECT BY:
 * EDUARDO OSORIO
 * XUAN DINH TRUONG
 * RICO (JEUN ON) CHAN
 */



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_xml, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_signin:
                Intent signinIntent = new Intent( this, SigninActivity.class );
                this.startActivity( signinIntent );
                return true;
            case R.id.action_signup:
                Toast.makeText(this,"Button doesn't do anything but show that we know how to implement it", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_help:
                Toast.makeText(this,"Button doesn't do anything but show that we know how to implement it", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
