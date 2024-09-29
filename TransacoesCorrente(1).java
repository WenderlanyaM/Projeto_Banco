import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TransacoesCorrente extends Transacoes{

  private boolean status = false;
  private double valorDivida;

  public TransacoesCorrente(String data, double valor, String tipo, Conta destino, Conta conta){
    super(data, valor, tipo, destino, conta);
  }
  public boolean getStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }
  public double getValorDivida() {
    return valorDivida;
  }
  public void setValorDivida(double valorDivida){
    this.valorDivida = valorDivida;
  }

  public String toString() {
      return String.format("Data: %s. \nValor: %.2fR$\nTipo: %s. \nDestino: %s", getDataAtual(), getValor(), getTipo(), getDestino());
  }
}