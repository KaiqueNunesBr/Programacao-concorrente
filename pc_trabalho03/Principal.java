import view.Tela;
import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application{
  /* ***************************************************************
  * Autor: Francisco Kaique Nunes Barbosa
  * Matricula: 201610441
  * Inicio: 26/07/2018
  * Ultima alteracao: 29072018
  * Nome: Nome do programa
  * Funcao: descricao do que eh o programa
  *************************************************************** */
  public static void main(String[] args) {
        launch(args);
    }
    
  /* ***************************************************************
  * Metodo: start
  * Funcao: inicia a tela
  * Parametros: stage
  * Retorno: void
  *************************************************************** */
  @Override
  public void start(Stage stage) {
      Tela tela = new Tela(stage);
      tela.show();
  } // fim do metodo start
} // fim da classe principal