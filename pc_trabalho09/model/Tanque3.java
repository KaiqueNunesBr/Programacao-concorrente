package model;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Tanque3 extends Thread {

  private Tela tela;
  private CentralDeSemaforos cs;
  private ImageView ivTank, ivTema, ivID;
  private Slider slVelocidade;
  private Button btPercurso;
  private Label lbDescricao, lbRapido, lbLento;
  private int velocidade, posX, posY;

  public Tanque3(Tela tela, CentralDeSemaforos cs) {
    this.tela = tela;
    this.cs = cs;
    posX = 100;
    posY = 0;
    velocidade = -3; // velocidade inicial
    
    ivTank = new ImageView(new Image("/view/imagens/tanques/3.png"));
    ivID = new ImageView(new Image("/view/imagens/tanques/3.png"));
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
    ivTank.toFront();
    rotacionar(90);

    ivTema.setLayoutX(550);
    ivTema.setLayoutY(212);

    ivID.setLayoutX(560);
    ivID.setLayoutY(230);

    lbDescricao.setLayoutX(620);
    lbDescricao.setLayoutY(222);

    btPercurso.setLayoutX(620);
    btPercurso.setLayoutY(242);

    slVelocidade.setLayoutX(610);
    slVelocidade.setLayoutY(272);

    lbRapido.setLayoutX(705);
    lbRapido.setLayoutY(282);

    lbLento.setLayoutX(615);
    lbLento.setLayoutY(282);

    Platform.runLater(() -> tela.getChildren().addAll(ivTema, ivID,
      slVelocidade, lbDescricao, ivTank, btPercurso, lbRapido, lbLento));

    /* acao do slider Entrar */
    slVelocidade.valueProperty().addListener((ObservableValue<? extends Number> observable,
          Number oldValue, Number newValue) -> {
      velocidade = newValue.intValue(); // velEntrar vai sempre ter o valor do slider
    }); // fim da acao do slider sl ler

    /* acao do botao percorrer */
    btPercurso.setOnAction(valor -> {
      if (tela.getPercurso(2).isVisible()) {
        tela.getPercurso(2).setVisible(false);
      } else {
        tela.getPercurso(2).setVisible(true);
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
    cs.downMutex(4); // inicia no Mutex: 4
    while (true) {
      percusso01();
      percusso02();
      percusso03();
      percusso04();
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
    moverX(true);
    moverX(true);
    cs.upMutex(4); // Mutex: 4, Tanques: 1, 3
    cs.downMutex(5); // Mutex: 5, Tanques: 1, 3
    cs.downMutex(24); // Mutex: 24, Tanques: 3, 5
    moverX(true);
  } // fim do metodo percusso01

  private void percusso02() {
    rotacionar(180);
    moverY(true);
    moverY(true);
    moverY(true);
    cs.upMutex(5); // Mutex: 5, Tanques: 1, 3
    cs.upMutex(24); // Mutex: 24, Tanques: 3, 5
    cs.downDeadLock(2); // DeadLock: 2, Tanques: 1, 3, 5
    cs.downMutex(22); // Mutex: 22, Tanques: 3, 4
    cs.downMutex(6); // Mutex: 6, Tanques: 1, 3
    moverY(true);
    moverY(true);
    cs.downMutex(25); // Mutex: 25, Tanques: 3, 5
    cs.downMutex(15); // Mutex: 15, Tanques: 2, 3
    moverY(true);
    moverY(true);
    cs.upMutex(6); // Mutex: 6, Tanques: 1, 3
    cs.upMutex(15); // Mutex: 15, Tanques: 2, 3
    cs.upDeadLock(2); // DeadLock: 2, Tanques: 1, 3, 5
    moverY(true);
    moverY(true);
    cs.upMutex(25); // Mutex: 25, Tanques: 3, 5
    cs.downDeadLock(0); // DeadLock: 0, Tanques: 1, 2, 3
    cs.downMutex(17); // Mutex: 17, Tanques: 2, 3
    moverY(true);
  } // fim do metodo percusso02

  private void percusso03() {
    rotacionar(-90);
    moverX(false);
    cs.downDeadLock(1); // DeadLock: 1, Tanques: 1, 3, 5
    cs.downMutex(26); // Mutex: 26, Tanques: 3, 5
    moverX(false);
    moverX(false);
    cs.downMutex(7); // Mutex: 7, Tanques: 1, 3
    moverX(false);
  } // fim do metodo percusso03

  private void percusso04() {
    rotacionar(0);
    moverY(false);
    cs.upMutex(17); // Mutex: 17, Tanques: 2, 3
    cs.upMutex(22); // Mutex: 22, Tanques: 3, 4
    cs.upMutex(26); // Mutex: 26, Tanques: 3, 5
    cs.upDeadLock(0); // DeadLock: 0, Tanques: 1, 2, 3
    cs.upDeadLock(1); // DeadLock: 1, Tanques: 1, 3, 5
    moverY(false);
    moverY(false);
    cs.downMutex(27); // Mutex: 27, Tanques: 3, 5
    cs.downMutex(16); // Mutex: 16, Tanques: 2, 3
    moverY(false);
    moverY(false);
    cs.upMutex(16); // Mutex: 16, Tanques: 2, 3
    cs.upMutex(7); // Mutex: 7, Tanques: 1, 3
    cs.downMutex(23); // Mutex: 23, Tanques: 3, 4
    moverY(false);
    moverY(false);
    cs.upMutex(27); // Mutex: 27, Tanques: 3, 5
    cs.upMutex(23); // Mutex: 23, Tanques: 3, 4
    moverY(false);
    moverY(false);
    cs.downMutex(4); // Mutex: 4, Tanques: 1, 3
    moverY(false);
  } // fim do metodo percusso03

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