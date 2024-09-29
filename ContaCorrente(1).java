public class ContaCorrente extends Conta{

  private double limite;
  private double divida;
  private boolean status = false;


  public ContaCorrente(String numeroConta, Agencia agencia, String nomeCliente, double saldo, String senha,  String tipo, double limite){
    super(numeroConta, agencia, nomeCliente, saldo, senha, tipo);
    this.limite = limite;
  }

  public double getLimite() {
    return limite;
  }
  public void setLimite(double limite) {
    this.limite = limite;
  }
  public double getDivida() {
    return divida;
  }
  public void setDivida(double divida) {
    this.divida = divida;
  }

  public boolean getStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }

  public String toString(){    
    return String.format("Conta Corrente: %s", super.getNumeroConta());  
  }
}