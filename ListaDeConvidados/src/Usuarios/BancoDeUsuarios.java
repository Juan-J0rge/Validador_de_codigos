package Usuarios;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//vai receber os codigos e usuarios, sera mandada para validação de codigo (classe ListaDeUsuariosValidos)
//se o codigo for valido sera mandada para lista de usuarios validos
public class BancoDeUsuarios {
    List<Usuario> listaDeUsuarios = new ArrayList<>();

    public Usuario cadastrar(String nome, int idade, String codigo) {
        Usuario usuario = new Usuario(nome, idade, codigo);
        listaDeUsuarios.add(usuario);
        return usuario;
    }

}
