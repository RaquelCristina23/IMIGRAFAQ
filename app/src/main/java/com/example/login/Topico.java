package com.example.login;

public class Topico {

    private String idioma;
    private String pergunta;
    private String resposta;
    private String id;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Topico() {

    }

    public Topico(String idioma, String pergunta, String resposta, String id) {
        this.idioma = idioma;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.id = id;


    }

}