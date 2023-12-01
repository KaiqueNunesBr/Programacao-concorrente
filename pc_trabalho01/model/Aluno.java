package model;

public class Aluno {
    private String nome;
    private String matricula;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }// fim do construtor
    /*************************************************
    * Metodo: toString
    * Funcao: Serve para representar o objeto "Aluno" como uma string.
    * Parametros: null
    * Retorno: representação de string do objeto
    ************************************************** */
    @Override
    public String toString() {
        return "[" + matricula + "] " + nome;
    }
}// fim da classe