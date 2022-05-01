package com.example.tp_android;


import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_android.adapetrs.ProfAdapter;
import com.example.tp_android.databinding.ActivityMainBinding;
import com.example.tp_android.model.Professeur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListeProfesseursActivity extends AppCompatActivity {

    ListView list_prof;
    LinkedList<Professeur> profs;
    FirebaseFirestore db;




    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_liste_professeurs);


     list_prof=(ListView) findViewById(R.id.list_view);
     db=FirebaseFirestore.getInstance();


        profs=new LinkedList<Professeur>();
        getallProfesseurs();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_prof);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ListeProfesseursActivity.this, AddProfActivity.class);
                startActivity(intent);
            }
        });


    }
    void getallProfesseurs(){

        showProgressDialog();
        db.collection("professeur")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getString("nom"));
                                System.out.println(document.getString("prenom"));
                                System.out.println(document.getString("tel"));
                                System.out.println(document.getString("photo"));
                                System.out.println(document.getString("departement"));
                                Professeur p = new Professeur(
                                        document.getString("nom"),
                                        document.getString("prenom"),
                                        document.getString("tel"),
                                        document.getString("photo"),
                                        document.getString("departement"));
                                profs.add(p);
                            }
                            System.out.println(profs);
                        } else {
                            System.out.println("Erreur");
                        }

                        hideProgressDialog();

//                        ArrayList<String> professeurs = new ArrayList<String>();
//                        for (Professeur prof: profs){
//                            professeurs.add(prof.getNom());
//                            professeurs.add(prof.getDepartement());
//                            professeurs.add(prof.getPrenom());
//                            professeurs.add(prof.getTel());
//                            professeurs.add(prof.getPhoto());
//                        }

                        ListView profView= findViewById(R.id.list_view);
                        profView.setAdapter(new ProfAdapter(ListeProfesseursActivity.this,profs));

                        profView.setClickable(true);




                       profView.setOnItemClickListener(

                               (parent,view,position,id)->{

                                   Professeur p= (Professeur) profView.getItemAtPosition(position);
                                   String nom=p.getNom();
                                   String prenom=p.getPrenom();
                                   String tel=p.getTel();
                                   String image=p.getPhoto();
                                   String departement=p.getDepartement();
//                                   Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(getApplicationContext(), professeurDetails.class);
                                   intent.putExtra("nom",nom);
                                   intent.putExtra("prenom",prenom);
                                   intent.putExtra("tel",tel);
                                   intent.putExtra("image",image);
                                   intent.putExtra("departement",departement);
                                   startActivity(intent);
                               }
                       );


                    }
                });
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}




