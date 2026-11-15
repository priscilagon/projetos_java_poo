package model;

import java.time.LocalDate;

public class Limpeza extends AcaoAmbiental {
    public Limpeza(String local, LocalDate data, double duracaoHoras) { super(local, data, duracaoHoras); }

    @Override
    public double fatorImpacto() { return 1.0; }
}
