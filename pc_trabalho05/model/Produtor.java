package model;

import java.util.concurrent.Semaphore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Produtor extends Thread {

    Tela tela;

    private ImageView Barra;
    private Image imBarra;

    private Slider controleProdutor;

    private int velProdutior;

    private Buffer buffer;
    private Vagas vagas;
    private Semaphore mutex;
    private Semaphore cheio;
    private Semaphore vazio;

    /* ***************************************************************
    * Metodo: Produtor
    * Funcao: construtor
    * Parametros: tela: Tela
    * Retorno: void
    *************************************************************** */
    public Produtor(Tela tela) {
        this.tela = tela;

        vagas = new Vagas();

        imBarra = new Image(getClass().getResourceAsStream("/view/imagens/subZero/barra.png"));
        Barra = new ImageView(imBarra);

        Barra.setLayoutX(540);
        Barra.setLayoutY(25);

        velProdutior = 500; // valor inicial do slide
        controleProdutor = new Slider(-30, -5, -5);
        controleProdutor.setBlockIncrement(5);
        controleProdutor.setMinorTickCount(5);
        controleProdutor.setLayoutX(540);
        controleProdutor.setLayoutY(25);
        controleProdutor.setMaxSize(350, 40);
        controleProdutor.setMinSize(350, 40);

        tela.getChildren().addAll(Barra, controleProdutor);

        controleProdutor.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velProdutior = 100 * (-newValue.intValue());
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
            if (vagas.getVaga3() && (buffer.getSize() < 3)) {
                Personagem novo = new Personagem(tela, vagas);
                try {
                    vazio.acquire();
                    mutex.acquire();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } // fim do try catch
                buffer.adicionar(novo);

                mutex.release();
                cheio.release();
            } // fim do if
            mySleep(velProdutior);
        } // fim do while
    } // fim do metodo run

    /* ***************************************************************
    * Metodo: setBuffer
    * Funcao: set o buffer
    * Parametros: buffer: Buffer
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
    } // fim do metodo serSemaphoros

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
} // fim da classe Produtor