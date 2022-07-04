package com.apiautos.apiautos.models;

import javax.persistence.*;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "autos")
public class Auto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "año", nullable = false)
    private int año;
    
    @Column(name = "imagen")
    private String imagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    private Marca marca;

}
