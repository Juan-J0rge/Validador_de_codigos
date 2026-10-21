import Codigos.CodigosGerados;
import Codigos.CodigosRepository;
import Conexao.Conexao;
import Exceptions.CodigoInvalidoException;
import Usuarios.UsuarioRepository;
import Validacao.Validacao;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Validacao validacao = new Validacao();

        try {
            Connection connection = Conexao.getIntancia().connection();
            System.out.println("Banco Conectado\n**************");
        } catch (Exception ex) {
            System.out.println("Erro ao conecatar no banco!");
            ex.printStackTrace();
        }




        System.out.println("Nome completo:");
        String nome = scanner.nextLine();

        System.out.println("Idade:");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.println("Codigo do convite:");
        String codigo = scanner.nextLine();

        try {
            validacao.validarCadastro(nome,idade,codigo);
            System.out.println("Acesso liberado");
        } catch (CodigoInvalidoException e) {
            System.out.println("Acesso negado: " + e.getMessage());
        }

    }
}
