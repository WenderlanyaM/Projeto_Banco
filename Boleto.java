import java.time.LocalDate;

public class Boleto {
    private Conta contaDestino;
    private String codigo;
    private double valor;
    private LocalDate dataVencimento;
    private boolean pago;

    public Boleto(String codigo, double valor, LocalDate dataVencimento) {
        this.codigo = codigo;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.pago = false;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setConta(Conta contaDestino){
        this.contaDestino = contaDestino;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void pagar() {
        this.pago = true;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "codigo='" + codigo + '\'' +
                ", valor=" + valor +
                ", dataVencimento=" + dataVencimento +
                ", pago=" + pago +
                '}';
    }
}
