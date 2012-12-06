/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.TelefoneDAO;
import br.com.pizzaria.dao.TelefoneDAOImp;
import br.com.pizzaria.entidade.Telefone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class TelefoneControle {
    
    private Telefone telefone;
    private TelefoneDAO telefoneDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Telefone getCidade() {
     if(telefone == null){
            telefone = new Telefone();
        }
        return telefone;
    }

    public void setCidade(Telefone telefone) {
        this.telefone = telefone;
    }

    public TelefoneDAO gettelefoneDAO() {
        return telefoneDAO;
    }

    public void settelefoneDAO(TelefoneDAO telefoneDAO) {
        this.telefoneDAO = telefoneDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        telefoneDAO = new TelefoneDAOImp();
        if (telefone.getId() == null) {
            telefoneDAO.salva(telefone);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Telefone salvo com sucesso!", ""));
        } else {
            telefoneDAO.altera(telefone);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Telefone alterado com sucesso!", ""));
        }
        limpar();
        return "pesqTelefone";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            telefoneDAO = new TelefoneDAOImp();
            telefone = (Telefone) model.getRowData();            
            telefoneDAO.remove(telefone);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Telefone excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel a exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        telefone = (Telefone) model.getRowData();
        setCidade(telefone);
        return "cadTelefone";
    }
    
    public String novoCidade() {        
        telefone = new Telefone();
        return "cadTelefone";
    }

    private void limpar() {
        telefone = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqTelefone";
    }
}

