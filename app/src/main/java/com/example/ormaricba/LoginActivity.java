package com.example.ormaricba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button buttonLgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.username);
        password=findViewById(R.id.password);
        buttonLgn=findViewById(R.id.buttonLogin);
        buttonLgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText=email.getText().toString();
                String passwordText=password.getText().toString();
                if (emailText.isEmpty()||passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill in both fields", Toast.LENGTH_SHORT).show();
                } else{
                    UserDatabase userDatabase=UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao=userDatabase.userDao();
                    new Thread(new Runnable(){
                        @Override
                        public void run(){
                            User user= userDao.login(emailText, passwordText);
                            if (user == null){
                                runOnUiThread(new Runnable(){@Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Entered credentials are not correct, try again", Toast.LENGTH_SHORT).show();
                                }
                                });
                            } else{
                                password.setText("Nice");

                            }
                        }
                    }).start();
                }
            }
        });


    }
}