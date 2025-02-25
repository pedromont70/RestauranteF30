package restaurante30.cozinha;

import restaurante30.Restaurante;
import restaurante30.atendimento.Cardapio;
import restaurante30.excecoes.CozinheiroIndisponivelException;
import restaurante30.excecoes.ParametroNegativoException;
import restaurante30.funcionario.Funcionario;
import restaurante30.cozinha.*;

public class Cozinheiro extends Funcionario {
    protected int habilidade;
    protected boolean preparandoPedido;
    protected boolean ehChefe;
    protected static final double SALARIO_BASE_COZINHEIRO = 3000.0;

    public Cozinheiro(String nome, int habilidade, boolean ehChefe) throws ParametroNegativoException {
        super(nome, 0);//O salário do cozinheiro vai ser calculado com base nas suas habilidades
        if(habilidade < 0){
            throw new ParametroNegativoException("A habilidade do cozinheiro não pode ser negativa.");
        }

        this.habilidade = habilidade;
        this.preparandoPedido = false;  //Ao se criar o cozinheiro ele não está preparando nenhum pedido, afinal, acabou de chegar
        this.ehChefe = ehChefe;
        this.salario = calcularSalario();
        Restaurante.getCozinha().getCozinheiros().add(this);
    }

    public void prepararPedido(Cardapio pedido) throws CozinheiroIndisponivelException {
        if(preparandoPedido == false) {
            setPreparandoPedido(true);
            Restaurante.getCozinha().getPedidosSendoPreparados().add(pedido);
            setPreparandoPedido(false);
        }
        else{
            throw new CozinheiroIndisponivelException("O cozinheiro não está disponível");
        }

    }

    @Override
    public double calcularSalario() {
        double bonusHabilidade = habilidade * 50.0; // Exemplo: cada nível de habilidade adiciona R$50

        // Se for chefe, recebe um bônus fixo
        double bonusChefe;

        if (ehChefe == true) {   //Redundante, mas legível
            bonusChefe = 500.0;
        } else {
            bonusChefe = 0.0;
        }

        return SALARIO_BASE_COZINHEIRO + bonusHabilidade + bonusChefe;
    }


    public int getHabilidade() {
        return this.habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public boolean getPreparandoPedido() {
        return this.preparandoPedido;
    }

    public void setPreparandoPedido(boolean preparandoPedido) {
        this.preparandoPedido = preparandoPedido;
    }

    public boolean getChefe() {
        return this.ehChefe;
    }

    public void setChefe(boolean chefe) {
        this.ehChefe = chefe;
    }


    public boolean isEhChefe() {
        return ehChefe;
    }

    public void setEhChefe(boolean ehChefe) {
        this.ehChefe = ehChefe;
    }
}