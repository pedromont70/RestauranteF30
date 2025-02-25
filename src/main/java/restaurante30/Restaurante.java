package restaurante30;

import restaurante30.atendimento.Garcom;
import restaurante30.atendimento.Mesa;
import restaurante30.cozinha.Cozinha;
import restaurante30.cozinha.Cozinheiro;
import restaurante30.cozinha.CozinheiroEspecial;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Restaurante {
    public static final int NUMERO_MESAS = 30;
    static Queue<Mesa> mesasVazias = new LinkedList<Mesa>();
    // A Queue (fila) é uma estrutura de dado do tipo
    // First-In-First-Out (FIFO), que funciona, como se
    // ninguém esperasse isso, igual a uma fila de verdade:
    // quem chegou primeiro sai primeiro e quem chegou por ultimo sai por ultimo.

    private static double caixaRestaurante = 0;

    static{
        // Adicionando todas as mesas ao restaurante
        // Inicialmente todas estão vazias
        // TODO: verificar se isso realmente funciona
        for(int i = 1; i <= NUMERO_MESAS; i++){
            mesasVazias.add(new Mesa(i, false));
        }
    }
    //Instanciando os garçons e cozinheiros.
    Garcom garcom1 = new Garcom("Felix");
    Garcom garcom2 = new Garcom("Ana");
    Garcom garcom3 = new Garcom("Lucas");
    Garcom garcom4 = new Garcom("Maria");
    Garcom garcom5 = new Garcom("Ricardo");
    Garcom garcom6 = new Garcom("Juliana");
    Garcom garcom7 = new Garcom("Sofia");
    Garcom garcom8 = new Garcom("Pedro");
    Garcom garcom9 = new Garcom("Gabriela");
    Garcom garcom10 = new Garcom("João");

    Cozinheiro cozinheiro1 = new Cozinheiro("Ana", 5, false);
    Cozinheiro cozinheiro2 = new Cozinheiro("Fernanda", 8, false);
    Cozinheiro cozinheiro3 = new Cozinheiro("Ricardo", 10, false);
    Cozinheiro cozinheiro4 = new Cozinheiro("Juliana", 3, false);
    Cozinheiro cozinheiro5 = new Cozinheiro("Carlos", 7, false);
    Cozinheiro cozinheiro6 = new Cozinheiro("Camila", 9, true);
    Cozinheiro cozinheiro7 = new Cozinheiro("Pedro", 6, false);
    Cozinheiro cozinheiro8 = new Cozinheiro("Isabela",10,true);
    CozinheiroEspecial cozinheiroEspecial1 = new CozinheiroEspecial("Gustavo", 9, true, 9);
    CozinheiroEspecial cozinheiroEspecial2 = new CozinheiroEspecial("Beatriz",7,false,5);


    static List<Garcom> listaGarcons = new ArrayList<Garcom>();
    static Cozinha cozinha = new Cozinha();

    public static double getCaixaRestaurante(){
        return caixaRestaurante;
    }

    public static void setCaixaRestaurante(double dinheiro){
        caixaRestaurante = dinheiro;
    }

    public static List<Garcom> getListaGarcons(){
        return listaGarcons;
    }

    public static Cozinha getCozinha(){
        return cozinha;
    }

    public double calcularSalarioFuncionarios(){
        double gastoSalarios = 0;

        listaGarcons = Restaurante.getListaGarcons();


        //Percorremos a lista de garçons, calculando o salário
        //de cada um e somando
        for(Garcom garcom : listaGarcons){
            gastoSalarios += garcom.calcularSalario();
        }

        //Agora percorremos a lista de cozinheiros

        for(Cozinheiro cozinheiro : cozinha.getCozinheiros()){
            gastoSalarios += cozinheiro.calcularSalario();
        }
        return gastoSalarios;
    }

    public Restaurante(){

        List<Garcom> listaGarcons = new ArrayList<Garcom>();
        Cozinha cozinha = new Cozinha();


    }



}