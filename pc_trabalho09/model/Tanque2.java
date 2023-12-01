package model;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Tanque2 extends Thread {

  private Tela tela;
  private CentralDeSemaforos cs;
  private ImageView ivTank, ivTema, ivID;
  private Slider slVelocidade;
  private Button btPercurso;
  private Label lbDescricao, lbRapido, lbLento;
  private int velocidade, posX, posY;

  public Tanque2(Tela tela, CentralDeSemaforos cs) {
    this.tela = tela;
    this.cs = cs;
    posX = 500;
    posY = 300;
    velocidade = -3; // velocidade inicial

    ivTank = new ImageView(new Image("/view/imagens/tanques/2.png"));
    ivID = new ImageView(new Image("/view/imagens/tanques/2.png"));
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
    rotacionar(-90);

    ivTema.setLayoutX(550);
    ivTema.setLayoutY(106);

    ivID.setLayoutX(560);
    ivID.setLayoutY(124);

    lbDescricao.setLayoutX(620);
    lbDescricao.setLayoutY(116);

    btPercurso.setLayoutX(620);
    btPercurso.setLayoutY(136);

    slVelocidade.setLayoutX(610);
    slVelocidade.setLayoutY(166);

    lbRapido.setLayoutX(705);
    lbRapido.setLayoutY(176);

    lbLento.setLayoutX(615);
    lbLento.setLayoutY(176);

    Platform.runLater(() -> tela.getChildren().addAll(ivTema, ivID,
      slVelocidade, lbDescricao, ivTank, btPercurso, lbRapido, lbLento));

    /* acao do slider Entrar */
    slVelocidade.valueProperty().addListener((ObservableValue<? extends Number> observable,
        Number oldValue, Number newValue) -> {
      velocidade = newValue.intValue(); // velEntrar vai sempre ter o valor do slider
    }); // fim da acao do slider sl ler

    /* acao do botao percorrer */
    btPercurso.setOnAction(valor -> {
      if (tela.getPercurso(1).isVisible()) {
        tela.getPercurso(1).setVisible(false);
      } else {
        tela.getPercurso(1).setVisible(true);
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
    cs.downMutex(0); // inicia no Mutex: 0
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
    rotacionar(-90);
    moverX(false);
    cs.upMutex(0); // Mutex: 0, Tanques: 1, 2
    cs.downMutex(1); // Mutex: 1, Tanques: 1, 2
    cs.downMutex(20); // Mutex: 20, Tanques: 2, 5
    moverX(false);
    moverX(false);
    cs.downMutex(15); // Mutex: 15, Tanques: 2, 3
    cs.downMutex(18); // Mutex: 18, Tanques: 2, 4
    moverX(false);
    moverX(false);
    cs.upMutex(1); // Mutex: 1, Tanques: 1, 2
    cs.upMutex(15); // Mutex: 15, Tanques: 2, 3
    cs.upMutex(18); // Mutex: 18, Tanques: 2, 4
    cs.upMutex(20); // Mutex: 20, Tanques: 2, 5
    cs.downMutex(2); // Mutex: 2, Tanques: 1, 2
    moverX(false);
    moverX(false);
    cs.downMutex(21); // Mutex: 21, Tanques: 2, 5
    cs.downMutex(16); // Mutex: 16, Tanques: 2, 3
    moverX(false);
    moverX(false);
    cs.upMutex(2); // Mutex: 2, Tanques: 1, 2
    cs.upMutex(16); // Mutex: 16, Tanques: 2, 3
    cs.downDeadLock(0); // DeadLock: 0, Tanques: 1, 2, 3
    cs.downMutex(19); // Mutex: 19, Tanques: 2, 4
    cs.downMutex(3); // Mutex: 3, Tanques: 1, 2
    moverX(false);
  } // fim do metodo percursso01

  private void percusso02() {
    rotacionar(180);
    moverY(true);
    moverY(true);
    moverY(true);
    moverY(true);
  } // fim do metodo percursso02

  private void percusso03() {
    rotacionar(90);
    moverX(true);
    cs.downMutex(17); // Mutex: 17, Tanques: 2, 3
    moverX(true);
    moverX(true);
    cs.upDeadLock(0); // DeadLock: 0, Tanques: 1, 2, 3
    cs.upMutex(3); // Mutex: 3, Tanques: 1, 2
    moverX(true);
    moverX(true);
    cs.upMutex(21); // Mutex: 21, Tanques: 2, 5
    moverX(true);
    moverX(true);
    cs.upMutex(17); // Mutex: 17, Tanques: 2, 3
    cs.upMutex(19); // Mutex: 19, Tanques: 2, 4
    cs.downMutex(0); // Mutex: 0, Tanques: 1, 2
    moverX(true);
    moverX(true);
    moverX(true);
  } // fim do metodo percursso03

  private void percusso04() {
    rotacionar(0);
    moverY(false);
    moverY(false);
    moverY(false);
    moverY(false);
  } // fim do metodo percursso04

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
    } // fim do id else
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
  } // fim do metodo rotacionar

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
} // fim da classe Tanque2
