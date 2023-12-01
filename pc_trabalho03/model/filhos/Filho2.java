package model.filhos;

import model.netos.Neto2;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Filho2 extends Thread {

    private Tela tela;
    private Line linha;
    private int largura = 75; // x
    private int altura = 225; // y

    public Filho2(Tela tela) {
        this.tela = tela;
    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Filho2, chama os processos Neto2
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        //System.out.println(filho1 + " nasceu!");

        Platform.runLater(() -> {
            Tela.imagemFilho2.setLayoutX(largura);
            Tela.imagemFilho2.setLayoutY(altura);
            linha = new Line(200, 325, 100, 275);
            tela.getChildren().addAll(Tela.imagemFilho2, linha);

        });
        try {
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemFilho2);
                Tela.imagemFilho2.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto2.png")));
                Tela.imagemFilho2.setLayoutX(largura);
                Tela.imagemFilho2.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho2);
            });
            sleep(4000);
            Neto2 neto2 = new Neto2(tela); // segundo neto
            neto2.start();
            sleep(30000);
            Platform.runLater(() -> { // fica idoso aos 50
                tela.getChildren().remove(Tela.imagemFilho2);
                Tela.imagemFilho2.setImage(new Image(getClass().getResourceAsStream("/view/imagens/idoso.png")));
                Tela.imagemFilho2.setLayoutX(largura);
                Tela.imagemFilho2.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho2);

            });
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch

        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemFilho2);
            Tela.imagemFilho2.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemFilho2.setLayoutX(largura);
            Tela.imagemFilho2.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemFilho2);

        });
    } // fim do metodo run
} // fim da classe Filho2
