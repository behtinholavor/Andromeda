package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import business.ProdutoBusiness;
import business.UnidadeBusiness;
import model.Produto;
import note.TitleNote;
import util.Global;
import util.TabelaModelo;

@SuppressWarnings("serial")
public class ProdutoView extends CadastroView {
	private Produto clsProduto;
	private ProdutoBusiness bsnProduto;
	private UnidadeBusiness bsnUnidade;
	private JLabel lblIdProduto;
	private JLabel lblProduto;
	private JTextField txtProduto;
	private JLabel lblCodProduto;
	private JTextField txtCodProduto;
	private JLabel lblPreco;
	private JTextField txtPreco;
	@SuppressWarnings("rawtypes")
	private JComboBox cbbUnidade;
	private JLabel lblDescricao;
	private JTextPane txtDescricao;
	private TableRowSorter<TableModel> sorCadastro;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoView view = new ProdutoView();
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProdutoView() {
		setTitle("Cadastro de Produtos");
		setType(Type.UTILITY);			
		setAlwaysOnTop(true);
		setResizable(false);		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\piece_small@1x.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 540);
		setLocationRelativeTo(null);		
		setContentPane(pnlCadastro);
		
		clsProduto = new Produto(psqCadastro);
		bsnProduto = new ProdutoBusiness();
		bsnUnidade = new UnidadeBusiness();
		
		lblIdProduto = new JLabel("ID");
		lblIdProduto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblIdProduto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblIdProduto.setBounds(709, 10, 65, 20);
		pnlForm.add(lblIdProduto);
		
