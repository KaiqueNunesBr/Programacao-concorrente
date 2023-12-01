package model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
  private List<Aluno> alunos;
  private String nome;

  public Curso(String nome) {
    this.nome = nome;
    alunos = new ArrayList<>();
  }//fim do construtor

  public List<Aluno> getAlunos() {
    return alunos;
  }
  public String getNome() {
    return nome;
  }
  /*************************************************
    * Metodo: toString
    * Funcao: Serve para representar o objeto "Aluno" como uma string
    * Parametros: null
    * Retorno: string
    ************************************************** */
  @Override
  public String toString() {
    return nome;
  }
}//fim da classe curso