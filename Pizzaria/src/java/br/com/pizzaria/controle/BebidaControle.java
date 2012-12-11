/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.BebidaDAO;
import br.com.pizzaria.dao.BebidaDAOImp;
import br.com.pizzaria.entidade.Bebida;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class BebidaControle {
    
    private Bebida bebida;
    private BebidaDAO bebidaDAO;
    private DataModel model;
    
    public Bebida getBebida() {
        if(bebida == null){
            bebida = new Bebida();
        }
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    
    public BebidaDAO getbebidaDAO() {
        return bebidaDAO;
    }

    public void setbebidaDAO(BebidaDAO bebidaDAO) {
        this.bebidaDAO = bebidaDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        bebidaDAO = new BebidaDAOImp();
        if (bebida.getId() == null) {
            bebidaDAO.salva(bebida);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida salva com sucesso!", ""));
        } else {
            bebidaDAO.altera(bebida);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida alterada com sucesso!", ""));
        }
        limpar();
        return "pesqBebida";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            bebidaDAO = new BebidaDAOImp();
            bebida = (Bebida) model.getRowData();            
            bebidaDAO.remove(bebida);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Bebida excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        bebida = (Bebida) model.getRowData();
        setBebida(bebida);
        return "cadBebida";
    }
    
    public String novoBebida() {        
        bebida = new Bebida();
        return "cadBebida";
    }

    private void limpar() {
        bebida = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqBebida";
    }
    
            public void pesquisaLike() {
        bebidaDAO = new BebidaDAOImp();
        List<Bebida> bebidas = bebidaDAO.pesquisaLikeNome(bebida.getNome());
        model = new ListDataModel(bebidas);
    }
}
