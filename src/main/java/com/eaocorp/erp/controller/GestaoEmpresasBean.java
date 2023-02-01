package com.eaocorp.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.eaocorp.erp.model.Empresa;
import com.eaocorp.erp.repository.Empresas;
import com.eaocorp.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Empresas empresas;
    
    private List<Empresa> listaEmpresas;
    
    @Inject
    private FacesMessages messages;
    
    private String termoPesquisa;
    
    public void pesquisar() {
    	 listaEmpresas = empresas.pesquisar(termoPesquisa);
         
         if (listaEmpresas.isEmpty()) {
             messages.info("Sua consulta não retornou registros.");
         }
    	
    }
    
    public void todasEmpresas() {
        listaEmpresas = empresas.todas();
    }
    
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
}