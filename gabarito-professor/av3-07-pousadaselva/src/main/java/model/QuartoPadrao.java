package model;

public class QuartoPadrao extends Acomodacao {
    public QuartoPadrao(String identificacao) { super(identificacao); }

    @Override
    public double valorDiaria() { return 180.0; }
}
