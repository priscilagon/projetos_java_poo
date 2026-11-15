package model;

import java.time.LocalDate;

public class EducacaoAmbiental extends AcaoAmbiental {
    public EducacaoAmbiental(String local, LocalDate data, double duracaoHoras) { super(local, data, duracaoHoras); }

    @Override
    public double fatorImpacto() { return 1.2; }
}
