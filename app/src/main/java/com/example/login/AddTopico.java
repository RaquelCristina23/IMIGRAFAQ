package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddTopico extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference topicosReference;
    private Spinner spinnerIdiomas;

    EditText pergunta, resposta;
    Button enviar;
    ListView listTopicos;
    ArrayList<Topico> topicos;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topico);

        database = FirebaseDatabase.getInstance();
        categoria = getIntent().getStringExtra("CATEGORIA");
        topicosReference = database.getReference("categorias/" + categoria);

        pergunta = findViewById(R.id.Pergunta);
        resposta = findViewById(R.id.Resposta);
        enviar = findViewById(R.id.btnEnviar);
        spinnerIdiomas = findViewById(R.id.spinnerIdioma2);

        // Adiciona as opções no spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.languageArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdiomas.setAdapter(adapter);


        topicos = new ArrayList<>();
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarComentario();
            }
        });

    }

    private void cadastrarComentario() {
        String pergunta = this.pergunta.getText().toString().trim();
        String resposta = this.resposta.getText().toString().trim();

        String idioma = "";

        if (spinnerIdiomas.getSelectedItemPosition() == 0) {
            idioma = "pt-br";
        } else {
            idioma = "fr-ht";
        }

        if (!TextUtils.isEmpty(pergunta) && !TextUtils.isEmpty(resposta)) {

            String id = topicosReference.push().getKey();

            Topico topico = new Topico();
            topico.setPergunta(pergunta);
            topico.setResposta(resposta);
            topico.setId(id);
            topico.setIdioma(idioma);

            topicosReference.child(id).setValue(topico);

            Toast.makeText(this, "Pergunta submetida", Toast.LENGTH_LONG).show();

            finish();

        } else {
            Toast.makeText(this, "Não deixe nenhum campo em branco", Toast.LENGTH_LONG).show();
        }

    }

}