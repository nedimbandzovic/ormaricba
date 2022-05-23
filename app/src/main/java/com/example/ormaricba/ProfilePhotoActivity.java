package com.example.ormaricba;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ProfilePhotoActivity extends AppCompatActivity {
    ImageView profilePic;
    Button upldButton;
    Uri imageUri;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_photo);
        profilePic=findViewById(R.id.imageView9upload);
        upldButton=findViewById(R.id.uploadbutton);
        storage=FirebaseStorage.getInstance();
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");

            }
        });
        upldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Uploading...");
        dialog.show();
        if (imageUri!=null){
            StorageReference reference=storage.getReference().child("images/" + UUID.randomUUID().toString());
            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Image uploaded successfully, you will be logged out", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intnet4=new Intent(ProfilePhotoActivity.this, LoginActivity.class);
                                intnet4.putExtra("pic",imageUri.toString());


                                startActivity(intnet4);
                                overridePendingTransition(0, 0);

                            }
                        }, 5000);

                    }
                    else{
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();


                    }

                }
            });
        }

    }

    ActivityResultLauncher<String> mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result!=null){
                profilePic.setImageURI(result);
                imageUri=result;


            }
        }
    });


}