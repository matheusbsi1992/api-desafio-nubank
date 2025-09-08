package com.br.nubank.dto;

import com.br.nubank.model.Cliente;
import com.br.nubank.model.Contato;
import com.br.nubank.validacao.clientecontato.ValidarErrorCliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidarErrorCliente
public class ClienteDTO {

    private Long idCliente;

    @NotBlank(message = "NOME DO CLIENTE E OBRIGATORIO")
    @Length(message = "NOME DO CLIENTE DEVE TER NO MINIMO 02 CARACTERES E NO MAXIMO 200 CARACTERES", min = 2, max = 200)
    private String nomeCliente;

    @NotNull(message = "DATA DE NASCIMENTO E OBRIGATORIO")
    @JsonFormat(pattern = "dd/MM/yyyy")
   /* @Pattern(
            regexp = "\\d{2}/\\d{2}/\\d{4}",
            message = "DATA DE NASCIMENTO INVÁLIDO. FORMATO CORRETO: XX/XX/XXXX"
    )*/
    private LocalDate dataNascimentoCliente;

    @JsonProperty(value = "contatos")
    @Valid
    private List<ContatoDTO> contatos;

    /*public ClienteDTO nv (Cliente cliente){

        setIdCliente(cliente.getId_cliente());
        setNomeCliente(cliente.getNome_cliente());
        setDataNascimentoCliente(cliente.getData_nascimento_cliente());
        setContatos(cliente.
                getContatoList().
                stream().
                map(dto-> new ContatoDTO().contatoParaContatoDTO(dto)).
                collect(Collectors.toList()));

        return this;
    }*/

    public ClienteDTO clienteParaClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getId_cliente());
        clienteDTO.setNomeCliente(cliente.getNome_cliente());
        clienteDTO.setDataNascimentoCliente(cliente.getData_nascimento_cliente());
        clienteDTO.setContatos(cliente.
                getContatoList().
                stream().
                map(dto-> new ContatoDTO().contatoParaContatoDTO(dto)).
                collect(Collectors.toList()));
        return clienteDTO;
    }

    /*public static List<ClienteDTO> clienteParaClienteListDTOContato(List<Cliente> clientes){
        return clientes.stream()
                .map(ClienteDTO::dclienteParaClienteDTO).
                collect(Collectors.toList());
    }

    public static ClienteDTO dclienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(cliente.getId_cliente());
        clienteDTO.setNomeCliente(cliente.getNome_cliente());
        clienteDTO.setDataNascimentoCliente(cliente.getData_nascimento_cliente());

        List<ContatoDTO> contatoes = new ArrayList<>();

        for (Contato contato : cliente.getContatoList()) {
            ContatoDTO contatoDTO = new ContatoDTO();

            contatoDTO.setEnderecoContato(contato.getEndereco_contato());
            contatoDTO.setNumeroContato(contato.getNumero_contato());

            //contatoDTO.setClienteDTO(clienteDTO);

            contatoes.add(contatoDTO);
        }
        clienteDTO.setContatos(contatoes);

        return clienteDTO;
    }*/

    /*public List<ClienteDTO> clienteParaClienteListDTOContato(List<Cliente> clientes){
        return clientes.stream()
                .map(this::dclienteParaClienteDTO).
                collect(Collectors.toList());
    }

    public static Cliente clienteDTOParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome_cliente(clienteDTO.getNomeCliente());
        cliente.setData_nascimento_cliente(clienteDTO.getDataNascimentoCliente());

        *//*List<Contato> contatoes = new ArrayList<>();

        for (ContatoDTO contatoDTO : clienteDTO.getContatoList()) {
            Contato contato = new Contato();

            contato.setEndereco_contato(contatoDTO.getEnderecoContato());
            contato.setNumero_contato(contatoDTO.getNumeroContato());

            contato.setCliente(cliente);

            contatoes.add(contato);
        }
        cliente.setContatoList(contatoes);*//*

        return cliente;
    }*/


    /*public static Contato clienteDTOParaContato(ClienteDTO clienteDTO) {

        var cliente = clienteDTOParaCliente(clienteDTO);

        //List<Contato> contatoes = new ArrayList<>();

        Contato contato = new Contato();
        for (ContatoDTO contatoDTO : clienteDTO.getContatos()) {

            contato.setEndereco_contato(contatoDTO.getEnderecoContato());
            contato.setNumero_contato(contatoDTO.getNumeroContato());

            //contato.setCliente(cliente);

            //contatoes.add(contato);
        }

        return contato;
    }*/

    /*public ClienteDTO dclienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(cliente.getId_cliente());
        clienteDTO.setNomeCliente(cliente.getNome_cliente());
        clienteDTO.setDataNascimentoCliente(cliente.getData_nascimento_cliente());

        List<ContatoDTO> contatoes = new ArrayList<>();

        for (Contato contato : cliente.getContatoList()) {
            ContatoDTO contatoDTO = new ContatoDTO();

            contatoDTO.setEnderecoContato(contato.getEndereco_contato());
            contatoDTO.setNumeroContato(contato.getNumero_contato());

            //contatoDTO.setClienteDTO(clienteDTO);

            contatoes.add(contatoDTO);
        }
        clienteDTO.setContatos(contatoes);

        return clienteDTO;
    }

    public static ClienteDTO clienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(cliente.getId_cliente());
        clienteDTO.setNomeCliente(cliente.getNome_cliente());
        clienteDTO.setDataNascimentoCliente(cliente.getData_nascimento_cliente());

        List<ContatoDTO> contatoes = new ArrayList<>();

        for (Contato contato : cliente.getContatoList()) {
            ContatoDTO contatoDTO = new ContatoDTO();

            contatoDTO.setEnderecoContato(contato.getEndereco_contato());
            contatoDTO.setNumeroContato(contato.getNumero_contato());

            //contatoDTO.setClienteDTO(clienteDTO);

            contatoes.add(contatoDTO);
        }
        clienteDTO.setContatos(contatoes);

        return clienteDTO;
    }
*/

}