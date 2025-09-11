package com.br.nubank.dto;


import com.br.nubank.model.Contato;
import com.br.nubank.validacao.clientecontato.ValidarErrorCliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO {

    private Long idContato;

    @NotBlank(message = "ENDERECO DE CONTATO E OBRIGATORIO")
    @Length(message = "ENDERECO DE CONTATO DEVE TER NO MINIMO 20 CARACTERES E NO MAXIMO 250 CARACTERES", min = 20, max = 250)
    private String enderecoContato;

    @NotBlank(message = "NUMERO DE CONTATO E OBRIGATORIO")
    @Pattern(
            regexp = "\\(\\d{2}\\)\\d\\d{4}-\\d{4}",
            message = "NUMERO DE CONTATO INVALIDO. FORMATO CORRETO: (79)99999-9999"
    )
    private String numeroContato;


    public ContatoDTO contatoParaContatoDTO(Contato dto) {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setIdContato(dto.getId_contato());
        contatoDTO.setEnderecoContato(dto.getEndereco_contato());
        contatoDTO.setNumeroContato(dto.getNumero_contato());
        return contatoDTO;
    }

    /*@JsonProperty("cliente")
    private ClienteDTO clienteDTO;*/


}