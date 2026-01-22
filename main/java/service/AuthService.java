package main.java.service;
import exceptions.*;
import main.java.model.User;
import main.java.repository.AuthRepository;
import java.sql.SQLException;

public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    // Validar cpf antes de autenticar ou registrar
    public void cpfIsValid(String cpf) throws InvalidCpfException { // Retornar true se o CPF for válido, false caso contrário
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {;
            throw new InvalidCpfException(cpf);
        }
    }

    public void loginAuthenticate(String cpf, String plainPassword)
            throws InvalidCpfException, AuthenticationFailedException, SQLException {

        try {
            cpfIsValid(cpf);

            var user = authRepository.findByCpf(cpf)
                    .orElseThrow(UserNotFoundInternalException::new);

            if (!main.java.util.PasswordUtil.checkPassword(plainPassword, user.getSenhaHash())) {
                throw new WrongPasswordInternalException();
            }

        } catch (UserNotFoundInternalException | WrongPasswordInternalException e) {
            // (INTERNAL → EXTERNAL)
            throw new AuthenticationFailedException();
        }
    }



    // antes de registrar, validar cpf e checar se já existe
    public void checkAlreadyExists(String cpf) throws SQLException, CpfAlreadyExistsException {
        var userOpt2 = authRepository.findByCpf(cpf);
        if (userOpt2.isPresent()) {
            throw new CpfAlreadyExistsException(cpf);
        }
    }

    // registrar usuario
    public void registerUser(String cpf, String plainPassword, User user ) throws SQLException, CpfAlreadyExistsException, InvalidCpfException { // Lógica para registrar um novo usuário
        cpfIsValid(cpf);
        checkAlreadyExists(cpf);

        String hash = main.java.util.PasswordUtil.plainToHash(plainPassword);
        user.setSenhaHash(hash);

        authRepository.saveUser(user);

}}








