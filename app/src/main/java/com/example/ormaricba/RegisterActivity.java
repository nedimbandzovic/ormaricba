package com.example.ormaricba;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class RegisterActivity extends AppCompatActivity {
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        checkBox=(CheckBox) findViewById(R.id.checkbox_meat);

        checkBox=(CheckBox) findViewById(R.id.checkbox_meat);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, String.format("checkbox onClick, isSelected: %s, identityHashCode: %s", checkBox.isSelected(), System.identityHashCode(checkBox)));
            }
        });

    }
}