/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.*;
import br.com.pizzaria.entidade.Funcao;
import br.com.pizzaria.entidade.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author tecnicom
 */
@ManagedBean(name = "funcionarioC")
@SessionScoped
public class FuncionarioControle {

    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDAO;
    private DataModel model;
    private Funcao funcao;

    public Funcionario getFuncionario() {
        if(funcionario == null){
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Funcao getFuncao() {
        if(funcao == null){
            funcao = new Funcao();
        }
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        funcionarioDAO = new FuncionarioDAOImp();
        if (funcionario.getId() == null) {
            funcionario.setFuncao(funcao);
            funcionarioDAO.salva(funcionario);

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "funcionario salva com sucesso!", ""));
        } else {
            funcionarioDAO.altera(funcionario);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "funcionario alterada com sucesso!", ""));
        }
        limpar();
        return "pesqFuncionario";
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            funcionarioDAO = new FuncionarioDAOImp();
            funcionario = (Funcionario) model.getRowData();
            funcionarioDAO.remove(funcionario);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Funcionario excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }

    public void pesquisaLike() {
        funcionarioDAO = new FuncionarioDAOImp();
        List<Funcionario> funcionarios = funcionarioDAO.pesquisaLikeNome(funcionario.getNome());
        model = new ListDataModel(funcionarios);
    }

    public String alterar() {
        funcionario = (Funcionario) model.getRowData();
        setFuncionario(funcionario);
        return "cadFuncionario";
    }

    public String novoFuncionario() {
        funcionario = new Funcionario();
        return "cadFuncionario";
    }

    private void limpar() {
        funcionario = null;
        funcao = null;
        //model = null;
    }

    public String limpaPesquisa() {
        limpar();
        return "pesqFuncionario";
    }

    public List<SelectItem> getComboFuncao() {
        FuncaoDAO fdao = new FuncaoDAOImp();
        List<Funcao> funcoes = fdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Funcao func : funcoes) {
            listaCombo.add(new SelectItem(func.getId(), func.getNome()));
        }
        return listaCombo;
    }
}
