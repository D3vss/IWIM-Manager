package com.example.tp_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

public class EtudiantDetailsActivity extends AppCompatActivity {

    TextView name_etudiant;
    TextView prenom_etd;
    TextView etudiant_phone;
    TextView backMenu;
    TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_details);

        backMenu=findViewById(R.id.Go_back_menu);
        name_etudiant=findViewById(R.id.name_etudiant);
        prenom_etd=findViewById(R.id.prenom_etd);
        etudiant_phone= findViewById(R.id.etudiant_phone);
        backButton=findViewById(R.id.Go_back);

        backButton.setOnClickListener(view ->{
            startActivity(new Intent(EtudiantDetailsActivity.this, ListEtudiantActivity.class));
        });

        backMenu.setOnClickListener(view ->{
            startActivity(new Intent(EtudiantDetailsActivity.this, MainActivity.class));
        });

        Intent intent = getIntent();


        String nom = intent.getStringExtra("nom");
        name_etudiant.setText(nom);


        String prenom = intent.getStringExtra("prenom");
        prenom_etd.setText(prenom);

        String tel = intent.getStringExtra("tel");
        etudiant_phone.setText(tel);


    }


}