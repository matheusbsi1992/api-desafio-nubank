/*
package com.br.nubank.test.mockscliente;


import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.dto.ContatoDTO;
import com.br.nubank.mapper.ClienteContatoMapeada;
import com.br.nubank.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MockClienteContatos {


    private ClienteContatoMapeada clienteContatoMapeada;

    @Autowired
    public MockClienteContatos(ClienteContatoMapeada clienteContatoMapeada) {
        this.clienteContatoMapeada =clienteContatoMapeada;
    }

    public MockClienteContatos(){
    }

    public Cliente createCliente() {

        int valorNovo = Math.incrementExact(10);

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNomeCliente("Maria Luiza" + valorNovo);

        String valueDateTime = "20/02/2025";
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(valueDateTime, simpleDateFormat);

        clienteDTO.setDataNascimentoCliente(localDate);

        var clienteNovo = clienteContatoMapeada.clienteDTOParaCliente(clienteDTO);

        clienteNovo.getContatoList().clear();

        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setNumeroContato("(79)999165475");
        contatoDTO.setEnderecoContato("Rua dos Lobos, Lagarto-Se, Nº 190, Casa de Hermógenes Botafogo");

        var contatoNovo = clienteContatoMapeada.contatoDTOParaContato(contatoDTO);

        clienteNovo.setContatoList(List.of(contatoNovo));

        return clienteNovo;
    }
}*/
