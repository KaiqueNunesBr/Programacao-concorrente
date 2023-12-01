
import view.Tela;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Principal extends Application {

  /* ***************************************************************
  * Autor: Francisco Kaique Nunes Barbosa
  * Matricula: 201610441
  * Inicio: 13/09/2018
  * Ultima alteracao: 23/09/2018
  * Nome: Leitores e Escritores
  * Funcao: Simula o problema Leitores e Escritores
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
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent e) {
        e.consume();;
        stage.close();
        System.exit(0);
      }
    });
  } // fim do metodo start
} // fim da classe Principal
