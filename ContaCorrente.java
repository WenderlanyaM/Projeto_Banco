public class ContaCorrente extends Conta{

  private double limite;


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

  
  public String toString(){    
    return String.format("Conta Corrente: %s", super.getNumeroConta());  
  }
}