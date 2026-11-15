package model;

/** Interface: define a geração de um certificado textual para o voluntário. */
public interface GeradorCertificado {
    String gerar(Voluntario voluntario, double horasTotais);
}
