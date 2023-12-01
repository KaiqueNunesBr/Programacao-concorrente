package model;

import java.util.concurrent.Semaphore;
import view.Tela;

public class CentralDeSemaforos {

  private Tanque1 t1;
  private Tanque2 t2;
  private Tanque3 t3;
  private Tanque4 t4;
  private Tanque5 t5;

  private Semaphore[] mutex, deadLock;

  public CentralDeSemaforos(Tela tela) {
    t1 = new Tanque1(tela, this);
    t2 = new Tanque2(tela, this);
    t3 = new Tanque3(tela, this);
    t4 = new Tanque4(tela, this);
    t5 = new Tanque5(tela, this);

    mutex = new Semaphore[31];
    deadLock = new Semaphore[4];
    iniciarSemaforos();
  } // fim do construtor

  private void iniciarSemaforos() {
    for (int i = 0; i < mutex.length; i++) {
      mutex[i] = new Semaphore(1);
    } // fim do for mutex
    for (int i = 0; i < deadLock.length; i++) {
      deadLock[i] = new Semaphore(2);
    } // fim do for deadLock
  }

  /* ***************************************************************
  * Metodo: iniciar
  * Funcao: inicia todas os tanques
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void iniciar() {
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
  } // fim do metodo inciar

  /* ***************************************************************
  * Metodo: downMutex
  * Funcao: efetua o down em mutex
  * Parametros: id: int, qual o mutex
  * Retorno: void
  *************************************************************** */
  public void downMutex(int id) {
    try {
      mutex[id].acquire();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo downMutex

  /* ***************************************************************
  * Metodo: upMutex
  * Funcao: efetua o up em mutex
  * Parametros: id: int, qual o mutex
  * Retorno: void
  *************************************************************** */
  public void upMutex(int id) {
    mutex[id].release();
  } // fim do metodo upMutex

  /* ***************************************************************
  * Metodo: downDeadLock
  * Funcao: efetua o down em deadLock
  * Parametros: id: int, qual o deadLock
  * Retorno: void
  *************************************************************** */
  public void downDeadLock(int id) {
    try {
      deadLock[id].acquire();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    } // fim do try catch
  } // fim do metodo downDeadLock

  /* ***************************************************************
  * Metodo: upDeadLock
  * Funcao: efetua o up em DeadLock
  * Parametros: id: int, qual o deadLock
  * Retorno: void
  *************************************************************** */
  public void upDeadLock(int id) {
    deadLock[id].release();
  } // fim do metodo upDeadLock
} // fim da classe CentralDeSemaforos