		lblProduto = new JLabel("Descrição");
		lblProduto.setBounds(10, 10, 182, 20);
		lblProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblProduto);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(10, 35, 764, 28);
		txtProduto.setToolTipText("Descrição do produto");
		txtProduto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlForm.add(txtProduto);
		txtProduto.setColumns(10);
		
		lblCodProduto = new JLabel("Código interno");
		lblCodProduto.setBounds(10, 80, 116, 20);
		lblCodProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblCodProduto);
		
		txtCodProduto = new JTextField();
		txtCodProduto.setBounds(10, 105, 575, 28);
		txtCodProduto.setToolTipText("Código interno do produto");
		txtCodProduto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtCodProduto.setColumns(10);
		pnlForm.add(txtCodProduto);
				
		lblPreco = new JLabel("R$");
		lblPreco.setBounds(595, 80, 82, 20);
		lblPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(595, 105, 116, 28);
		txtPreco.setToolTipText("Preço do produto (R$)");
		txtPreco.setFont(new Font("Segoe UI", Font.PLAIN, 15));		
		pnlForm.add(txtPreco);
		
		cbbUnidade = new JComboBox();
		cbbUnidade.setToolTipText("Unidade do produto");
		cbbUnidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbbUnidade.setModel(new DefaultComboBoxModel(bsnUnidade.ColunaUnidade(2)));
		cbbUnidade.setBounds(721, 105, 53, 28);
		pnlForm.add(cbbUnidade);
		
		lblDescricao = new JLabel("Descrição auxiliar");
		lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 150, 156, 20);
		pnlForm.add(lblDescricao);
		
		txtDescricao = new JTextPane();
		txtDescricao.setToolTipText("Descrição auxiliar do produto");
		txtDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtDescricao.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescricao.setBounds(10, 175, 764, 153);
		pnlForm.add(txtDescricao);
		
		CadastroForm(false);
		
	}
	
	
	@Override
	public void CadastroInserir() {
		super.CadastroInserir();
		txtProduto.requestFocus();
	}

	@Override
	public void CadastroAlterar() {
		super.CadastroAlterar();
		txtProduto.requestFocus();		
	}
	
	@Override
	public boolean CadastroRemover() {			
		boolean flag = super.CadastroRemover();		
		if (flag == true) {			
			int id = Integer.parseInt(lblIdProduto.getText());					
			bsnProduto.DeletarProduto(id);					
			CadastroGrid(0);			
		}
		return flag;
	}
	
	@Override
	public boolean CadastroSalvar() {
		boolean flag = super.CadastroSalvar();		
		if (flag == true) {										
			clsProduto.setIdproduto(Integer.parseInt(lblIdProduto.getText()));
			clsProduto.setProduto(txtProduto.getText());
			clsProduto.setCodproduto(txtCodProduto.getText());
			clsProduto.setPreco(Float.parseFloat(txtPreco.getText().replaceAll(",", ".")));							
			clsProduto.setIdunidade(cbbUnidade.getSelectedIndex()+1);	
			clsProduto.setUnidade(cbbUnidade.getSelectedItem().toString());			
			clsProduto.setDescricao(txtDescricao.getText());
			
			if (lblIdProduto.getText().equals("0")) {
				clsProduto.setUscadast(Global.sessao.getUsuario().getLogin());
				clsProduto.setDtcadast(new Timestamp(new Date().getTime()));		
				bsnProduto.InserirProduto(clsProduto);							
			} else {								
				clsProduto.setUsmodifi(Global.sessao.getUsuario().getLogin());			
				clsProduto.setDtmodifi(new Timestamp(new Date().getTime()));			
				bsnProduto.AtualizarProduto(clsProduto);											
			}							
			CadastroGrid(lblIdProduto.getText().equals("0") ? 0 : tblCadastro.getSelectedRow());
		}
		return flag;							
	}		

	@Override
	public void CadastroCancelar() {
		super.CadastroCancelar();	
		tblCadastro.requestFocus();		
	}
	
	@Override
	public void CadastroGrid(int row) {
		super.CadastroGrid(row);
		Field[] fields = clsProduto.getClass().getDeclaredFields();						
		String[] colunas = new String[fields.length-1]; 											
		
		for (int i = 0; i < fields.length-1; i++) {												
			Annotation annotation = fields[i].getAnnotation(TitleNote.class);			
			if(annotation instanceof TitleNote) {			
				colunas[i] = ((TitleNote) annotation).value();						
			} else {			
				colunas[i] = fields[i].getName();			
			}
	    }	
						
		ArrayList<Object[]> linhas = bsnProduto.TabelaProduto();		
		TabelaModelo table = new TabelaModelo(linhas, colunas);
		
		tblCadastro.setModel(table);		
		tblCadastro.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblCadastro.getColumnModel().getColumn(0).setResizable(false);
		tblCadastro.getColumnModel().getColumn(0).setCellRenderer(CellRight);		
		tblCadastro.getColumnModel().getColumn(0).setHeaderRenderer(ColLeft);		
		
		tblCadastro.getColumnModel().getColumn(1).setPreferredWidth(400);
		tblCadastro.getColumnModel().getColumn(1).setResizable(false);
		tblCadastro.getColumnModel().getColumn(1).setCellRenderer(CellLeft);
		tblCadastro.getColumnModel().getColumn(1).setHeaderRenderer(ColLeft);
				
		tblCadastro.getColumnModel().getColumn(2).setPreferredWidth(125);
		tblCadastro.getColumnModel().getColumn(2).setResizable(false);
		tblCadastro.getColumnModel().getColumn(2).setCellRenderer(CellLeft);
		tblCadastro.getColumnModel().getColumn(2).setHeaderRenderer(ColLeft);
				
		tblCadastro.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblCadastro.getColumnModel().getColumn(3).setResizable(false);
		tblCadastro.getColumnModel().getColumn(3).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(3).setHeaderRenderer(ColCenter);
		tblCadastro.getColumnModel().getColumn(3).setMaxWidth(0);
		
		tblCadastro.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblCadastro.getColumnModel().getColumn(4).setResizable(false);
		tblCadastro.getColumnModel().getColumn(4).setCellRenderer(CellRight);
		tblCadastro.getColumnModel().getColumn(4).setHeaderRenderer(ColRight);		
		tblCadastro.getColumnModel().getColumn(4).setMaxWidth(0);
		
		tblCadastro.getColumnModel().getColumn(5).setPreferredWidth(45);
		tblCadastro.getColumnModel().getColumn(5).setResizable(false);
		tblCadastro.getColumnModel().getColumn(5).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(5).setHeaderRenderer(ColCenter);			
		
		tblCadastro.getColumnModel().getColumn(6).setPreferredWidth(75);
		tblCadastro.getColumnModel().getColumn(6).setResizable(false);
		tblCadastro.getColumnModel().getColumn(6).setCellRenderer(CellRight);
		tblCadastro.getColumnModel().getColumn(6).setHeaderRenderer(ColRight);		
		
		tblCadastro.getColumnModel().getColumn(7).setPreferredWidth(500);
		tblCadastro.getColumnModel().getColumn(7).setResizable(false);
		tblCadastro.getColumnModel().getColumn(7).setCellRenderer(CellLeft);
		tblCadastro.getColumnModel().getColumn(7).setHeaderRenderer(ColLeft);		
		
		tblCadastro.getColumnModel().getColumn(8).setPreferredWidth(100);		
		tblCadastro.getColumnModel().getColumn(8).setResizable(false);
		tblCadastro.getColumnModel().getColumn(8).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(8).setHeaderRenderer(ColCenter);		
				
		tblCadastro.getColumnModel().getColumn(9).setPreferredWidth(130);
		tblCadastro.getColumnModel().getColumn(9).setResizable(false);
		tblCadastro.getColumnModel().getColumn(9).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(9).setHeaderRenderer(ColCenter);	
				
		tblCadastro.getColumnModel().getColumn(10).setPreferredWidth(100);
		tblCadastro.getColumnModel().getColumn(10).setResizable(false);
		tblCadastro.getColumnModel().getColumn(10).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(10).setHeaderRenderer(ColCenter);				
		
		tblCadastro.getColumnModel().getColumn(11).setPreferredWidth(130);
		tblCadastro.getColumnModel().getColumn(11).setResizable(false);
		tblCadastro.getColumnModel().getColumn(11).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(11).setHeaderRenderer(ColCenter);
		
		tblCadastro.getTableHeader().setReorderingAllowed(false);
		tblCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tblCadastro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblCadastro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
		tblCadastro.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tblCadastro.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCadastro.setFillsViewportHeight(true);						
		tblCadastro.setCellSelectionEnabled(false); 		
		tblCadastro.setRowSelectionAllowed(true);	
		
		sorCadastro = new TableRowSorter<TableModel>(tblCadastro.getModel());
		sorCadastro.setRowFilter(null);
        tblCadastro.setRowSorter(sorCadastro);
        
        if (linhas.size() > 0) {
			tblCadastro.setRowSelectionInterval(row, row);
		}
		
	}

	@Override
	public void CadastroLoad() {
		//super.CadastroLoad();
		if ((tblCadastro.getRowCount() > 0) && (tblCadastro.getSelectedRow() >= 0)) {			
			lblIdProduto.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 0)));
			txtProduto.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 1)));
			txtCodProduto.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 2)));			
			cbbUnidade.setSelectedItem(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 5)));						
			txtPreco.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 6)));					
			txtDescricao.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 7)));		
		} else {
			CadastroClear();
		}
		
	}

	@Override
	public void CadastroClear() {
		super.CadastroClear();
		lblIdProduto.setText("0");
		txtProduto.setText("");
		txtCodProduto.setText("");
		txtPreco.setText("0.00");
		cbbUnidade.setSelectedIndex(1);		
		txtDescricao.setText("");
	}

	@Override
	public void CadastroPesquisar() {
		super.CadastroPesquisar();		
		if (btnPesquisar.isEnabled()) {
			PesquisaView frmPesquisa = new PesquisaView();
			frmPesquisa.setOwner(frmCadastro);
			frmPesquisa.setAssembly(clsProduto);	
			frmPesquisa.setSearch(psqCadastro);
			frmPesquisa.setVisible(true);					
		}		
	}
	
}
