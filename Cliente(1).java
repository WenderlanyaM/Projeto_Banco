public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private double renda;
    private String ocupacao;
    private Conta conta;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    // Construtor
    public Cliente(String nome, String cpf, String endereco, String telefone, String email, double renda, String ocupacao, Conta conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.renda = renda;
        this.ocupacao = ocupacao;
        this.conta = conta;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getRenda() {
        return renda;
    }
    public void setRenda(double renda) {
        this.renda = renda;
    }
    public String getOcupacao() {
        return ocupacao;
    }
    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }
    public void setContaCorrente(ContaCorrente contaCorrente){
        this.contaCorrente = contaCorrente;
    }
    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }
    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public void exibirDadosCliente(Cliente cliente) {
        System.out.println("Seu dados: ");
        System.out.println("\n-----------------------------------------------");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Profissão: " + cliente.getOcupacao());
        System.out.println("Renda: " + cliente.getRenda());
        System.out.println("-----------------------------------------------\n");
    }
}