package com.forjack.transito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.forjack.transito.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
   // @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    @Size(max= 20)
    private String marca;

    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
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
