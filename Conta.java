import java.util.List;
import java.util.ArrayList;

public abstract class Conta{

  private String numeroConta;
  private Agencia agencia;
  private Cliente cliente;
  private String nomeCliente;
  private double saldo;
  private String senha;
  private String dataAbertura;
  private String dataUltimaMovimentacao;
  private String tipo;
  private String senhaPix;
  List<String> chavePix = new ArrayList<String>();
  

  public Conta(String numeroConta, Agencia agencia, String nomeCliente, double saldo, String senha, String tipo){
    this.numeroConta = numeroConta;
    this.agencia = agencia;
    this.nomeCliente = nomeCliente;
    this.saldo = saldo;
    this.senha = senha;
    this.tipo = tipo;
  }

  public String getNumeroConta() {
    return numeroConta;
  }
  public void setNumeroConta(String numeroConta) {
    this.numeroConta = numeroConta;
  }
  public Agencia getAgencia() {
    return agencia;
  }
  public void setAgencia(Agencia agencia){
    this.agencia = agencia;
  }
  public String getNomeCliente() {
    return nomeCliente;
  }
  public void setNomeCliente(String nomeCliente){
    this.nomeCliente = nomeCliente;
  }
  public double getSaldo() {
    return saldo;
  }
  public void setSaldo(double saldo){
    this.saldo = saldo;
  }
  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha){
    this.senha = senha;
  }
  public String getDataAbertura() {
    return dataAbertura;
  }
  public void setDataAbertura(String dataAbertura){
    this.dataAbertura = dataAbertura;
  }
  public String getDataUltimaMovimentacao() {
    return dataUltimaMovimentacao;
  }
  public void setDataUltimaMovimentacao(String dataUltimaMovimentacao){
    this.dataUltimaMovimentacao = dataUltimaMovimentacao;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo){
    this.tipo = tipo;
  }
  public String getSenhaPix() {
    return senhaPix;
  }
  public void setSenhaPix(String senhaPix){
    this.senhaPix = senhaPix;
  }

  public List<String> getChavePix() {
    return chavePix;
  }
  // Método para adicionar uma chave pix à lista
  public void adicionarChavePix(String chavePix) {
    this.chavePix.add(chavePix);
  }

  // Método para remover uma chave pix da lista
  public void removerChavePix(String chavePix) {
    this.chavePix.remove(chavePix);
  }
  
  // Método para exibir dados da conta
  public void exibirDadosConta(Conta conta){
    System.out.println("Dados da conta: ");
    System.out.println("\n-----------------------------------------------");
    System.out.println("Nome do cliente: " + conta.getNomeCliente());
    System.out.println("Numero da conta " + conta.getNumeroConta());
    System.out.println("Agencia: " + conta.getAgencia());
    System.out.println("Tipo: " + conta.getTipo());
    System.out.println("chave Pix: " + conta.getChavePix());
    System.out.println("-----------------------------------------------\n");
  }
}