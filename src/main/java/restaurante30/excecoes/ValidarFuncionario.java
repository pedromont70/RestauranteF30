package restaurante30.excecoes;

import restaurante30.Restaurante;
import restaurante30.atendimento.Garcom;
import restaurante30.cozinha.Cozinheiro;
import restaurante30.cozinha.CozinheiroEspecial;

public class ValidarFuncionario {
    public void validarFuncionarios(Restaurante restaurante) {
        for (Garcom garcom : restaurante.getListaGarcons()) {
            if (garcom.getNome() == null || garcom.getNome().isEmpty()) {
                throw new NomeInvalidoException("Nome inválido para o garçom.");
            }
            if (garcom.calcularSalario() < 0) {
                throw new SalarioInvalidoException("Salário inválido para o garçom: " + garcom.getNome());
            }
        }

        for (Cozinheiro cozinheiro : restaurante.getCozinha().getCozinheiros()) {
            if (cozinheiro.getNome() == null || cozinheiro.getNome().trim().isEmpty()) {
                throw new NomeInvalidoException("Nome inválido para o cozinheiro.");
            }
            if (cozinheiro.calcularSalario() < 0) {
                throw new SalarioInvalidoException("Salário inválido para o cozinheiro: " + cozinheiro.getNome());
            }
            if (cozinheiro.getHabilidade() < 0) {
                throw new ParametroNegativoException("Habilidade inválida para o cozinheiro: " + cozinheiro.getNome());
            }

            if(cozinheiro instanceof CozinheiroEspecial){
                if (((CozinheiroEspecial)cozinheiro).getEspecialidade() < 0) {
                    throw new ParametroNegativoException("Especialidade inválida para o cozinheiro especial: " + cozinheiro.getNome());
                }
            }

        }
    }
}