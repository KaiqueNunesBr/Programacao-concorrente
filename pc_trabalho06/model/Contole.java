package model;

import java.util.concurrent.Semaphore;
import view.Tela;

public class Contole {

    private int n;
    private int estados[], posX[], posY[];

    private Filosofo filosofo[];

    private Semaphore mutex;
    private Semaphore s[];

    public Contole(Tela tela) {
        n = 5;
        estados = new int[]{0, 0, 0, 0, 0};
        filosofo = new Filosofo[n];

        mutex = new Semaphore(1);
        s = new Semaphore[n];

        for (int i = 0; i < estados.length; i++) {
            estados[i] = 0;
        }

        posX = new int[]{480, 645, 635, 205, 195};
        posY = new int[]{45, 170, 465, 480, 165};

        for (int i = 0; i < 5; i++) {
            filosofo[i] = new Filosofo(i, tela, this, posX[i], posY[i], mutex, s, estados);
            s[i] = new Semaphore(0);
            filosofo[i].start();
        }
    }

    public int getN() {
        return n;
    }

    public Filosofo getFilosofo(int id) {
        return filosofo[id];
    }
}
