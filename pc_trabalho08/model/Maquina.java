package model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Maquina extends Thread {

  private Tela tela;
  private Fabrica fabrica;
  private Robo robo;

  private Image imEsperando, imTrabalhando;
  private ImageView ivEstado;

  /* ***************************************************************
  * Metodo: Maquina
  * Funcao: construtor
  * Parametros: tela: tela, a tela da aplicacao, fabrica: Fabrica, para ter acesso a fila de robos
  * Retorno: void
  *************************************************************** */
  public Maquina(Tela tela, Fabrica fabrica) {
    this.tela = tela;
    this.fabrica = fabrica;
    imTrabalhando = new Image("/view/imagens/estados/1.png");
    imEsperando = new Image("/view/imagens/estados/2.png");

    ivEstado = new ImageView(imEsperando);
    ivEstado.setLayoutX(960);
    ivEstado.setLayoutY(280);

    Platform.runLater(() -> tela.getChildren().add(ivEstado));
  } // fim do construtor

  /* ***************************************************************
  * Metodo: run
  * Funcao: o que sera executado pela thread
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  @Override
  public void run() {
    while (true) { // executa infinitamente
      fabrica.downRobot();
      fabrica.downMutex();
      fabrica.setWaiting(fabrica.getWaiting() - 1);
      robo = fabrica.getOrdem().remove(); // remove o primeiro da fila
      fabrica.upMachine();
      fabrica.upMutex();
      montarCabeca();
    } // fim do while
  } // fim do run

  /* ***************************************************************
  * Metodo: montarCabeca
  * Funcao: organiza todo o processo da maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void montarCabeca() {
    sobreporMaquina(); // atualiza a imagem
    robo.sairDe(robo.getID()); // faz o robo sair da esteira de espera
    trabalhar(); // muda a cor da imagem
    mySleep(tela.getVelMontar()); // espera o tempo determinado
    robo.montar(); // coloca a cabeca
    robo.sairMaquina(); // robo pronto,  sair
    esperar(); // muda a cor da imagem
  } // fim do metodo trabalhar

  /* ***************************************************************
  * Metodo: trabalhar
  * Funcao: alterar o estado para trabalhar
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void trabalhar() {
    Platform.runLater(() -> ivEstado.setImage(imTrabalhando));
  } // fim do metodo esperar

  /* ***************************************************************
  * Metodo: esperar
  * Funcao: alterar o estado para esperar
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void esperar() {
    Platform.runLater(() -> ivEstado.setImage(imEsperando));
  } // fim do metodo esperar

  /* ***************************************************************
  * Metodo: sobreporMaquina
  * Funcao: faz com que ivEstado esteje sempre visivel
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void sobreporMaquina() {
    Platform.runLater(() -> {
      tela.getChildren().remove(ivEstado);
      tela.getChildren().add(ivEstado);
    });
  } // fim do metodo sobreporMaquina

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
} // fim da classe Maquina
