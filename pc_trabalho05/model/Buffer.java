package model;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue<Personagem> fila;
    /* ***************************************************************
    * Metodo: Buffer
    * Funcao: construtor
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public Buffer() {
        fila = new LinkedList<>();
    } // fim do construtor

    /* ***************************************************************
    * Metodo: adicionar
    * Funcao: adicionar um personagem a fila
    * Parametros: personagem: Personagem
    * Retorno: void
    *************************************************************** */
    public void adicionar(Personagem personagem) {
        fila.add(personagem);
    } // fim do metodo adicionar

    /* ***************************************************************
    * Metodo: remover
    * Funcao: remove e retorna o primeiro personagem da fila
    * Parametros: void
    * Retorno: Personagem
    *************************************************************** */
    public Personagem remover() {
        return fila.poll();
    } // fim do metodo remover

    /* ***************************************************************
    * Metodo: getSize
    * Funcao: retorna o tamanho da fila
    * Parametros: void
    * Retorno: int
    *************************************************************** */
    public int getSize() {
        return fila.size();
    } // fim do metodo getSize
} // fim da classe Buffer