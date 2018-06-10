package com.example.eosorio1.project;

import android.graphics.Color;
import android.widget.CompoundButton;

/**
 * Created by osori on 4/12/2018.
 */
public class CheckedButton implements CompoundButton.OnCheckedChangeListener{
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            buttonView.setBackgroundColor(Color.GRAY);
        else
            buttonView.setBackgroundColor(Color.WHITE);
    }
}