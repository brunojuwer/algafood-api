package br.com.juwer.algafoodapi.domain.model;

import javax.persistence.*;

import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
