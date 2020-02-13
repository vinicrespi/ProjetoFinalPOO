/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PessoaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import projetofinal.Conexao;

/**
 *
 * @author Vinicius
 */
public class PessoaDAO {

    final String NOMEDOBANCO = "projeto_poo";
    final String NOMEDATABELA = "pessoas";

    int cont;

    public static int getIdade(java.util.Date data) {
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(data);
        Calendar dataAtual = Calendar.getInstance();

        int diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);
        int diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);
        int idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

        if (diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
            idade--;
        }

        return idade;
    }
    public boolean inserir(PessoaDTO pessoaDTO){
        try{
            Connection conn = Conexao.conectar(NOMEDOBANCO);
            String sql= "INSERT INTO " + NOMEDATABELA + " (nome,conjuge,datanasc,idade,rg,cpf"+ ") VALUES(?,?,?,?,?,?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, pessoaDTO.getNome());
                ps.setString(2, pessoaDTO.getConjuge());
                ps.setDate(3, pessoaDTO.getDataNasc());
                ps.setInt(4, pessoaDTO.getIdade());
                ps.setString(5, pessoaDTO.getRg());
                ps.setString(6, pessoaDTO.getCpf());
                ps.executeUpdate();
                ps.close();
                conn.close();
                
            return true;
        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }
     public boolean alterar(PessoaDTO pessoaDTO){
            try{
            Connection conn = Conexao.conectar(NOMEDOBANCO);
            String sql= "UPDATE " + NOMEDATABELA + " SET nome, conjuge=?, datanasc=?, idade=?, rg=?, cpf=? WHERE nome=?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, pessoaDTO.getNome());
                ps.setString(2, pessoaDTO.getConjuge());
                ps.setDate(3, pessoaDTO.getDataNasc());
                ps.setInt(4, pessoaDTO.getIdade());
                ps.setString(5, pessoaDTO.getRg());
                ps.setString(6, pessoaDTO.getCpf());
                ps.executeUpdate();
                ps.close();
                conn.close();
                
            return true;
        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
            e.printStackTrace();
            return false;
        }
     }
     public boolean excluir(PessoaDTO pessoaDTO){
         try{
             Connection conn = Conexao.conectar(NOMEDOBANCO);
             String sql = "DELETE FROM " + NOMEDATABELA + " WHERE nome=?;";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, pessoaDTO.getNome());
             ps.executeUpdate();
             ps.close();
             conn.close();
             return true;
         }catch(Exception e){
             System.err.println("Erro: " + e.toString());
             e.printStackTrace();
             return false;
         }
     }
     public PessoaDTO procurarPorNome(PessoaDTO pessoaDTO){
         try{
             Connection conn = Conexao.conectar(NOMEDOBANCO);
             String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome=?;";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, pessoaDTO.getNome());
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 PessoaDTO obj = new PessoaDTO();
                 obj.setNome(rs.getString(1));
                 obj.setConjuge(rs.getString(2));
                 obj.setDataNasc(rs.getDate(3));
                 obj.setIdade(rs.getInt(4));
                 obj.setRg(rs.getString(5));
                 obj.setCpf(rs.getString(6));
                 ps.close();
                 rs.close();
                 conn.close();
                 return obj;
             }else{
                 ps.close();
                 rs.close();
                 conn.close();
                 return null;
             }
             
         }catch(Exception e){
             return null;
         }
     }
     public boolean existe(PessoaDTO pessoaDTO){
    try{
        Connection conn = Conexao.conectar(NOMEDOBANCO);
        String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pessoaDTO.getNome());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            ps.close();
            rs.close();
            conn.close();
            return true;
        }
    }catch(Exception e){
        System.err.println("Erro: " + e.toString());
        e.printStackTrace();
        return false;
    }
    return false;
}
     public List<PessoaDTO> pesquisarTodos(){
    try{
        Connection conn = Conexao.conectar(NOMEDOBANCO);
        String sql = "SELECT * FROM " + NOMEDATABELA + ";";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<PessoaDTO> listObj = montarLista(rs);
        return listObj;
    }catch(Exception e){
        System.err.println("Erro: " + e.toString());
        e.printStackTrace();
        return null;
    }
}

public List<PessoaDTO> montarLista(ResultSet rs){
    List<PessoaDTO> listObj = new ArrayList<PessoaDTO>();
    try{
        while(rs.next()){
            PessoaDTO obj = new PessoaDTO();
            obj.setNome(rs.getString(1));
            obj.setConjuge(rs.getString(2));
            obj.setDataNasc(rs.getDate(3));
            obj.setIdade(rs.getInt(4));
            obj.setRg(rs.getString(5));
            obj.setCpf(rs.getString(6));
            listObj.add(obj);
        }
        return listObj;
    }catch(Exception e){
        System.err.println("Erro: " + e.toString());
        e.printStackTrace();
        return null;
    }

}




}
