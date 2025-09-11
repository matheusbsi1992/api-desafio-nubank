package com.br.nubank.service;

import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.dto.ContatoDTO;
import com.br.nubank.mapper.ClienteContatoMapeada;
import com.br.nubank.model.Contato;
import com.br.nubank.repository.ClienteRepository;
import com.br.nubank.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
import static com.br.nubank.dto.ClienteDTO.clienteDTOParaCliente;
import static com.br.nubank.dto.ClienteDTO.clienteParaClienteDTO;
*/
//import static com.br.nubank.dto.ClienteDTO.clienteParaClienteDTO;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private ContatoRepository contatoRepository;

    private ClienteContatoMapeada clienteContatoMapeada;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ContatoRepository contatoRepository, ClienteContatoMapeada clienteContatoMapeada) {
        Objects.requireNonNull(clienteRepository);
        Objects.requireNonNull(contatoRepository);
        Objects.requireNonNull(clienteContatoMapeada);
        this.clienteRepository = clienteRepository;
        this.contatoRepository = contatoRepository;
        this.clienteContatoMapeada = clienteContatoMapeada;
    }

    public ClienteDTO inserirCliente(ClienteDTO clienteDTO) {

        //var novoValor = clienteDTOParaCliente(clienteDTO);

        var novoCliente = clienteContatoMapeada.clienteDTOParaCliente(clienteDTO);

        var resultadoCliente = clienteRepository.save(novoCliente);

      /*  List<Contato> contatoes = clienteDTO.
                getContatos().
                stream().
                map(contatoDTO -> {

                    Contato contato = clienteContatoMapeada.contatoDTOParaContato(contatoDTO);
                    contato.setCliente(resultadoCliente);
                    return contatoRepository.save(contato);

                }).toList();

        resultadoCliente.setContatoList(contatoes);*/

/*
        List<Contato> contatoes = new ArrayList<>();

        for (ContatoDTO contatoDTO : clienteDTO.getContatoList()) {
            Contato contato = new Contato();

            contato.setEndereco_contato(contatoDTO.getEnderecoContato());
            contato.setNumero_contato(contatoDTO.getNumeroContato());

            contato.setCliente(resultadoClienteDTO);

            contatoRepository.save(contato);

            contatoes.add(contato);
        }
        resultadoClienteDTO.setContatoList(contatoes);
*/

        return /*clienteParaClienteDTO*/clienteContatoMapeada.clienteParaClienteDTO(resultadoCliente);
    }

    public ClienteDTO alterarCliente(ClienteDTO clienteDTO) {

        //var novoValor = clienteDTOParaCliente(clienteDTO);
        var cliente = clienteRepository
                .findById(clienteDTO.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("CLIENTE NAO ENCONTRADO"));

        var resultadoCliente = clienteContatoMapeada.clienteDTOParaCliente(clienteDTO);

        cliente.setNome_cliente(resultadoCliente.getNome_cliente());
        cliente.setData_nascimento_cliente(resultadoCliente.getData_nascimento_cliente());

        var novocliente = clienteRepository.save(cliente);

        novocliente.getContatoList().clear();

       /* for (ContatoDTO contatoDTO: clienteDTO.getContatos()){

            var novoValorContato = clienteContatoMapeada.contatoDTOParaContato(contatoDTO);

            novoValorContato.setNumero_contato(contatoDTO.getNumeroContato());
            novoValorContato.setEndereco_contato(contatoDTO.getEnderecoContato());

            novoValorContato.setCliente(novocliente);

            novocliente.getContatoList().add(novoValorContato);

            contatoRepository.save(novoValorContato);
        }*/

        clienteDTO.
                getContatos().
                stream().
                map(contatoDTO -> {

                    var contato = clienteContatoMapeada.contatoDTOParaContato(contatoDTO);

                    contato.setNumero_contato(contatoDTO.getNumeroContato());
                    contato.setEndereco_contato(contatoDTO.getEnderecoContato());

                    contato.setCliente(novocliente);

                    novocliente.getContatoList().add(contato);

                    return contatoRepository.save(contato);

                }).toList();

        //novocliente.setContatoList(contatoes);

/*
        List<Contato> contatoes = new ArrayList<>();

        for (ContatoDTO contatoDTO : clienteDTO.getContatoList()) {
            Contato contato = new Contato();

            contato.setEndereco_contato(contatoDTO.getEnderecoContato());
            contato.setNumero_contato(contatoDTO.getNumeroContato());

            contato.setCliente(resultadoClienteDTO);

            contatoRepository.save(contato);

            contatoes.add(contato);
        }
        resultadoClienteDTO.setContatoList(contatoes);
*/

        return /*clienteParaClienteDTO*/clienteContatoMapeada.clienteParaClienteDTO(novocliente);
    }

    public void deletarClienteEContato(Long idCliente){
        var cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("CLIENTE NAO ENCONTRADO"));

        clienteRepository.delete(cliente);

    }

    public Page<ClienteDTO> listarClientesESeusContatos(Pageable pageable) {
        //stream().
        /*map(clienteDTO::clienteParaClienteDTO).
                        collect(Collectors.toList());*/
        //.
        //collect(Collectors.toList());

        return clienteRepository.
                listarContatosDeCadaCliente(pageable).
                //stream().
                /*map(clienteDTO::clienteParaClienteDTO).
                collect(Collectors.toList());*/
                        map(clienteContatoMapeada::clienteParaClienteDTO);
    }

    /*public List<ClienteDTO> listarContatosDeClientes() {
        var resultadoNovo = clienteRepository.
                listarContatosDeCadaCliente();
        return clienteDTO.clienteParaClienteListDTOContato(resultadoNovo);
    }*/

    public List<ClienteDTO> listarContatosDeClientes(Long idCliente) {

        return clienteRepository.
                listarContatoPorCadaCliente(idCliente)
                .stream()
                .map(clienteContatoMapeada::clienteParaClienteDTO)
                .toList();
        //  return listarContatosDeClientes().stream().filter(p->p.getIdCliente().longValue()==idCliente).toList();
        /*return clienteRepository.
                //listarContatosDeCadaCliente().
                listarContatoPorCadaCliente(idCliente).
                stream().
                filter(p -> p.getId_cliente().longValue() == idCliente).
        */        //map(ClienteDTO::clienteParaClienteDTO).
        //toList();
    }

}