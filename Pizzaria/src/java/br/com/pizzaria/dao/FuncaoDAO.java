/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Funcao;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface FuncaoDAO extends BaseDAO<Funcao, Long>{
    List<Funcao> pesquisaLikeNome(String nome);
}
