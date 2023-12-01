package model.filhos;

import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Filho3 extends Thread {

    private Tela tela;
    private Line linha;
    private int largura = 275; // x
    private int altura = 225; // y

    public Filho3(Tela tela) {
        this.tela = tela;

    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Filho3
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Platform.runLater(() -> {
            Tela.imagemFilho3.setLayoutX(largura);
            Tela.imagemFilho3.setLayoutY(altura);
            linha = new Line(200, 325, 300, 275);
            tela.getChildren().addAll(Tela.imagemFilho3,linha);
        });
        try {
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemFilho3);
                Tela.imagemFilho3.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto3.png")));
                Tela.imagemFilho3.setLayoutX(largura);
                Tela.imagemFilho3.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho3);
            });
            sleep(34000);
            Platform.runLater(() -> { // fica idoso aos 50
                tela.getChildren().remove(Tela.imagemFilho3);
                Tela.imagemFilho3.setImage(new Image(getClass().getResourceAsStream("/view/imagens/idoso.png")));
                Tela.imagemFilho3.setLayoutX(largura);
                Tela.imagemFilho3.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemFilho3);

            });
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch
        
        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemFilho3);
            Tela.imagemFilho3.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemFilho3.setLayoutX(largura);
            Tela.imagemFilho3.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemFilho3);
        });
    } // fim do metodo run
} // fim da classe Filho3

