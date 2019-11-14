package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Categorias extends AppCompatActivity {

    private Button btnSaude;
    private Button btnEducacao;
    private Button btnEmpregos;
    private Button btnCras;
    private Button btnContatos;
    private Button btnEventos;

    private FirebaseDatabase database;
    private DatabaseReference usuarioReference;

    private String idiomaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        database = FirebaseDatabase.getInstance();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        usuarioReference = database.getReference("usuarios/" + currentFirebaseUser.getUid());

        btnSaude = (Button) findViewById(R.id.btnSaude);
        btnEducacao = (Button) findViewById(R.id.btnEducacao);
        btnEmpregos = (Button) findViewById(R.id.btnEmpregos);
        btnCras = (Button) findViewById(R.id.btnCras);
        btnContatos = (Button) findViewById(R.id.btnContatos);
        btnEventos = (Button) findViewById(R.id.btnEventos);

        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("saude");

            }
        });

        btnEducacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("educacao");

            }
        });

        btnEmpregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("empregos");

            }
        });

        btnCras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("cras");

            }
        });

        btnContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("contatos");

            }
        });

        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AbrirTopico("eventos");

            }
        });


        usuarioReference.child("idioma").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idiomaUsuario = dataSnapshot.getValue().toString();
                ajustarIdioma();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void AbrirTopico(String categoria) {

        Intent intent = new Intent(Categorias.this, ListarTopicos.class);

        intent.putExtra("CATEGORIA", categoria);

        startActivity(intent);

    }

    private void ajustarIdioma() {
        if (idiomaUsuario.equals("pt-br")) {
            btnSaude.setText(R.string.categoriaSaudePtBr);
            btnEducacao.setText(R.string.categoriaEducacaoPtBr);
            btnEmpregos.setText(R.string.categoriaEmpregosPtBr);
            btnCras.setText(R.string.categoriaCrasPtBr);
            btnContatos.setText(R.string.categoriaContatoPtBr);
            btnEventos.setText(R.string.categoriaOnibusPtBr);
        } else {
            btnSaude.setText(R.string.categoriaSaudeFrHt);
            btnEducacao.setText(R.string.categoriaEducaçãoFrHt);
            btnEmpregos.setText(R.string.categoriaEducaçãoFrHt);
            btnCras.setText(R.string.categoriaCrasFrHt);
            btnContatos.setText(R.string.categoriaContatoFrHt);
            btnEventos.setText(R.string.categoriaOnibusFrHt);
        }
    }

}
