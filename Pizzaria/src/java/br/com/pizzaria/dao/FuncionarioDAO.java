/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.dao;

import br.com.pizzaria.entidade.Funcionario;
import java.util.List;

/**
 *
 * @author Cursos Livres
 */
public interface FuncionarioDAO extends BaseDAO<Funcionario, Long> {

    List<Funcionario> pesquisaLikeNome(String nome);
}
