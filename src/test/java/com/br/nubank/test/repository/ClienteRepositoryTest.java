package com.br.nubank.test.repository;


import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.dto.ContatoDTO;
import com.br.nubank.execucao.ApiNubankDesafioApplication;
import com.br.nubank.mapper.ClienteContatoMapeada;
import com.br.nubank.model.Cliente;
import com.br.nubank.repository.ClienteRepository;
/*import com.br.nubank.test.mockscliente.MockClienteContatos;*/
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/// //-- Loading all context in application (Services, Controllers, etc.)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {
        ApiNubankDesafioApplication.class}
)
// -- Forcer in using archive H2 Test
@TestPropertySource(locations = "classpath:application-test.properties")
//-- Not found Configure DataBase Production
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ANNOTATED) // Habilita a injeção via cons
// -- Test Using Junit 5
@ExtendWith(SpringExtension.class)
//@Transactional
//@AutoConfigureTestEntityManager
/*@EnableJpaRepositories(basePackages = {
        "com.br.nubank.*"
})*/

//@EnableTransactionManagement
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteContatoMapeada clienteContatoMapeada;
    
    /*private MockClienteContatos mockScheduling;
*/
    @BeforeEach
    public void inicializarMock() {
        /*this.mockScheduling = new MockClienteContatos();*/
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnIDUsuario() {

        Long idUsuario = 1L;

        List<Cliente> clienteDTOList = clienteRepository.listarContatoPorCadaCliente(idUsuario);

        Assertions.assertNotNull(clienteDTOList.size() > 0);
        Assertions.assertTrue(!clienteDTOList.isEmpty());
        assertThat(!clienteDTOList.isEmpty()).isTrue();
    }

    @Test
    void returnAllClient() {

        Page/*List*/<Cliente> clienteDTOList = clienteRepository.listarContatosDeCadaCliente(PageRequest.of(1, 1));

        Assertions.assertNotNull(clienteDTOList.getSize());//*.size()*/ > 0);
        Assertions.assertTrue(!clienteDTOList.isEmpty());
        assertThat(!clienteDTOList.isEmpty()).isTrue();
    }


    @Test
    void createDSCliente() {
        //--- Premier Cliente

        var valueExistent = /*mockScheduling.*/createCliente();

        clienteRepository.save(valueExistent);
        clienteRepository.flush();

    }

    public Cliente createCliente() {

        int valorNovo = (int) (Math.random()*10);

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNomeCliente("Maria Luiza " + valorNovo);

        String valueDateTime = "20/02/2025";
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(valueDateTime, simpleDateFormat);

        clienteDTO.setDataNascimentoCliente(localDate);

        var clienteNovo = clienteContatoMapeada.clienteDTOParaCliente(clienteDTO);

        /*clienteNovo.getContatoList().clear();*/

        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setNumeroContato("(79)999165475");
        contatoDTO.setEnderecoContato("Rua dos Lobos, Lagarto-Se, Nº 190, Casa de Hermógenes Botafogo");

        var contatoNovo = clienteContatoMapeada.contatoDTOParaContato(contatoDTO);

        contatoNovo.setCliente(clienteNovo);

        clienteNovo.setContatoList(List.of(contatoNovo));

        return clienteNovo;
    }

}
