package Codigos;

import java.util.HashSet;
import java.util.Random;

// vai armazenar os codigos validos
public class CodigosGerados {

    RegraDeCodigo regra = new RegraDeCodigo();
    private Random random = new Random();

    HashSet<String> codigoValidos = new HashSet<>();

    public void gerarCodigos() {
       while ( codigoValidos.size() < 50 ) {

           char letra1 = (char) ('A' + random.nextInt(26));
           char letra2 = (char) ('A' + random.nextInt(26));

           int numeros = random.nextInt(10000);

           String codigos = String.format("%c%c-%04d", letra1, letra2, numeros);

           if (regra.formatacaoDosCodigos(codigos)) {
               codigoValidos.add(codigos);
           } else {
               System.out.println("Formato do codigo invalido!");
           }
       }
    }

    //verifica se codigo inserido existe na lista de codigos validos
    public boolean codigoExiste(String codigo) {
       return codigoValidos.contains(codigo);
    }

    public HashSet<String> getCodigoValidos() {
        return codigoValidos;
    }

    @Override
    public String toString() {
        return "CodigosGerados{" +
                " codigoValidos=" + codigoValidos +
                '}';
    }
}

