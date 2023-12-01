package model.bisnetos;

import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import view.Tela;

public class Bisneto extends Thread {
    private Tela tela;
    private Line linha;
    private int largura = 175; // x
    private int altura = 25; // y

    public Bisneto(Tela tela) {
        this.tela = tela;

    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo bisneto
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Platform.runLater(() -> {
            Tela.imagemBisneto.setLayoutX(largura);
            Tela.imagemBisneto.setLayoutY(altura);
            linha = new Line(200, 125, 200, 75);
            tela.getChildren().addAll(Tela.imagemBisneto, linha);
        });

        try {
            sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch

        Platform.runLater(() -> {
            tela.getChildren().remove(Tela.imagemBisneto);
            Tela.imagemBisneto.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemBisneto.setLayoutX(largura);
            Tela.imagemBisneto.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemBisneto);
        });
    } // fim do metodo run
} // fim da classe bisneto
