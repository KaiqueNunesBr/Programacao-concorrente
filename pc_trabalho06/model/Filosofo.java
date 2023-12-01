package model;

import java.util.concurrent.Semaphore;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Filosofo extends Thread {

    private Tela tela;
    private Contole jantar;

    private Image imPensando;
    private Image imComendo;
    private Image imEsperando;

    private ImageView imEstado;

    private int id;
    private int esquerda;
    private int direita;

    private Semaphore mutex;
    private Semaphore s[];
    private int estados[];

    public Filosofo(int id, Tela tela, Contole jantar, int posX, int posY, Semaphore mutex, Semaphore s[], int estados[]) {
        this.tela = tela;
        this.jantar = jantar;
        this.id = id;

        this.mutex = mutex;
        this.s = s;
        this.estados = estados;

        esquerda = (id + jantar.getN() - 1) % jantar.getN();
        direita = (id + 1) % jantar.getN();

        imPensando = new Image(getClass().getResourceAsStream("/view/imagens/pensando.png"));
        imComendo = new Image(getClass().getResourceAsStream("/view/imagens/comendo.png"));
        imEsperando = new Image(getClass().getResourceAsStream("/view/imagens/esperando.png"));

        imEstado = new ImageView(imPensando);

        imEstado.setLayoutX(posX);
        imEstado.setLayoutY(posY);

        Platform.runLater(() -> tela.getChildren().add(imEstado));
    }

    @Override
    public void run() {
        while (true) {
            pensando();
            pegaGarfos();
            comendo();
            devolverGafos();
        } // fim do while
    } // fim do run

    private void pensando() {
        Platform.runLater(() -> imEstado.setImage(imPensando));
        sleepT(tela.getVelocidadePensar(id));
    }

    private void pegaGarfos() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        estados[id] = 1;

        this.test();
        Platform.runLater(() -> imEstado.setImage(imEsperando));
        mutex.release();
        try {
            s[id].acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void comendo() {
        Platform.runLater(() -> imEstado.setImage(imComendo));
        sleepT(tela.getVelocidadeComer(id));
    }

    private void devolverGafos() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        estados[id] = 0;
        jantar.getFilosofo(esquerda).test();
        jantar.getFilosofo(direita).test();
        mutex.release();
    }

    private void test() {
        if (estados[id] == 1 && estados[esquerda] != 2 && estados[direita] != 2) {
            estados[id] = 2;
            s[id].release();
        }
    }

    private void sleepT(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
