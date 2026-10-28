import Codigos.CodigosGerados;
import Codigos.CodigosRepository;
import Conexao.Conexao;
import Exceptions.CodigoInvalidoException;
import Exceptions.DadosInvalidosException;
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



        while (true) {
            System.out.println("Digite nome completo: (ou digite 'sair' se quiser encerrar)");
            String nome = scanner.nextLine();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }


            try {
                System.out.println("Idade:");
                int idade = Integer.parseInt(scanner.nextLine());

                System.out.println("Codigo do convite:");
                String codigo = scanner.nextLine();

                validacao.validarCadastro(nome,idade,codigo);
                System.out.println("Acesso liberado");
            } catch (CodigoInvalidoException e) {
                System.out.println("Acesso negado: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Idade invalida!. Digite apenas numeros");

            }catch (DadosInvalidosException e) {
                System.out.println("Dados do usuario invalidos: " + e.getMessage());

            }finally {
                System.out.println("\n*** Programa encerrado ***");
            }

        }


    }
}
