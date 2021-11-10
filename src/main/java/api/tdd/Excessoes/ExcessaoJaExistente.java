package api.tdd.Excessoes;

public class ExcessaoJaExistente extends RuntimeException {
    public ExcessaoJaExistente(String msg) {
        super(msg);
    }
}
