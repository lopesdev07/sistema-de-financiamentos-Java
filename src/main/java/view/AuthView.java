package view;

import exceptions.AuthenticationFailedException;
import exceptions.CpfAlreadyExistsException;
import exceptions.InvalidCpfException;
import model.User;
import service.AuthService;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthView {
    private final AuthService service;
    public AuthView(AuthService service) {
        this.service = service;
    }

    public void menuLoginOuRegistro(Scanner scanner) { //cria loop que só se desfaz com o login true
        boolean autenticado = false;
        while (!autenticado) {
            try {
                System.out.println("--- Bem-vindo ao Sistema de Autenticação ---");
                System.out.println("Antes de navegar pelo sistema, por favor faça login ou registre-se caso seja o primeiro acesso.");
                System.out.println("1. Login");
                System.out.println("2. Registrar-se");
                System.out.print("Escolha uma opção:");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch(opcao) {
                    case 1:
                        autenticado = telaLogin(scanner);
                        break;
                    case 2:
                        telaRegistro(scanner);
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
            }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número válido");
                scanner.nextLine();
            }}}

    public boolean telaLogin(Scanner scanner) {
        try {
            System.out.println("--- Tela de Login ---");
            System.out.println("Por favor, insira seu CPF e senha para entrar.");
            System.out.print("Digite seu CPF:");
            String cpf = scanner.nextLine();
            System.out.print("Digite sua senha:");
            String senha = scanner.nextLine();
            service.loginAuthenticate(cpf, senha);
            return true;

        }
        catch (InvalidCpfException e) {
            System.out.println(e.getMessage());
            return false;
        }
        catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados. Tente novamente.");
            return false;
        }
        catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void telaRegistro(Scanner scanner) {
        boolean registrado = false;
        while (!registrado) {
            try {
            System.out.println("--- Tela de registro ---");
            System.out.println("Por favor, insira seu CPF e senha para se registrar.");
            System.out.print("Digite seu CPF:");
            String cpf = scanner.nextLine();
            System.out.print("Digite sua senha:");
            String senha = scanner.nextLine();
            int userID = 0;

            User novoUsuario = new User(userID, cpf, senha);

            service.registerUser(cpf, senha, novoUsuario);
            System.out.println("Registro bem-sucedido! Agora você pode fazer login.");
            registrado = true;
        } catch (CpfAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCpfException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados. Tente novamente.");
        }
    }}
}





