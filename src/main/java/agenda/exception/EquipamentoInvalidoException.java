package agenda.exception;

public class EquipamentoInvalidoException extends Exception{
    public EquipamentoInvalidoException () {
        super("Nossas assistências não consertam esse tipo de equipamento.");
    }
}
