package com.br.nubank.controller;


import com.br.nubank.dto.ClienteDTO;
import com.br.nubank.exceptions.statuscode.ApiResponsesPadrao;
import com.br.nubank.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.br.nubank.exceptions.utilexceptions.UtilMyBindingResult.resultError;

@RestController
@RequestMapping("/")
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

/*
    @GetMapping("returnAll")
    public ResponseEntity<List<SchedulingDTO>> listAll() {
        List<SchedulingDTO> transactionDTOS = schedulingService.listAll();

        return ResponseEntity.ok(transactionDTOS);
    }

    @GetMapping("returnSchedulingEmail/{email}")
    public ResponseEntity<List<SchedulingDTO>> schedulingListEmail(@PathVariable(value = "email") String email) {
        List<SchedulingDTO> transactionDTOS = schedulingService.schedulingListEmail(email);
        return ResponseEntity.ok(transactionDTOS);
    }

    @GetMapping("returnSchedulingStatus/{status}")
    public ResponseEntity<List<SchedulingDTO>> schedulingListStatus(@PathVariable(value = "status") String status) {
        List<SchedulingDTO> transactionDTOS = schedulingService.schedulingListStatus(status);
        return ResponseEntity.ok(transactionDTOS);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTransaction(@RequestBody SchedulingDTO schedulingDTO) {
        var identityUpdateScheduling = schedulingService.updateScheduling(schedulingDTO);
        return ResponseEntity.status(HttpStatus.OK).body(identityUpdateScheduling);
    }
*/

    @Operation(summary = "Listar Cliente e Contato")
    @ApiResponsesPadrao
    @GetMapping("clientes")
    public ResponseEntity<?> listAll(@RequestParam(defaultValue = "0", required = false, name = "pagina") int pagina,
                                     @RequestParam(defaultValue = "20", required = false, name = "itens") int itens) {
        var transactionDTOS = clienteService.listarClientesESeusContatos(PageRequest.of(pagina, itens));
        return ResponseEntity.ok(transactionDTOS);
    }

    @Operation(summary = "Inserir Cliente e Contato")
    @ApiResponsesPadrao
    @PostMapping("clientes")
    public ResponseEntity<?> insertTransaction(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(resultError(result));
        }
        var identificarInserirCliente = clienteService.inserirCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(identificarInserirCliente);
    }

    @Operation(summary = "Atualizar Cliente e Contato")
    @ApiResponsesPadrao
    @PutMapping("clientes")
    public ResponseEntity<?> updateTransaction(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(resultError(result));
        }
        var identificarAlterarCliente = clienteService.alterarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(identificarAlterarCliente);
    }

    @Operation(summary = "Deletar Cliente e Contato")
    @ApiResponsesPadrao
    @DeleteMapping("clientes/{id}/contatos")
    public ResponseEntity<?> deleteTransaction(@PathVariable(name = "id") Long idCliente) {
        clienteService.deletarClienteEContato(idCliente);
        return ResponseEntity.status(204).build();/* status(HttpStatus.NO_CONTENT).body("")*/
    }

    @Operation(summary = "Listar Cliente e Seu Contato")
    @ApiResponsesPadrao
    @GetMapping("clientes/{id}/contatos")
    public ResponseEntity<?> clienteContatoList(@PathVariable(value = "id") Long idCliente) {
        List<ClienteDTO> transactionDTOS = clienteService.listarContatosDeClientes(idCliente);
        return ResponseEntity.ok(transactionDTOS);
    }

}