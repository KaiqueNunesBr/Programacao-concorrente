#include <sys/types.h>
#include <unistd.h>
#include <iostream>

using namespace std;

/********************************************************************
* Autor: Francisco Kaique Nunes Barbosa
* Matricula: 201610441
* Inicio: 19/072018
* Ultima alteracao: 21/07/2018
* Nome: Fork em c++
* Funcao: Simula processos com base na arvore genealogia de 7 pessoas
********************************************************************* */
int main(void){
  
  // o processo pai eh o proprio main
  pid_t primeiroFilho;
  pid_t segundoFilho;
  pid_t terceiroFilho;
  pid_t primeiroNeto;
  pid_t segundoNeto;
  pid_t bisneto;
  
  cout << "Nasce o Pai\n";
  primeiroFilho = fork();
  
  if (primeiroFilho == 0){ //processo do primeiro filho
    sleep(22);
    cout << "Nasce o Primeiro filho\n";
    primeiroNeto = fork();
    if (primeiroNeto == 0){ // processo do primeiro neto
      sleep(16);
      cout << "Nasce o Primeiro Neto\n";
      bisneto = fork();
      if (bisneto == 0){ // processo do bisneto
        sleep(30);
        cout << "Nasce o Bisneto\n";
        sleep(12);
        cout << "Morre o Bisneto\n";
      }else { // processo do primeiro neto
        sleep(35);
        cout << "Morre o Primeiro Neto\n";
      } // fim do if else do bisneto
    }else { // processo do primeiro filho
      sleep(61);
      cout << "Morre o Primeiro filho\n";
    }// fim do if else do primeiro neto
  } else { // processo pai
    segundoFilho = fork();
    if (segundoFilho == 0){ // processo do segundo filho
      sleep(25);
      cout << "Nasce o Segundo filho\n";
      segundoNeto = fork();
      if (segundoNeto == 0){ // processo do segundo neto
        sleep(20);
        cout << "Nasce o Segundo Neto\n";
        sleep(33);
        cout << "Morre o Segundo Neto\n";
      }else { // processo do segundo filho
        sleep(55);
        cout << "Morre o Segundo filho\n";
      } // fim do if else do segundo neto
    } else { // processo pai
      terceiroFilho = fork();
      if (terceiroFilho == 0){ //processo do terceiro filho
        sleep(32);
        cout << "Nasce o Terceito filho\n";
        sleep(55);
        cout << "Morre o Terceito filho\n";
      }else { // processo pai
        sleep(90);
        cout << "Morre o Pai\n";
      } // fim do if else do terceiro filho
    } // fim do if else do segundo filho
  } // fim do if else do primeiro filho
} // fim do main
