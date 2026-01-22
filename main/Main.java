package main;

import main.java.view.AuthView;
import main.java.view.MenuPrincipalView;
import main.java.view.CasaView;
import main.java.view.ApartamentoView;
import main.java.view.TerrenoView;
import main.java.repository.AuthRepository;
import main.java.repository.CasaRepository;
import main.java.repository.ApartamentoRepository;
import main.java.repository.TerrenoRepository;
import main.java.service.AuthService;
import main.java.service.CasaService;
import main.java.service.ApartamentoService;
import main.java.service.TerrenoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AuthRepository authRepo = new AuthRepository();
        CasaRepository casaRepo = new CasaRepository();
        ApartamentoRepository aptRepo = new ApartamentoRepository();
        TerrenoRepository terrenoRepo = new TerrenoRepository();

        AuthService authService = new AuthService(authRepo);
        CasaService casaService = new CasaService(casaRepo);
        ApartamentoService apartamentoService = new ApartamentoService(aptRepo);
        TerrenoService terrenoService = new TerrenoService(terrenoRepo);

        AuthView authView = new AuthView(authService);
        CasaView casaView = new CasaView(casaService);
        ApartamentoView apartamentoView = new ApartamentoView(apartamentoService);
        TerrenoView terrenoView = new TerrenoView(terrenoService);

        MenuPrincipalView menu = new MenuPrincipalView(casaView, apartamentoView, terrenoView);

        // Fluxo principal (autenticação / menu)
        authView.menuLoginOuRegistro(scanner);
        menu.exibirMenuPrincipal(scanner);

        scanner.close();
    }
}