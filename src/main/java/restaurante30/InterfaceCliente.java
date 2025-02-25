package restaurante30;
import restaurante30.atendimento.Cardapio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InterfaceCliente extends Application {
    private Stage primaryStage;
    private int numeroMesa;
    private String nomeCliente;
    private List<Cardapio> pedidos = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Restaurante - Cliente");
        mostrarTelaInicial();
    }

    private void mostrarTelaInicial() {
        Label lblBoasVindas = new Label("Seja Bem-vindo ao Restaurante F30");
        lblBoasVindas.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff;");

        Label labelMesa = new Label("Número da Mesa:");
        labelMesa.setStyle("-fx-text-fill: white;");
        TextField campoMesa = new TextField();

        Label labelNome = new Label("Nome:");
        labelNome.setStyle("-fx-text-fill: white;");
        TextField campoNome = new TextField();

        Button btnContinuar = new Button("Continuar");
        btnContinuar.setOnAction(e -> {
            try {
                int mesa = Integer.parseInt(campoMesa.getText());
                if (mesa < 1 || mesa > 30) {
                    throw new NumberFormatException();
                }
                numeroMesa = mesa;
                nomeCliente = campoNome.getText();
                mostrarCardapio();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Número da mesa inválido! Digite um número entre 1 e 30.");
            }
        });

        VBox layout = new VBox(15, lblBoasVindas, labelMesa, campoMesa, labelNome, campoNome, btnContinuar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #0A2748;");

        primaryStage.setScene(new Scene(layout, 720, 720));
        primaryStage.show();
    }

    private void mostrarCardapio() {
        Label lblTitulo = new Label("Cardápio:");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #0A2748;");

        Cardapio[] itens = Cardapio.values();
        for (int i = 0; i < itens.length; i++) {
            Cardapio item = itens[i];
            ImageView imgView = new ImageView(new Image(item.getImagem()));
            imgView.setFitWidth(100);
            imgView.setPreserveRatio(true);

            Label lblNome = new Label(item.getNome() + " - R$ " + item.getPreco());
            lblNome.setStyle("-fx-text-fill: white;");

            Button btnAdd = new Button("+");
            Button btnRemove = new Button("-");
            Label lblQtd = new Label("0");
            lblQtd.setStyle("-fx-text-fill: white;");

            btnAdd.setOnAction(e -> {
                int qtd = Integer.parseInt(lblQtd.getText()) + 1;
                lblQtd.setText(String.valueOf(qtd));
                pedidos.add(item);
            });

            btnRemove.setOnAction(e -> {
                int qtd = Integer.parseInt(lblQtd.getText());
                if (qtd > 0) {
                    lblQtd.setText(String.valueOf(qtd - 1));
                    pedidos.remove(item);
                }
            });

            HBox hBox = new HBox(5, btnRemove, lblQtd, btnAdd);
            VBox vBox = new VBox(5, imgView, lblNome, hBox);
            vBox.setStyle("-fx-alignment: center;");

            grid.add(vBox, i % 2, i / 2);
        }

        Button btnFinalizar = new Button("Finalizar Pedido");
        btnFinalizar.setOnAction(e -> mostrarResumo());

        VBox layout = new VBox(10, lblTitulo, scrollPane, btnFinalizar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #0A2748;");

        primaryStage.setScene(new Scene(layout, 720, 1080));
    }

    private void mostrarResumo() {
        double total = pedidos.stream().mapToDouble(Cardapio::getPreco).sum();
        Label lblResumo = new Label("Resumo do Pedido:");
        lblResumo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextArea areaResumo = new TextArea();

        StringBuilder resumo = new StringBuilder();
        for (Cardapio p : pedidos) {
            resumo.append(p.getNome()).append(" - R$ ").append(p.getPreco()).append("\n");
        }
        resumo.append("Total: R$ ").append(total);
        areaResumo.setText(resumo.toString());
        areaResumo.setEditable(false);

        Button btnPagar = new Button("Pagar Conta");
        btnPagar.setOnAction(e -> {
            salvarPedido(total);
            pedidos.clear();
            mostrarTelaInicial();
        });

        VBox layout = new VBox(10, lblResumo, areaResumo, btnPagar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #0A2748;");

        primaryStage.setScene(new Scene(layout, 720, 1080));
    }

    private void salvarPedido(double totalPago) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        try (FileWriter writer = new FileWriter("transacoes.txt", true)) {
            writer.write(hora + "," + numeroMesa + "," + nomeCliente + "," + totalPago + "\n");
        } catch (IOException e) {
            mostrarAlerta("Erro ao salvar pedido!");
        }
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensagem, ButtonType.OK);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
