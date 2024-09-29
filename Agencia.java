public class Agencia{

  private String nome;
  private String cnpj;
  private String endereco;
  private String telefone;
  private String email;
  private String senha;
  private Banco banco;
  private String numero;

  public Agencia(Banco banco, String numero){
    this.banco = banco;
    this.numero = numero;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome){
    this.nome = nome;
  }
  public String getCnpj() {
    return cnpj;
  }
  public void setCnpj(String cnpj){
    this.cnpj = cnpj;
  }
  public String getEndereco() {
    return endereco;
  }
  public void setEndereco(String endereco){
    this.endereco = endereco;
  }
  public String getTelefone() {
    return telefone;
  }
  public void setTelefone(String telefone){
    this.telefone = telefone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha){
    this.senha = senha;
  }
  public void setBanco(Banco banco){
    this.banco = banco;
  }
  public Banco getBanco(){
    return banco;
  }
  public void setNumero(String numero){
    this.numero = numero;
  }
  public String getNumero(){
    return numero;
  }

  @Override
public String toString() {
     return String.format(numero);
}
}
