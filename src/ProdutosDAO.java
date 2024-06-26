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
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    PreparedStatement prep = null;
    ResultSet rs;
    conectaDAO conectadao = new conectaDAO();
    Connection conn = conectadao.connectDB();

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listaProd = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO prod = new ProdutosDTO();
                prod.setId(rs.getInt(1)); 
                prod.setNome(rs.getString(2));
                prod.setValor(rs.getInt(3));
                prod.setStatus(rs.getString(4));
                listaProd.add(prod);

            }
            conectadao.desconectar();
            
            return listaProd;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
 

    public int cadastrarProduto(ProdutosDTO produto) {;
        int status;
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ? ,?)";
        try {

            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            conectadao.desconectar();
            System.out.println(status);
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            conectadao.desconectar();
            return ex.getErrorCode();
        }
    }

}
