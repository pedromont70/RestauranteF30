package restaurante30.atendimento;

public enum Cardapio {
    PIZZA(1, "Pizza", 30.0, "file:src/imagens/pizza.png", false),
    HAMBURGUER(2, "Hambúrguer", 20.0, "file:src/imagens/hamburguer.png", false),
    QUEIJO(3, "Queijo", 10.0, "file:src/imagens/queijo.png", false),
    LASANHA(4, "Lasanha", 25.0, "file:src/imagens/lasanha.png", false),
    PASTA(5, "Pasta", 22.0, "file:src/imagens/pasta.png", false),
    SUSHI(6, "Sushi", 35.0, "file:src/imagens/sushi.png", true),
    SALADA(7, "Salada", 15.0, "file:src/imagens/salada.png", false),
    FRANGO(8, "Frango Assado", 28.0, "file:src/imagens/frango.png", false),
    BOLO(9, "Bolo de Chocolate", 18.0, "file:src/imagens/bolo.png", false),
    SORVETE(10, "Sorvete", 12.0, "file:src/imagens/sorvete.png", false),
    STEAK(11, "Steak", 40.0, "file:src/imagens/steak.png", true),
    TACOS(12, "Tacos", 18.0, "file:src/imagens/tacos.png", false),
    MACARRONADA(13, "Macarronada", 23.0, "file:src/imagens/macarronada.png", false),
    PEIXE(14, "Peixe Grelhado", 30.0, "file:src/imagens/peixe.png", false),
    SOPA(15, "Sopa de Legumes", 12.0, "file:src/imagens/sopa.png", false),
    RAVIOLI(16, "Ravioli", 26.0, "file:src/imagens/ravioli.png", false),
    CHURRASCO(17, "Churrasco", 45.0, "file:src/imagens/churrasco.png", true),
    PANQUECAS(18, "Panquecas", 16.0, "file:src/imagens/panquecas.png", false),
    RISOTO(19, "Risoto", 32.0, "file:src/imagens/risoto.png", false),
    OMELETE(20, "Omelete", 14.0, "file:src/imagens/omelete.png", false),
    TORTA(21, "Torta de Limão", 17.0, "file:src/imagens/torta.png", false),
    COSTELA(22, "Costela Barbecue", 38.0, "file:src/imagens/costela.png", true),
    HOTDOG(23, "Hot Dog", 15.0, "file:src/imagens/hotdog.png", false),
    PASTEL(24, "Pastel", 9.0, "file:src/imagens/pastel.png", false),
    CEVICHE(25, "Ceviche", 34.0, "file:src/imagens/ceviche.png", true),
    ESPAGUETE(26, "Espaguete", 21.0, "file:src/imagens/espaguete.png", false),
    FRANGO_XADREZ(27, "Frango Xadrez", 29.0, "file:src/imagens/frangoxadrez.png", false),
    COXINHA(28, "Coxinha", 8.0, "file:src/imagens/coxinha.png", false),
    BRIGADEIRO(29, "Brigadeiro", 5.0, "file:src/imagens/brigadeiro.png", false),
    CARBONARA(30, "Carbonara", 31.0, "file:src/imagens/carbonara.png", false);

    private final int codigoPedido;
    private final String nome;
    private final double preco;
    private final String imagem;
    private final boolean ehEspecial;

    Cardapio(int codigoPedido, String nome, double preco, String imagem, boolean ehEspecial) {
        this.codigoPedido = codigoPedido;
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
        this.ehEspecial = ehEspecial;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }

    public boolean isEspecial() {
        return ehEspecial;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco + (ehEspecial ? " (Especial)" : "");
    }
}
