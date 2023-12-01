package view;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Escritor extends AnchorPane {
  private Tela tela;

  private Image imEscritor;
  private Image[] imEstados;

  private ImageView ivFoto, ivEstado, ivBd;

  private String style;

  private Slider slEscrever;
  private Slider slEsperar;

  private Label lbVelEscrevendo;
  private Label lbVelEsperando;

  private Acoes acoes;

  private int velEsperar, velEscrever;

  /* ***************************************************************
  * Metodo: Escritor
  * Funcao: Construtor
  * Parametros: id: int, usado para identificar a foto,
  * tela: Tela, para ter acesso aos semaforos
  * Retorno: void
  *************************************************************** */
  public Escritor(int id, Tela tela) {
    this.tela = tela;
    imEscritor = new Image("/view/imagens/escritores/" + id + ".png");
    imEstados = new Image[2]; // total de estados
    imEstados[0] = new Image("/view/imagens/acoes/escrever.gif"); // escrever
    imEstados[1] = new Image("/view/imagens/acoes/esperar.gif"); // esperar

    ivFoto = new ImageView(imEscritor);
    ivEstado = new ImageView(imEstados[1]); // estado inicial: esperar

    ivBd = new ImageView(new Image("/view/imagens/escrever.gif")); // usada quando tiver acessando o bd
    
    style = "-fx-background-color: rgba(243, 178, 102)";
    slEscrever = new Slider(500, 5000, 500);
    slEsperar = new Slider(500, 5000, 500);
    
    lbVelEscrevendo = new Label("Velocidade de escrita");
    lbVelEsperando = new Label("Velocidade de espera");
    
    velEscrever = 500;
    velEsperar = 500;

    construir();

    acoes = new Acoes(); // cria uma nova thread
  }

  /* ***************************************************************
  * Metodo: construir
  * Funcao: configura os elementos da tela
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void construir() {
    this.setPrefSize(300, 100); // tamaho do bloco do escritor
    this.setStyle(style); // cor do background

    /* Configuracoes do ImageView fotos */
    setLeftAnchor(ivFoto, 5.0);
    setTopAnchor(ivFoto, 2.5);
    setBottomAnchor(ivFoto, 2.5);
    /* Configuracoes do ImageView estado */
    setRightAnchor(ivEstado, 5.0);
    setTopAnchor(ivEstado, 18.0);
    setBottomAnchor(ivEstado, 18.0);
    /* Configuracoes do Label velocidade escrevendo */
    lbVelEscrevendo.setLayoutY(10);
    setLeftAnchor(lbVelEscrevendo, 100.0);
    setRightAnchor(lbVelEscrevendo, 75.0);
    /* Configuracoes do slider escrever */
    slEscrever.setLayoutY(25);
    setLeftAnchor(slEscrever, 100.0);
    setRightAnchor(slEscrever, 75.0);
    slEscrever.setBlockIncrement(5);
    slEscrever.setMinorTickCount(5);
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
    this.getChildren().addAll(ivFoto, ivEstado, lbVelEscrevendo, slEscrever, lbVelEsperando, slEsperar);
    /* acao do slider escrever */
    slEscrever.valueProperty().addListener((ObservableValue<? extends Number> observable,
            Number oldValue, Number newValue) -> {
        velEscrever = newValue.intValue();
    }); // fim da acao do slider slEscrever
    /* acao do slider esperar */
    slEsperar.valueProperty().addListener((ObservableValue<? extends Number> observable,
            Number oldValue, Number newValue) -> {
        velEsperar = newValue.intValue();
    }); // fim da acao do slider SlEsperar
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
        fazerEsperar(); // regiao nao critica
        tela.downDb(); // obtem acesso exclusivo
        fazerEscrever(); // atualiza oss dados,  regiao critica
        tela.upDb(); //libera acesso exclusivo
      } // fim do while
    } // fim do metodo rin

    /* ***************************************************************
    * Metodo: fazerEscrever
    * Funcao: seta o escritor para Escrever
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void fazerEscrever() {
      Platform.runLater(() -> {
        ivEstado.setImage(imEstados[0]);
        tela.getApBancoDados().getChildren().add(ivBd);
      });
      sleepT(velEscrever);
      Platform.runLater(() -> tela.getApBancoDados().getChildren().remove(ivBd));
    } // fim do metodo fazerEscrever

    /* ***************************************************************
    * Metodo: fazerEsperar
    * Funcao: seta o escritor para esperar
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void fazerEsperar() {
      Platform.runLater(() -> {
        ivEstado.setImage(imEstados[1]);
      });
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
  } // fim da classe Acoes
} // fim da classe Escritor