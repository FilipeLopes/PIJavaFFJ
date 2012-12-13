/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.*;
import br.com.pizzaria.entidade.Funcao;
import br.com.pizzaria.entidade.Funcao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tecnicom
 */
@ManagedBean(name = "funcaoC")
@SessionScoped
public class FuncaoControle {

    private Funcao funcao;
    private FuncaoDAO funcaoDAO;
    private DataModel model;

    public Funcao getFuncao() {
        if(funcao == null){
            funcao = new Funcao();
        }
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public FuncaoDAO getFuncaoDAO() {
        return funcaoDAO;
    }

    public void setFuncaoDAO(FuncaoDAO funcaoDAO) {
        this.funcaoDAO = funcaoDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        funcaoDAO = new FuncaoDAOImp();
        if (funcao.getId() == null) {
            funcaoDAO.salva(funcao);

            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Função salva com sucesso!", ""));
        } else {
            funcaoDAO.altera(funcao);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Função alterada com sucesso!", ""));
        }
        limpar();
        
        return "pesqFuncao";
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            funcaoDAO = new FuncaoDAOImp();
            funcao = (Funcao) model.getRowData();
            funcaoDAO.remove(funcao);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Funcao excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }

    public String pesquisaLike() {
        funcaoDAO = new FuncaoDAOImp();
        List<Funcao> funcaos = funcaoDAO.pesquisaLikeNome(funcao.getNome());
        model = new ListDataModel(funcaos);
        return "";
    }

    public String alterar() {
        funcao = (Funcao) model.getRowData();
        setFuncao(funcao);
        return "cadFuncao";
    }

    public String novoFuncao() {
        funcao = new Funcao();
        return "cadFuncao";
    }

    private void limpar() {
        funcao = null;
        model = null;
    }

    public String limpaPesquisa() {
        limpar();
        return "pesqFuncao";
    }

}
