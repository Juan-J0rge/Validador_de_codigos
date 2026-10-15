import Codigos.CodigosGerados;
import Codigos.CodigosRepository;
import Conexao.Conexao;
import Usuarios.UsuarioRepository;
import Validacao.Validacao;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        CodigosRepository codigosRepository = new CodigosRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Validacao validacao = new Validacao();
        try {
            Connection connection = Conexao.getIntancia().connection();
            System.out.println("Banco Conectado\n**************");
        } catch (Exception ex) {
            System.out.println("Erro ao conecatar no banco!");
            ex.printStackTrace();
        }


        boolean resultado = codigosRepository.existeDisponivel( "AC-2907");
        System.out.println("Existente = " + resultado);

        int id = usuarioRepository.salvar("Teste3", 15);
        System.out.println("ID gerado: " + id);

        boolean ok = codigosRepository.marcarComoValidado("AC-2907", 2);
        System.out.println(ok);


        validacao.validarCadastro("Juan", 21, "QY-3579");

    }
}
