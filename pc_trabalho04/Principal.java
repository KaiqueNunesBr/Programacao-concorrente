
import view.Tela;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Principal extends Application {

    /* ***************************************************************
  * Autor: Francisco Kaique Nunes Barbosa
  * Matricula: 201610441
  * Inicio: 07/08/2018
  * Ultima alteracao: 12/08/2018
  * Nome: Problema do Trem
  * Funcao: "Resolver" o problema da regiao critica
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
} // fim da classe principal
