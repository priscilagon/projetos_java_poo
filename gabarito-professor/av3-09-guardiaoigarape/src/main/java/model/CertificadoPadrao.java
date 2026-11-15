package model;

/** Implementação padrão de emissão de certificado (texto simples). */
public class CertificadoPadrao implements GeradorCertificado {
    @Override
    public String gerar(Voluntario voluntario, double horasTotais) {
        return "=== CERTIFICADO DE VOLUNTARIADO ===\n"
                + "Certificamos que " + voluntario.getNome()
                + " participou de ações de proteção aos igarapés de Manaus,\n"
                + "totalizando " + horasTotais + " horas de voluntariado ambiental.\n"
                + "Projeto Guardião do Igarapé.";
    }
}
