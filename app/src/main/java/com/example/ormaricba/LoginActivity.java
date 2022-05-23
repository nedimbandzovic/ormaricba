package com.example.ormaricba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button buttonLgn;
    String ImageURIUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.username);
        password=findViewById(R.id.password);
        buttonLgn=findViewById(R.id.buttonLogin);
        Intent intent=getIntent();
        String NSurname = intent.getStringExtra("nameAndSur");
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
                    User user= userDao.login(emailText, passwordText);
                    new Thread(new Runnable(){
                        @Override
                        public void run(){
                            if (user == null){
                                runOnUiThread(new Runnable(){@Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Entered credentials are not correct, try again", Toast.LENGTH_SHORT).show();
                                }
                                });
                            } else{
                                Intent intent8=new Intent(LoginActivity.this, ProfileActivity.class);
                                intent8.putExtra("emailText", emailText);
                                intent8.putExtra("nameAndSurnameFinal", user.getName().toString());
                                intent8.putExtra("passwordFinal", passwordText);
                                startActivity(intent8);
                                overridePendingTransition(0, 0);
                            }
                        }
                    }).start();
                }
            }
        });


    }
}