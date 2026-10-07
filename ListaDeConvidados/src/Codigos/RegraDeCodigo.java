package Codigos;

// vai conter a regra que o codigo deve ter
public class RegraDeCodigo {

    public boolean formatacaoDosCodigos(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        String regra = "^[A-Z]{2}-\\d{4}$";

        return codigo.matches(regra);
    }



}
