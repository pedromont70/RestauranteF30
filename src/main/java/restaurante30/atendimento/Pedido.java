package restaurante30.atendimento;

public interface Pedido {
    // Isso aqui não precisa pq já tá no enum
    //private int orderNumber;
    //private Cardapio plate;

    // Na interface vamos declarar apenas os esqueletos
    // dos métodos a serem implementados

    public void fazerPedido(Cardapio prato);

    //public double getPreco();

    //public Cardapio getPrato(Cardapio prato);

    //public int getCodigo();


}
