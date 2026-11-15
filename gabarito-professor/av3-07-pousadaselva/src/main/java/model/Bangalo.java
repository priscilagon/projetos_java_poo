package model;

public class Bangalo extends Acomodacao {
    public Bangalo(String identificacao) { super(identificacao); }

    @Override
    public double valorDiaria() { return 320.0; }
}
