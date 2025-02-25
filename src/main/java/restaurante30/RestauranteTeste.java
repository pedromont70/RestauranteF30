package restaurante30;

import restaurante30.atendimento.Garcom;
import restaurante30.cozinha.Cozinheiro;
import restaurante30.cozinha.CozinheiroEspecial;
import restaurante30.excecoes.ValidarFuncionario;
import restaurante30.funcionario.Funcionario;

public class RestauranteTeste {
    //Instanciando os garçons e cozinheiros.
    Garcom garcom1 = new Garcom("");
    Garcom garcom2 = new Garcom("Ana");
    Garcom garcom3 = new Garcom("Lucas");
    Garcom garcom4 = new Garcom("Maria");
    Garcom garcom5 = new Garcom("Ricardo");
    Garcom garcom6 = new Garcom("Juliana");
    Garcom garcom7 = new Garcom("Sofia");
    Garcom garcom8 = new Garcom("Pedro");
    Garcom garcom9 = new Garcom("Gabriela");
    Garcom garcom10 = new Garcom("João");

    Cozinheiro cozinheiro1 = new Cozinheiro("Eduardo", 5, false);
    Cozinheiro cozinheiro2 = new Cozinheiro("Fernanda", 8, false);
    Cozinheiro cozinheiro3 = new Cozinheiro("Ricardo", 10, false);
    Cozinheiro cozinheiro4 = new Cozinheiro("Juliana", 3, false);
    Cozinheiro cozinheiro5 = new Cozinheiro("Carlos", 7, false);
    Cozinheiro cozinheiro6 = new Cozinheiro("Camila", 9, true);
    Cozinheiro cozinheiro7 = new Cozinheiro("Pedro", 6, false);
    Cozinheiro cozinheiro8 = new Cozinheiro("Isabela", 10, true);
    CozinheiroEspecial cozinheiroEspecial1 = new CozinheiroEspecial("Gustavo", 9, true, 3);
    CozinheiroEspecial cozinheiroEspecial2 = new CozinheiroEspecial("Beatriz", 7, false, 5);
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