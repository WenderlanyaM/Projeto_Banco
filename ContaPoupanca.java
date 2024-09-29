public class ContaPoupanca extends Conta{

  private double rendimento = 0.05;

  public ContaPoupanca(String numeroConta, Agencia agencia, String nomeCliente, double saldo, String senha, String tipo){    
    super(numeroConta, agencia, nomeCliente, saldo, senha, tipo);  
  }

  public double getRendimento() {
    return rendimento;
  }
  public void setRendimento(double rendimento){
    this.rendimento = rendimento;
  }

  
  public String toString(){    
    return String.format("Conta Poupança: %s", super.getNumeroConta());  
  }
}