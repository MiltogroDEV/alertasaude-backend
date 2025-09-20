package com.example.alertasaude_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dosagem", nullable = false)
    private String dosagem;

    @Column(name = "tarja", nullable = false)
    private String tarja;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "tomar_de", nullable = false)
    private int tomarDe;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
