package Usuarios;

public class Usuario {
    public String nome;
    public int idade;
    public String codigo;

    public Usuario(String nome, int idade, String codigo) {
        this.nome = nome;
        this.idade = idade;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
