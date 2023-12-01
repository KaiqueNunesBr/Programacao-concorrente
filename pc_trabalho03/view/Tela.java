package view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.pai.Pai;

public class Tela extends AnchorPane {

    private Stage stage;
    private int largura;
    private int altura;
    private Image bebe;
    public static ImageView imagemPai;
    public static ImageView imagemFilho1;
    public static ImageView imagemFilho2;
    public static ImageView imagemFilho3;
    public static ImageView imagemNeto1;
    public static ImageView imagemNeto2;
    public static ImageView imagemBisneto;
    public static Button iniciar   = new Button(" Iniciar ");

    public Tela(Stage stage) {
        imagemPai = null;
        imagemFilho1 = null;
        imagemFilho2 = null;
        imagemFilho3 = null;
        imagemNeto1 = null;
        imagemNeto2 = null;
        imagemBisneto = null;

        this.stage = stage;
        this.largura = 411;
        this.altura = 411;
    } //  fim do construtor
    
    /* ***************************************************************
    * Metodo: tornaBebe
    * Funcao: trasforma todas as imagens na imagem do bebe
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void tornaBebe(){
        bebe = new Image(getClass().getResourceAsStream("/view/imagens/bebe.png"));
        imagemPai = new ImageView(bebe);
        imagemFilho1 = new ImageView(bebe);
        imagemFilho2 = new ImageView(bebe);
        imagemFilho3 = new ImageView(bebe);
        imagemNeto1 = new ImageView(bebe);
        imagemNeto2 = new ImageView(bebe);
        imagemBisneto = new ImageView(bebe);
    } // fim do metodo tornaBebe

    /* ***************************************************************
    * Metodo: construirTela
    * Funcao: constroi a tela e configura o botao iniciar
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void construirTela() {
        iniciar.setLayoutX(15);
        iniciar.setLayoutY(15);
        iniciar.setLayoutX(15);
        iniciar.setLayoutY(30);
        this.getChildren().add(iniciar);

        iniciar.setOnAction(valor -> {
            this.tornaBebe();
            Pai pai = new Pai(this);
            pai.start();
            this.getChildren().clear();
            this.getChildren().add(iniciar);
            iniciar.setDisable(true);
        });

        this.setPrefSize(largura, altura);
        this.stage.setScene(new Scene(this, largura, altura));
    } // fim do metodo construirTela

  /* ***************************************************************
  * Metodo: show
  * Funcao: exibe esta tela
  * Parametros: void
  * Retorno: void
  *************************************************************** */
    public void show() {
        this.construirTela();
        this.stage.show();
    } // fim do metod show
} // fim da classe Tela
