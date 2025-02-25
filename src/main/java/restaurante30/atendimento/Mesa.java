package restaurante30.atendimento;
import restaurante30.Restaurante;

import java.util.Collections;

public class Mesa implements Pedido{
    private double totalGasto;
    private int numeroMesa;
    private boolean isOcupada;


    public Mesa(int numeroMesa, boolean isOcupada) {
        this.numeroMesa = numeroMesa;
        this.isOcupada = isOcupada;
        this.totalGasto = 0;
    }


    public void listarCardapio(){
        System.out.println("Peça ao garçom o prato pelo nome ou pelo código!");
        System.out.println("\n========CARDÁPIO RESTAURANTE F30=========\n");
        System.out.println("Código");
        for(Cardapio prato : Cardapio.values()){
            System.out.println(prato.getCodigoPedido() + " ....... " + prato.name() + " " + prato.getPreco() + "R$");
        }
    }

    public Cardapio getPrato(Cardapio prato){
        return prato;
    }


    public void fazerPedido(Cardapio prato){
        if(this.isOcupada == false){
            this.isOcupada = true;
            // Se o pedido foi feito, então alguém está ocupando a mesa
        }

        this.totalGasto = this.totalGasto + prato.getPreco();

        // O pedido tem que ir Mesa -> Garçom -> Cozinha

        Collections.shuffle(Restaurante.getListaGarcons()); //Rearranjo aleatóriamente a lista dos garçons...

        Garcom garcomAtendendo = Restaurante.getListaGarcons().get(0); //...e pego o primeiro garçom da nova lista para atender

        garcomAtendendo.fazerPedido(prato);
        //Aqui a mesa repassa para o garçom o prato que ela deseja pedir e o garçom anota apenas o código do prato
        //para enviar para a cozinha

    }


    public int getNumeroMesa(){
        return this.numeroMesa;
    }

    // retorna o quanto deve ser pago e "limpa" a mesa
    public void pagarConta(double totalGasto) {
        System.out.println("O total pago pela mesa " + this.getNumeroMesa() + " foi:"  + totalGasto + "R$");
        this.totalGasto = 0;
        this.isOcupada = false;
        Restaurante.setCaixaRestaurante(Restaurante.getCaixaRestaurante() + totalGasto);
    }

}