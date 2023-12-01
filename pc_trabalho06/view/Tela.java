package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Contole;

public class Tela extends BorderPane {

    private Stage stage;

    private VBox vbEsquerda;
    private Slider sldComer[];
    private int velocidadeComer[];

    private VBox vbDireita;
    private Slider sldPensar[];
    private int velocidadePensar[];

    private Label lblComer;
    private Label lblPensar;

    private Button iniciar;

    private AnchorPane acpCentro;
    private Image imgMesa;
    private ImageView imvMesa;

    private Contole comecar;

    public Tela(Stage stage) {
        this.stage = stage;

        vbEsquerda = new VBox();
        lblComer = new Label("Velocidade de comer!");
        sldComer = new Slider[5];
        velocidadeComer = new int[5];

        vbDireita = new VBox();
        lblPensar = new Label("velocidade  de pensar!");
        sldPensar = new Slider[5];
        velocidadePensar = new int[5];

        iniciar = new Button("Iniciar");
        imgMesa = new Image(getClass().getResourceAsStream("/view/imagens/mesa.png"));
        imvMesa = new ImageView(imgMesa);
        acpCentro = new AnchorPane();
    }

    private void construirTela() {
        vbEsquerda.setAlignment(Pos.CENTER);
        vbEsquerda.setSpacing(40);
        vbEsquerda.getChildren().add(lblComer);

        vbDireita.setAlignment(Pos.CENTER);
        vbDireita.setSpacing(40);
        vbDireita.getChildren().add(lblPensar);

        acpCentro.getChildren().add(imvMesa);

        BorderPane.setAlignment(iniciar, Pos.CENTER); // faz o botao ficar no contro da tela

        this.setLeft(vbEsquerda);
        this.setRight(vbDireita);
        this.setBottom(iniciar);
        this.setCenter(acpCentro);
        this.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < sldComer.length; i++) {

            velocidadeComer[i] = 1000;
            sldComer[i] = new Slider(1000, 5000, 1000);
            sldComer[i].setMinorTickCount(5);
            sldComer[i].setBlockIncrement(5);
        }

        sldComer[0].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadeComer[0] = newValue.intValue();
            }
        });

        sldComer[1].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadeComer[1] = newValue.intValue();
            }
        });

        sldComer[2].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadeComer[2] = newValue.intValue();
            }
        });

        sldComer[3].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadeComer[3] = newValue.intValue();
            }
        });

        sldComer[4].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadeComer[4] = newValue.intValue();
            }
        });

        vbEsquerda.getChildren().addAll(sldComer);

        for (int i = 0; i < sldComer.length; i++) {

            velocidadePensar[i] = 1000;
            sldPensar[i] = new Slider(1000, 5000, 1000);
            sldPensar[i].setMinorTickCount(5);
            sldPensar[i].setBlockIncrement(5);
        }

        sldPensar[0].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadePensar[0] = newValue.intValue();
            }
        });
        sldPensar[1].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadePensar[1] = newValue.intValue();
            }
        });
        sldPensar[2].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadePensar[2] = newValue.intValue();
            }
        });
        sldPensar[3].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadePensar[3] = newValue.intValue();
            }
        });
        sldPensar[4].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                    Number oldValue, Number newValue) {
                velocidadePensar[4] = newValue.intValue();
            }
        });
        vbDireita.getChildren().addAll(sldPensar);

        this.stage.setScene(new Scene(this));

        iniciar.setOnAction(valor -> {
            comecar = new Contole(this);
            iniciar.setDisable(true); // desabilita o botao
        });
    }

    public void show() {

        this.construirTela();
        this.stage.show();
    }

    public int getVelocidadeComer(int id) {
        return velocidadeComer[id];
    }

    public int getVelocidadePensar(int id) {
        return velocidadePensar[id];
    }
}
