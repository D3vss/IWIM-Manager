package com.example.tp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class professeurDetails extends AppCompatActivity {

    TextView name_prof;
    TextView prenom_prof;
    TextView tel_prof;
    TextView departement_prof;
    TextView backMenu;
    ImageView profile_prof;
    TextView backButton;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professeur_details);

        backMenu=findViewById(R.id.Go_back_menu);
        name_prof = (TextView) findViewById(R.id.name_prof);
        profile_prof = (ImageView) findViewById(R.id.profile_image);
        prenom_prof = (TextView) findViewById(R.id.prof_prenom);
        tel_prof =(TextView) findViewById(R.id.prof_phone);
        departement_prof =(TextView) findViewById(R.id.prof_dept);
        backButton = findViewById(R.id.Go_back);

        backButton.setOnClickListener(view ->{
            startActivity(new Intent(professeurDetails.this, ListeProfesseursActivity.class));
        });

        backMenu.setOnClickListener(view ->{
            startActivity(new Intent(professeurDetails.this, MainActivity.class));
        });

        Intent intent = getIntent();


        String nom = intent.getStringExtra("nom");
        name_prof.setText(nom);


        String prenom = intent.getStringExtra("prenom");
        prenom_prof.setText(prenom);

        String tel = intent.getStringExtra("tel");
        tel_prof.setText(tel);

        String departement = intent.getStringExtra("departement");
        departement_prof.setText(departement);



        String photo = intent.getStringExtra("image");
        Uri imageUrl= Uri.parse(photo);


        storageRef = FirebaseStorage.getInstance().getReference("photo/professeur/"+imageUrl);
        try {
            File locationFile = File.createTempFile(photo,"jpeg");
            storageRef.getFile(locationFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    profile_prof.setImageURI(imageUrl);
                    //start
                    Bitmap bitmap = BitmapFactory.decodeFile(locationFile.getAbsolutePath());
                    profile_prof.setImageBitmap(bitmap);
                    //end
                    Toast.makeText(getApplicationContext(),"profile prof image done",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Probleme to take the pic",Toast.LENGTH_LONG).show();
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }



        System.out.println(storageRef.getFile(imageUrl)+"HANA11");

        System.out.println(imageUrl+"wsslt");








    }
}