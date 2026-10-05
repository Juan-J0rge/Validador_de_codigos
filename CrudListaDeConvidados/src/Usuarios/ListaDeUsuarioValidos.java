package Usuarios;

import java.util.HashMap;

public class ListaDeUsuarioValidos{
    // guarda apenas usuarios que ja foram validados
    HashMap<String, Usuario> listUser = new HashMap<>();

    public void adicionarUsuarioValido(String codigo, Usuario usuario) {
        listUser.put(codigo, usuario);
    }

    // checar o limite de usuarios na lista
    public boolean limiteDaLista() {
        return listUser.size() < 50;
    }


}
