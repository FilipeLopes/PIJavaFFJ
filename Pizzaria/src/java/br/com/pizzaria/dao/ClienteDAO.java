/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Cliente;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ClienteDAO extends BaseDAO<Cliente, Long>{
    List<Cliente> pesquisaLikeNome(String nome);
}
