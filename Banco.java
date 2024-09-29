public class Banco{
  private String nome;
  private String cnpj;
  private Agencia agencia;
  private String senha;
  private Conta[] contas;
  private Cliente[] clientes;
  private ContaCorrente[] contasCorrente;
  private ContaPoupanca[] contasPoupanca;
  private int contadorContas;
  private int contadorContasCorrente;
  private int contadorContasPoupanca;

  public Banco(String nome){
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getCnpj() {
    return cnpj;
  }
  public void setCnpj(String cnpj){
    this.cnpj = cnpj;
  }
  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha){
    this.senha = senha;
  }
  public Agencia getAgencia() {
    return agencia;
  }
  public void setAgencia(Agencia agencia){
    this.agencia = agencia;
  }
  public Conta[] getContas() {
    return contas;
  }
  public void setContas(Conta[] contas){
    this.contas = contas;
  }
  public Cliente[] getClientes() {
    return clientes;
  }
  public void setClientes(Cliente[] clientes){
    this.clientes = clientes;
  }
  public ContaCorrente[] getContasCorrente() {
    return contasCorrente;
  }
  public void setContasCorrente(ContaCorrente[] contasCorrente){
    this.contasCorrente = contasCorrente;
  }
  public ContaPoupanca[] getContasPoupanca() {
    return contasPoupanca;
  }
  public void setContasPoupanca(ContaPoupanca[] contasPoupanca){
    this.contasPoupanca = contasPoupanca;
  }
  public int getContadorContas() {
    return contadorContas;
  }
  public void setContadorContas(int contadorContas){
    this.contadorContas = contadorContas;
  }
  public int getContadorContasCorrente() {
    return contadorContasCorrente;
  }
  public void setContadorContasCorrente(int contadorContasCorrente){
    this.contadorContasCorrente = contadorContasCorrente;
  }
  public int getContadorContasPoupanca() {
    return contadorContasPoupanca;
  }
  public void setContadorContasPoupanca(int contadorContasPoupanca){
    this.contadorContasPoupanca = contadorContasPoupanca;
  }

  // Método para adicionar uma conta à lista
  public void adicionarConta(Conta conta){
    this.contas[this.contadorContas] = conta;
    this.contadorContas++;
  }
  // Método para adicionar uma conta corrente à lista
  public void adicionarContaCorrente(ContaCorrente contaCorrente){
    this.contasCorrente[this.contadorContasCorrente] = contaCorrente;
    this.contadorContasCorrente++;
  }
  // Método para adicionar uma conta poupança à lista
  public void adicionarContaPoupanca(ContaPoupanca contaPoupanca){
    this.contasPoupanca[this.contadorContasPoupanca] = contaPoupanca;
    this.contadorContasPoupanca++;
  }
  // Método para remover uma conta da lista
  public void removerConta(Conta conta){
    for (int i = 0; i < this.contas.length; i++) {
      if (this.contas[i] == conta) {
        this.contas[i] = null;
        break;
      }
    }
  }
  // Método para remover uma conta corrente da lista
  public void removerContaCorrente(ContaCorrente contaCorrente){
    for (int i = 0; i < this.contasCorrente.length; i++) {
      if (this.contasCorrente[i] == contaCorrente) {
        this.contasCorrente[i] = null;
        break;
      }
    }
  }
  // Método para remover uma conta poupança da lista
  public void removerContaPoupanca(ContaPoupanca contaPoupanca){
    for (int i = 0; i < this.contasPoupanca.length; i++) {
      if (this.contasPoupanca[i] == contaPoupanca) {
        this.contasPoupanca[i] = null;
        break;
      }
    }
  }
  // Método para listar todas as contas
  public void listarContas(){
    for (int i = 0; i < this.contas.length; i++) {
      if (this.contas[i] != null) {
        System.out.println(this.contas[i]);
      }
    }
  }
  // Método para listar todas as contas corrente
  public void listarContasCorrente(){
    for (int i = 0; i < this.contasCorrente.length; i++) {
      if (this.contasCorrente[i] != null) {
        System.out.println(this.contasCorrente[i]);
      }
    }
  }
  // Método para listar todas as contas poupança
  public void listarContasPoupanca(){
    for (int i = 0; i < this.contasPoupanca.length; i++) {
      if (this.contasPoupanca[i] != null) {
        System.out.println(this.contasPoupanca[i]);
      }
    }
  }

}