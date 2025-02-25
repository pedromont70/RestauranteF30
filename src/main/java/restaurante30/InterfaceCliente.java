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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class CardapioItem {
    private String nome;
    private double preco;
    private String imagem;

    public CardapioItem(String nome, double preco, String imagem) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getImagem() { return imagem; }
}

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
        Label lblBoasVindas = new Label("Seja Bem vindo ao Restaurante F30");
        lblBoasVindas.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff;");

        Label labelMesa = new Label("Número da Mesa:");
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
                mostrarAlerta("Número da mesa inválido! Digite um número entre 1 e 30.");
            }
        });

        VBox layout = new VBox(15, lblBoasVindas, labelMesa, campoMesa, labelNome, campoNome, btnContinuar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #0A2748;");

        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }

    private void mostrarCardapio() {
        Label lblTitulo = new Label("Cardápio:");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ObservableList<CardapioItem> itens = FXCollections.observableArrayList(
                new CardapioItem("Pizza", 30.0, "file:src/imagens/capa.png"),
                new CardapioItem("Hambúrguer", 20.0, "file:src/imagens/eu.jpeg"),
                new CardapioItem("Queijo", 10.0, "file:src/imagens/eles.jpg"),
                new CardapioItem("Pizza", 30.0, "file:src/imagens/capa.png")
        );

        for (int i = 0; i < itens.size(); i++) {
            CardapioItem item = itens.get(i);
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
                pedidos.add(new Pedido(item.getNome(), item.getPreco()));
            });

            btnRemove.setOnAction(e -> {
                int qtd = Integer.parseInt(lblQtd.getText());
                if (qtd > 0) {
                    lblQtd.setText(String.valueOf(qtd - 1));
                    pedidos.remove(new Pedido(item.getNome(), item.getPreco()));
                }
            });

            HBox hBox = new HBox(5, btnRemove, lblQtd, btnAdd);
            VBox vBox = new VBox(5, imgView, lblNome, hBox);
            vBox.setStyle("-fx-alignment: center;");

            grid.add(vBox, i % 2, i / 2);
        }

        Button btnFinalizar = new Button("Finalizar Pedido");
        btnFinalizar.setOnAction(e -> mostrarResumo());

        VBox layout = new VBox(10, lblTitulo, grid, btnFinalizar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #0A2748;");

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void mostrarResumo() {
        double total = pedidos.stream().mapToDouble(Pedido::getPreco).sum();
        Label lblResumo = new Label("Resumo do Pedido:");
        lblResumo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextArea areaResumo = new TextArea();

        StringBuilder resumo = new StringBuilder();
        for (Pedido p : pedidos) {
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

        primaryStage.setScene(new Scene(layout, 800, 600));
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



