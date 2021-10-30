package agenda.exception;

public class HorarioOcupadoException extends Exception {
    public HorarioOcupadoException() {
        super("Este horário não está disponível.");
    }
}
