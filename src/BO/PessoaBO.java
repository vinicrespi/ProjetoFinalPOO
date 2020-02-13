/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.PessoaDAO;
import DTO.PessoaDTO;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class PessoaBO {
    public boolean inserir(PessoaDTO pessoaDTO){
        if(existe(pessoaDTO)!=true){
            PessoaDAO pessoaDAO = new PessoaDAO();
            return pessoaDAO.inserir(pessoaDTO);
        }
        return false;
    }
    public boolean alterar(PessoaDTO pessoaDTO){
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.alterar(pessoaDTO);
    }
    public boolean excluir(PessoaDTO pessoaDTO){
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.excluir(pessoaDTO);
    }
    public PessoaDTO procurarPorNome(PessoaDTO pessoaDTO){
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.procurarPorNome(pessoaDTO);
    }
    public boolean existe(PessoaDTO pessoaDTO){
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.existe(pessoaDTO);

    }
    public List<PessoaDTO> PesquisarTodos(){
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.pesquisarTodos();
    }
    
}
