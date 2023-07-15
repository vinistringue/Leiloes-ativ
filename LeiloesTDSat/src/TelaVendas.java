import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaVendas extends JFrame {

    private JTable tabelaProdutos;

    public TelaVendas() {
        setTitle("Tela de Vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Cria a tabela de produtos
        tabelaProdutos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        // Obtém a lista de produtos vendidos
        ArrayList<ProdutosDTO> produtosVendidos = listarProdutosVendidos();

        // Cria o modelo da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        // Adicione outras colunas necessárias

        // Preenche a tabela com os produtos vendidos
        for (ProdutosDTO produto : produtosVendidos) {
            model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    // Adicione outras colunas necessárias
            });
        }

        tabelaProdutos.setModel(model);
    }

    private ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ProdutosDAO produtosDAO = new ProdutosDAO();
        return produtosDAO.listarProdutosVendidos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaVendas telaVendas = new TelaVendas();
            telaVendas.setVisible(true);
        });
    }
}
