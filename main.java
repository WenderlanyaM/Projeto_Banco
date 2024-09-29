import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.List;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.format.DateTimeParseException;

 public class Main {

     public static String getDataAtual() {
         try {
             LocalDate dataAtual = LocalDate.now();
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             return dataAtual.format(formatter);
         } catch (Exception e) {
             System.out.println("Erro ao obter a data atual: " + e.getMessage());
             return "Data indisponível";
         }
     }

     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         List<Transacoes> extrato = new ArrayList<Transacoes>();
         List<Cliente> clientes = new ArrayList<Cliente>();
         List<Boleto> boletos = new ArrayList<Boleto>();

             Banco banco = new Banco("NuBank");
             Banco bancoCaixa = new Banco("Caixa");

             Agencia agenciaPadrao = new Agencia(banco, "0001");
             banco.setAgencia(agenciaPadrao);
             Agencia agenciaCaixa = new Agencia(bancoCaixa, "0222");
             bancoCaixa.setAgencia(agenciaCaixa);

             ContaCorrente contaPadrao = new ContaCorrente("00001111", agenciaPadrao, "João", 1000.0, "1234", "Conta Corrente", 2000);
             Cliente clientePadrao = new Cliente("João", "12345678901", "rua tal, numero tal, Bairro tal", "83966660000", "joao@gmail.com", 1000.0, "tal", contaPadrao);
             clientePadrao.getConta().adicionarChavePix("12345678901");
             clientes.add(clientePadrao);

             ContaPoupanca contaPadrao2 = new ContaPoupanca("11110000", agenciaCaixa, "Maria", 1000.0, "1234", "Conta Poupança");
             Cliente clientePadrao2 = new Cliente("Maria", "11234567890", "rua tal, numero tal, Bairro tal", "83900006666", "maria@gmail.com", 1000.0, "tal", contaPadrao2);
             clientePadrao2.getConta().adicionarChavePix("11234567890");
             clientes.add(clientePadrao2);

             while (true) {
                 while (true) {
                     System.out.println("SOMOS NUBANK");
                     String cpf = "";
                     boolean validCpf = false;
                     while (!validCpf) {
                         System.out.print("Digite seu CPF para realizar seu login ou cadastro: \n");
                         String cpfInput = scanner.nextLine();
                         if (cpfInput.length() == 11) {
                             try {
                                 Long cpftest = Long.parseLong(cpfInput);
                                 cpf = cpfInput;
                                 validCpf = true;
                             } catch (NumberFormatException e) {
                                 System.out.println("\n-----------------------------------------------");
                                 System.out.println("Atenção!!! CPF inválido. Por favor, insira apenas números.");
                                 System.out.println("-----------------------------------------------\n");
                             }
                         } else {
                             System.out.println("\n-----------------------------------------------");
                             System.out.println("Atenção!!! CPF deve conter exatamente 11 dígitos.");
                             System.out.println("-----------------------------------------------\n");
                         }
                     }

                 Cliente cliente = null;
                 for (Cliente c : clientes) {
                     if (c.getCpf().equals(cpf)) {
                         cliente = c;
                         break;
                     }
                 }

                 if (cliente != null) {
                     System.out.println("-----------------------------------------------");
                     System.out.println("Bem vindo de volta, " + cliente.getNome() + "!");
                     System.out.print("Digite sua senha: ");
                     String senha = scanner.nextLine();
                     System.out.print("-----------------------------------------------");

                     if (senha.equals(cliente.getConta().getSenha())) {
                         System.out.println("");
                         System.out.println("Acesso liberado!\n");
                         int n = 0;
                         while (n != 9) {
                             System.out.println("SERVIÇOS: \n");
                             System.out.println("1 - Consultar saldo.\n2 - Consultar extrato.\n3 - Depósito.\n4 - Transferência.\n5 - Saque.\n6 - Pix.\n7 - Pagamentos.\n8 - Ver perfil.\n9 - Sair\n");

                             try {
                                 n = scanner.nextInt();
                                 scanner.nextLine(); // Consumir o restante da linha
                             } catch (Exception e) {
                                 System.out.println("Entrada inválida. Por favor, digite um número.");
                                 scanner.nextLine(); // Limpar entrada inválida
                                 continue;
                             }

                             switch (n) {
                                 case 1: // Consultar saldo
                                     try {
                                         String saldoFormatado = String.format("%.2f", cliente.getConta().getSaldo());
                                         System.out.println("Seu saldo é de: " + saldoFormatado + "R$\n");
                                     } catch (Exception e) {
                                         System.out.println("Erro ao consultar saldo: " + e.getMessage());
                                     }
                                     break;

                                 case 2: // Consultar extrato
                                     try {
                                         System.out.println("Seu extrato em " + getDataAtual() + ": ");
                                         System.out.println("-----------------------------------------------");
                                         for (Transacoes t : extrato) {
                                             if (t.getTipo().equals("Depósito")) {
                                                 System.out.println("Data: " + getDataAtual());
                                                 System.out.printf("Valor: %.2fR$\nTipo: %s.\nConta destino: %s\n",
                                                         t.getValor(), t.getTipo(), t.getConta());
                                             } else {
                                                 System.out.println(t);
                                             }
                                             System.out.println("-----------------------------------------------");
                                         }
                                         System.out.println("");
                                     } catch (Exception e) {
                                         System.out.println("Erro ao consultar extrato: " + e.getMessage());
                                     }
                                     break;

                                 case 3: // Depositar
                                     try {
                                         System.out.print("Digite o valor a ser depositado: ");
                                         double valorDeposito = scanner.nextDouble();
                                         scanner.nextLine(); // Consumir o caractere de nova linha

                                         if (cliente.getConta().getTipo().equals("Conta Corrente")) {
                                             Transacoes deposito = new TransacoesCorrente(getDataAtual(), valorDeposito, "Depósito", cliente.getConta(), cliente.getConta());
                                             deposito.depositar(valorDeposito, cliente.getConta(), cliente.getConta());
                                             extrato.add(deposito);
                                             System.out.println("\nConcluído! Seu depósito foi realizado com sucesso!\n");
                                         } else if (cliente.getConta().getTipo().equals("Conta Poupança")) {
                                             Transacoes deposito = new TransacoesPoupanca(getDataAtual(), valorDeposito, "Depósito", cliente.getConta(), cliente.getConta());
                                             deposito.depositar(valorDeposito, cliente.getConta(), cliente.getConta());
                                             extrato.add(deposito);
                                             System.out.println("\nConcluído! Seu depósito foi realizado com sucesso!\n");
                                         } else {
                                             System.out.println("Conta não encontrada");
                                         }
                                     } catch (Exception e) {
                                         System.out.println("Erro ao realizar depósito: " + e.getMessage());
                                     }
                                     break;

                                     case 4://Transferência
                                     try {
                                         System.out.print("Digite o valor a ser transferido: ");
                                         double valorTransferencia = scanner.nextDouble();
                                         scanner.nextLine(); 
                                         System.out.print("Digite o numero da conta de destino: ");
                                         String destinoTransferencia = scanner.nextLine();
                                         System.out.print("Digite a agência sem o dígito: ");
                                         String guardaAgenciaTransferencia = scanner.nextLine();

                                         Cliente clienteDestinoTransferencia = null;
                                         for (Cliente c : clientes) {
                                             if (c.getConta().getNumeroConta().equals(destinoTransferencia) && c.getConta().getAgencia().getNumero().equals(guardaAgenciaTransferencia)) {
                                                 clienteDestinoTransferencia = c;
                                                 break;
                                             }
                                         }
                                         if (cliente.getConta().getTipo().equals("Conta Corrente") ){
                                             System.out.println("-----------------------------------------------");
                                             Transacoes transferencia = new TransacoesCorrente(getDataAtual(), valorTransferencia, "Transferência", clienteDestinoTransferencia.getConta(), cliente.getConta());
                                             transferencia.transferir(valorTransferencia, destinoTransferencia, guardaAgenciaTransferencia, cliente.getConta());
                                             extrato.add(transferencia);
                                             break;
                                         } else if (cliente.getConta().getTipo().equals("Conta Poupança")){
                                             Transacoes transferencia = new TransacoesPoupanca(getDataAtual(), valorTransferencia, "Transferência", clienteDestinoTransferencia.getConta(), cliente.getConta());
                                             transferencia.transferir(valorTransferencia, destinoTransferencia, guardaAgenciaTransferencia, cliente.getConta());
                                             extrato.add(transferencia);
                                             break;
                                         } else{
                                             System.out.println("Conta não encontrada");
                                         }
                                     } catch (Exception e) {
                                            System.out.println("Erro ao realizar Transferência: " + e.getMessage());
                                        }
                                     break;
                                     case 5://Saque
                                     try {
                                         System.out.print("Digite o valor a ser sacado: ");
                                         double valorSaque = scanner.nextDouble();
                                         scanner.nextLine(); // Consumir o restante da linha

                                         if (cliente.getConta().getTipo().equals("Conta Corrente")){
                                             Transacoes saque = new TransacoesCorrente(getDataAtual(), valorSaque, "Saque", cliente.getConta(), cliente.getConta());
                                             saque.sacar(valorSaque, cliente.getConta());
                                             extrato.add(saque);
                                             break;

                                         } else if (cliente.getConta().getTipo().equals("Conta Poupança")){
                                             Transacoes saque = new TransacoesPoupanca(getDataAtual(), valorSaque, "Saque", cliente.getConta(), cliente.getConta());
                                             saque.sacar(valorSaque, cliente.getConta());
                                             extrato.add(saque);
                                             break;
                                         } else{
                                             System.out.println("Conta não encontrada");
                                         }
                                      } catch (Exception e) {
                                                   System.out.println("Erro ao realizar saque: " + e.getMessage());
                                      }
                                      break;

                                     case 6://Pix
                                     try {
                                         System.out.print("Digite o valor a ser transferido: ");
                                         double valorPix = scanner.nextDouble();
                                         scanner.nextLine(); // Consumir o restante da linha
                                         System.out.print("Digite a chave de destino (CPF, Celular, Email, CNPJ, ...): ");
                                         String destinoPix = scanner.nextLine();

                                         Cliente clienteDestinoPix = null;
                                         for (Cliente c : clientes) {
                                           for(int i = 0; i < c.getConta().getChavePix().size(); i++){
                                             if (c.getConta().getChavePix().get(i).equals(destinoPix)) {
                                                 clienteDestinoPix = c;
                                                 break;
                                             }
                                           }
                                         }
                                         if (cliente.getConta().getTipo().equals("Conta Corrente")){
                                             Transacoes pix = new TransacoesCorrente(getDataAtual(), valorPix, "PIX", clienteDestinoPix.getConta(), cliente.getConta());
                                             pix.pix(valorPix, destinoPix, cliente.getConta());
                                             extrato.add(pix);
                                             break;
                                         } else if (cliente.getConta().getTipo().equals("Conta Poupança")){
                                             Transacoes pix = new TransacoesPoupanca(getDataAtual(), valorPix, "PIX", clienteDestinoPix.getConta(), cliente.getConta());
                                             pix.pix(valorPix, destinoPix, cliente.getConta());
                                             extrato.add(pix);
                                             break;
                                         } else{
                                             System.out.println("Conta não encontrada");
                                         }
                                      } catch (Exception e) {
                                                   System.out.println("Erro ao realisar pix: " + e.getMessage());
                                          }
                                      break;

                                     case 7://Boleto
                                     try {
                                         System.out.print("Digite o valor do boleto: ");
                                         double valorBoleto = scanner.nextDouble();
                                         scanner.nextLine(); // Consumir o restante da linha
                                         System.out.print("Digite o número do boleto: ");
                                         String destinoBoleto = scanner.nextLine();

                                         if (boletos.contains(destinoBoleto)) {
                                             System.out.println("\n-----------------------------------------------");
                                             System.out.println("Atenção, esse boleto já foi pago!");
                                             System.out.println("Por favor, recadastre os dados do boleto. Você será redirecionado(a) para tela de serviços.");
                                             System.out.println("-----------------------------------------------\n");
                                         } else {

                                             Boleto boletoatual = null;
                                             for (Boleto b : boletos) {
                                                 if (b.getCodigo().equals(destinoBoleto)) {
                                                     boletoatual = b;
                                                     break;
                                                 }
                                             }
                                             if (cliente.getConta().getTipo().equals("Conta Corrente")){
                                                 Transacoes boleto = new TransacoesCorrente(getDataAtual(), valorBoleto, "Boleto", boletoatual.getContaDestino(), cliente.getConta());
                                                 boleto.boleto(valorBoleto, destinoBoleto, cliente.getConta());
                                                 extrato.add(boleto);
                                                 break;
                                             } else if (cliente.getConta().getTipo().equals("Conta Poupança")){
                                                 Transacoes boleto = new TransacoesPoupanca(getDataAtual(), valorBoleto, "Boleto", boletoatual.getContaDestino(), cliente.getConta());
                                                 boleto.boleto(valorBoleto, destinoBoleto, cliente.getConta());
                                                 extrato.add(boleto);
                                                 break;
                                             } else{
                                                 System.out.println("Conta não encontrada");
                                             }
                                         }  
                                      } catch (Exception e) {
                                              System.out.println("Erro ao consultar boleto: " + e.getMessage());
                                          }
                                      break;

                                     case 8://Dados do Cliente
                                     try{
                                         cliente.exibirDadosCliente(cliente);
                                         cliente.getConta().exibirDadosConta(cliente.getConta());
                                      } catch (Exception e) {
                                          System.out.println("Erro ao consultar dados: " + e.getMessage());
                                          }
                                          break;
                                 case 9: // Sair
                                     System.out.println("-----------------------------------------------");
                                     System.out.println("Saindo, aguarde...");
                                     System.out.println("Você será redirecionado(a) para tela de login!");
                                     System.out.println("-----------------------------------------------");
                                     System.out.println("");
                                     break;

                                 default:
                                     System.out.println("Opção inválida!");
                             }
                         }
                     } else {
                         System.out.println("\n-----------------------------------------------");
                         System.out.println("Atenção!!! Senha incorreta.");
                         System.out.println("-----------------------------------------------\n");
                     }
                 } else {
                     System.out.println("");
                     System.out.println("Notamos que você ainda não faz parte da família NUBANK. Por favor, digite: \n");

                     // Cadastro do cliente
                     System.out.print("Seu nome completo: ");
                     String nome = lerNomeValido(scanner);

                     String rua, numero, bairro;
                     while (true) {
                         System.out.print("Digite sua rua: ");
                         rua = scanner.nextLine();
                         if (rua.isEmpty()) {
                             System.out.println("Atenção! Rua não pode ser vazia.");
                         } else {
                             break;
                         }
                     }

                     while (true) {
                         System.out.print("Digite o número da residência: ");
                         numero = scanner.nextLine();
                         if (numero.isEmpty()) {
                             System.out.println("Atenção! Número da residência não pode ser vazio.");
                         } else {
                             break;
                         }
                     }

                     while (true) {
                         System.out.print("Digite seu bairro: ");
                         bairro = scanner.nextLine();
                         if (bairro.isEmpty()) {
                             System.out.println("Atenção! Bairro não pode ser vazio.");
                         } else {
                             break;
                         }
                     }
                     String endereco = rua + ", " + numero + ", " + bairro;

                     System.out.print("Seu telefone: ");
                     String telefone = scanner.nextLine();

                     System.out.print("Seu email: ");
                     String email = scanner.nextLine();

                     System.out.print("Sua profissão: ");
                     String ocupacao = scanner.nextLine();

                     System.out.print("Sua renda: ");
                     double renda = scanner.nextDouble();
                     scanner.nextLine(); // Consumir o restante da linha

                     System.out.print("Sua senha: ");
                     String senha = scanner.nextLine();

                     System.out.print("Tipo de conta (1 - Conta Corrente, 2 - Conta Poupança): ");
                     int tipoConta = scanner.nextInt();
                     scanner.nextLine(); // Consumir o restante da linha

                     if (tipoConta == 1){
                         ContaCorrente contaNova = new ContaCorrente("00002222", agenciaPadrao, nome, 0.0, senha, "Conta Corrente", 700);
                         Cliente clienteC = new Cliente(nome, cpf, endereco, telefone, email, renda, ocupacao, contaNova);
                         clientes.add(clienteC);
                         System.out.println("-----------------------------------------------");
                         System.out.println("Parabéns, você agora faz parte da família NUBANK!");
                         System.out.println("Você será redirecionado(a) para tela de login!");
                         System.out.println("-----------------------------------------------\n");
                     } else if (tipoConta == 2){
                         ContaPoupanca contaNova = new ContaPoupanca("22220000", agenciaPadrao, nome, 0.0, senha, "Conta Poupança");
                         Cliente clienteP = new Cliente(nome, cpf, endereco, telefone, email, renda, ocupacao, contaNova);
                         clientes.add(clienteP);
                         System.out.println("-----------------------------------------------");
                         System.out.println("Parabéns, você agora faz parte da família NUBANK!");
                         System.out.println("Você será redirecionado(a) para tela de login!");
                         System.out.println("-----------------------------------------------\n");
                     } else{
                         System.out.println("Opção inválida!");
                     }
                 }
             }
          }    
     }

     public static String lerNomeValido(Scanner scanner) {
         while (true) {
             String nome = scanner.nextLine();
             if (nome.matches("[a-zA-Z\\s]+")) {
                 return nome;
             } else {
                 System.out.println("\n-----------------------------------------------");
                 System.out.print("Atenção!!! Caracteres inválidos. Digite novamente (somente letras e espaços são permitidos): ");
                 System.out.println("\n-----------------------------------------------\n");
             }
             System.out.print("Seu nome completo: ");
         }
     }
 }
