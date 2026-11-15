package model;

import java.time.LocalDate;

public class Plantio extends AcaoAmbiental {
    public Plantio(String local, LocalDate data, double duracaoHoras) { super(local, data, duracaoHoras); }

    @Override
    public double fatorImpacto() { return 1.5; } // plantio de mudas tem maior peso ambiental
}
