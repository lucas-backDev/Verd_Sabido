package verde_sabido;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
    
    public RegraNegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}