
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Aluno;
import model.Curso;
import java.util.List;
import java.util.ArrayList;

public class Principal extends Application {
/* ******************************************
* Autor: Francisco Kaique Nunes Barbosa
* Matricula: 201610441
* Inicio: 05/07/2018
* Ultima alteracao: 10/072018
* Nome: Lista de Alunos
* Funcao: Cadastra e filtrar alunos pro curso,
* com a possibilidade de cadastrar novo curso.
******************************************** */

  public static void main(String[] args) {
    launch();
  }// fim do main

  @Override
  public void start(Stage palco) throws Exception {
    HBox box = new HBox(); // o layout principal que separa as telas na horizontal
    box.setPrefSize(150, 200);
    // style eh o padrao de todos os VBox
    String style = "-fx-padding: 5; -fx-border-style: solid inside;"
                 + " -fx-border-width: 2; -fx-border-insets: 2;"
                 + " -fx-border-radius: 5; -fx-border-color: lightGrey;"; 

    List<Curso> cursos;
    cursos = new ArrayList<>();

    // criando cursos e alunos para facilitar a utilizacao
    final Curso computacao = new Curso("Computacao");
    final Aluno aluno1 = new Aluno("Joao", "01");
    final Aluno aluno2 = new Aluno("Pedro", "02");
    final Curso medicina = new Curso("Medicina");
    final Aluno aluno3 = new Aluno("Maria", "03");
    final Aluno aluno4 = new Aluno("Jack", "05");
    computacao.getAlunos().add(aluno1);
    computacao.getAlunos().add(aluno2);
    medicina.getAlunos().add(aluno3);
    medicina.getAlunos().add(aluno4);
    cursos.add(computacao);
    cursos.add(medicina);

    /*************************************************
    * PRIMEIRA PARTE
    * Aqui eh possivel cadastra um novo usuario
    * e atribuilo a um curso novo ou ja existente.
    ************************************************** */
    VBox primeiraParte = new VBox();
    primeiraParte.setPrefSize(150, 200);
    primeiraParte.setStyle(style);
    primeiraParte.setSpacing(05);

    Label lbNome = new Label("Nome");
    Label lbMatricula = new Label("Matricula");
    Label lbCurso = new Label("Curso");

    TextField tfNome = new TextField();
    TextField tfMatricula = new TextField();

    ComboBox<String> cbCurso = new ComboBox<>();
    cbCurso.setEditable(true);
    //cbCurso.setPromptText("Curso");

    Button btSalvar = new Button();
    btSalvar.setText("Salvar");

    primeiraParte.getChildren().addAll(lbNome, tfNome, lbMatricula, 
                         tfMatricula, lbCurso, cbCurso, btSalvar);

    /*************************************************
    * SEGUNDA PARTE
    * Aqui selecionar um curso, no qual sera mostrado
    * na Terceira parte dentro de um ListView.
    ************************************************** */
    VBox segundaParte = new VBox();
    segundaParte.setPrefSize(150, 200);
    segundaParte.setStyle(style);
    segundaParte.setSpacing(05);

    Label lbPesquisarCurso = new Label("Pesquisar por Curso:");

    ComboBox<String> cbPesquisarCurso = new ComboBox<>();
    cbPesquisarCurso.setPrefWidth(145);
    cbPesquisarCurso.setPromptText("Curso");

    segundaParte.getChildren().addAll(lbPesquisarCurso, cbPesquisarCurso);

    /*************************************************
    * TERCEIRA PARTE
    * Aqui mostra em um ListView todos os alunos
    * cadastrados naquele curso selecionado.
    ************************************************** */
    VBox terceiraParte = new VBox();
    terceiraParte.setPrefSize(150, 200);
    terceiraParte.setStyle(style);
    terceiraParte.setSpacing(05);

    ListView<Aluno> lvAlunos = new ListView<>();

    terceiraParte.getChildren().add(lvAlunos);

    /*************************************************
    * Agrupa todas as telas e exibe.
    ************************************************** */
    box.getChildren().addAll(primeiraParte, segundaParte, terceiraParte);
    Scene cena = new Scene(box, 450, 200);
    palco.setTitle("Lista de Alunos");
    palco.setScene(cena);
    palco.show();

    /*************************************************
    * Configuracoes das acoes dos componentes.
    ************************************************** */
    // adiciona os cursos da lista dentos dos ComboBox
    
    for(Curso c : cursos){
    cbPesquisarCurso.getItems().addAll(c.getNome());
    cbCurso.getItems().addAll(c.getNome());
    }

    // salva um novo aluno em um curso novo/ja existente
    // como tambem atualiza os ComboBox
    btSalvar.setOnAction(acao -> {
      Boolean novo = true;
      for (Curso c : cursos) {
        if (cbCurso.getValue().toString().equals(c.getNome())) {
          Aluno a = new Aluno(tfNome.getText(), tfMatricula.getText());
          c.getAlunos().add(a);
          cursos.add(c);
          novo = false;
          break;
        }
      }
      if (novo) {
        Curso cur = new Curso(cbCurso.getValue().toString());
        Aluno a = new Aluno(tfNome.getText(), tfMatricula.getText());
        cur.getAlunos().add(a);
        cursos.add(cur);
        cbPesquisarCurso.getItems().add(cur.getNome());
        cbCurso.getItems().add(cur.getNome());
      }
      for (Curso c : cursos) {
        if (cbPesquisarCurso.getValue().toString().equals(c.getNome())) {
          lvAlunos.getItems().clear();
          lvAlunos.getItems().addAll(c.getAlunos());
        }// fim do if
      }// fim do for
      
      tfNome.clear();
      tfMatricula.clear();
      cbCurso.setValue(null);
    });//fim da acao do botao salvar


    // adiciona os alunos e atualiza o ListView
    cbPesquisarCurso.setOnAction(acao -> {
      for (Curso c : cursos) {
        if (cbPesquisarCurso.getValue().toString().equals(c.getNome())) {
          lvAlunos.getItems().clear();
          lvAlunos.getItems().addAll(c.getAlunos());
        }// fim do if
      }// fim do for
    });//fim da acao do comboBox pesquisar
  }//fim do metodo start
}//Fim da classe principal