package model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Personagem extends Thread {
    private Tela tela;

    private ImageView subZero;

    private Image indo;
    private Image morrendo;
    private Image esperando;

    private Vagas vagas;

    private int posX;
    private int posY;

    private boolean posicaoMorte;

    /* ***************************************************************
    * Metodo: Personagem
    * Funcao: construtor
    * Parametros: tela: Tela, buffer: Buffer, vagas: Vagas
    * Retorno: void
    *************************************************************** */
    public Personagem(Tela tela, Vagas vagas) {
        this.tela = tela;
        this.vagas = vagas;
        
        posicaoMorte = false;

        indo = new Image(getClass().getResourceAsStream("/view/imagens/subZero/indo.gif"));
        morrendo = new Image(getClass().getResourceAsStream("/view/imagens/subZero/morrendo.gif"));
        esperando = new Image(getClass().getResourceAsStream("/view/imagens/subZero/esperando.png"));

        subZero = new ImageView(esperando);

        posX = 900;
        posY = 210;

        subZero.setLayoutX(posX);
        subZero.setLayoutY(posY);
        Platform.runLater(() -> tela.getChildren().add(subZero));
        this.start();
    } // fim do construtor

    /* ***************************************************************
    * Metodo: run
    * Funcao: o que sera executado pela thread
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        terceiraVaga();
    } // fim do run

    /* ***************************************************************
    * Metodo: setPosicao
    * Funcao: configurar a posicao do ImageView
    * Parametros: posX, posY: int
    * Retorno: void
    *************************************************************** */
    private void setPosicao(int posX, int posY) {
        Platform.runLater(() -> {
            subZero.setLayoutX(posX);
            subZero.setLayoutY(posY);
        });
    } // fim do metodo setPosicao

    /* ***************************************************************
    * Metodo: primeiraVaga
    * Funcao: animacao da segunda para a primeira vaga
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void primeiraVaga() {
        Platform.runLater(() -> {
            subZero.setImage(indo);
        });
        for (; posX > 335; posX -= 5) {
            setPosicao(posX, posY);
            mySleep(10);
        } // fim do for
        Platform.runLater(() -> {
            subZero.setImage(esperando);
        });
        posicaoMorte = true;
    } //  fim do metodo primeiraVaga

    /* ***************************************************************
    * Metodo: segundaVaga
    * Funcao: animacao da terceira para a segunda vaga
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void segundaVaga() {
        vagas.setVaga2(false);
        Platform.runLater(() -> {
            subZero.setImage(indo);
        });
        for (; posX > 465; posX -= 5) {
            setPosicao(posX, posY);
            mySleep(10);
        } // fim do for
        Platform.runLater(() -> {
            subZero.setImage(esperando);
        });
        while (!vagas.getVaga1()) {
            mySleep(10);
        } // fim do while
        vagas.setVaga1(false);
        vagas.setVaga2(true);
        primeiraVaga();
    } //  fim do metodo segundaVaga

    /* ***************************************************************
    * Metodo: terceiraVaga
    * Funcao: animacao da inicializacao para a terceira vaga
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void terceiraVaga() {
        vagas.setVaga3(false);
        Platform.runLater(() -> {
            subZero.setImage(indo);
        });
        for (; posX > 595; posX -= 5) {
            setPosicao(posX, posY);
            mySleep(10);
        } // fim do for

        Platform.runLater(() -> {
            subZero.setImage(esperando);
        });
        while (!vagas.getVaga2()) {
            mySleep(10);
        } // fim do while
        vagas.setVaga2(false);
        vagas.setVaga3(true);
        segundaVaga();
    } // fim do metodo terceiraVaga

    /* ***************************************************************
    * Metodo: morrer
    * Funcao: animacao de morte
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void morrer() {
        vagas.setVaga1(false);
        Platform.runLater(() -> {
            subZero.setImage(morrendo);
        });
        mySleep(100);
        //posicaoMorte = false;
        vagas.setVaga1(true);
        Platform.runLater(() -> tela.getChildren().remove(subZero));
    } // fim do metodo morrer

    /* ***************************************************************
    * Metodo: mySleep
    * Funcao: evitar try/catch por todo codigo
    * Parametros: tempo:int
    * Retorno: void
    *************************************************************** */
    private void mySleep(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } // fim do try catch
    } // fim do metodo mySleep
    
    /* ***************************************************************
    * Metodo: getPosicaoMorte
    * Funcao: retorna se o personagem esta na posicao para morte
    * Parametros: void
    * Retorno: booleam
    *************************************************************** */
    public boolean getPosicaoMorte() {
        return posicaoMorte;
    } // fim do metodo getPosicaoMorte
} // fim da classe Personagem