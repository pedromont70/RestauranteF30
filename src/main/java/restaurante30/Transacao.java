package restaurante30;

public class Transacao {
    private String hora;
    private int numeroMesa;
    private String nomeCliente;
    private double totalPago;

    public Transacao(String hora, int numeroMesa, String nomeCliente, double totalPago) {
        this.hora = hora;
        this.numeroMesa = numeroMesa;
        this.nomeCliente = nomeCliente;
        this.totalPago = totalPago;
    }

    public String getHora() {
        return hora;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getTotalPago() {
        return totalPago;
    }
}