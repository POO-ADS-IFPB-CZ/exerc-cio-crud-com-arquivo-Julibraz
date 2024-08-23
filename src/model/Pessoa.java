package model;

import java.io.Serializable;
import java.util.Objects;

public class Pessoa implements Serializable {
    private long serialVersionUID;
    private String nome;
    private String email;

    public Pessoa() {
        serialVersionUID = 1L;
        nome = nome;
        email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa: " +
                "nome= '" + nome +
                "', email= '" + email +
                "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getEmail(), pessoa.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEmail());
    }
}
