package com.example.ormaricba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView nameAndSurname, emailAdd;
    String emailxxx,NS, passwordF;
    ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        String imageUri;


        nameAndSurname=findViewById(R.id.textView6);
        emailAdd=findViewById(R.id.textView7);
        profilePhoto=findViewById(R.id.imageView10);
        emailxxx=intent.getStringExtra("emailText");
        emailAdd.setText(emailxxx);
        NS=intent.getStringExtra("nameAndSurnameFinal");
        nameAndSurname.setText(NS);
        passwordF=intent.getStringExtra("passwordFinal");
        Uri uri = intent.getParcelableExtra("imageProf");
        profilePhoto.setImageURI(uri);


        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profPhoto=new Intent (ProfileActivity.this, ProfilePhotoActivity.class);
                intent.putExtra("pass", passwordF);
                intent.putExtra("mejl", emailxxx);
                startActivity(profPhoto);
                overridePendingTransition(0, 0);


            }
        });





    }
}