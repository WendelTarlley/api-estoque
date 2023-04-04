package br.com.devtarlley.apiestoque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name="users")
public class User {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String cpf;

    @OneToMany
    private List<ItemPreco> itemPreco;
}
