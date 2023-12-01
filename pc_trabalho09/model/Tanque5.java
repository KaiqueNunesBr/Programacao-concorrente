package model;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Tanque5 extends Thread {

  private Tela tela;
  private CentralDeSemaforos cs;
  private ImageView ivTank, ivTema, ivID;
  private Slider slVelocidade;
  private Button btPercurso;
  private Label lbDescricao, lbRapido, lbLento;
  private int velocidade, posX, posY;

  public Tanque5(Tela tela, CentralDeSemaforos cs) {
    this.tela = tela;
    this.cs = cs;
    posX = 400;
    posY = 200;
    velocidade = -3; // velocidade inicial

    ivTank = new ImageView(new Image("/view/imagens/tanques/5.png"));
    ivID = new ImageView(new Image("/view/imagens/tanques/5.png"));
    ivTema = new ImageView(new Image("/view/imagens/controle.png"));

    slVelocidade = new Slider(-5, -1, velocidade);
    btPercurso = new Button("Exibir o Percurso");

    lbDescricao = new Label("Velocidade do Tanque");
    lbRapido = new Label("Rapido");
    lbLento = new Label("Lento");

    construir();
  } // fim do construtor

  /* ***************************************************************
  * Metodo: construir
  * Funcao: configura as posicoes na tela
  * Parametros: tempo:int
  * Retorno: void
  *************************************************************** */
  private void construir() {
    ivTank.setLayoutX(posX);
    ivTank.setLayoutY(posY);
    rotacionar(90);

    ivTema.setLayoutX(550);
    ivTema.setLayoutY(424);

    ivID.setLayoutX(560);
    ivID.setLayoutY(442);

    lbDescricao.setLayoutX(620);
    lbDescricao.setLayoutY(434);

    btPercurso.setLayoutX(620);
    btPercurso.setLayoutY(454);

    slVelocidade.setLayoutX(610);
    slVelocidade.setLayoutY(484);

    lbRapido.setLayoutX(705);
    lbRapido.setLayoutY(494);

    lbLento.setLayoutX(615);
    lbLento.setLayoutY(494);

    Platform.runLater(() -> tela.getChildren().addAll(ivTema, ivID,
      slVelocidade, lbDescricao, ivTank, btPercurso, lbRapido, lbLento));

    /* acao do slider Entrar */
    slVelocidade.valueProperty().addListener((ObservableValue<? extends Number> observable,
            Number oldValue, Number newValue) -> {
        velocidade = newValue.intValue(); // velEntrar vai sempre ter o valor do slider
    }); // fim da acao do slider sl ler

    /* acao do botao percorrer */
    btPercurso.setOnAction(valor -> {
      if (tela.getPercurso(4).isVisible()) {
        tela.getPercurso(4).setVisible(false);
      } else {
        tela.getPercurso(4).setVisible(true);
      } // fim do id else
    });
  } // fim do metodo construir

  /* ***************************************************************
  * Metodo: run
  * Funcao: o que sera executado
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  @Override
  public void run() {
    while (true) {
      percusso01();
      percusso02();
      percusso03();
      percusso04();
      percusso05();
      percusso06();
      percusso07();
      percusso08();
      percusso09();
      percusso10();
      percusso11();
      percusso12();
      percusso13();
      percusso14();
      percusso15();
      percusso16();
    }
  }

  /* ***************************************************************
  * Metodos: percusso N
  * Funcao: percorre uma estrada
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void percusso01() {
    rotacionar(90);
    moverX(true);
    cs.downMutex(10); // Mutex: 10, Tanques: 1, 5
    moverX(true);
  } // fim do metodo percusso01

  private void percusso02() {
    rotacionar(0);
    moverY(false);
    moverY(false);
    moverY(false);
    moverY(false);
  } // fim do metodo percusso02

  private void percusso03() {
    rotacionar(-90);
    moverX(false);
    moverX(false);
    moverX(false);
    cs.downMutex(24); // Mutex: 24, Tanques: 3, 5
    moverX(false);
  } // fim do metodo percusso03

  private void percusso04() {
    rotacionar(180);
    moverY(true);
    moverY(true);
  } // fim do metodo percusso04

  private void percusso05() {
    rotacionar(-90);
    moverX(false);
    cs.upMutex(24); // Mutex: 24, Tanques: 3, 5
    moverX(false);
  } // fim do metodo percusso05

  private void percusso06() {
    rotacionar(180);
    moverY(true);
    cs.upMutex(10); // Mutex: 10, Tanques: 1, 5
    cs.downMutex(28); // Mutex: 28, Tanques: 4, 5
    cs.downMutex(12); // Mutex: 12, Tanques: 1, 5
    moverY(true);
  } // fim do metodo percusso06

  private void percusso07() {
    rotacionar(-90);
    moverX(false);
    cs.upMutex(12); // Mutex: 12, Tanques: 1, 5
    cs.downMutex(27); // Mutex: 27, Tanques: 3, 5
    moverX(false);
  } // fim do metodo percusso07

  private void percusso08() {
    rotacionar(180);
    moverY(true);
    cs.upMutex(28); // Mutex: 28, Tanques: 4, 5
    cs.downMutex(21); // Mutex: 21, Tanques: 2, 5
    cs.downMutex(13); // Mutex: 13, Tanques: 1, 5
    moverY(true);
  } // fim do metodo percusso08

  private void percusso09() {
    rotacionar(-90);
    moverX(false);
    cs.upMutex(13); // Mutex: 13, Tanques: 1, 5
    cs.upMutex(27); // Mutex: 27, Tanques: 3, 5
    cs.downDeadLock(1); // DeadLock: 1, Tanques: 1, 3, 5
    cs.downMutex(30); // Mutex: 30, Tanques: 4, 5
    cs.downMutex(14); // Mutex: 14, Tanques: 1, 5
    moverX(false);
  } // fim do metodo percusso09

  private void percusso10() {
    rotacionar(180);
    moverY(true);
    moverY(true);
    moverY(true);
    moverY(true);
  } // fim do metodo percusso10

  private void percusso11() {
    rotacionar(90);
    moverX(true);
    cs.downMutex(26); // Mutex: 26, Tanques: 3, 5
    moverX(true);
    moverX(true);
    cs.upDeadLock(1); // DeadLock: 1, Tanques: 1, 3, 5
    cs.upMutex(14); // Mutex: 14, Tanques: 1, 5
    moverX(true);
  } // fim do metodo percusso11

  private void percusso12() {
    rotacionar(0);
    moverY(false);
    cs.upMutex(21); // Mutex: 21, Tanques: 2, 5
    cs.upMutex(26); // Mutex: 26, Tanques: 3, 5
    cs.upMutex(30); // Mutex: 30, Tanques: 4, 5
    moverY(false);
  } // fim do metodo percusso12

  private void percusso13() {
    rotacionar(90);
    moverX(true);
    cs.downDeadLock(2); // DeadLock: 2, Tanques: 1, 3, 5
    cs.downDeadLock(3); // DeadLock: 3, Tanques: 1, 4, 5
    cs.downMutex(25); // Mutex: 25, Tanques: 3, 5
    cs.downMutex(29); // Mutex: 29, Tanques: 4, 5
    moverX(true);
  } // fim do metodo percusso13

  private void percusso14() {
    rotacionar(0);
    moverY(false);
    cs.downMutex(11); // Mutex: 11, Tanques: 1, 5
    cs.downMutex(20); // Mutex: 20, Tanques: 2, 5
    moverY(false);
  } // fim do metodo percusso14

  private void percusso15() {
    rotacionar(90);
    moverX(true);
    cs.upMutex(25); // Mutex: 25, Tanques: 3, 5
    cs.upMutex(29); // Mutex: 29, Tanques: 4, 5
    cs.upDeadLock(2); // DeadLock: 2, Tanques: 1, 3, 5
    cs.upDeadLock(3); // DeadLock: 3, Tanques: 1, 4, 5
    moverX(true);
  } // fim do metodo percusso15

  private void percusso16() {
    rotacionar(0);
    moverY(false);
    cs.upMutex(11); // Mutex: 11, Tanques: 1, 5
    cs.upMutex(20); // Mutex: 20, Tanques: 2, 5
    moverY(false);
  } // fim do metodo percusso16

  /* ***************************************************************
  * Metodo: moverX
  * Funcao: move no eixo X
  * Parametros: sentido: boolean, true positivo, false negativo
  * Retorno: void
  *************************************************************** */
  private void moverX(boolean sentido) {
    int ate;

    if (sentido) {
      ate = posX + 50;
      for (; posX < ate; posX += 1) {
        setPosicao(posX, posY);
        mySleep();
      } // fim do for
    } else {
      ate = posX - 50;
      for (; posX > ate; posX -= 1) {
        setPosicao(posX, posY);
        mySleep();
      } // fim do for
    } // fim do if else
  } // fim do metodo moverX

  /* ***************************************************************
  * Metodo: moverY
  * Funcao: move no eixo Y
  * Parametros: sentido: boolean, true positivo, false negativo
  * Retorno: void
  *************************************************************** */
  private void moverY(boolean sentido) {
    int ate;
    if (sentido) {
      ate = posY + 50;
      for (; posY < ate; posY += 1) {
        setPosicao(posX, posY);
        mySleep();
      } // fim do for
    } else {
      ate = posY - 50;
      for (; posY > ate; posY -= 1) {
        setPosicao(posX, posY);
        mySleep();
      } // fim do for
    } // fim do if else
  } // fim do metodo moverY

  /* ***************************************************************
  * Metodo: setPosicao
  * Funcao: configurar a posicao do ImageView
  * Parametros: posX, posY: int
  * Retorno: void
  *************************************************************** */
  private void setPosicao(int posX, int posY) {
    Platform.runLater(() -> {
      ivTank.setLayoutX(posX);
      ivTank.setLayoutY(posY);
    });
  } // fim do metodo setPosicao

  /* ***************************************************************
* Metodo: rotacionar
* Funcao: rotaciona a imagem
* Parametros: angulo:int
* Retorno: void
*************************************************************** */
  private void rotacionar(int angulo) {
    Platform.runLater(() -> {
      ivTank.setRotate(angulo);
    });
  }

/* ***************************************************************
* Metodo: mySleep
* Funcao: evitar try/catch por todo codigo
* Parametros: tempo:int
* Retorno: void
*************************************************************** */
  private void mySleep() {
    try {
      sleep(-velocidade);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo mySleep
}
