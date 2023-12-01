package model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Trem1 extends Thread {
    private ImageView imTrem1;
    private Image tremLateral;
    private Image tremDescendo;
    private Image tremSubindo;

    private int velocidade;
    private int posX;
    private int posY;

    private Bandeira bandeiras;

    public Trem1(Tela tela, Bandeira bandeiras, int velocidade) {
        this.bandeiras = bandeiras;
        
        // imagens do trem
        tremDescendo = new Image(getClass().getResourceAsStream("/view/imagens/TremDescendo.png"));
        tremLateral = new Image(getClass().getResourceAsStream("/view/imagens/TremLateral.png"));
        tremSubindo = new Image(getClass().getResourceAsStream("/view/imagens/TremSubindo.png"));

        // iniciando a primeira imagem do trem
        imTrem1 = new ImageView(tremLateral);

        // configuracao da velocidade do trem quato maior for o valor de slide menor sera o tempo do sleep
        this.velocidade = 100 / velocidade;
        
        // posicao inicial
        posX = 540;
        posY = 50;

        imTrem1.setLayoutX(posX);
        imTrem1.setLayoutY(posY);
        tela.getChildren().add(imTrem1); // adicionando o trem na tela
    } // fim do construtor

    /* ***************************************************************
    * Metodo: run
    * Funcao: o que sera executado pela thread
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        while (true) {
            cimaDepoisCritica();
            direita();
            baixo();
            esquerda();
            cimaAntesCritica();
            regiaoCritica();
        } // fim do while
    } // fim do run

    /* ***************************************************************
    * Metodo: setPosicao
    * Funcao: configurar a posicao do ImageView imTrem1
    * Parametros: int posicao X, int posicao Y
    * Retorno: void
    *************************************************************** */
    private void setPosicao(int posX, int posY) {
        Platform.runLater(() -> {
            imTrem1.setLayoutX(posX);
            imTrem1.setLayoutY(posY);
        });
    } // fim do metodo setPosicao

    /* ***************************************************************
    * Metodo: setVelocidade
    * Funcao: alterar a velocidade
    * Parametros: int, a nova velocidade
    * Retorno: void
    *************************************************************** */
    public void setVelocidade(int velocidade) {
        this.velocidade = 100 / velocidade;
    } // fim do metodo setVelocidade

    /* ***************************************************************
    * Metodo: cimaDepoisCri
    * Funcao: responsavel pela movimentacao em cima depois da regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void cimaDepoisCritica() {
        for (; posX < 820; posX += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo cimaDepoisCri

    /* ***************************************************************
    * Metodo: direita
    * Funcao: responsavel pela movimentacao na direita
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void direita() {
        imTrem1.setImage(tremDescendo);

        for (; posY < 400; posY += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo direita

    /* ***************************************************************
    * Metodo: baixo
    * Funcao: responsavel pela movimentacao parte de baixo
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void baixo() {
        imTrem1.setImage(tremLateral);
        for (; posX > 19; posX -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo baixo

    /* ***************************************************************
    * Metodo: esquerda
    * Funcao: responsavel pela movimentacao na esquerda
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void esquerda() {
        imTrem1.setImage(tremSubindo);
        for (; posY > 50; posY -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo esquerda

    /* ***************************************************************
    * Metodo: cimaAntesCritico
    * Funcao: responsavel pela movimentacao em cima antes da regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void cimaAntesCritica() {
        imTrem1.setImage(tremLateral);
        for (; posX < 340; posX += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo cimaAntesCritico

    /* ***************************************************************
    * Metodo: regiaoCritica
    * Funcao: responsavel pela movimentacao em cima na regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void regiaoCritica() {
        bandeiras.entrarRegiaoCritica(1);
        for (; posX < 540; posX += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
        bandeiras.sairRegiaoCritica(1);
    } // fim do metodo regiaoCritica
} // fim da classe trem1 
