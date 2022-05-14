package com.example.tp_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

public class EtudiantDetailsActivity extends AppCompatActivity {
    private static final int REQUEST_CALL=1;

    TextView name_etudiant;
    TextView prenom_etd;
    TextView etudiant_phone;
    TextView backMenu;
    TextView backButton;
    ImageView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_details);

        call=findViewById(R.id.call);
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

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

    }
    private void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(EtudiantDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EtudiantDetailsActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else{
            String dial= "tel: "+ etudiant_phone.getText().toString();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                System.out.println("no");
            }
        }
    }


}