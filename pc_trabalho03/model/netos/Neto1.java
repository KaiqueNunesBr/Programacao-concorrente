package model.netos;

import model.bisnetos.Bisneto;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Neto1 extends Thread {

    private Tela tela;
    private Line linha;
    private int largura = 175; // x
    private int altura = 125; // y

    public Neto1(Tela tela) {
        this.tela = tela;

    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Neto1, chama os processos Bisneto
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        //System.out.println(filho1 + " nasceu!");

        Platform.runLater(() -> {
            Tela.imagemNeto1.setLayoutX(largura);
            Tela.imagemNeto1.setLayoutY(altura);
            linha = new Line(200, 225, 200, 175);
            tela.getChildren().addAll(Tela.imagemNeto1, linha);

        });
        try {
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemNeto1);
                Tela.imagemNeto1.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto0.png")));
                Tela.imagemNeto1.setLayoutX(largura);
                Tela.imagemNeto1.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemNeto1);
            });
            sleep(14000);
            Bisneto bisneto = new Bisneto(tela);
            bisneto.start();
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemNeto1);
            Tela.imagemNeto1.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemNeto1.setLayoutX(largura);
            Tela.imagemNeto1.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemNeto1);

        });
    } // fim do metodo run
} // fim da classe Neto1
