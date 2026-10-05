package Codigos;

import Conexao.Conexao;

import java.sql.*;

public class CodigosRepository {

        public void salvarCodigos(String codigo) {
            String sql = "INSERT INTO codigos (codigo) VALUES (?)";
            try (Connection connection = Conexao.getIntancia().connection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, codigo);
                stmt.executeUpdate();

                System.out.println("Codigo inserido com sucesso!");

            } catch (SQLException ex) {
                System.out.println("Erro ao Inserir o codigo");
                ex.printStackTrace();
            }
        }

        public boolean existeDisponivel(String codigo) {
            String sql = "SELECT * FROM codigos WHERE codigo = ? AND validado = false ";
            try (Connection connection = Conexao.getIntancia().connection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, codigo);
                ResultSet rs = stmt.executeQuery();

                return rs.next();

            }catch (SQLException ex) {
                System.out.println("Erro ao buscar codigo");
                ex.printStackTrace();
                return false;
            }
        }




    }

