package main.java.view;

import exceptions.AuthenticationFailedException;
import exceptions.CpfAlreadyExistsException;
import exceptions.InvalidCpfException;
import main.java.model.User;
import main.java.service.AuthService;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthView {
    private final AuthService service;


    public AuthView(AuthService service) {
        this.service = service;
    }


    public void menuLoginOuRegistro(Scanner scanner) { //cria loop que só se desfaz com o login bem sucedido
        boolean autenticado = false;
        while (!autenticado) {
            try {
                System.out.println("--- Bem-vindo ao Sistema de Autenticação ---");
                System.out.println("Antes de navegar pelo sistema, por favor faça login ou registre-se caso seja o primeiro acesso.");
                System.out.println("1. Login");
                System.out.println("2. Registrar");
                System.out.println("Escolha uma opção:");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch(opcao) {
                    case 1:
                        autenticado = telaLogin(scanner);
                        break;
                    case 2:
                        scanner.nextLine();
                        telaRegistro(scanner);
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
            }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Por favor, insira um número válido");
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
        catch(InvalidCpfException e) {
            System.err.println(e.getMessage());
            return false;
        }
        catch(SQLException e) {
            System.err.println("Erro ao acessar o banco de dados. Tente novamente.");
            return false;
        }
        catch (AuthenticationFailedException e) {
            System.err.println(e.getMessage());
            return false;
        }
         // Retornar true se o login for bem-sucedido, false/nao alterar caso contrário
    }

    public void telaRegistro(Scanner scanner) {
        try {
            System.out.println("--- Tela de registro ---");
            System.out.println("Por favor, insira seu CPF e senha para se registrar.");
            System.out.print("Digite seu CPF:");
            String cpf = scanner.nextLine();
            System.out.print("Digite sua senha:");
            String senha = scanner.nextLine();

            User novoUsuario = new User(cpf, senha);

            service.registerUser(cpf, senha, novoUsuario);
            System.out.println("Registro bem-sucedido! Agora você pode fazer login.");
        } catch (CpfAlreadyExistsException e) {
            System.err.println(e.getMessage());
        } catch (InvalidCpfException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados. Tente novamente.");
        }
    }}





