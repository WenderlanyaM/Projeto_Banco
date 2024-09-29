import java.time.LocalDate;
public class TransacoesPoupanca extends Transacoes implements Rendimento{

  private double rendimento = 0.05;
  private Conta destino;

  public TransacoesPoupanca(String data, double valor, String tipo, Conta destino, Conta conta){
    super(data, valor, tipo, destino, conta);
  }

  public double getRendimento() {
    return rendimento;
  }
  public void setRendimento(double rendimento){
    this.rendimento = rendimento;
  }

  public void aplicarRendimento(double valor, Conta conta) {
    LocalDate dataAtual = LocalDate.now();
    LocalDate primeiroDiaMesAtual = dataAtual.withDayOfMonth(1);
    if (dataAtual.isEqual(primeiroDiaMesAtual)) {
      conta.setSaldo(conta.getSaldo() + (valor * rendimento));
    }
  }

  public String toString() {
      return String.format("Data: %s. \nValor: %.2fR$\nTipo: %s. \nDestino: %s", getDataAtual(), getValor(), getTipo(), getDestino());
  }
}