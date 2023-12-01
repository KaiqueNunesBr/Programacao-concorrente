package model;

import view.Tela;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Robo extends Thread {

  private Tela tela;
  private Fabrica fabrica;
  private Image imRobosAntes, imRobosDepois;
  private ImageView ivRobo;

  private int id, posX, posY;

  /* ***************************************************************
  * Metodo: Robo
  * Funcao: construtor
  * Parametros: tela: tela, fabrica: Fabrica, maq: Maquina
  * Retorno: void
  *************************************************************** */
  public Robo(Tela tela, Fabrica fabrica, Maquina maq) {
    this.tela = tela;
    this.fabrica = fabrica;

    // posicao inicial de todos ps robos
    posX = 0;
    posY = 280;

    gerarId();
    setPosicao(posX, posY);

    Platform.runLater(() -> {
        tela.getChildren().add(ivRobo);
    });
  } // fim do construtor

  /* ***************************************************************
  * Metodo: gerarId
  * Funcao: gerar ids aleatoriamente para alternar os robos
  * e confirura o ivRobo com este id
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void gerarId() {
    Random temp = new Random();
    id = temp.nextInt(5); // de 0 a 4

    imRobosAntes = new Image("/view/imagens/antes/" + id + ".png");
    imRobosDepois = new Image("/view/imagens/depois/" + id + ".png");

    ivRobo = new ImageView(imRobosAntes);

    this.start();
  } // fim do metodo gerarId


  /* ***************************************************************
  * Metodo: run
  * Funcao: o que sera executado pela thread
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  @Override
  public void run() {
    fabrica.downMutex();
    if (fabrica.getWaiting() < fabrica.getChairs()) {
      fabrica.setWaiting(fabrica.getWaiting() + 1);
      fabrica.getOrdem().add(this);

      Chegar();

      fabrica.upRobot();
      fabrica.upMutex();
      fabrica.downMachine();

      //esteiraMontagem();
    } else {
      sobreporReciclar();
      fabrica.upMutex();
      comDefeito();
    } // fim do if else
  } // fim do run

  /* ***************************************************************
  * Metodo: sobreporReciclar
  * Funcao: faz com que ivEstado esteje sempre visivel
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void sobreporReciclar() {
    Platform.runLater(() -> {
      tela.getChildren().remove(tela.getIvReciclar());
      tela.getChildren().add(tela.getIvReciclar());
    });
  } // fim do metodo sobreporReciclar

  /* ***************************************************************
  * Metodo: montar
  * Funcao: altera a imagen do robo, "monta o robo" 
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void montar() {
    ivRobo.setImage(imRobosDepois);
  } // fim do metodo montar

  /* ***************************************************************
  * Metodo: Chegar
  * Funcao: encontra uma vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void Chegar() {
    for (int i = 0; i < fabrica.getChairs(); i++) {
      if (fabrica.getContemRobo(i)) { // verifica se a vaga i esta desocupada
        id = i;
        irPara(i);
        return;
      } // fim do if 
    } // fim do for
  } //  fim do metodo chegar

  /* ***************************************************************
  * Metodo: irPara
  * Funcao: defini para onde o robo vai
  * Parametros: pos:int, qual vaga deve ir
  * Retorno: void
  *************************************************************** */
  private void irPara(int pos) {
    fabrica.setContemRobo(false, pos); // tem alguem na vaga pos
    switch (pos) {
      case 0:
        chegarPrimeira();
        break;
      case 1:
        chegarSegunda();
        break;
      case 2:
        chegarTerceira();
        break;
      case 3:
        chegarQuarta();
        break;
      case 4:
        chegarQuinta();
        break;
    } // fim do switch case
  } // fim do metodo irPara

  /* ***************************************************************
  * Metodo: chegarPrimeira
  * Funcao: vai pra a vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void chegarPrimeira() {
    for (; posX < 360; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY > 0; posY -= 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posX < 605; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo chegarPrimeira

  /* ***************************************************************
  * Metodo: chegarSegunda
  * Funcao: vai pra a vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void chegarSegunda() {
    for (; posX < 360; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY > 140; posY -= 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posX < 605; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo chegarSegunda

  /* ***************************************************************
  * Metodo: chegarTerceira
  * Funcao: vai pra a vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void chegarTerceira() {
    for (; posX < 605; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo chegarTerceira

  /* ***************************************************************
  * Metodo: chegarQuarta
  * Funcao: vai pra a vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void chegarQuarta() {
    for (; posX < 360; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY < 420; posY += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posX < 605; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo chegarQuarta

  /* ***************************************************************
  * Metodo: chegarQuinta
  * Funcao: vai pra a vaga
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void chegarQuinta() {
    for (; posX < 360; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY < 560; posY += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posX < 605; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo chegarQuinta

  /* ***************************************************************
  * Metodo: sairDe
  * Funcao: de onde o robo esta ate a maquina
  * Parametros: pos: int, onde o robo esta
  * Retorno: void
  *************************************************************** */
  public void sairDe(int pos) {
    fabrica.setContemRobo(true, pos);
    switch (pos) {
      case 0:
      case 1:
        sairCima();
        break;
      case 2:
        sairMeio();
        break;
      case 3:
      case 4:
        sairBaixo();
        break;
    } // fim do switch case
  } // fim do metodo sairDe

  /* ***************************************************************
  * Metodo: sairCima
  * Funcao: vai para a maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void sairCima() {
    for (; posX < 720; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY < 280; posY += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
    sairMeio();
  } // fim do metodo sairCima

  /* ***************************************************************
  * Metodo: sairMeio
  * Funcao: vai para a maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void sairMeio() {
    for (; posX < 965; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo sairMeio

  /* ***************************************************************
  * Metodo: sairBaixo
  * Funcao: vai para a maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void sairBaixo() {
    for (; posX < 720; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY > 280; posY -= 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
    sairMeio();
  } // fim do metodo sairBaixo

  /* ***************************************************************
  * Metodo: comDefeito
  * Funcao: vai para a reciclagem
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void comDefeito() {
    for (; posX < 120; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for

    for (; posY < 425; posY += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } //  fim do metodo comDefeito

  /* ***************************************************************
  * Metodo: sairMaquina
  * Funcao: vai para a fora da tela
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void sairMaquina() {
    for (; posX < 1250; posX += 5) {
      setPosicao(posX, posY);
      mySleep(10);
    } // fim do for
  } // fim do metodo sairMaquina

  /* ***************************************************************
  * Metodo: setPosicao
  * Funcao: configurar a posicao do ImageView
  * Parametros: posX, posY: int
  * Retorno: void
  *************************************************************** */
  private void setPosicao(int posX, int posY) {
    Platform.runLater(() -> {
      ivRobo.setLayoutX(posX);
      ivRobo.setLayoutY(posY);
    });
  } // fim do metodo setPosicao

  /* ***************************************************************
  * Metodo: getID
  * Funcao: get
  * Parametros: void
  * Retorno: id: int , o id deste robo
  *************************************************************** */
  public int getID() {
    return id;
  }

  /* ***************************************************************
  * Metodo: mySleep
  * Funcao: evitar try/catch por todo codigo
  * Parametros: tempo:int
  * Retorno: void
  *************************************************************** */
  private void mySleep(int tempo) {
    try {
      sleep(tempo);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo mySleep
} // fim da classe robo
