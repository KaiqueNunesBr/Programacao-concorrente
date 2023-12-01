package model.netos;

import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Neto2 extends Thread {

    private Tela tela;
    private Line linha;
    private int largura = 75; // x
    private int altura = 125; // y

    public Neto2(Tela tela) {
        this.tela = tela;

    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Neto2
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Platform.runLater(() -> {
            Tela.imagemNeto2.setLayoutX(largura);
            Tela.imagemNeto2.setLayoutY(altura);
            linha = new Line(100, 225, 100, 175);
            tela.getChildren().addAll(Tela.imagemNeto2, linha);
        });
        try {
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemNeto2);
                Tela.imagemNeto2.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto0.png")));
                Tela.imagemNeto2.setLayoutX(largura);
                Tela.imagemNeto2.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemNeto2);
            });
            sleep(17000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemNeto2);
            Tela.imagemNeto2.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemNeto2.setLayoutX(largura);
            Tela.imagemNeto2.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemNeto2);

        });
    } // fim do metodo run
} // fim da classe Neto2

