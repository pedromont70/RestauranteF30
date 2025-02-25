package restaurante30.atendimento;

public enum Cardapio {
    PIZZA(30.0, 1, true),
    HAMBURGUER(25.0, 2, true),
    COCA_ZERO_500ML(5.0, 3, false),
    PASTELZINHO_6UND(15.0, 4, false),
    CAMARAO_EMPANADO(20.0, 5, false),
    PAO_DE_ALHO(10.0, 6, false),
    BOLINHO_DE_BACALHAU(18.0, 6, false),
    SUSHI_20UND(14.0, 7, true),
    LASANHA_BOLONHESA(35.0, 8, false),
    FILET_MIGNON(50.0, 9, false),
    RISOTO_DE_FUNGHI(38.0, 10, false),
    PEIXE_GRELHADO(40.0, 11, false),
    FRANGO_PARMEGIANA(28.0, 12, false),
    ESPAGUETE_ALHO_E_OLEO(25.0, 13, false),
    MOQUECA_DE_ARRAIA(45.0, 14, false),
    CHURRASCO_GRELHADO(42.0, 15, false),
    SALADA_VEGETARIANA(12.0, 16, false),
    MACARRAO_COM_QUEIJO(22.0, 17, false),
    BATATA_RUSTICA(12.0, 18, false),
    ARROZ_INTEGRAL(9.0, 19, false),
    LEGUMES_NO_VAPOR(10.0, 20, false),
    POLENTA_FRITA(11.0, 21, false),
    AGUA_COM_GAS_500ML(5.0, 22, false),
    SUCO_DE_LARANJA_300ML(7.0, 23, true),
    CAIPIRINHA_300ML(20.0, 24, true),
    CERVEJA_IPA_500ML(12.0, 25, false),
    REFRIGERANTE_GUARANA_500ML(6.0, 26, false),
    CAFE_COM_LEITE_200ML(5.0, 27, true),
    TORTA_DE_LIMAO(15.0, 28, false),
    BABA_DE_MOCA(12.0, 29, false),
    PAVE_DE_CHOCOLATE(14.0, 30, false);

    private double preco;
    private int codigo;
    private boolean ehEspecial; //Se vai ser preparado por um cozinheiro especial

    Cardapio(double preco, int codigo, boolean ehEspecial) {
        this.preco = preco;
        this.codigo = codigo;
        this.ehEspecial = ehEspecial;
    }

    public double getPreco() {
        return preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean getEhEspecial(){
        return ehEspecial;
    }
}