package view;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Leitor extends AnchorPane {
  private Tela tela;

  private Image imLeitor;
  private Image[] imEstados;

  private ImageView ivFoto, ivEstado, ivBd;

  private String style;
  private Slider slLer;
  private Slider slEsperar;

  private Label lbVelLendo;
  private Label lbVelEsperando;

  private Acoes acoes;

  private int velEsperar, velLer;

  /* ***************************************************************
  * Metodo: Leitor
  * Funcao: Construtor
  * Parametros: id: int, usado para identificar a foto,
  * tela: Tela, para ter acesso aos semaforos
  * Retorno: void
  *************************************************************** */
  public Leitor(int id, Tela tela) {
      this.tela = tela;
      this.imLeitor = new Image("/view/imagens/leitores/" + id + ".png");
      this.imEstados = new Image[2]; // total de estados
      imEstados[0] = new Image("/view/imagens/acoes/ler.gif");
      imEstados[1] = new Image("/view/imagens/acoes/esperar.gif");

      ivFoto = new ImageView(imLeitor);
      ivEstado = new ImageView(imEstados[1]);
      ivBd = new ImageView(new Image("/view/imagens/ler.gif"));

      style = "-fx-background-color: rgba(243, 178, 102)";

      slLer = new Slider(500, 5000, 500);
      slEsperar = new Slider(500, 5000, 500);

      lbVelLendo = new Label("Velocidade de leitura");
      lbVelEsperando = new Label("Velocidade de espera");

      velLer = 500;
      velEsperar = 500;

      construir();
      acoes = new Acoes();
  }

  /* ***************************************************************
  * Metodo: construir
  * Funcao: configura os elementos da tela
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void construir() {
      this.setPrefSize(300, 100); // tamaho do bloco do leiroe
      this.setStyle(style); // cor do background

      /* Configuracoes do ImageView fotos */
      setLeftAnchor(ivFoto, 5.0);
      setTopAnchor(ivFoto, 2.5);
      setBottomAnchor(ivFoto, 2.5);

      /* Configuracoes do ImageView estado */
      setRightAnchor(ivEstado, 5.0);
      setTopAnchor(ivEstado, 18.0);
      setBottomAnchor(ivEstado, 18.0);

      /* Configuracoes do Label velocidade lendo */
      lbVelLendo.setLayoutY(10);
      setLeftAnchor(lbVelLendo, 100.0);
      setRightAnchor(lbVelLendo, 75.0);

      /* Configuracoes do slider ler */
      slLer.setLayoutY(25);
      setLeftAnchor(slLer, 100.0);
      setRightAnchor(slLer, 75.0);
      slLer.setBlockIncrement(5);
      slLer.setMinorTickCount(5);

      /* Configuracoes do Label velocidade esperando */
      lbVelEsperando.setLayoutY(50);
      setLeftAnchor(lbVelEsperando, 100.0);
      setRightAnchor(lbVelEsperando, 75.0);

      /* Configuracoes do slider esperar */
      slEsperar.setLayoutY(75);
      setLeftAnchor(slEsperar, 100.0);
      setRightAnchor(slEsperar, 75.0);
      slEsperar.setBlockIncrement(5);
      slEsperar.setMinorTickCount(5);

      this.getChildren().addAll(ivFoto, ivEstado, lbVelLendo, slLer, lbVelEsperando, slEsperar);

      /* acao do slider ler */
      slLer.valueProperty().addListener((ObservableValue<? extends Number> observable,
              Number oldValue, Number newValue) -> {
          velLer = newValue.intValue();
      }); // fim da acao do slider sl ler

      /* acao do slider esperar */
      slEsperar.valueProperty().addListener((ObservableValue<? extends Number> observable,
              Number oldValue, Number newValue) -> {
          velEsperar = newValue.intValue();
      }); // fim da acao do slider sl Esperar
  } // fim do metodo construir

  /* ***************************************************************
  * Metodo: getAcoes
  * Funcao: retorna a thread responsavel por este escritor
  * Parametros: void
  * Retorno: Thread
  *************************************************************** */
  public Thread getAcoes() {
      return acoes;
  } // fim do metodo getAcoes

  /* ***************************************************************
  * Classe: Acoes
  * Funcao: O que todos escritores farao
  *************************************************************** */
  private class Acoes extends Thread {

    /* ***************************************************************
    * Metodo: run
    * Funcao: seta oque escritor ira fazer
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
      while (true) {
        tela.downMutex(); // obtem acesso exclusivo a rc
        tela.setRc(tela.getRc() + 1); // um leitor a mais agora
        if (tela.getRc() == 1) { // se este for o primeiro leitor
          tela.downDb(); // bloqueia acessa dos escritores
        } // fim do if
        tela.upMutex(); // libera o acesso exclusivo a rc
        fazerLer(); // acesso aos dados
        tela.downMutex(); // obtem acesso exclusivo a ‘rc’
        tela.setRc(tela.getRc() - 1); // um leitor a menos agora
        if (tela.getRc() == 0) { // se este for o ultimo leitor
          tela.upDb(); // libera acessa dos escritores
        } // fim do if
        tela.upMutex(); // libera o acesso exclusivo a rc
        fazerEsperar(); // regiao nao critica
      } // fim do while
    } // fim do metodo run

    /* ***************************************************************
    * Metodo: fazerEscrever
    * Funcao: seta o escritor para Escrever
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void fazerLer() {
      Platform.runLater(() -> {
       ivEstado.setImage(imEstados[0]);
       tela.getApBancoDados().getChildren().add(ivBd);
      });
      sleepT(velLer);
      Platform.runLater(() -> tela.getApBancoDados().getChildren().remove(ivBd));
    } // fim do metodo fazerLer

    /* ***************************************************************
    * Metodo: fazerEsperar
    * Funcao: seta o escritor para esperar
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void fazerEsperar() {
      Platform.runLater(() -> ivEstado.setImage(imEstados[1]));
      sleepT(velEsperar);
    } // fim do metodo fazerEsperar

    /* ***************************************************************
    * Metodo: sleepT
    * Funcao: compactat o codigo
    * Parametros: temp: int, tempo a esperar
    * Retorno: void
    *************************************************************** */
    private void sleepT(int tempo) {
      try {
        sleep(tempo);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      } // fim do try catchs
    } // fim do metodo sleepT
  } // fim da classe acoes
} // fim da classe leitor
