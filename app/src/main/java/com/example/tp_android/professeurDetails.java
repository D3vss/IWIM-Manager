package com.example.tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class professeurDetails extends AppCompatActivity {


    TextView name_prof;
    TextView prenom_prof;
    TextView tel_prof;
    TextView departement_prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professeur_details);

        name_prof = (TextView) findViewById(R.id.name_prof);
        prenom_prof = (TextView) findViewById(R.id.prof_prenom);
        tel_prof =(TextView) findViewById(R.id.prof_phone);
        departement_prof =(TextView) findViewById(R.id.prof_dept);

        Intent intent = getIntent();


        String nom = intent.getStringExtra("nom");
        name_prof.setText(nom);


        String prenom = intent.getStringExtra("prenom");
        prenom_prof.setText(prenom);

        String tel = intent.getStringExtra("tel");
        tel_prof.setText(tel);

        String departement = intent.getStringExtra("departement");
        departement_prof.setText(departement);



    }
}