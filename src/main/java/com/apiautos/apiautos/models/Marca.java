package com.apiautos.apiautos.models;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "marcas")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;

    @JsonBackReference
    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Auto> autos = new HashSet<>();
    
    @Column(name = "origen")
    private String origen;

    @Column(name = "logo")
    private String logo;

    public void removeAuto(Auto auto){
        autos.remove(auto);
        auto.setMarca(null);
    }
}
