package agenda.exception;

import java.sql.Time;

public class ForaHorarioComercialException extends Exception{
    public ForaHorarioComercialException(final Time hora) {
        super("Assistência não funciona de " + hora + " horas.");
    }
}
