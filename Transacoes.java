import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transacoes implements TransacaoBase{

  private String descricao;
  private String data;
  private double valor;
  private String tipo;
  private Conta destino;
  private Conta conta;
  private Boleto boleto;


  public Transacoes(String data, double valor, String tipo, Conta destino, Conta conta){
    this.data = data;
    this.valor = valor;
    this.tipo = tipo;  
    this.destino = destino;
    this.conta = conta;
  }

  public String getDescricao() {
    return descricao;
  }
  public void setDescricao(String descricao){
    this.descricao = descricao;
  }
  public String getData() {
    return data;
  }
  public void setData(String data){
    this.data = data;
  }
  public double getValor() {
    return valor;
  }
  public void setValor(double valor){
    this.valor = valor;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo){
    this.tipo = tipo;
  }
  public Conta getDestino() {
    return destino;
  }
  public void setDestino(Conta destino){
    this.destino = destino;
  }
  public Conta getConta() {
    return conta;
  }
  public void setConta(Conta conta) {
    this.conta = conta;
  }
  public Boleto getBoleto() {
    return boleto;
  }
  public void setBoleto(Boleto boleto){
    this.boleto = boleto;
  }

  public void transacoesbase(double valor, Conta destino, Conta conta, String tipo){
    if(valor <= conta.getSaldo()){
      conta.setSaldo(conta.getSaldo() - valor);
      destino.setSaldo(destino.getSaldo() + valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\n%s de %.2fR$ realizada com sucesso!\n\n", tipo, valor);
    } else {
      System.out.printf("Saldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getSaldo());
    }
  }

  public void depositar(double valor, Conta destino ,Conta conta){
    conta.setSaldo(conta.getSaldo() + valor);
    conta.setDataUltimaMovimentacao(getDataAtual());
  }

  public void sacar(double valor, Conta conta){
  if(valor <= conta.getSaldo()){
      conta.setSaldo(conta.getSaldo() - valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\nSaque de %.2fR$ realizada com sucesso!\n\n", valor);
    } else {
      System.out.printf("Saldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getSaldo());
    }
  }

  public void transferir(double valor, String numeroconta, String agencia, Conta conta){
    transacoesbase(valor, destino, conta, "Transferência");
  }

  public void pix(double valor, String chavepix, Conta conta){
    transacoesbase(valor, destino , conta, "PIX");
  }

  public void boleto(double valor, String codigo, Conta conta){
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

//Método para obter a data atual formatada
  public static String getDataAtual() {
      LocalDate dataAtual = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return dataAtual.format(formatter);
  }

  public String toStrContaing() {
      return String.format("Data: %s. \nValor: %.2fR$\nTipo: %s. \nDestino: %s", getDataAtual(), valor, tipo, destino);
  }
}