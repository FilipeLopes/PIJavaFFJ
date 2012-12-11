/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaria.controle;

import br.com.pizzaria.dao.ClienteDAO;
import br.com.pizzaria.dao.ClienteDAOImp;
import br.com.pizzaria.entidade.Cliente;
import br.com.pizzaria.entidade.Endereco;
import br.com.pizzaria.entidade.Pessoa;
import br.com.pizzaria.entidade.Telefone;
import br.com.pizzaria.entidade.Usuario;
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

@ManagedBean(name = "clienteC")
@SessionScoped
public class ClienteControle {
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private Endereco endereco;
    private Usuario usuario;
    private DataModel model;

    public Endereco getEndereco() {
        if(endereco == null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Cliente getCliente() {
        if(cliente == null){
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
    
    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        clienteDAO = new ClienteDAOImp();
        if (cliente.getId() == null) {
            cliente.setEndereco(endereco);
            //cliente.setUsuario(usuario);
            clienteDAO.salva(cliente);
            
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente salva com sucesso!", ""));
        } else {
            clienteDAO.altera(cliente);
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente alterada com sucesso!", ""));
        }
        limpar();
        return "pesqCliente";
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            clienteDAO = new ClienteDAOImp();
            cliente = (Cliente) model.getRowData();            
            clienteDAO.remove(cliente);
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cliente excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";

    }
    
    public String alterar() {
        cliente = (Cliente) model.getRowData();
        setCliente(cliente);
        return "cadCliente";
    }
    
    public String novoCliente() {        
        cliente = new Cliente();
        return "cadCliente";
    }

    private void limpar() {
        cliente = null;        
    }
    
    public String limpaPesquisa() {
        limpar();
        return "pesqCliente";
    }
    
        public void pesquisaLike() {
        clienteDAO = new ClienteDAOImp();
        List<Cliente> clientes = clienteDAO.pesquisaLikeNome(cliente.getNome());
        model = new ListDataModel(clientes);
    }
}
