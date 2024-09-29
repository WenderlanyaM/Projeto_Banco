import java.time.LocalDate;
public class TransacoesPoupanca extends Transacoes implements Rendimento{

  private double rendimento = 0.05;
  private Conta destino;
  private Boleto boleto;

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

    public void depositar(double valor, Conta destino ,ContaPoupanca conta){
      conta.setSaldo(conta.getSaldo() + valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
    }

    public void sacar(double valor, ContaPoupanca conta){
    if(valor <= conta.getSaldo()){
        conta.setSaldo(conta.getSaldo() - valor);
        conta.setDataUltimaMovimentacao(getDataAtual());
        System.out.printf("\nSaque de %.2fR$ realizada com sucesso!\n\n", valor);
      } else {
        System.out.printf("Saldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getSaldo());
      }
    }

    public void transferir(double valor, String numeroconta, String agencia, ContaPoupanca conta){
      transacoesbase(valor, destino, conta, "Transferência");
    }

    public void pix(double valor, String chavepix, ContaPoupanca conta){
      transacoesbase(valor, destino , conta, "PIX");
    }

    public void boleto(double valor, String codigo, ContaPoupanca conta){
      if (codigo.equals(boleto.getCodigo())){
        LocalDate hoje = LocalDate.now();
        if (boleto.getDataVencimento().isBefore(hoje)) {
           System.out.println("Boleto está vencido. Aplicando multa.");
            // Simulação de multa por atraso, ex: 10% do valor
           double valorComMulta = boleto.getValor() * 1.10;
           if (conta.getSaldo() >= valorComMulta) {
              conta.setSaldo(conta.getSaldo() - valorComMulta);
              boleto.pagar();
              System.out.println("Boleto vencido pago com multa. Valor total: " + valorComMulta);
              boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + valorComMulta);
              conta.setDataUltimaMovimentacao(getDataAtual());
            } 
            else {
              System.out.println("Saldo insuficiente para pagar o boleto vencido com multa.");
            }
        } 
        else {
            if (conta.getSaldo() >= boleto.getValor()) {
              conta.setSaldo(conta.getSaldo() - boleto.getValor());
              boleto.pagar();
              System.out.println("Boleto pago com sucesso. Valor: " + boleto.getValor());
              boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + boleto.getValor());
              conta.setDataUltimaMovimentacao(getDataAtual());
            } 
            else {
              System.out.println("Saldo insuficiente para pagar o boleto.");
            }
        }
      }
      else{
        System.out.println("Código do boleto não encontrado!");
      }
    }
  
  public String toString() {
      return String.format("Data: %s. \nValor: %.2fR$\nTipo: %s. \nDestino: %s", getDataAtual(), getValor(), getTipo(), getDestino());
  }
}