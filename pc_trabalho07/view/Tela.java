package view;

import java.util.concurrent.Semaphore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tela extends AnchorPane {
  private Stage stage;

  private VBox vbLeitores, vbEscritores;
  private GridPane gbBotoes;
  private AnchorPane apBancoDados;

  private Button btIniciar, btNovoLeitor, btNovoEscritor;

  private ImageView ivBack;

  private Leitor[] leiror;
  private Escritor[] escritor;
  private String style;

  private int contLeitores, contEscritores, rc;

  private Semaphore mutex, db;

  public Tela(Stage stage) {
    this.stage = stage;

    vbLeitores = new VBox();
    vbEscritores = new VBox();
    gbBotoes = new GridPane();
    apBancoDados = new AnchorPane();

    ivBack = new ImageView(new Image("/view/imagens/back.png"));
    style = "-fx-background-color: rgba(243, 178, 102 , .5)";

    btIniciar = new Button("Iniciar ");
    btNovoLeitor = new Button(" Leitor ");
    btNovoEscritor = new Button("Escritor");

    // 6 eh o numero maximo de leirores/ escritorea
    leiror = new Leitor[6];
    escritor = new Escritor[6];

    contLeitores = 0;
    contEscritores = 0;
    rc = 0;

    mutex = new Semaphore(1);
    db = new Semaphore(1);
    } // fim do construtot

  /* ***************************************************************
  * Metodo: construirTela
  * Funcao: setar a posicao e funcao de cada componente 
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void construirTela() {
    this.setPrefSize(1000, 660); // tamanho da aplicacao

    /* Configuracoes do vBox leitores */
    // onde vai ficar todos os leitores.
    vbLeitores.setPrefSize(330, 500);
    vbLeitores.setStyle(style); // seta o style com os determinados em style
    // faz com que todos os componentes adicionados fique centralizados
    vbLeitores.setAlignment(Pos.CENTER);
    vbLeitores.setSpacing(5); // o espacamento da borda e dos componentes
    // ancorando na tela
    setLeftAnchor(vbLeitores, 10.0);
    setTopAnchor(vbLeitores, 10.0);
    setBottomAnchor(vbLeitores, 10.0);

    /* Configuracoes do vBox escritores */
    // onde vai ficar todos os escritores.
    vbEscritores.setPrefSize(330, 500);
    vbEscritores.setStyle(style); // seta o style com os determinados em style
    vbEscritores.setSpacing(5); // o espacamento da borda e dos componentes
    // faz com que todos os componentes adicionados fique centralizados
    vbEscritores.setAlignment(Pos.CENTER);
    // ancorando na tela
    setRightAnchor(vbEscritores, 10.0);
    setTopAnchor(vbEscritores, 10.0);
    setBottomAnchor(vbEscritores, 10.0);

    /* Configuracoes do gridPane Botoes */
    // faz com que todos os componentes adicionados fique centralizados
    gbBotoes.setAlignment(Pos.CENTER);
    gbBotoes.setHgap(15); // distancia horizontal entre as celulas
    // adiciona os botoes
    gbBotoes.add(btIniciar, 1, 0);
    gbBotoes.add(btNovoLeitor, 0, 0);
    gbBotoes.add(btNovoEscritor, 2, 0);
    // onde fica todos os botoes
    // Ancora a direita/esquerda, considerando a borda,
    // o tamanho de vbEscritores/vbLeitores e o espacamento
    setRightAnchor(gbBotoes, vbEscritores.getPrefWidth() + 20);
    setLeftAnchor(gbBotoes, vbLeitores.getPrefWidth() + 20);
    setTopAnchor(gbBotoes, 10.0);

    /* Configuracoes do Anchor pane banco de Dados */
    apBancoDados.setStyle("-fx-background-color: rgba(102, 167, 243 , 0.5)");
    apBancoDados.setPrefSize(280, 400); // a largura dependo do tamanho da tela
    // Ancora a direita/esquerda, considerando a borda , 
    // o tamanho de vbEscritores/vbLeitores e o espacamento
    setRightAnchor(apBancoDados, vbEscritores.getPrefWidth() + 20);
    setLeftAnchor(apBancoDados, vbLeitores.getPrefWidth() + 20);
    setBottomAnchor(apBancoDados, 50.0);
    setTopAnchor(apBancoDados, 75.0);

    // adicina todos os componentes acima na Tela
    this.getChildren().addAll(ivBack, vbLeitores, vbEscritores, gbBotoes, apBancoDados);
    this.stage.setScene(new Scene(this));

    /* acao do botao iniciar */
    btIniciar.setOnAction(valor -> {
      apBancoDados.getChildren().add(new ImageView(new Image(("/view/imagens/bd.png"))));
      for (int i = 0; i < contLeitores; i++) { // inicia todos os leires criados
        leiror[i].getAcoes().start();
      } // fim do if
      for (int i = 0; i < contEscritores; i++) { // inicia todos os escritores criados
        escritor[i].getAcoes().start();
      } // fim do if
      // desabilita todos os botoes
        btIniciar.setDisable(true);
        btNovoLeitor.setDisable(true);
        btNovoEscritor.setDisable(true);
    });

    /* acao do botao Novo Leitor */
    btNovoLeitor.setOnAction(valor -> {
      // responsavel por permitir no maximo seis leitores
      if (contLeitores < 6) { // verifica se eh possivel adicionar um novo leitor
        leiror[contLeitores] = new Leitor(contLeitores, this);
        vbLeitores.getChildren().add(leiror[contLeitores]);
        contLeitores++;
      } // fim do if
      // responsavel desabilitar o botao quando chegar em seis leitores
      if (contLeitores == 6) {
        btNovoLeitor.setDisable(true); // desabilita o botao
      } // fim do if
    });

    /* acao do botao Novo Escritor */
    btNovoEscritor.setOnAction(valor -> {
    // responsavel por permitir no maximo seis Escritores
      if (contEscritores < 6) { // verifica se eh possivel adicionar um novo escritor
        escritor[contEscritores] = new Escritor(contEscritores, this);
        vbEscritores.getChildren().add(escritor[contEscritores]);
        contEscritores++;
      } // fim do if
      // responsavel desabilitar o botao quando chegar em seis EScritores
      if (contEscritores == 6) {
        btNovoEscritor.setDisable(true); // desabilita o botao
      } // fim do if
    });
  } // fim do metodo construirTela

    /* ***************************************************************
    * Metodo: show
    * Funcao: inicia a tela
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void show() {
      this.construirTela();
      this.stage.setTitle("Leitores e Escritores"); // titulo da aplicacao
      this.stage.getIcons().add(new Image("/view/imagens/logo.png")); // imagem da aplicacao
      this.stage.show();
    }// fim do metodo show

    /* ***************************************************************
    * Metodo: downMutex
    * Funcao: 
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void downMutex() {
      try {
        mutex.acquire();
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      } // fim do try catch
    } // fim do metodo downMutex

    /* ***************************************************************
    * Metodo: upMutex
    * Funcao: alterar o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public void upMutex() {
      mutex.release();
    } // fim do metodo upMutex

    /* ***************************************************************
    * Metodo: downDb
    * Funcao: alterar o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public void downDb() {
      try {
        db.acquire();
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      } // fim do try catch
    } // fim do metodo downDb

    /* ***************************************************************
    * Metodo: upDb
    * Funcao: alterar o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public void upDb() {
      db.release();
    } // fim do metodo upDb

    /* ***************************************************************
    * Metodo: getRc
    * Funcao: pega o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public int getRc() {
      return rc;
    } // fim do metodo getRc

    /* ***************************************************************
    * Metodo: setRc
    * Funcao: alterar o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public void setRc(int rc) {
      this.rc = rc;
    } // fim do metodo setRc

    /* ***************************************************************
    * Metodo: getApBancoDados
    * Funcao: alterar o rc
    * Parametros: rc: int, novo valor
    * Retorno: void
    *************************************************************** */
    public AnchorPane getApBancoDados() {
      return apBancoDados;
    } // fim do metodo getApBancoDados
} // fim da classe Tela
