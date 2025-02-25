package restaurante30;

import restaurante30.atendimento.Garcom;
import restaurante30.cozinha.Cozinheiro;
import restaurante30.cozinha.CozinheiroEspecial;
import restaurante30.excecoes.ValidarFuncionario;
import restaurante30.funcionario.Funcionario;

public class RestauranteTeste {
    public static void main(String[] args){
        Restaurante F30 = new Restaurante();
        ValidarFuncionario validar = new ValidarFuncionario();
        try {
            validar.validarFuncionarios(F30);
        } catch (Exception e) {
            System.out.println("Erro encontrado: " + e.getMessage());
        }

        System.out.println(F30.calcularSalarioFuncionarios());
        System.out.println("Total de funcionários: " + Funcionario.getContadorFuncionarios());
    }

}