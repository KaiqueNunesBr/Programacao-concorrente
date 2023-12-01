package model.filhos;

import model.netos.Neto1;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Filho1 extends Thread {

    private Tela tela;
    private Line linha;
    private int largura;
    private int altura;


    public Filho1(Tela tela) {
        this.tela = tela;
        this.largura = 175;
        this.altura = 225;
    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Filho1, chama os processos Neto1
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Platform.runLater(() -> {
            Tela.imagemFilho1.setLayoutX(largura);
            Tela.imagemFilho1.setLayoutY(altura);
            linha = new Line(200, 325, 200, 275);
            tela.getChildren().addAll(Tela.imagemFilho1, linha);
        });
        try { 
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemFilho1);
                Tela.imagemFilho1.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto1.png")));
                Tela.imagemFilho1.setLayoutX(largura);
                Tela.imagemFilho1.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho1);
            });
            Neto1 neto1 = new Neto1(tela); // primeiro neto
            neto1.start();
            sleep(34000);
            Platform.runLater(() -> { // fica idoso aos 50 anos
                tela.getChildren().remove(Tela.imagemFilho1);
                Tela.imagemFilho1.setImage(new Image(getClass().getResourceAsStream("/view/imagens/idoso.png")));
                Tela.imagemFilho1.setLayoutX(largura);
                Tela.imagemFilho1.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho1);

            });
            sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch

        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemFilho1);
            Tela.imagemFilho1.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemFilho1.setLayoutX(largura);
            Tela.imagemFilho1.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemFilho1);
        });
    } // fim do metodo run
}// fim da classe Filho1
