package Codigos;

import Conexao.Conexao;

import java.sql.*;

public class CodigosRepository {

    Connection connection = Conexao.getIntancia().connection();

        public void salvarCodigos(String codigo) {
            String sql = "INSERT INTO codigos (codigo) VALUES (?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {

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
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, codigo);
                ResultSet rs = stmt.executeQuery();

                return rs.next();

            }catch (SQLException ex) {
                System.out.println("Erro ao buscar codigo");
                ex.printStackTrace();
                return false;
            }
        }

        public int contarCodigos() {
            String sql = "SELECT COUNT(*) FROM codigos";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return rs.getInt(1);

            } catch (SQLException ex) {
                System.out.println("Erro ao executar");
                 ex.printStackTrace();
                return 0;
            }

        }




    }

