package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import view.Tela;

public class Fabrica extends Thread {

  private Tela tela;
  private Maquina maq;
  private boolean[] contemRobo;

  private Queue<Robo> ordem;

  private int waiting, chairs;
  private Semaphore mutex, machine, robot;

  /* ***************************************************************
  * Metodo: Fabrica
  * Funcao: construtor
  * Parametros: tela: Tela
  * Retorno: void
  *************************************************************** */
  public Fabrica(Tela tela) {
    this.tela = tela;
    maq = new Maquina(tela, this);
    contemRobo = new boolean[5];

    ordem = new LinkedList<>();

    waiting = 0; // numero de robos na fila
    chairs = 5; // numero de esteiras para espera

    iniciarPosicoes();

    mutex = new Semaphore(1);
    machine = new Semaphore(0); // maquina a espera de robos
    robot = new Semaphore(0); // robos a espera da maquina
  } // fim do metodo construtor

  /* ***************************************************************
  * Metodo: iniciarPosicoes
  * Funcao: iniciar todo o vetor contemRobo com true
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  private void iniciarPosicoes() {
    for (int i = 0; i < contemRobo.length; i++) {
      contemRobo[i] = true;
    } // fim do for i
  } // fim do metodo iniciarPosicoes

  @Override
  public void run() {
    maq.start();
    while (true) { // executa infinitamente
      Robo novo = new Robo(tela, this, maq); // cria um novo Robo
      mySleep(tela.getVelEntrar()); // espera o tempo determinado
    } // fim do while
  } // fim do metodo run

  /* ***************************************************************
  * Metodo: getContemRobo
  * Funcao: get
  * Parametros: id: int, a posicao no vetor da vaga
  * Retorno: void
  *************************************************************** */
  public boolean getContemRobo(int id) {
    return contemRobo[id];
  } // fim do metodo getContemRobo

  /* ***************************************************************
  * Metodo: setContemRobo
  * Funcao: set
  * Parametros: contemRobo: boolean, novo valor; id: int, a posicao no vetor da vaga
  * Retorno: void
  *************************************************************** */
  public void setContemRobo(boolean contemRobo, int id) {
    this.contemRobo[id] = contemRobo;
  } // fim do metodo setContemRobo

  /* ***************************************************************
  * Metodo: getOrdem
  * Funcao: get
  * Parametros: void
  * Retorno: ordem: Queue, fila de chegada
  *************************************************************** */
  public Queue<Robo> getOrdem() {
    return ordem;
  }

  /* ***************************************************************
  * Metodo: downMutex
  * Funcao: efetua o down no mutex
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
  * Funcao: efetua o up no mutex
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void upMutex() {
    mutex.release();
  } // fim do metodo upMutex

  /* ***************************************************************
  * Metodo: downRobot
  * Funcao: efetua o down no mutex
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void downRobot() {
    try {
      robot.acquire();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo downMutex

  /* ***************************************************************
  * Metodo: upRobot
  * Funcao: efetua o up no robo
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void upRobot() {
    robot.release();
  } // fim do metodo upMutex


  /* ***************************************************************
  * Metodo: downMachine
  * Funcao: efetua o down na maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void downMachine() {
    try {
      machine.acquire();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo downMutex

  /* ***************************************************************
  * Metodo: upMachine
  * Funcao: efetua o up na maquina
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void upMachine() {
    machine.release();
  } // fim do metodo upMutex

  /* ***************************************************************
  * Metodo: getWaiting
  * Funcao: get
  * Parametros: void
  * Retorno: waiting: int
  *************************************************************** */
  public int getWaiting() {
    return waiting;
  } // fim do metodo getWaiting

  /* ***************************************************************
  * Metodo: setWaiting
  * Funcao: set
  * Parametros: waiting: int
  * Retorno: void
  *************************************************************** */
  public void setWaiting(int waiting) {
    this.waiting = waiting;
  } // fim do metodo setWaiting

  /* ***************************************************************
  * Metodo: getChairs
  * Funcao: get
  * Parametros: void
  * Retorno: waiting: int
  *************************************************************** */
  public int getChairs() {
    return chairs;
  } // fim do metodo getChairs

  /* ***************************************************************
  * Metodo: mySleep
  * Funcao: evitar try/catch por todo codigo
  * Parametros: tempo:int
  * Retorno: void
  *************************************************************** */
  private void mySleep(int tempo) {
    try {
      sleep(tempo);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo mySleep
} // fim da classe Fabrica
