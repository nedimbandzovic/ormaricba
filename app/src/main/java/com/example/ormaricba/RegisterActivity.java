package com.example.ormaricba;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    CheckBox checkBox;
    EditText name, email, password, confirmpassword;
    Button buttonSGP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        checkBox=(CheckBox) findViewById(R.id.checkbox_meat);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.enterpassword);
        confirmpassword=findViewById(R.id.enterconfirmpassword);
        buttonSGP=findViewById(R.id.buttonForSignUp);

        checkBox=(CheckBox) findViewById(R.id.checkbox_meat);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, String.format("checkbox onClick, isSelected: %s, identityHashCode: %s", checkBox.isSelected(), System.identityHashCode(checkBox)));
            }
        });
        buttonSGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());

                if (!validateInput(user)){
                    Toast.makeText(getApplicationContext(), "You have to fill in all the fields", Toast.LENGTH_SHORT).show();
                } else if (!validateCheckBox()){
                    Toast.makeText(getApplicationContext(), "You have to confirm our Privacy Policy and Terms", Toast.LENGTH_SHORT).show();
                } else if (!validatePassword(user)) {
                    Toast.makeText(getApplicationContext(), "Your password must have 8 characters", Toast.LENGTH_SHORT).show();
                } else if (!samePswrdChecker(user)){
                    Toast.makeText(getApplicationContext(), "Your passwords do not match, please check again", Toast.LENGTH_SHORT).show();
                } else if (!isEmailValid(user)){
                    Toast.makeText(getApplicationContext(), "Your email does not use the correct format", Toast.LENGTH_SHORT).show();
                }else{
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Your registration is successful, you will be redirected to login in 5 seconds", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intnet=new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intnet);
                                            overridePendingTransition(0, 0);

                                        }
                                    }, 5000);
                                }
                            });
                        }
                    }).start();
                }
            }
        });

    }

    private Boolean validateInput(User user){
        if (user.getName().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty() || confirmpassword.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
    private Boolean validateCheckBox(){
        if (checkBox.isChecked()){
            return true;
        }
        return false;
    }
    private Boolean validatePassword(User user){
        if (user.getPassword().length()<8){
            return false;
        }
        return true;
    }
    private Boolean samePswrdChecker(User user){
        if (user.getPassword().equals(confirmpassword.getText().toString())){
            return true;
        }
        return false;
    }
    private boolean isEmailValid(User user) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches();
    }
}