package restaurante30;
import restaurante30.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterfaceGerente extends Application {
    private Stage primaryStage;
    private TableView<Transacao> tabelaTransacoes;
    private Label lucroTotalLabel;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Restaurante - Gerente");
        mostrarTelaInicial();
    }

    private void mostrarTelaInicial() {
        Label titulo = new Label("Seja Bem-Vindo, Gerente!");
        titulo.setFont(new Font("Arial", 28));
        titulo.setTextFill(Color.WHITE);

        Button btnTransacoes = new Button("Ver Transações do Dia");
        Button btnFuncionarios = new Button("Ver Funcionários");
        estilizarBotao(btnTransacoes);
        estilizarBotao(btnFuncionarios);

        btnTransacoes.setOnAction(e -> mostrarTransacoes());
        //btnFuncionarios.setOnAction(e -> mostrarFuncionarios());

        VBox layout = new VBox(20, titulo, btnTransacoes, btnFuncionarios);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #0D47A1;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void mostrarTransacoes() {
        Label lblTitulo = new Label("Transações do Dia");
        lblTitulo.setFont(new Font("Arial", 24));
        lblTitulo.setTextFill(Color.WHITE);

        tabelaTransacoes = new TableView<>();
        TableColumn<Transacao, String> colunaHora = new TableColumn<>("Hora");
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

        TableColumn<Transacao, Integer> colunaMesa = new TableColumn<>("Número da Mesa");
        colunaMesa.setCellValueFactory(new PropertyValueFactory<>("numeroMesa"));

        TableColumn<Transacao, String> colunaCliente = new TableColumn<>("Cliente");
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));

        TableColumn<Transacao, Double> colunaTotal = new TableColumn<>("Total Pago");
        colunaTotal.setCellValueFactory(new PropertyValueFactory<>("totalPago"));

        tabelaTransacoes.getColumns().addAll(colunaHora, colunaMesa, colunaCliente, colunaTotal);
        carregarTransacoes();

        lucroTotalLabel = new Label();
        lucroTotalLabel.setFont(new Font("Arial", 18));
        lucroTotalLabel.setTextFill(Color.WHITE);
        atualizarLucroTotal();

        Button btnVoltar = new Button("Voltar");
        estilizarBotao(btnVoltar);
        btnVoltar.setOnAction(e -> mostrarTelaInicial());

        VBox layout = new VBox(20, lblTitulo, tabelaTransacoes, lucroTotalLabel, btnVoltar);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #0D47A1;");

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void carregarTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("transacoes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    transacoes.add(new Transacao(partes[0], Integer.parseInt(partes[1]), partes[2], Double.parseDouble(partes[3])));
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Erro ao carregar transações!");
        }
        tabelaTransacoes.getItems().setAll(transacoes);
    }

    private void atualizarLucroTotal() {
        double lucroTotal = tabelaTransacoes.getItems().stream().mapToDouble(Transacao::getTotalPago).sum();
        lucroTotalLabel.setText("Lucro Total do Dia: R$ " + String.format("%.2f", lucroTotal));
    }

    private void estilizarBotao(Button botao) {
        botao.setStyle("-fx-background-color: #64B5F6; -fx-text-fill: white; -fx-font-size: 16px;");
        botao.setMinWidth(220);
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensagem, ButtonType.OK);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}