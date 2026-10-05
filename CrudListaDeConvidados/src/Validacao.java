import Codigos.CodigosGerados;
import Codigos.RegraDeCodigo;
import Exceptions.CodigoInvalidoException;
import Exceptions.ListaCheiaException;
import Usuarios.BancoDeUsuarios;
import Usuarios.ListaDeUsuarioValidos;
import Usuarios.Usuario;

public class Validacao {

    RegraDeCodigo regra = new RegraDeCodigo();
    BancoDeUsuarios bancoUsuario = new BancoDeUsuarios();
    CodigosGerados codigos = new CodigosGerados();
    ListaDeUsuarioValidos usuarioValido = new ListaDeUsuarioValidos();

    public void validarCadastro(String nome, int idade, String codigo) {

        if (!regra.formatacaoDosCodigos(codigo)) {
            throw new CodigoInvalidoException("A formatação do seu código esta errada!");
        }

        if (!codigos.codigoExiste(codigo)) {
            throw new CodigoInvalidoException("O seu codigo não existe!");
        }

        if (!usuarioValido.limiteDaLista()) {
            throw new ListaCheiaException("A lista do casamento ja está cheia! Confirme com o noivo(a).");
        }


            Usuario usuario = bancoUsuario.cadastrar(nome, idade, codigo);
            usuarioValido.adicionarUsuarioValido(codigo, usuario);
    }

}
