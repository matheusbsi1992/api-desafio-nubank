package com.br.nubank.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
@Table(name = "cliente")
@ToString(exclude = "contatoList")
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "nome_cliente")
    private String nome_cliente;

    @Column(name = "data_nascimento_cliente")
    private LocalDate data_nascimento_cliente;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_contato")
    private List<Contato> contatoList;

}