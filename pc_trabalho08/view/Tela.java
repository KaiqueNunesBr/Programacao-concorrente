package view;

import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model.Fabrica;

public class Tela extends AnchorPane {

  private Stage stage;
  private ImageView ivReciclar;
  private ImageView ivEsteira;
  private Button btIniciar;
  private int velMontar, velEntrar;
  private Slider slMontar, slEntrar;
  private Label lbMontar, lbEntrar;
  private Fabrica fabrica;

  /* ***************************************************************
  * Metodo: Tela
  * Funcao: construtor
  * Parametros: stage: Stage
  * Retorno: void
  *************************************************************** */
  public Tela(Stage stage) {
    this.stage = stage;

    ivEsteira = new ImageView(new Image("/view/imagens/fundo.png"));
    ivReciclar = new ImageView(new Image("/view/imagens/reciclar.png"));
    btIniciar = new Button("   Iniciar Producao   ");

    velMontar = 500;
    velEntrar = 500;

    slMontar = new Slider(500, 5000, velMontar);
    slEntrar = new Slider(500, 5000, velEntrar);

    lbMontar = new Label("Velocidade de Montagem");
    lbEntrar = new Label("Velocidade de Entrada");

    fabrica = new Fabrica(this);

  } // fim do construtot

  /* ***************************************************************
	* Metodo: construirTela
  * Funcao: setar a posicao e funcao de cada componente 
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void construirTela() {

    /* definindo a posicao de cada componente */
    ivEsteira.setLayoutX(0);
    ivEsteira.setLayoutY(0);

    ivReciclar.setLayoutX(120);
    ivReciclar.setLayoutY(420);

    btIniciar.setLayoutX(120);
    btIniciar.setLayoutY(200);

    slEntrar.setLayoutX(120);
    slEntrar.setLayoutY(150);

    lbEntrar.setLayoutX(120);
    lbEntrar.setLayoutY(130);

    slMontar.setLayoutX(120);
    slMontar.setLayoutY(100);

    lbMontar.setLayoutX(120);
    lbMontar.setLayoutY(80);

    // adicionando-os na tela
    this.getChildren().addAll(ivEsteira, ivReciclar, btIniciar, slEntrar, lbEntrar, slMontar, lbMontar);
    this.stage.setScene(new Scene(this));

    /* acao do botao iniciar */
    btIniciar.setOnAction(valor -> {
        fabrica.start();
    });

    /* acao do slider Montar */
    slMontar.valueProperty().addListener((ObservableValue<? extends Number> observable,
        Number oldValue, Number newValue) -> {
      velMontar = newValue.intValue(); // velMontar vai sempre ter o valor do slider
    }); // fim da acao do slider sl ler

    /* acao do slider Entrar */
    slEntrar.valueProperty().addListener((ObservableValue<? extends Number> observable,
        Number oldValue, Number newValue) -> {
      velEntrar = newValue.intValue(); // velEntrar vai sempre ter o valor do slider
    }); // fim da acao do slider sl ler
  } // fim do metodo construirTela

  /* ***************************************************************
  * Metodo: getVelMontar
  * Funcao: get
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public int getVelMontar() {
    return velMontar;
  } // fim do metodo getVelMontar

  /* ***************************************************************
  * Metodo: getVelEntrar
  * Funcao: get
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public int getVelEntrar() {
    return velEntrar;
  } // fim do metodo getVelEntrar

  /* ***************************************************************
  * Metodo: getIvReciclar
  * Funcao: get
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public ImageView getIvReciclar() {
    return ivReciclar;
  } // fim do metodo getIvReciclar

  /* ***************************************************************
  * Metodo: show
  * Funcao: inicia a tela
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void show() {
    this.construirTela();
    this.stage.setTitle("Fabrica de Robos"); // titulo da aplicacao
    this.stage.show();
  }// fim do metodo show
} // fim da classe Tela
