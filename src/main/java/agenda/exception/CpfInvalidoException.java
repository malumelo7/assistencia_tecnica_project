package agenda.exception;

public class CpfInvalidoException extends Exception {
    public CpfInvalidoException(final String cpf) {
        super("CPF: " + cpf + " inválido");
    }
}
