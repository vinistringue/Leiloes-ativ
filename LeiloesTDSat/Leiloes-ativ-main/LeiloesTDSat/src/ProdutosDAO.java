
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    private String sql;

    ProdutosDAO(conectaDAO conexao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ProdutosDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            // Definir a consulta SQL para inserir os dados do produto
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

            // Preparar a declaração SQL
            prep = conn.prepareStatement(sql);

            // Definir os valores dos parâmetros da consulta
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            // Executar a operação de inserção
            prep.executeUpdate();
            // Exibir uma mensagem informando o sucesso do cadastro
            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {

            // Capturar exceções e exibir uma mensagem de erro
            System.err.println("Erro ao cadastrar o produto: " + e.getMessage());
        } finally {
            // Fechar a conexão com o banco de dados
            {
                try {
                    if (prep != null) {
                        prep.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }

        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

    public void venderProduto(int idProduto) {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, idProduto);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            // Close the resources (prep, resultset, and connection) here
        }
    }

    List<ProdutosDTO> listarProdutosVendidos() {
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor((int) resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));
                produtosVendidos.add(produto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            // Close the resources (prep, resultset, and connection) here
        }
        return produtosVendidos;
    }

    List<ProdutosDTO> listaTableVendas(String statusVenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
