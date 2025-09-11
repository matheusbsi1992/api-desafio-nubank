package com.br.nubank.mapper;

import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.dto.ContatoDTO;
import com.br.nubank.model.Cliente;
import com.br.nubank.model.Contato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClienteContatoMapeada {

    /// **Mapeamento do cliente**

    @Mappings({
            @Mapping(source = "idCliente", target = "id_cliente"),
            @Mapping(source = "nomeCliente", target = "nome_cliente"),
            @Mapping(source = "dataNascimentoCliente", target = "data_nascimento_cliente", dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "contatos", target = "contatoList", qualifiedByName = "mapListContatoDTOParaSingleContato")
    })
    Cliente clienteDTOParaCliente(ClienteDTO clienteDTO);


    @Mappings({
            @Mapping(source = "id_cliente", target = "idCliente"),
            @Mapping(source = "nome_cliente", target = "nomeCliente"),
            @Mapping(source = "data_nascimento_cliente", target = "dataNascimentoCliente", dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "contatoList", target = "contatos", qualifiedByName = "mapSingleContatoParaListContatoDTO")
    })
    ClienteDTO clienteParaClienteDTO(Cliente cliente);


    /// **Mapeamento do Contato**

    @Mappings({
            @Mapping(source = "id_contato", target = "idContato"),
            @Mapping(source = "endereco_contato", target = "enderecoContato"),
            @Mapping(source = "numero_contato", target = "numeroContato")
    })
    ContatoDTO contatoParaContatoDTO(Contato contato);

    @Mappings({
            @Mapping(source = "idContato", target = "id_contato"),
            @Mapping(source = "enderecoContato", target = "endereco_contato"),
            @Mapping(source = "numeroContato", target = "numero_contato")
    })
    Contato contatoDTOParaContato(ContatoDTO contatoDTO);

    // Converte um único Contato para uma lista de ContatoDTO com um único elemento
    @Named(value = "mapSingleContatoParaListContatoDTO")
    default List<ContatoDTO> mapSingleContatoParaListContatoDTO(List<Contato> contatos) {
        if (contatos == null) {
            return null;
        }
        return contatos.
                stream().
                map(this::contatoParaContatoDTO).
                toList();
                //collect(Collectors.toList());
        //List.of(mapContatoParaContatoDTO(contato));
    }

    // Converte uma lista de ContatoDTO para um único Contato (pega o primeiro item da lista)
    @Named(value = "mapListContatoDTOParaSingleContato")
    default List<Contato> mapListContatoDTOParaSingleContato(List<ContatoDTO> contatoDTOS) {
        if (contatoDTOS == null || contatoDTOS.isEmpty()) {
            return null;
        }
        return contatoDTOS.
                stream().
                map(this::contatoDTOParaContato).
                toList();
        //return mapContatoDTOParaContato(contatoDTOS.get(0));
    }

    // Lista de clientes
    //List<ClienteDTO> toDTOList(List<Cliente> clientes);
}