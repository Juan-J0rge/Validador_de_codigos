import Codigos.CodigosGerados;
import Codigos.CodigosRepository;
import Conexao.Conexao;
import Validacao.Validacao;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        CodigosRepository codigosRepository = new CodigosRepository();
        Validacao validado = new Validacao();
        try {
            Connection connection = Conexao.getIntancia().connection();
            System.out.println("Banco Conectado\n**************");
        } catch (Exception ex) {
            System.out.println("Erro ao conecatar no banco!");
            ex.printStackTrace();
        }


        boolean resultado = codigosRepository.existeDisponivel( "NR-1670");
        System.out.println("Existente = " + resultado);

    }
}
