package com.example.tp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddProfActivity extends AppCompatActivity {

    EditText nomProf;
    EditText prenomProf;
    EditText departementProf;
    Button photoProf;
    EditText telProf;
    ImageView confirm_button;
    TextView Back_menu;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof);

        nomProf = findViewById(R.id.nomProf);
        prenomProf = findViewById(R.id.prenomProf);
        departementProf = findViewById(R.id.departementProf);
        photoProf = findViewById(R.id.buttonPhoto);
        telProf = findViewById(R.id.telProf);
        confirm_button = findViewById(R.id.confirm_button);
        Back_menu = findViewById(R.id.Back_menu);
        db = FirebaseFirestore.getInstance();

        photoProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getImageFromAlbum();
                System.out.println("photo should be inserted");
                Toast.makeText(getApplicationContext(),"B7ala zdti tswira",Toast.LENGTH_LONG).show();
            }
        });



        confirm_button.setOnClickListener(view ->{
            createProf();
        });

        Back_menu.setOnClickListener(view ->{
            startActivity(new Intent(AddProfActivity.this, MainActivity.class));
        });

    }

    private void createProf() {
        Map<String, Object> prof = new HashMap<>();
        String nom = nomProf.getText().toString();
        String prenom = prenomProf.getText().toString();
        String departement = departementProf.getText().toString();
        String tel = telProf.getText().toString();
        prof.put("nom", nom);
        prof.put("prenom", prenom);
        prof.put("departement", departement);
        prof.put("tel", tel);
        prof.put("photo","photo insert here");


        db.collection("professeur")
                .add(prof)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(AddProfActivity.this, ListeProfesseursActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Erreur"+e);
                    }
                });


    }



}