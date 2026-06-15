package service;

import exceptions.*;
import model.User;
import repository.AuthRepository;
import java.sql.SQLException;

public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    // Validar cpf antes de autenticar ou registrar
    public boolean cpfIsValid(String cpf) {
        if (cpf != null && cpf.length() == 11 && cpf.matches("\\d+")) {;
            return true;
        }
        return false;
    }

    public void loginAuthenticate(String cpf, String plainPassword) throws InvalidCpfException, AuthenticationFailedException, SQLException {

        try {
            if (!cpfIsValid(cpf)) {
                throw new InvalidCpfException(cpf);
            }

            var user = authRepository.findByCpf(cpf)
                    .orElseThrow(UserNotFoundInternalException::new);

            if (!util.PasswordUtil.checkPassword(plainPassword, user.getSenhaHash())) {
                throw new WrongPasswordInternalException();
            }

            Sessao.login(user.getUserId());

        } catch (UserNotFoundInternalException | WrongPasswordInternalException e) {
            // (INTERNAL → EXTERNAL)
            throw new AuthenticationFailedException();
        }
    }

    // Verificar se o cpf já existe antes de registrar
    public void checkAlreadyExists(String cpf) throws SQLException, CpfAlreadyExistsException {
        var userOpt2 = authRepository.findByCpf(cpf);
        if (userOpt2.isPresent()) {
            throw new CpfAlreadyExistsException(cpf);
        }
    }

    // registrar usuario
    public void registerUser(String cpf, String plainPassword, User user ) throws SQLException, CpfAlreadyExistsException, InvalidCpfException { // Lógica para registrar um novo usuário
        if (!cpfIsValid(cpf)) {
            throw new InvalidCpfException(cpf);
        }
        checkAlreadyExists(cpf);

        String hash = util.PasswordUtil.plainToHash(plainPassword);
        user.setSenhaHash(hash);

        authRepository.saveUser(user);

}}








