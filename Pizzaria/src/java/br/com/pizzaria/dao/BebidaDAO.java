/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Bebida;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface BebidaDAO extends BaseDAO<Bebida, Long> {
    List<Bebida> pesquisaLikeNome(String nome);
}
