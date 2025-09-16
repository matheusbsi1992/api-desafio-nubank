package com.br.nubank.execucao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.PrintStream;

@SpringBootApplication/*(scanBasePackages = "com.br.nubank")*/
@ComponentScan(basePackages = {"com.*"})
@EntityScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.*"}/*.br.nubank.repository*/)
@EnableCaching
public class ApiNubankDesafioApplication {

   /* public static Cliente clienteDTOParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome_cliente(clienteDTO.getNomeCliente());
        cliente.setData_nascimento_cliente(clienteDTO.getDataNascimentoCliente());

        List<Contato> contatoes = new ArrayList<>();

        for (ContatoDTO contatoDTO : clienteDTO.getContatoList()) {
            Contato contato = new Contato();

            contato.setEndereco_contato(contatoDTO.getEnderecoContato());
            contato.setNumero_contato(contatoDTO.getNumeroContato());

            contato.setCliente(cliente);

            contatoes.add(contato);
        }
        cliente.setContatoList(contatoes);

        return cliente;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ApiNubankDesafioApplication.class, args);
    }

   /* @Bean
    CommandLineRunner runner(ClienteRepository clienteRepo) {
        return args -> {

            ClienteDTO clienteDTO = new ClienteDTO();

            clienteDTO.setNomeCliente("Maria Luiza Helena");
            clienteDTO.setDataNascimentoCliente(LocalDate.of(2025, 8, 21));

            ContatoDTO contatoDTO = new ContatoDTO();

            contatoDTO.setNumeroContato("79999165475");
            contatoDTO.setEnderecoContato("Rua Lau Fei");

            clienteDTO.setContatoList(List.of(contatoDTO));

            var c = clienteDTOParaCliente(clienteDTO);

            clienteRepo.save(c);
            clienteRepo.flush();

        };
    }*/

}