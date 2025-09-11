package com.br.nubank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "contato")
@Table(name = "contato")
@ToString(exclude = "cliente")
public class Contato {

    @Id
    @Column(name = "id_contato", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contato;

    @Column(name = "endereco_contato")
    private String endereco_contato;

    @Column(name = "numero_contato")
    private String numero_contato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    /*@JsonIgnore*/
    private Cliente cliente;

}