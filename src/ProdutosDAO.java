/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        listagem.clear();
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            while(resultset.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                listagem.add(p);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos.");
        }
        
        return listagem;
    }
    public void venderProduto(int id){
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement("UPDATE produtos SET status = \"Vendido\" WHERE id = ?");
            prep.setInt(1, id);
            prep.executeQuery();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso.");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao definir como vendido.");
        }
    }
    
    
    
        
}

