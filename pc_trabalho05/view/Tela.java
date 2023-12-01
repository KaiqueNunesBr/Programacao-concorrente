package view;

import java.util.concurrent.Semaphore;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Buffer;
import model.Consumidor;
import model.Produtor;

public class Tela extends AnchorPane {
    private Stage stage;

    private Image logo;
    private Image arena;
    private static ImageView logoBotao;
    private static ImageView plano_De_Fundo;

    private static Button iniciar;

    public static final String SLIDER_TOOLTIP = "so um teste";

    private Semaphore mutex;
    private Semaphore vazio;
    private Semaphore cheio;

    public Tela(Stage stage) {
        
        mutex = new Semaphore(1);
        vazio = new Semaphore(3);
        cheio = new Semaphore(0);

        logo = new Image(getClass().getResourceAsStream("/view/imagens/cenario/logo.png"));
        arena = new Image(getClass().getResourceAsStream("/view/imagens/cenario/MortalKombateAcido.png"));

        plano_De_Fundo = new ImageView(arena);
        logoBotao = new ImageView(logo);

        iniciar = new Button("", logoBotao); // botao responsavel por iniciar as Threads
        iniciar.setMaxSize(5, 5); // faz com que o botao tenha o
        iniciar.setMinSize(5, 5); // tamanho e a forma da imagem
        iniciar.setCursor(Cursor.HAND); // fas o cursor mudar para a hand ao passar o mouse

        this.stage = stage;
        this.stage.setResizable(false); // bloqueia a funcao de redimensionar a pagina
        this.stage.setTitle("Produtor Consumidor");
    } //  fim do construtor

    /* ***************************************************************
    * Metodo: construirTela
    * Funcao: costroi a tela e configura o botao iniciar
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void construirTela() {
        // seta a posicao do botao iniciar
        iniciar.setLayoutX(505);
        iniciar.setLayoutY(40);

        this.getChildren().addAll(plano_De_Fundo, iniciar);

        this.stage.setScene(new Scene(this));

        iniciar.setOnAction(valor -> {
            Consumidor consumidor = new Consumidor(this); // cria a thread consumidor
            Produtor produtor = new Produtor(this); // cria a thread produtor
            Buffer buffer = new Buffer(); // cria o buffer

            consumidor.setBuffer(buffer); // passa o buffer para consumidor
            produtor.setBuffer(buffer); // passa o buffer para produtor
            consumidor.setSemaphoros(mutex, cheio, vazio); // passa os semaforos para consumidor
            produtor.setSemaphoros(mutex, cheio, vazio); // passa os semaforos para produtor

            consumidor.start(); // inicia a thread consumidor
            produtor.start(); // inicia a thread produtor
            iniciar.setDisable(true); // desabilita o botao
        }); // fim da acao do botao iniciar
    } // fim do metodo construirTela

    /* ***************************************************************
    * Metodo: show
    * Funcao: exibir esta tela
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void show() {
        this.construirTela();
        this.stage.show();
    } // fim do metod show
} // fim da classe Tela