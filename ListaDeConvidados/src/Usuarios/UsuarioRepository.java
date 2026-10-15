package Usuarios;

import Conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {
    Connection connection = Conexao.getIntancia().connection();
    public int salvar(String nome, int idade) {
        String sql = "INSERT INTO usuarios (nome, idade) VALUES (?,?) RETURNING id";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir usuario");
            ex.printStackTrace();
            return -1;
        }
    }
}
