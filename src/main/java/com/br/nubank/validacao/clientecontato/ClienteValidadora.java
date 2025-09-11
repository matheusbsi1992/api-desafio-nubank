package com.br.nubank.validacao.clientecontato;


import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.repository.ClienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/*@Component*/
/*@Data*/
public class ClienteValidadora implements ConstraintValidator<ValidarErrorCliente, ClienteDTO> {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteValidadora(ClienteRepository clienteRepositorio) {
        Objects.requireNonNull(clienteRepositorio);
        this.clienteRepository = clienteRepositorio;
    }

    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        boolean isValid = true;

        //---Dados para outras integracoes que nao seja atualizacao

        // System.out.println("cliente --->>>" + clienteDTO.getIdCliente());
        if (clienteDTO.getIdCliente() == null || clienteDTO.getIdCliente() == 0) {

            if(clienteDTO.getDataNascimentoCliente()!=null && clienteDTO.getDataNascimentoCliente().isAfter(LocalDate.now())){
                constraintValidatorContext.buildConstraintViolationWithTemplate("A DATA DE NASCIMENTO NAO PODE SER MAIOR DO QUE A DATA DE HOJE")
                        .addPropertyNode("dataNascimentoCliente").addConstraintViolation();
                isValid = false;
            }

            if (clienteRepository.buscarNomeCliente(clienteDTO.getNomeCliente().toUpperCase())) {
                constraintValidatorContext.buildConstraintViolationWithTemplate("NOME DO CLIENTE JA EXISTE")
                        .addPropertyNode("nomeCliente").addConstraintViolation();
                isValid = false;
            }
            return isValid;
        }

        //---Dados para outras integracoes que nao seja adicao/insercao
        //--- Verificar se o nome do cliente já está sendo usado por outro cliente (excluindo o próprio cliente)
        if (clienteRepository.buscarNomeCliente(clienteDTO.getNomeCliente().toUpperCase(), clienteDTO.getIdCliente())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("NOME DO CLIENTE JA EXISTE")
                    .addPropertyNode("nomeCliente").addConstraintViolation();
            isValid = false;

        } else {
            return isValid;
        }
        return isValid;
    }

}