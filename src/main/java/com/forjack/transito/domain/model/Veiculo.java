package com.forjack.transito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
   // @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    @Size(max= 20)
    private String marca;

    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotBlank
    @Size(max = 7)
    private String placa;


    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCadastro;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;

}
