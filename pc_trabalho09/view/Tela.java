package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model.*;

public class Tela extends AnchorPane {

  private Stage stage;
  private ImageView ivArena, ivPercurso[];
  private Button btIniciar;
  private CentralDeSemaforos cs;

  /* ***************************************************************
  * Metodo: Tela
  * Funcao: construtor
  * Parametros: stage: Stage
  * Retorno: void
  *************************************************************** */
  public Tela(Stage stage) {
    this.stage = stage;
    btIniciar = new Button("Iniciar");

    cs = new CentralDeSemaforos(this);

    ivArena = new ImageView(new Image("/view/imagens/arena.png"));
    ivPercurso = new ImageView[5];
  } // fim do construtot

  /* ***************************************************************
* Metodo: construirTela
* Funcao: setar a posicao e funcao de cada componente 
* Parametros: void
* Retorno: void
*************************************************************** */
  private void construirTela() {
    btIniciar.setLayoutX(640);
    btIniciar.setLayoutY(520);

    for (int i = 0; i < ivPercurso.length; i++) {
      ivPercurso[i] = new ImageView(new Image("/view/imagens/percurso/" + (i+1) + ".png"));
      ivPercurso[i].setVisible(false);
    } // fim do for i
    
    ivPercurso[1].setLayoutY(300);
    ivPercurso[2].setLayoutX(100);
    ivPercurso[3].setLayoutY(200);

    // adicionando-os na tela
    this.getChildren().addAll(ivPercurso);
    this.getChildren().addAll(ivArena, btIniciar);
    this.stage.setScene(new Scene(this));
    ivArena.toBack();

    /* acao do botao iniciar */
    btIniciar.setOnAction(valor -> {
      ivArena.toBack();
      cs.iniciar();
      btIniciar.setDisable(true);
    });
  } // fim do metodo construirTela

  public void show() {
    this.construirTela();
    
    // bloqueia o tamanho da tela
    this.stage.setMaxHeight(590);
    this.stage.setMinHeight(590);
    this.stage.setMaxWidth(770);
    this.stage.setMinWidth(770);
    
    this.stage.setTitle("Tank 1990"); // titulo da aplicacao
    this.stage.getIcons().add(new Image("/view/imagens/icon.png")); // imagem da aplicacao
    this.stage.show();
  }// fim do metodo show

  /* ***************************************************************
  * Metodo: getPercurso
  * Funcao: fet
  * Parametros: id: int, qual percurso
  * Retorno: percurso
  *************************************************************** */
  public ImageView getPercurso(int id) {
    return ivPercurso[id];
  } // fim do metodo getPercurso
} // fim da classe Tela
