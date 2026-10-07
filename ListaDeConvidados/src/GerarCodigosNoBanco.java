import Codigos.CodigosGerados;
import Codigos.CodigosRepository;

public class GerarCodigosNoBanco {
    public static void main(String[] args) {
        CodigosRepository codigosRepository = new CodigosRepository();
        CodigosGerados codigosGerados = new CodigosGerados();
        int contagem = codigosRepository.contarCodigos();
        if (contagem > 0) {
            System.out.println("Códigos ja foram gerados no banco");
        } else {
            codigosGerados.gerarCodigos();
            for (String codigos : codigosGerados.getCodigoValidos()) {
                codigosRepository.salvarCodigos(codigos);
            }

            System.out.println("50 codigos gerados no banco de dados!");
        }

   }
}
