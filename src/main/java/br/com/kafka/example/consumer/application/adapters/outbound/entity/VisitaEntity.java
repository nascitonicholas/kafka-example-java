package br.com.kafka.example.consumer.application.adapters.outbound.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VisitaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;
    @Column(name = "data")
    private String data;
    @Column(name = "horario")
    private String horario;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf")
    private UsuarioEntity usuario;

}
