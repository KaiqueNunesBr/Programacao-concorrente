package model;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Tela;

public class Trem2 extends Thread {
    private ImageView imTrem2;
    private Image tremLateral;
    private Image tremDescendo;
    private Image tremSubindo;

    private int velocidade;
    private int posX;
    private int posY;

    private Bandeira bandeiras;

    public Trem2(Tela tela, Bandeira bandeiras, int velocidade) {
        this.bandeiras = bandeiras;

        // imagens do trem
        tremDescendo = new Image(getClass().getResourceAsStream("/view/imagens/TremDescendo.png"));
        tremLateral = new Image(getClass().getResourceAsStream("/view/imagens/TremLateral.png"));
        tremSubindo = new Image(getClass().getResourceAsStream("/view/imagens/TremSubindo.png"));

        // iniciando a primeira imagem do trem
        imTrem2 = new ImageView(tremLateral);

        // configuracao da velocidade do trem quato maior for o valor de slide menor sera o tempo do sleep
        this.velocidade = 100 / velocidade;

        // posicao inicial
        posX = 350;
        posY = 90;

        imTrem2.setLayoutX(posX);
        imTrem2.setLayoutY(posY);
        tela.getChildren().add(imTrem2); // adicionando o trem na tela
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
            esquerda();
            baixo();
            direita();
            cimaAntesCritica();
            regiaoCritica();
        } // fim do while
    } // fim do run

    /* ***************************************************************
    * Metodo: setPosicao
    * Funcao: configurar a posicao do ImageView imTrem2
    * Parametros: int posicao X, int posicao Y
    * Retorno: void
    *************************************************************** */
    private void setPosicao(int posX, int posY) {
        Platform.runLater(() -> {
            imTrem2.setLayoutX(posX);
            imTrem2.setLayoutY(posY);
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
    * Metodo: cimaDepoisCritica
    * Funcao: responsavel pela movimentacao em cima depois da regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void cimaDepoisCritica() {
        for (; posX > 85; posX -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo cimaDepoisCritica

    /* ***************************************************************
    * Metodo: esquerda
    * Funcao: responsavel pela movimentacao na esquerda
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void esquerda() {
        imTrem2.setImage(tremDescendo);
        for (; posY < 330; posY += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo esquerda

    /* ***************************************************************
    * Metodo: baixo
    * Funcao: responsavel pela movimentacao parte de baixo
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void baixo() {
        imTrem2.setImage(tremLateral);
        for (; posX < 750; posX += 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo baixo

    /* ***************************************************************
    * Metodo: direita
    * Funcao: responsavel pela movimentacao na direita
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void direita() {
        imTrem2.setImage(tremSubindo);
        for (; posY > 85; posY -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo direita

    /* ***************************************************************
    * Metodo: cimaAntesCritico
    * Funcao: responsavel pela movimentacao em cima antes da regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void cimaAntesCritica() {
        imTrem2.setImage(tremLateral);
        for (; posX > 530; posX -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
    } // fim do metodo cimaAntesCritica

    /* ***************************************************************
    * Metodo: regiaoCritica
    * Funcao: responsavel pela movimentacao em cima na regiao critica
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    private void regiaoCritica() {
        bandeiras.entrarRegiaoCritica(2);
        for (; posX > 490; posX -= 5, posY -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
        for (; posX > 385; posX -= 5) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
        for (; posX > 330; posX -= 5, posY += 4) {
            setPosicao(posX, posY);
            try {
                sleep(velocidade);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } // fim do try catch
        } // fim do for
        bandeiras.sairRegiaoCritica(2);
    } // fim do metodo regiaoCritica
} // fim da classe Trem2