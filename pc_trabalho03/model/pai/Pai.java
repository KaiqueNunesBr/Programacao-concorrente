package model.pai;

import model.filhos.*;
import view.Tela;
import java.lang.Thread;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class Pai extends Thread {
    private Tela tela;
    private int largura;
    private int altura;

    public Pai(Tela tela) {
        this.tela = tela;
        this.largura = 175;
        this.altura = 325;
    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: criar o processo Pai, chama os processos Filho1, Filho2 e Filho3
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Platform.runLater(() -> {
            Tela.imagemPai.setLayoutX(largura);
            Tela.imagemPai.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemPai);
        });
        try { 
            sleep(16000);
            Platform.runLater(() -> { // fica adulto aos 16 anos
                tela.getChildren().remove(Tela.imagemPai);
                Tela.imagemPai.setImage(new Image(getClass().getResourceAsStream("/view/imagens/adulto0.png")));
                Tela.imagemPai.setLayoutX(largura);
                Tela.imagemPai.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemPai);
            });
            sleep(6000);
            Filho1 filho1 = new Filho1(tela); // primeiro filho
            filho1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch

        try { 
            sleep(3000);
            Filho2 filho2 = new Filho2(tela); // segundo filho
            filho2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch

        try { 
            sleep(7000);
            Filho3 filho3 = new Filho3(tela); // terceiro filho
            filho3.start();

            sleep(18000);
            Platform.runLater(() -> { // fica idoso aos 50 anos
                tela.getChildren().remove(Tela.imagemPai);
                Tela.imagemPai.setImage(new Image(getClass().getResourceAsStream("/view/imagens/idoso.png")));
                Tela.imagemPai.setLayoutX(largura);
                Tela.imagemPai.setLayoutY(altura);
                tela.getChildren().add(Tela.imagemPai);
            });
            sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // fim do try catch do terceiro filho

        Platform.runLater(() -> { // morre 90 anos
            tela.getChildren().remove(Tela.imagemPai);
            Tela.imagemPai.setImage(new Image(getClass().getResourceAsStream("/view/imagens/morte.png")));
            Tela.imagemPai.setLayoutX(largura);
            Tela.imagemPai.setLayoutY(altura);
            tela.getChildren().add(Tela.imagemPai);
            Tela.iniciar.setDisable(false);
        });
    } // fim do metodo run
} // fim da classe Pai
