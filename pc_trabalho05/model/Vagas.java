package model;

public class Vagas {

    private boolean vaga1;
    private boolean vaga2;
    private boolean vaga3;

    /* ***************************************************************
    * Metodo: Vagas
    * Funcao: construtor
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public Vagas() {
        // seta todas as vagas como true, para indicar que a vaga esta livre
        vaga1 = true;
        vaga2 = true;
        vaga3 = true;
    } // fim do construtor

    /* ***************************************************************
    * Metodo: getVaga1
    * Funcao: pegas o valor da vaga 1
    * Parametros: void
    * Retorno: boolean
    *************************************************************** */
    public boolean getVaga1() {
        return vaga1;
    }

    /* ***************************************************************
    * Metodo: getVaga2
    * Funcao: pegas o valor da vaga 2
    * Parametros: void
    * Retorno: boolean
    *************************************************************** */
    public boolean getVaga2() {
        return vaga2;
    }

    /* ***************************************************************
    * Metodo: getVaga3
    * Funcao: pegas o valor da vaga 3
    * Parametros: void
    * Retorno: boolean
    *************************************************************** */
    public boolean getVaga3() {
        return vaga3;
    }

    /* ***************************************************************
    * Metodo: setVaga1
    * Funcao: altera o valor da vaga 1
    * Parametros: vaga1: boolean, o novo valor
    * Retorno: void
    *************************************************************** */
    public void setVaga1(boolean vaga1) {
        this.vaga1 = vaga1;
    }

    /* ***************************************************************
    * Metodo: setVaga2
    * Funcao: altera o valor da vaga 2
    * Parametros: vaga2: boolean, o novo valor
    * Retorno: void
    *************************************************************** */
    public void setVaga2(boolean vaga2) {
        this.vaga2 = vaga2;
    }

    /* ***************************************************************
    * Metodo: setVaga3
    * Funcao: altera o valor da vaga 3
    * Parametros: vaga3: boolean, o novo valor
    * Retorno: void
    *************************************************************** */
    public void setVaga3(boolean vaga3) {
        this.vaga3 = vaga3;
    }
}