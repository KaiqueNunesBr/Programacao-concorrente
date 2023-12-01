package view;

import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Bandeira;
import model.Trem1;
import model.Trem2;

public class Tela extends AnchorPane {
    private Stage stage;

    private int largura;
    private int altura;
    private int velocidade1;
    private int velocidade2;

    private Bandeira bandeiras;
    private Trem1 trem1;
    private Trem2 trem2;

    private Slider velTrem1;
    private Slider velTrem2;

    private static ImageView plano_De_Fundo;
    private static ImageView imBandeira1;
    private static ImageView imBandeira2;

    private static Button iniciar;

    public Tela(Stage stage) {

        velocidade1 = 1; // velocidade da Thread do trem 1
        velocidade2 = 1; // velocidade da Thread do trem 2

        velTrem1 = new Slider(1, 12, 1); // responsavel por alterar a velocidade da Thread do trem 1
        velTrem2 = new Slider(1, 12, 1); // responsavel por alterar a velocidade da Thread do trem 2

        bandeiras = new Bandeira(this); // responsavel por manter o controle na regiao critica

        iniciar = new Button(" Iniciar "); // responsavel por iniciar as Thread
        plano_De_Fundo = new ImageView(new Image(getClass().getResourceAsStream("/view/imagens/planos.png")));
        imBandeira1 = new ImageView(new Image(getClass().getResourceAsStream("/view/imagens/bandeiraVerde.png")));
        imBandeira2 = new ImageView(new Image(getClass().getResourceAsStream("/view/imagens/bandeiraVerde.png")));

        this.stage = stage;
        this.largura = 900;
        this.altura = 500;
    } //  fim do construtor

    /* ***************************************************************
    * Metodo: construirTela
    * Funcao: costroi a tela e configura o botao iniciar
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void construirTela() {
        iniciar.setLayoutX(425);
        iniciar.setLayoutY(300);

        plano_De_Fundo.setLayoutX(0);
        plano_De_Fundo.setLayoutY(0);

        imBandeira1.setLayoutX(350);
        imBandeira1.setLayoutY(5);

        imBandeira2.setLayoutX(550);
        imBandeira2.setLayoutY(120);
        imBandeira2.setPickOnBounds(true);

        velTrem1.setLayoutX(250);
        velTrem1.setLayoutY(300);
        velTrem1.setShowTickLabels(true);
        velTrem1.setShowTickMarks(true);
        velTrem1.setBlockIncrement(1);

        velTrem2.setLayoutX(550);
        velTrem2.setLayoutY(300);
        velTrem2.setShowTickLabels(true);
        velTrem2.setShowTickMarks(true);
        velTrem2.setBlockIncrement(1);

        // coloca "todo" na nesta tela
        this.getChildren().addAll(plano_De_Fundo, iniciar, velTrem1, velTrem2, imBandeira1, imBandeira2);

        // responsavel por alterar a velocidade do trem 1 no instante em que  o slide se mover
        velTrem1.valueProperty().addListener((ObservableValue<? extends Number> ov, Number ova, Number newValue) -> {
            try {
                trem1.setVelocidade(newValue.intValue());
            } catch (Exception e) {
                velocidade1 = newValue.intValue();
            }
        });

        // responsavel por alterar a velocidade do trem 2 no instante em que  o slide se mover
        velTrem2.valueProperty().addListener((ObservableValue<? extends Number> ov, Number ova, Number newValue) -> {
            try {
                trem2.setVelocidade(newValue.intValue());
            } catch (Exception e) {
                velocidade2 = newValue.intValue();
            }
        });
        
        // inicia as Threads
        iniciar.setOnAction(valor -> {
            trem1 = new Trem1(this, bandeiras, velocidade1);
            trem2 = new Trem2(this, bandeiras, velocidade2);
            trem1.start();
            trem2.start();
        });

        this.setPrefSize(largura, altura);
        this.stage.setScene(new Scene(this, largura, altura));
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

    /* ***************************************************************
    * Metodo: setImBandeira1
    * Funcao: alterar a imagem da bandeira
    * Parametros: Image,  a nova cor da bandeira 
    * Retorno: void
    *************************************************************** */
    public void setImBandeira1(Image corBandeira) {
        Tela.imBandeira1.setImage(corBandeira);
    }

    /* ***************************************************************
    * Metodo: setImBandeira2
    * Funcao: alterar a imagem da bandeira
    * Parametros: Image,  a nova cor da bandeira 
    * Retorno: void
    *************************************************************** */
    public void setImBandeira2(Image corBandeira) {
        Tela.imBandeira2.setImage(corBandeira);
    } // fim do metodo setImBandeira
} // fim da classe Tela
