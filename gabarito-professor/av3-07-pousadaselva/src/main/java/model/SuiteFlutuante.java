package model;

public class SuiteFlutuante extends Acomodacao {
    public SuiteFlutuante(String identificacao) { super(identificacao); }

    @Override
    public double valorDiaria() { return 550.0; }
}
