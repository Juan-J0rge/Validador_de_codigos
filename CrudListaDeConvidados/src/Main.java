import Codigos.CodigosGerados;
import Codigos.CodigosRepository;
import Conexao.Conexao;
import Usuarios.BancoDeUsuarios;

import java.sql.Connection;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        CodigosRepository codigosRepository = new CodigosRepository();
        CodigosGerados codigosGerados = new CodigosGerados();
        Validacao validado = new Validacao();

        try {
            Connection connection = Conexao.getIntancia().connection();
            System.out.println("Banco Conectado\n**************");
        } catch (Exception ex) {
            System.out.println("Erro ao conecatar no banco!");
            ex.printStackTrace();
        }

        boolean resultado = codigosRepository.existeDisponivel("AB-1234");
        System.out.println(resultado);


//        codigosGerados.gerarCodigos();
//        for (String codigos: codigosGerados.getCodigoValidos()) {
//            codigosRepository.salvarCodigos(codigos);
//        }
//
//        validado.validarCadastro("Juan", 21, );








    }
}
