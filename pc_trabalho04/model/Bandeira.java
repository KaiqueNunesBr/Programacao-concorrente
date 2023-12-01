package model;

import javafx.scene.image.Image;
import view.Tela;

public class Bandeira {

    private Image bandeiraVermelha;
    private Image bandeiraVerde;

    private Tela tela;

    private boolean variavelTravamento;

    public Bandeira(Tela tela) {
        this.variavelTravamento = true; // responsavel por diser ha trem na regiao critica
        this.tela = tela;
        
        // cores das bandeiras
        bandeiraVermelha = new Image(getClass().getResourceAsStream("/view/imagens/bandeiraVermelha.png"));
        bandeiraVerde = new Image(getClass().getResourceAsStream("/view/imagens/bandeiraVerde.png"));
    } // fim do construtor

    /* ***************************************************************
    * Metodo: entrarRegiaoCritica
    * Funcao: antes da thread que chamou entrar na regiao critica verifica se
    * se ja existe alguma outra thread na regiao critica se existir esta espera
    * a saida, caso contrario, bloqueia a entrada de uma possivel thread
    * Parametros: int, 1 caso seja chamado pelo trem 1 
    * e 2 caso seja chamado pelo trem 2
    * Retorno: descricao do valor retornado
    *************************************************************** */
    synchronized void entrarRegiaoCritica(int trem) {
        if (variavelTravamento) {
            variavelTravamento = false;
            if (trem == 1) {
                bloquearPassagem2();
            } else {
                bloquearPassagem1();
            }
        } else {
            try {
                while (!variavelTravamento) {
                    wait();//Travar a thread
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do if else
    } // fim do metodo entrarRegiaoCritica

    /* ***************************************************************
    * Metodo: sairRegiaoCritica
    * Funcao: sinaliza que a thread saiu da regiao critica 
    * Parametros: int, 1 caso seja chamado pelo trem 1 
    * e 2 caso seja chamado pelo trem 2
    * Retorno: descricao do valor retornado
    *************************************************************** */
    synchronized void sairRegiaoCritica(int trem) {
        variavelTravamento = true;
        if (trem == 1) {
            liberarPassagem2();
        } else {
            liberarPassagem1();
        }
        try {
            notifyAll();//Travar a thread
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // fim do metodo sairRegiaoCritica

    /* ***************************************************************
    * Metodo: bloquearPassagem2
    * Funcao: set a bandeira do trem 2 como vermelha
    * Parametros: void 
    * Retorno: void
    *************************************************************** */
    private void bloquearPassagem2() {
        tela.setImBandeira2(bandeiraVermelha);
    } // fim do metodo bloquearPassagem2

    /* ***************************************************************
    * Metodo: bloquearPassagem1
    * Funcao: set a bandeira do trem 1 como vermelha
    * Parametros: void 
    * Retorno: void
    *************************************************************** */
    private void bloquearPassagem1() {
        tela.setImBandeira1(bandeiraVermelha);
    } // fim do metodo bloquearPassagem1

    /* ***************************************************************
    * Metodo: liberarPassagem2
    * Funcao: set a bandeira do trem 2 como verde
    * Parametros: void 
    * Retorno: void
    *************************************************************** */
    private void liberarPassagem2() {
        tela.setImBandeira2(bandeiraVerde);
    } // fim do metodo liberarPassagem2

    /* ***************************************************************
    * Metodo: liberarPassagem1
    * Funcao: set a bandeira do trem 1 como verde
    * Parametros: void 
    * Retorno: void
    *************************************************************** */
    private void liberarPassagem1() {
        tela.setImBandeira1(bandeiraVerde);
    } // fim do metodo liberarPassagem1
} // fim da classe Bandeira
