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


    public boolean cpfIsValid(String cpf) {
        if (cpf != null && cpf.length() == 11 && cpf.matches("\\d+")) {
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
                    .orElseThrow(UserNotFoundException::new);

            if (!util.PasswordUtil.checkPassword(plainPassword, user.getPasswordHash())) {
                throw new InvalidPasswordException();
            }

            Session.login(user.getUserId());

        } catch (UserNotFoundException | InvalidPasswordException e) {
            throw new AuthenticationFailedException();
        }
    }

    public void checkAlreadyExists(String cpf) throws SQLException, CpfAlreadyRegisteredException {
        var userOpt2 = authRepository.findByCpf(cpf);
        if (userOpt2.isPresent()) {
            throw new CpfAlreadyRegisteredException(cpf);
        }
    }

    public void registerUser(String cpf, String plainPassword, User user ) throws SQLException, CpfAlreadyRegisteredException, InvalidCpfException {
        if (!cpfIsValid(cpf)) {
            throw new InvalidCpfException(cpf);
        }
        checkAlreadyExists(cpf);

        String hash = util.PasswordUtil.plainToHash(plainPassword);
        user.setPasswordHash(hash);

        authRepository.saveUser(user);

    }}