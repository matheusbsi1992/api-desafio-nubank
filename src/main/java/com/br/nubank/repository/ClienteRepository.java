package com.br.nubank.repository;

import com.br.nubank.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

     /* @Query(value = "SELECT " +
              "c.id_cliente, " +
              "c.nome_cliente, " +
              "c.data_nascimento_cliente," +
              "cont.id_contato, " +
              "cont.endereco_contato, " +
              "cont.numero_contato " +
              "FROM cliente c " +
              "LEFT JOIN contato cont ON c.id_cliente = cont.id_cliente " +
              "ORDER BY c.id_cliente", nativeQuery = true)
    List<Cliente> listarContatosDeCadaCliente();*/

    @Query(value = "SELECT c " +
             "FROM cliente c " +
             "LEFT JOIN FETCH c.contatoList")
    Page<Cliente> listarContatosDeCadaCliente(Pageable pageable);

    /*@Query(value = "SELECT " +
            "c.id_cliente, " +
            "c.nome_cliente, " +
            "c.data_nascimento_cliente," +
            "cont.endereco_contato, " +
            "cont.numero_contato " +
            "FROM cliente c " +
            "LEFT JOIN contato cont ON c.id_cliente = cont.id_cliente " +
            "WHERE c.id_cliente = :id_cliente", nativeQuery = true)*/
    @Query(value = "SELECT   c " +
            "FROM cliente c " +
            "LEFT JOIN FETCH c.contatoList WHERE c.id_cliente=?1")
    List<Cliente> listarContatoPorCadaCliente(/*@Param("id_cliente")*/ Long id_cliente);

    @Query("SELECT CASE WHEN COUNT(c) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM cliente c " +
            "WHERE UPPER(c.nome_cliente) = :nome_cliente AND c.id_cliente <> :id_cliente")
    boolean buscarNomeCliente(@Param("nome_cliente") String nomeCliente, @Param("id_cliente") Long idCliente);

    @Query("SELECT CASE WHEN COUNT(c) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM cliente c " +
            "WHERE UPPER(c.nome_cliente) = :nome_cliente")
    boolean buscarNomeCliente(@Param("nome_cliente") String nomeCliente);

}