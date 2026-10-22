package Validacao;

import Codigos.CodigosRepository;
import Codigos.RegraDeCodigo;
import Exceptions.CodigoInvalidoException;
import Exceptions.DadosInvalidosException;
import Exceptions.UsuarioInvalidoException;
import Usuarios.UsuarioRepository;

import java.sql.Connection;

public class Validacao {

    RegraDeCodigo regra = new RegraDeCodigo();
    CodigosRepository codigosRepository = new CodigosRepository();
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void validarCadastro(String nome, int idade, String codigo) {

        if(nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome do usuario invalido! (O nome não pode ser vazio!)");
        }

        if (idade < 0 || idade > 120) {
            throw new DadosInvalidosException("Idade do usuario invalida");
        }


        if (!regra.formatacaoDosCodigos(codigo)) {
            throw new CodigoInvalidoException("A formatação do seu código esta errada!");
        }

        if (!codigosRepository.existeDisponivel(codigo)) {
            throw new CodigoInvalidoException("Seu codigo não existe ou ja foi usado!");
        }

        int usuarioId = usuarioRepository.salvar(nome, idade);

        if (usuarioId == -1) {
            throw new UsuarioInvalidoException("Erro ao salvar usuario!");
        }

        boolean validado = codigosRepository.marcarComoValidado(codigo, usuarioId);

        if (!validado) {
            throw new CodigoInvalidoException("Erro ao validar codigo!");
        }


    }

}
