package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Class.forName;

/**
 *
 * @author juanjorge
 *
 */

public class Conexao {
    private static Conexao intancia;

    private final Connection connection;

    private Conexao()  {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/codigos","postgres","123456");
        } catch (SQLException ex) {
            throw new RuntimeException("Ocorreu um erro ao se conectar no banco de dados");
        }
    }

    public static Conexao getIntancia() {
        if (Objects.isNull(intancia)) {
            // o new vai iniciar o construtor acima!
            intancia = new Conexao();
        }
        return intancia;
    }

    public Connection connection() {
        return connection;
    }

}
