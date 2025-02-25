package restaurante30;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurante30.atendimento.Garcom;
import restaurante30.cozinha.Cozinheiro;
import restaurante30.funcionario.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InterfaceGerente extends Application {
    private Stage primaryStage;
    private Label lucroTotalLabel;
    private Restaurante restaurante;

    public InterfaceGerente(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Restaurante - Gerente");
        mostrarTelaInicial();
    }

    private void mostrarTelaInicial() {
        Label titulo = new Label("Seja Bem-Vindo ao Restaurante F30");
        titulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Button btnTransacoes = new Button("Ver Transações do Dia");
        Button btnFuncionarios = new Button("Ver Funcionários");
        estilizarBotao(btnTransacoes);
        estilizarBotao(btnFuncionarios);

        btnTransacoes.setOnAction(e -> mostrarTransacoes());
        btnFuncionarios.setOnAction(e -> mostrarOpcoesFuncionarios());

        VBox layout = new VBox(20, titulo, btnTransacoes, btnFuncionarios);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #0D47A1;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void mostrarTransacoes() {
        Label lblTitulo = new Label("Transações do Dia");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextArea areaTransacoes = new TextArea();
        areaTransacoes.setEditable(false);

        double faturamentoTotal = carregarTransacoes(areaTransacoes);
        lucroTotalLabel = new Label("Faturamento Total: R$ " + String.format("%.2f", faturamentoTotal));
        lucroTotalLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        Button btnVoltar = new Button("Voltar");
        estilizarBotao(btnVoltar);
        btnVoltar.setOnAction(e -> mostrarTelaInicial());

        VBox layout = new VBox(20, lblTitulo, areaTransacoes, lucroTotalLabel, btnVoltar);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #0D47A1;");

        primaryStage.setScene(new Scene(layout, 600, 500));
    }

    private double carregarTransacoes(TextArea areaTransacoes) {
        double total = 0;
        StringBuilder texto = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("transacoes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 4) {
                    texto.append("Hora: ").append(partes[0])
                            .append(" | Mesa: ").append(partes[1])
                            .append(" | Cliente: ").append(partes[2])
                            .append(" | Total Pago: R$ ").append(partes[3]).append("\n");
                    total += Double.parseDouble(partes[3]);
                }
            }
        } catch (IOException e) {
            texto.append("Erro ao carregar transações!");
        }
        areaTransacoes.setText(texto.toString());
        return total;
    }

    private void mostrarOpcoesFuncionarios() {
        Button btnGarcons = new Button("Ver Garçons");
        Button btnCozinheiros = new Button("Ver Cozinheiros");
        Button btnSalarioTotal = new Button("Mostrar Soma Salários");
        estilizarBotao(btnGarcons);
        estilizarBotao(btnCozinheiros);
        estilizarBotao(btnSalarioTotal);

        btnGarcons.setOnAction(e -> mostrarGarcons());
        btnCozinheiros.setOnAction(e -> mostrarCozinheiros());
        btnSalarioTotal.setOnAction(e -> mostrarSalarioTotal());

        Button btnVoltar = new Button("Voltar");
        estilizarBotao(btnVoltar);
        btnVoltar.setOnAction(e -> mostrarTelaInicial());

        VBox layout = new VBox(20, btnGarcons, btnCozinheiros, btnSalarioTotal, btnVoltar);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #0D47A1;");

        primaryStage.setScene(new Scene(layout, 600, 400));
    }

    private void mostrarGarcons() {
        List<Garcom> garcons = restaurante.getListaGarcons();
        String mensagem = garcons.stream()
                .map(g -> "Nome: " + g.getNome() + " | Salário: R$ " + g.getSalario())
                .collect(Collectors.joining("\n"));
        mostrarMensagem("Garçons", mensagem);
    }

    private void mostrarCozinheiros() {
        List<Cozinheiro> cozinheiros = restaurante.getCozinha().getCozinheiros();
        String mensagem = cozinheiros.stream()
                .map(c -> "Nome: " + c.getNome() + " | Salário: R$ " + c.getSalario() +
                        " | Chefe: " + (c.getChefe() ? "Sim" : "Não") +
                        " | Nível: " + c.getHabilidade())
                .collect(Collectors.joining("\n"));
        mostrarMensagem("Cozinheiros", mensagem);
    }

    private void mostrarSalarioTotal() {
        double totalSalarios = restaurante.calcularSalarioFuncionarios();
        mostrarMensagem("Salário Total", "Soma dos salários: R$ " + String.format("%.2f", totalSalarios));
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void estilizarBotao(Button botao) {
        botao.setStyle("-fx-background-color: #64B5F6; -fx-text-fill: white; -fx-font-size: 16px;");
        botao.setMinWidth(220);
    }
    public InterfaceGerente() {
        this.restaurante = new Restaurante(); // OU inicializar com um valor padrão
    }

    public static void main(String[] args) {
        launch(args);
    }
}