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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import business.VeiculoBusiness;
import model.Veiculo;
import note.TitleNote;
import util.Global;
import util.TabelaModelo;

@SuppressWarnings("serial")
public class VeiculoView extends CadastroView {
	protected Veiculo clsVeiculo;
	protected VeiculoBusiness bsnVeiculo;
	private JLabel lblIdVeiculo;
	private JLabel lblPlaca;
	private JTextField txtPlaca;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JLabel lblModelo;
	private JTextField txtModelo;
	private TableRowSorter<TableModel> sorCadastro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VeiculoView view = new VeiculoView();
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
	public VeiculoView() {
		tblCadastro.setName("tblCadastro");
		setTitle("Cadastro de Veículos");
		setType(Type.UTILITY);			
		setAlwaysOnTop(true);
		setResizable(false);		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\piece_small@1x.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 540);
		setLocationRelativeTo(null);
		setContentPane(pnlCadastro);
		
		clsVeiculo = new Veiculo(psqCadastro);
		bsnVeiculo = new VeiculoBusiness();
		
		lblIdVeiculo = new JLabel("ID");
		lblIdVeiculo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblIdVeiculo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblIdVeiculo.setBounds(709, 10, 65, 20);
		pnlForm.add(lblIdVeiculo);
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 10, 90, 20);
		lblPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(10, 35, 167, 28);
		txtPlaca.setToolTipText("Placa do veiculo");
		txtPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlForm.add(txtPlaca);
		txtPlaca.setColumns(10);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(187, 10, 90, 20);
		lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(187, 35, 254, 28);
		txtMarca.setToolTipText("Marca do veiculo");
		txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlForm.add(txtMarca);
		txtMarca.setColumns(10);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(451, 10, 90, 20);
		lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pnlForm.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(451, 35, 323, 28);
		txtModelo.setToolTipText("Modelo do veiculo");
		txtModelo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlForm.add(txtModelo);
		txtModelo.setColumns(10);
		
		CadastroForm(false);

	}
	
	@Override
	public void CadastroInserir() {
		super.CadastroInserir();
		txtPlaca.requestFocus();
	}
	
	@Override
	public void CadastroAlterar() {
		super.CadastroAlterar();
		txtPlaca.requestFocus();
	}
	
	@Override
	public boolean CadastroRemover() {
		boolean flag = super.CadastroRemover();		
		if (flag == true) {			
			int id = Integer.parseInt(lblIdVeiculo.getText());					
			bsnVeiculo.DeletarVeiculo(id);					
			CadastroGrid(0);			
		}
		return flag;
		
	}
	
	@Override
	public boolean CadastroSalvar() {
		boolean flag = super.CadastroSalvar();		
		if (flag == true) {			
			clsVeiculo.setIdveiculo(Integer.parseInt(lblIdVeiculo.getText()));			
			clsVeiculo.setPlaca(txtPlaca.getText());
			clsVeiculo.setMarca(txtMarca.getText());
			clsVeiculo.setModelo(txtModelo.getText());			
			
			if (lblIdVeiculo.getText().equals("0")) {
				clsVeiculo.setUscadast(Global.sessao.getUsuario().getLogin());
				clsVeiculo.setDtcadast(new Timestamp(new Date().getTime()));		
				bsnVeiculo.InserirVeiculo(clsVeiculo);							
			} else {								
				clsVeiculo.setUsmodifi(Global.sessao.getUsuario().getLogin());			
				clsVeiculo.setDtmodifi(new Timestamp(new Date().getTime()));			
				bsnVeiculo.AtualizarVeiculo(clsVeiculo);											
			}				
			CadastroGrid(lblIdVeiculo.getText().equals("0") ? 0 : tblCadastro.getSelectedRow());	
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
		Field[] fields = clsVeiculo.getClass().getDeclaredFields();						
		String[] colunas = new String[fields.length-1]; 											
		for (int i = 0; i < fields.length-1; i++) {												
			Annotation annotation = fields[i].getAnnotation(TitleNote.class);			
			if(annotation instanceof TitleNote) {			
				colunas[i] = ((TitleNote) annotation).value();						
			} else {			
				colunas[i] = fields[i].getName();			
			}
	    }	
		
		ArrayList<Object[]> linhas = bsnVeiculo.TabelaVeiculo();		
		TabelaModelo table = new TabelaModelo(linhas, colunas);
		
		tblCadastro.setModel(table);		
		tblCadastro.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblCadastro.getColumnModel().getColumn(0).setResizable(false);
		tblCadastro.getColumnModel().getColumn(0).setCellRenderer(CellRight);		
		tblCadastro.getColumnModel().getColumn(0).setHeaderRenderer(ColLeft);
		
		tblCadastro.getColumnModel().getColumn(1).setPreferredWidth(65);
		tblCadastro.getColumnModel().getColumn(1).setResizable(false);
		tblCadastro.getColumnModel().getColumn(1).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(1).setHeaderRenderer(ColCenter);
		
		tblCadastro.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblCadastro.getColumnModel().getColumn(2).setResizable(false);
		tblCadastro.getColumnModel().getColumn(2).setCellRenderer(CellLeft);
		tblCadastro.getColumnModel().getColumn(2).setHeaderRenderer(ColLeft);
				
		tblCadastro.getColumnModel().getColumn(3).setPreferredWidth(220);
		tblCadastro.getColumnModel().getColumn(3).setResizable(false);
		tblCadastro.getColumnModel().getColumn(3).setCellRenderer(CellLeft);
		tblCadastro.getColumnModel().getColumn(3).setHeaderRenderer(ColLeft);		
				
		tblCadastro.getColumnModel().getColumn(4).setPreferredWidth(100);		
		tblCadastro.getColumnModel().getColumn(4).setResizable(false);
		tblCadastro.getColumnModel().getColumn(4).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(4).setHeaderRenderer(ColCenter);		
				
		tblCadastro.getColumnModel().getColumn(5).setPreferredWidth(130);
		tblCadastro.getColumnModel().getColumn(5).setResizable(false);
		tblCadastro.getColumnModel().getColumn(5).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(5).setHeaderRenderer(ColCenter);	
				
		tblCadastro.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblCadastro.getColumnModel().getColumn(6).setResizable(false);
		tblCadastro.getColumnModel().getColumn(6).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(6).setHeaderRenderer(ColCenter);				
		
		tblCadastro.getColumnModel().getColumn(7).setPreferredWidth(130);
		tblCadastro.getColumnModel().getColumn(7).setResizable(false);
		tblCadastro.getColumnModel().getColumn(7).setCellRenderer(CellCenter);
		tblCadastro.getColumnModel().getColumn(7).setHeaderRenderer(ColCenter);
		
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
			lblIdVeiculo.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 0)));
			txtPlaca.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 1)));
			txtMarca.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 2)));
			txtModelo.setText(String.valueOf(tblCadastro.getValueAt(tblCadastro.getSelectedRow(), 3)));
		} else {
			CadastroClear();						
		}				
	}
	
	@Override
	public void CadastroClear() { 
		super.CadastroClear();
		lblIdVeiculo.setText("0");
		txtPlaca.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		
	}
	
	@Override
	public void CadastroPesquisar() {
		super.CadastroPesquisar();		
		if (btnPesquisar.isEnabled()) {
			PesquisaView frmPesquisa = new PesquisaView();
			frmPesquisa.setOwner(frmCadastro);
			frmPesquisa.setAssembly(clsVeiculo);	
			frmPesquisa.setSearch(psqCadastro);
			frmPesquisa.setVisible(true);					
		}		
	}
	

}
