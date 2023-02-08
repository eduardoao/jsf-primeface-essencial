package com.eaocorp.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.eaocorp.erp.model.Empresa;
import com.eaocorp.erp.model.RamoAtividade;
import com.eaocorp.erp.model.TipoEmpresa;
import com.eaocorp.erp.repository.Empresas;
import com.eaocorp.erp.repository.RamoAtividades;
import com.eaocorp.erp.service.CadastroEmpresaService;
import com.eaocorp.erp.util.FacesMessages;

import org.primefaces.PrimeFaces;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	private List<Empresa> listaEmpresas;

	@Inject
	private FacesMessages messages;

	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private CadastroEmpresaService cadastroEmpresaService;

	private String termoPesquisa;

	private Converter ramoAtividadeConverter;

	private Empresa empresa;

	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void prepararEdicao( ) {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
		
		
	}

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

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}

		messages.info("Empresa salva com sucesso!");

		// Não funcionou #Issue 001
		PrimeFaces instance = PrimeFaces.current();
		instance.ajax().update(Arrays.asList("formCadastro:empresasDataTable", "formCadastro:messages"));
		// Não funcionou #Issue 001
	}

	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);

		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

		return listaRamoAtividades;
	}

	 public boolean isEmpresaSeleciona() {
	        return empresa != null && empresa.getId() != null;
	    }

}