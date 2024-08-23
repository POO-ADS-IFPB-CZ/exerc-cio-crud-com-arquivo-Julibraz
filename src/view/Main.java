package view;

import dao.PessoaDao;
import model.Pessoa;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PessoaDao pessoaDao = new PessoaDao();

        System.out.println("********************************************************");
        System.out.println("****** Bem vindo ao sistema de cadastro de pessoas *****");
        System.out.println("********************************************************");

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n1 - Cadastrar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Deletar pessoa");
            System.out.println("0 - Sair\n");
            System.out.println("********************************************************");

            opcao = scan.nextInt(); //Le a opçao
            scan.nextLine(); //consumir o restante da linha para evitar problemas com nextLine

            switch (opcao) {
                case 1:
                    Pessoa pessoa = new Pessoa();
                    System.out.println("Informe o nome da pessoa a ser cadastrada: ");
                    pessoa.setNome(scan.nextLine());
                    System.out.println("Informe o email da pessoa a ser cadastrada: ");
                    pessoa.setEmail(scan.nextLine());
                    if (pessoaDao.salvarPessoa(pessoa)) {
                        System.out.println("Pessoa salva com sucesso!");
                    } else {
                        System.out.println("Falha ao salvar pessoa");
                    }
                    break;
                case 2:
                    pessoaDao.listarPessoas();
                    break;
                case 3:
                    System.out.println("Insira o email da pessoa que deseja deletar: ");
                    String email = scan.nextLine();
                    if (pessoaDao.deletarPessoa(email)) {
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("Falha ao deletar pessoa");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
