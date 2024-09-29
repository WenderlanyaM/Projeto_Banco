public interface TransacaoBase{

  public void transacoesbase(double valor, Conta destino, Conta conta, String tipo);

  public void depositar(double valor,Conta depositar, Conta conta);

  public void sacar(double valor, Conta conta);

  public void transferir(double valor, String numeroconta, String agencia, Conta conta);

  public void pix(double valor, String chavepix, Conta conta);

  public void boleto(double valor, String codigo, Conta conta, Boleto boleto);

}