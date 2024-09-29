import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transacoes implements TransacaoBase{

  private boolean status = false;
  private double valorDivida;
  private String datainicial;
  private String datafinal;
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
  public boolean getStatus() {
    return status;
  }
  public void setStatus(boolean status){
    this.status = status;
  }
  public double getValorDivida() {
    return valorDivida;
  }
  public void setValorDivida(double valorDivida){
    this.valorDivida = valorDivida;
  }
  public String getDatainicial() {
    return datainicial;
  }
  public void setDatainicial(String datainicial){
    this.datainicial = datainicial;
  }
  public String getDatafinal() {
    return datafinal;
  }
  public void setDatafinal(String datafinal){
    this.datafinal = datafinal;
  }
  

  public void transacoesbase(double valor, Conta destino, Conta conta, String tipo){
    if(valor <= conta.getSaldo()){
      conta.setSaldo(conta.getSaldo() - valor);
      destino.setSaldo(destino.getSaldo() + valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\n%s de %.2fR$ realizada com sucesso!\n\n", tipo, valor);
    } else if (valor <= conta.getLimite() && valor > conta.getSaldo()){
        System.out.println("\nVocê esta usando o seu cheque especial!");
        status = true;
        conta.setStatus(true);
        LocalDate atual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        datainicial = atual.format(formatter);
        LocalDate dataFinal = atual.plusDays(30);
        datafinal = dataFinal.format(formatter);
        conta.setLimite(conta.getLimite() - valor);
        valorDivida = valorDivida + valor;
        conta.setDivida(conta.getDivida() + valor);
        destino.setSaldo(destino.getSaldo() + valor);
        conta.setDataUltimaMovimentacao(getDataAtual());
        System.out.printf("\n%s de %.2fR$ realizada com sucesso!\n\n", tipo, valor);
    } else {
      System.out.printf("\nSaldo insuficiente! \n" + "Limite disponível: %.2fR$ \n", conta.getLimite());
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
    } else if (valor <= conta.getLimite() && valor > conta.getSaldo()){
    System.out.println("\nVocê esta usando o seu cheque especial!");
    status = true;
    conta.setStatus(true);
    LocalDate atual = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    datainicial = atual.format(formatter);
    LocalDate dataFinal = atual.plusDays(30);
    datafinal = dataFinal.format(formatter);
    conta.setLimite(conta.getLimite() - valor);
    valorDivida = valorDivida + valor;
    conta.setDivida(conta.getDivida() + valor);
    conta.setDataUltimaMovimentacao(getDataAtual());
    System.out.printf("\nSaque de %.2fR$ realizada com sucesso!\n\n", valor);
  }else {
      System.out.printf("\nSaldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getSaldo());
    }
  }

  public void transferir(double valor, String numeroconta, String agencia, Conta conta){
    transacoesbase(valor, destino, conta, "Transferência");
  }

  public void pix(double valor, String chavepix, Conta conta){
    transacoesbase(valor, destino , conta, "PIX");
  }

  
  public void boleto(double valor, String codigo, Conta conta, Boleto boleto){
    if(boleto.isPago() == false){
      LocalDate hoje = LocalDate.now();
      if (boleto.getDataVencimento().isBefore(hoje)) {
         System.out.println("Boleto está vencido. Aplicando multa.");
          // Simulação de multa por atraso, ex: 10% do valor
         double valorComMulta = boleto.getValor() * 1.10;
         if (conta.getSaldo() >= valorComMulta) {
            conta.setSaldo(conta.getSaldo() - valorComMulta);
            boleto.pagar();
            System.out.println("\nBoleto vencido pago com multa. Valor total: " + valorComMulta);
          } else if (conta.getLimite() >= valorComMulta && valorComMulta > conta.getSaldo()){
             System.out.println("\nVocê esta usando o seu cheque especial!");
             status = true;
             conta.setStatus(true);
             LocalDate atual = LocalDate.now();
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             datainicial = atual.format(formatter);
             LocalDate dataFinal = atual.plusDays(30);
             datafinal = dataFinal.format(formatter);
             conta.setLimite(conta.getLimite() - valorComMulta);
             valorDivida = valorDivida + valorComMulta;
             conta.setDivida(conta.getDivida() + valorComMulta);
             boleto.pagar();
             System.out.println("\nBoleto vencido pago com multa. Valor total: " + valorComMulta);
             boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + valorComMulta);
             System.out.println("\nBoleto pago com sucesso!");
             conta.setDataUltimaMovimentacao(getDataAtual());
           }
          else {
            System.out.println("\nSaldo insuficiente para pagar o boleto vencido com multa.");
          }
      } 
      else {
          if (conta.getSaldo() >= boleto.getValor()) {
            conta.setSaldo(conta.getSaldo() - boleto.getValor());
            boleto.pagar();
            System.out.println("Boleto pago com sucesso. Valor: " + boleto.getValor());
          } else if (conta.getLimite() >= boleto.getValor() && boleto.getValor() > conta.getSaldo()){
              System.out.println("\nVocê esta usando o seu cheque especial!");
              status = true;
              conta.setStatus(true);
              LocalDate atual = LocalDate.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
              datainicial = atual.format(formatter);
              LocalDate dataFinal = atual.plusDays(30);
              datafinal = dataFinal.format(formatter);
              conta.setLimite(conta.getLimite() - boleto.getValor());
              valorDivida = valorDivida + boleto.getValor();
              conta.setDivida(conta.getDivida() + boleto.getValor());
              boleto.pagar();
              System.out.println("\nBoleto pago com sucesso. Valor: " + boleto.getValor());
              boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + boleto.getValor());
              conta.setDataUltimaMovimentacao(getDataAtual());
            }
          else {
            System.out.println("\nSaldo insuficiente para pagar o boleto.");
          }
      }
    } else {
      System.out.println("\nEsse boleto já foi pago.\n");
    }
    }

  public void pagarDivida(Conta conta){
    if(conta.getSaldo() >= conta.getDivida()*(0.23*30)){
      conta.setSaldo(conta.getSaldo() - (conta.getDivida()*(0.23*30)));
      valorDivida = 0;
      System.out.println("Divida paga com sucesso!");
    }
    else{
      System.out.println("Você não possui saldo suficiente para pagar a divida!" + "\nSaldo disponível: " + conta.getSaldo());
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