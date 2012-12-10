/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.SaborDAO;
import br.com.pizzaria.dao.SaborDAOImp;
import br.com.pizzaria.entidade.Sabor;
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
@ManagedBean(name="saborCont")
@SessionScoped
public class SaborControle {
    
        
    private Sabor sabor;
    private SaborDAO saborDAO;
    private DataModel model;
    
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Sabor getSabor() {
     if(sabor == null){
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public SaborDAO getsaborDAO() {
        return saborDAO;
    }

    public void setsaborDAO(SaborDAO saborDAO) {
        this.saborDAO = saborDAO;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        saborDAO = new SaborDAOImp();
        if (sabor.getId() == null) {
            saborDAO.salva(sabor);            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor salvo com sucesso!", ""));
        } else {
            saborDAO.altera(sabor);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor alterado com sucesso!", ""));
        }
        limpar();
        return "pesqSabor";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            saborDAO = new SaborDAOImp();
            sabor = (Sabor) model.getRowData();            
            saborDAO.remove(sabor);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sabor excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        sabor = (Sabor) model.getRowData();
        setSabor(sabor);
        return "cadSabor";
    }
    
    public String novoSabor() {        
        sabor = new Sabor();
        return "cadSabor";
    }

    private void limpar() {
        sabor = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqSabor";
    }
    
    public void pesquisaLikeSabor() {
        saborDAO = new SaborDAOImp();
        List<Sabor> sabores = saborDAO.pesquisaLikeSabor(sabor.getSabor());
        model = new ListDataModel(sabores);
    }
}
