package agenda.exception;

public class IdNaoEncontradoException extends Exception{
    public IdNaoEncontradoException() {
        super("O ID solicitado não foi encontrado");
    }
}
