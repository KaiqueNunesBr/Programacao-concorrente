package model;

import view.Tela;

import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Consumidor extends Thread {

    private ImageView scorpion;
    private Image imEsperando;
    private Image imJogando;

    private ImageView corrente;
    private Image imCorrente;

    private ImageView barra;
    private Image imBarra;

    private Slider controleConsumidor;
    private int velConsumidor;

    private Personagem vitima;

    private Semaphore mutex;
    private Semaphore cheio;
    private Semaphore vazio;
    private Buffer buffer;

    /* ***************************************************************
    * Metodo: Consumidor
    * Funcao: construtor
    * Parametros: tela: Tela
    * Retorno: void
    *************************************************************** */
    public Consumidor(Tela tela) {
        imEsperando = new Image(getClass().getResourceAsStream("/view/imagens/scorpion/esperando.png"));
        imCorrente = new Image(getClass().getResourceAsStream("/view/imagens/scorpion/corrente.png"));
        imBarra = new Image(getClass().getResourceAsStream("/view/imagens/scorpion/barra.png"));
        imJogando = new Image(getClass().getResourceAsStream("/view/imagens/scorpion/jogando.png"));

        scorpion = new ImageView(imEsperando);
        corrente = new ImageView(imCorrente);
        barra = new ImageView(imBarra);

        // posicao do Consumidor
        scorpion.setLayoutX(75);
        scorpion.setLayoutY(210);

        // posicao da bara de "Velocidade"
        barra.setLayoutX(125);
        barra.setLayoutY(25);

        // posicao da corrente
        corrente.setLayoutX(190);
        corrente.setLayoutY(225);
        corrente.setVisible(false);

        velConsumidor = 500;
        controleConsumidor = new Slider(-30, -5, -5);
        controleConsumidor.setMinorTickCount(5);
        controleConsumidor.setBlockIncrement(5);
        controleConsumidor.setLayoutX(125);
        controleConsumidor.setLayoutY(25);
        controleConsumidor.setMaxSize(350, 40);
        controleConsumidor.setMinSize(350, 40);

        tela.getChildren().addAll(scorpion, corrente, barra, controleConsumidor); // adicionando o trem na tela

        controleConsumidor.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velConsumidor = 100 * (-newValue.intValue());
            }
        }); // fim da acao do slider controleProdutor
    } // fim do construtor

    /* ***************************************************************
    * Metodo: run
    * Funcao: o que sera executado pela thread
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        while (true) {
            esperar();
            try {
                cheio.acquire();
                mutex.acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            vitima = buffer.remover();
            matar();
            mutex.release();
            vazio.release();

            mySleep(velConsumidor);
        } // fim do while
    } // fim do run

    /* ***************************************************************
    * Metodo: esperar
    * Funcao: esperar ate que tenha algo pra consumir
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void esperar() {
        Platform.runLater(() -> {
            scorpion.setImage(imEsperando);
        });
    } // fim do metodo esperar

    /* ***************************************************************
    * Metodo: jogarCorrente
    * Funcao: animacao da corrente esticando
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void jogarCorrente() {
        Platform.runLater(() -> {
            scorpion.setImage(imJogando);
            corrente.setVisible(true);
            corrente.setFitWidth(1);
        });
        for (int cont = 1; cont < 180; cont += 5) {
            moverCorrente(cont);
            mySleep(5);
        } // fim do for
    } // fim do metodo jogarCorrente

    /* ***************************************************************
    * Metodo: puxarCorrente
    * Funcao: animacao da corrente contraindo
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void puxarCorrente() {
        for (int cont = 180; cont > 0; cont -= 5) {
            moverCorrente(cont);
            mySleep(5);
        } // fim do for
        Platform.runLater(() -> {
            corrente.setVisible(false);
            scorpion.setImage(imEsperando);
        });
    } // fim do metodo puxarCorrente

    /* ***************************************************************
    * Metodo: moverCorrente
    * Funcao: evitar Platform por todo codigo
    * Parametros: timer: int
    * Retorno: void
    *************************************************************** */
    private void moverCorrente(int timer) {
        Platform.runLater(() -> corrente.setFitWidth(timer));
    } // fim do metodo moverCorrente

    /* ***************************************************************
    * Metodo: matar
    * Funcao: toda acao de jogar, matar e puxar corrente junto
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void matar() {
        while (!vitima.getPosicaoMorte()) {
            mySleep(10);
        } // fim do while
        jogarCorrente();
        vitima.morrer();
        puxarCorrente();
    } // fim do metodo matar

    /* ***************************************************************
    * Metodo: mySleep
    * Funcao: evitar try/catch por todo codigo
    * Parametros: tempo: int
    * Retorno: void
    *************************************************************** */
    private void mySleep(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } // fim do metodo
    } // fim do metodo mySleep

    /* ***************************************************************
    * Metodo: setBuffer
    * Funcao: set o buffer
    * Parametros: buffer: buffer
    * Retorno: void
    *************************************************************** */
    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    } // fim do metodo setBuffer

    /* ***************************************************************
    * Metodo: setSemaphoros
    * Funcao: set os semaforos
    * Parametros: mutex, cheio, vazio: Semaphore
    * Retorno: void
    *************************************************************** */
    public void setSemaphoros(Semaphore mutex, Semaphore cheio, Semaphore vazio) {
        this.mutex = mutex;
        this.cheio = cheio;
        this.vazio = vazio;
    } // fim so  do metodo setSemaphoros
} // fim da classe Consumidor