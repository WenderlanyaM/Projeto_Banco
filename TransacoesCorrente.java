import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TransacoesCorrente extends Transacoes{

  private double taxa = 0.23;
  private boolean status = false;
  private double valorDivida;
  private Conta destino;
  private Boleto boleto;
  private String datainicial;
  private String datafinal;

  public TransacoesCorrente(String data, double valor, String tipo, Conta destino, Conta conta){
    super(data, valor, tipo, destino, conta);
  }
  public double getTaxa() {
    return taxa;
  }
  public void setTaxa(double taxa) {
    this.taxa = taxa;
  }
  public boolean getStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }

  public void transacoesbase(double valor, Conta destino, ContaCorrente conta, String tipo){
    if(valor <= conta.getSaldo()){
      conta.setSaldo(conta.getSaldo() - valor);
      destino.setSaldo(destino.getSaldo() + valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\n%s de %.2fR$ realizada com sucesso!\n", tipo, valor);
    } else if (valor <= conta.getLimite() && valor > conta.getSaldo()){
      System.out.println("Você esta usando o seu cheque especial!");
      status = true;
      LocalDate atual = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      datainicial = atual.format(formatter);
      LocalDate dataFinal = atual.plusDays(30);
      datafinal = dataFinal.format(formatter);
      conta.setLimite(conta.getLimite() - valor);
      valorDivida = valorDivida + valor;
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\n%s de %.2fR$ realizada com sucesso!\n\n", tipo, valor);
    } else {
      System.out.printf("Saldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getLimite());
    }
  }

  public void depositar(double valor, Conta destino ,ContaCorrente conta){
    conta.setSaldo(conta.getSaldo() + valor);
    conta.setDataUltimaMovimentacao(getDataAtual());
  }

  public void sacar(double valor, ContaCorrente conta){
    if(valor <= conta.getSaldo()){
      conta.setSaldo(conta.getSaldo() - valor);
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\nSaque de %.2fR$ realizada com sucesso!\n", valor);
    } else if (valor <= conta.getLimite() && valor > conta.getSaldo()){
      System.out.println("Você esta usando o seu cheque especial!");
      status = true;
      LocalDate atual = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      datainicial = atual.format(formatter);
      LocalDate dataFinal = atual.plusDays(30);
      datafinal = dataFinal.format(formatter);
      conta.setLimite(conta.getLimite() - valor);
      valorDivida = valorDivida + valor;
      conta.setDataUltimaMovimentacao(getDataAtual());
      System.out.printf("\nSaque de %.2fR$ realizada com sucesso!\n\n", valor);
    } else {
      System.out.printf("Saldo insuficiente! \nVocê tem o limite de %.2fR$", conta.getLimite());
    }
  }

  public void transferir(double valor, String numeroconta, String agencia, ContaCorrente conta){
    transacoesbase(valor, destino, conta, "Transferência");
  }

  public void pix(double valor, String chavepix, ContaCorrente conta){
    transacoesbase(valor, destino , conta, "PIX");
  }

  public void boleto(double valor, String codigo, ContaCorrente conta){
    if (codigo.equals(boleto.getCodigo())){
      LocalDate hoje = LocalDate.now();
      if (boleto.getDataVencimento().isBefore(hoje)) {
         System.out.println("Boleto está vencido. Aplicando multa.");
          // Simulação de multa por atraso, ex: 10% do valor
         double valorComMulta = boleto.getValor() * 1.10;
         if (conta.getSaldo() >= valorComMulta) {
            conta.setSaldo(conta.getSaldo() - valorComMulta);
            System.out.println("Boleto vencido pago com multa. Valor total: " + valorComMulta);
            boleto.pagar();
            boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + valorComMulta);
            System.out.println("Boleto pago com sucesso!");
            conta.setDataUltimaMovimentacao(getDataAtual());
          } else if (conta.getLimite() >= valorComMulta && valorComMulta > conta.getSaldo()){
            System.out.println("Você esta usando o seu cheque especial!");
            status = true;
            LocalDate atual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            datainicial = atual.format(formatter);
            LocalDate dataFinal = atual.plusDays(30);
            datafinal = dataFinal.format(formatter);
            conta.setLimite(conta.getLimite() - valorComMulta);
            valorDivida = valorDivida + valorComMulta;
            boleto.pagar();
            System.out.println("Boleto vencido pago com multa. Valor total: " + valorComMulta);
            boleto.getContaDestino().setSaldo(boleto.getContaDestino().getSaldo() + valorComMulta);
            System.out.println("Boleto pago com sucesso!");
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
          } else if (conta.getLimite() >= boleto.getValor() && boleto.getValor() > conta.getSaldo()){
            System.out.println("Você esta usando o seu cheque especial!");
            status = true;
            LocalDate atual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            datainicial = atual.format(formatter);
            LocalDate dataFinal = atual.plusDays(30);
            datafinal = dataFinal.format(formatter);
            conta.setLimite(conta.getLimite() - boleto.getValor());
            valorDivida = valorDivida + boleto.getValor();
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

  public void calculaTaxa(){
    if (status = true){
      System.out.printf("Data inicial: %s\n", datainicial);
      System.out.println("Valor da divida: " + valorDivida);
      System.out.println("Taxa de juros: " + taxa);
      System.out.println("Valor da taxa: " + (valorDivida * (taxa*30)));
      System.out.println("Valor total da divida: " + (valorDivida + (valorDivida * (taxa*30)))+ "até a data: " + datafinal);
    }
    else{
      System.out.println("Você não possui divida!");
    }
  }

  Scanner input = new Scanner(System.in);
  public void pagarDivida(ContaCorrente conta){
    if (status = true){
      System.out.println("Você possui divida!");
      System.out.println("Valor da divida: " + valorDivida);
      System.out.println("Valor total da divida: " + (valorDivida + (valorDivida * (taxa*30)))+ "até a data: " + datafinal);
      System.out.println("Deseja pagar a divida? (S/N)");
      String resposta = input.nextLine();
      if (resposta.equals("S")){
        conta.setSaldo(conta.getSaldo() - valorDivida);
        valorDivida = 0;
        System.out.println("Divida paga com sucesso!");
      }
      else{
        System.out.println("Divida não paga!");
      }
    }
    else{
      System.out.println("Você não possui divida!");
    }
  }


  public String toString() {
      return String.format("Data: %s. \nValor: %.2fR$\nTipo: %s. \nDestino: %s", getDataAtual(), getValor(), getTipo(), getDestino());
  }
}