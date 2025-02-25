package restaurante30.atendimento;

import restaurante30.Restaurante;
import restaurante30.excecoes.PedidoInvalidoException;
import restaurante30.funcionario.Funcionario;

public class Garcom extends Funcionario implements Pedido {
    private int mesasServidas;
    private static final double SALARIO_BASE_GARCOM = 2000.0;
    private static final double BONUS_POR_MESA = 10.0;

    public Garcom(String nome) {
        super(nome, 0); //O salário será calculado com base no número de mesas atendidas
        this.salario = SALARIO_BASE_GARCOM;
        this.mesasServidas = 0;
        Restaurante.getListaGarcons().add(this);
    }

    @Override
    public double calcularSalario() {
        //O Salário do garçom vai ser igual ao seu salário inicial mais um bonus por mesas servidas.
        return SALARIO_BASE_GARCOM + (mesasServidas * BONUS_POR_MESA);
    }

    public void fazerPedido(Cardapio pratoSolicitado) throws PedidoInvalidoException {
        boolean pedidoValido = false;

        for (Cardapio prato : Cardapio.values()) {
            if (pratoSolicitado.getCodigoPedido() == prato.getCodigoPedido()) {
                Restaurante.getCozinha().adicionarPedidoCozinha(pratoSolicitado);
                mesasServidas++;
                pedidoValido = true;
                break;
            }
        }

        if (!pedidoValido) {
            throw new PedidoInvalidoException("Código inválido! Não há prato com esse código.");
        }
    }
}