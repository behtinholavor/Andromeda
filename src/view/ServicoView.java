package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import business.ClienteBusiness;
import business.MoedaBusiness;
import business.ProdutoBusiness;
import business.ServicoBusiness;
import business.VeiculoBusiness;
import model.Cliente;
import model.Pesquisa;
import model.Produto;
import model.Servico;
import model.ServicoProduto;
import model.State;
import model.Veiculo;
import note.TitleNote;
import util.Global;
import util.Numero.NumeroFormato;
import util.Numero.NumeroLimite;
import util.TabelaModelo;

@SuppressWarnings("serial")
public class ServicoView extends FormView {
	public ServicoView frmServico;
	private MoedaBusiness bsnMoeda;
	private JPanel pnlOs;
	private JPanel pnlServico;
	private JFormattedTextField txtSubtotal;
	private JFormattedTextField txtAcrescimo;
	private JFormattedTextField txtDesconto;
	private JFormattedTextField txtTotal;
	@SuppressWarnings("rawtypes")
	private JComboBox cbbMoeda;
	private JPanel pnlMoeda;
	private JLabel lblSubtotal;
	private JLabel lblAcrescimoPerc;
	private JLabel lblDescontoPerc;
	private JLabel lblTotal;
	private JLabel lblAcrescimo;
	private JLabel lblDesconto;
	private JLabel lblMoeda;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private JFormattedTextField txtPlaca;
	private JLabel lblPlaca;
	private JLabel lblFone;
	private JFormattedTextField txtFone;
	private JTabbedPane tbpServico;
	private JPanel tbsConsulta;
	private JTable tblProduto;
	private JScrollPane spnGrid;
	private JPanel tbsTempo;
	private JButton btnPlaca;
	private JButton btnCliente;
	private JFormattedTextField txtCliente;
	private JLabel lblCliente;
	private JPanel tbsObs;
	private Cliente clsCliente;
	private Produto clsProduto;
	private Veiculo clsVeiculo;
	private Servico clsServico;
	private ServicoProduto clsServicoProduto;
	private Pesquisa psqCliente;
	private Pesquisa psqProduto;
	private Pesquisa psqVeiculo;
	private Pesquisa psqServico;
	private ClienteBusiness bsnCliente;
	private ProdutoBusiness bsnProduto;
	private VeiculoBusiness bsnVeiculo;
	private ServicoBusiness bsnServico;
	private JScrollPane spnObs;
	private JTextPane txtObs;
	private JButton btnInserir;
	private JButton btnPesquisar;
	private JButton btnSalvar;
	private JButton btnVoltar;
	public State sttServico;
	private JLabel lblOS;	
	private TableRowSorter<TableModel> sorProduto;	
	private TabelaModelo tbmProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServicoView view = new ServicoView();
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ServicoView() {
		setTitle("Ordem de Serviço");
		setAlwaysOnTop(true);
		setResizable(false);		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\piece_small@1x.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 615);
		setLocationRelativeTo(null);
		setContentPane(pnlForm);

		frmServico  = this;			
		sttServico  = new State(0);
		
		psqCliente 	= new Pesquisa();
		psqProduto	= new Pesquisa(); 
		psqVeiculo	= new Pesquisa();
		psqServico	= new Pesquisa();
		
		clsCliente  = new Cliente(psqCliente);
		clsProduto  = new Produto(psqProduto);
		clsVeiculo  = new Veiculo(psqVeiculo);				
		clsServico	= new Servico(psqServico);
		clsServicoProduto = new ServicoProduto();
		
		bsnCliente	= new ClienteBusiness();  
		bsnProduto	= new ProdutoBusiness();
		bsnVeiculo	= new VeiculoBusiness();
		bsnServico	= new ServicoBusiness();
		bsnMoeda    = new MoedaBusiness();
		
		sorProduto 	= new TableRowSorter<TableModel>();
		tbmProduto	= new TabelaModelo();		
		
		pnlOs = new JPanel();
		pnlOs.setName("pnlCliente");
		pnlOs.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlOs.setBounds(5, 6, 794, 60);
		pnlOs.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlForm.add(pnlOs);
		pnlOs.setLayout(null);
		
		lblOS = new JLabel("");
		lblOS.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblOS.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblOS.setBounds(717, 5, 65, 20);
		pnlOs.add(lblOS);
		
		lblData = new JLabel("Data");
		lblData.setHorizontalTextPosition(SwingConstants.CENTER);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblData.setBounds(10, 5, 94, 20);
		pnlOs.add(lblData);
		
		try {
			txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtData.setText("");
		txtData.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtData.setToolTipText("Data da ordem de servi\u00E7o");
		txtData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtData.setBounds(16, 29, 94, 25);
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		pnlOs.add(txtData);
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPlaca.setBounds(106, 5, 96, 20);		
		pnlOs.add(lblPlaca);
		
		try {
			txtPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		txtPlaca.setToolTipText("Placa do ve\u00EDculo");
		txtPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(111, 29, 96, 25);
		txtPlaca.setHorizontalAlignment(SwingConstants.CENTER);		
		pnlOs.add(txtPlaca);
		
		btnPlaca = new JButton("");		
		btnPlaca.setToolTipText("Pesquisa de ve\u00EDculos");
		btnPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnPlaca.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\find_small@1x.png"));
		btnPlaca.setBounds(207, 29, 25, 24);
		btnPlaca.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtPlaca.isEditable()) {
					PesquisaView frmPesquisa = new PesquisaView();
					frmPesquisa.setOwner(frmServico);					
					frmPesquisa.setAssembly(clsVeiculo);
					frmPesquisa.setSearch(psqVeiculo);
					frmPesquisa.setVisible(true);
				}
			}
		});
		pnlOs.add(btnPlaca);

		lblFone = new JLabel("Fone");
		lblFone.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFone.setHorizontalAlignment(SwingConstants.LEFT);
		lblFone.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFone.setBounds(234, 5, 116, 20);
		pnlOs.add(lblFone);
		
		try {
			txtFone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtFone.setHorizontalAlignment(SwingConstants.CENTER);
		txtFone.setText("");
		txtFone.setToolTipText("Telefone do cliente");
		txtFone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtFone.setBounds(234, 29, 116, 25);
		pnlOs.add(txtFone);
		
		lblCliente = new JLabel("Nome");
		lblCliente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCliente.setBounds(351, 5, 116, 20);
		pnlOs.add(lblCliente);
		
		txtCliente = new JFormattedTextField();
		txtCliente.setToolTipText("Nome do cliente");
		txtCliente.setHorizontalAlignment(SwingConstants.LEFT);
		txtCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCliente.setBounds(351, 29, 406, 25);
		pnlOs.add(txtCliente);
		
		btnCliente = new JButton("");
		btnCliente.setToolTipText("Pesquisa de Clientes");
		btnCliente.setName("btnCliente");
		btnCliente.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\find_small@1x.png"));
		btnCliente.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCliente.setBounds(757, 29, 25, 24);
		btnCliente.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (txtFone.isEditable() && txtCliente.isEditable()) {					
					PesquisaView frmPesquisa = new PesquisaView();
					frmPesquisa.setOwner(frmServico);
					frmPesquisa.setAssembly(clsCliente);	
					frmPesquisa.setSearch(psqCliente);
					frmPesquisa.setVisible(true);
				}
			}
		});
		pnlOs.add(btnCliente);
		
		pnlServico = new JPanel();
		pnlServico.setName("pnlProduto");
		pnlServico.setBounds(5, 70, 794, 295);
		pnlServico.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlForm.add(pnlServico);
		pnlServico.setLayout(null);
		
		tbpServico = new JTabbedPane(JTabbedPane.BOTTOM);
		tbpServico.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tbpServico.setBounds(0, 0, 794, 295);
		tbpServico.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlServico.add(tbpServico);
		
		tbsConsulta = new JPanel();
		tbsConsulta.setRequestFocusEnabled(false);
		tbsConsulta.setFocusTraversalKeysEnabled(false);
		tbsConsulta.setAlignmentY(Component.TOP_ALIGNMENT);
		tbsConsulta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tbsConsulta.setBorder(null);
		tbsConsulta.setToolTipText("Produtos/servi\u00E7os da OS");
		tbsConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tbpServico.addTab("", new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\table_small@1x.png"), tbsConsulta, "");
		tbpServico.setEnabledAt(0, true);
		tbsConsulta.setLayout(null);
		
		tblProduto = new JTable();
		tblProduto.setBorder(null);
		tblProduto.setAlignmentY(Component.TOP_ALIGNMENT);
		tblProduto.setAlignmentX(Component.LEFT_ALIGNMENT);
		tblProduto.setBounds(0, 0, 0, 0);
		spnGrid = new JScrollPane(tblProduto);
		spnGrid.setFocusTraversalKeysEnabled(false);
		spnGrid.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spnGrid.setAlignmentY(Component.TOP_ALIGNMENT);
		spnGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
		spnGrid.setBorder(null);
		spnGrid.setLocation(0, 0);
		spnGrid.setSize(790, 265);
		tbsConsulta.add(spnGrid);				
		tblProduto.setBounds(10, 11, 764, 140);
		
		tbsTempo = new JPanel();
		tbsTempo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tbpServico.addTab("", new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\clock_small@1x.png"), tbsTempo, null);
		
		tbsObs = new JPanel();
		tbsObs.setFocusTraversalKeysEnabled(false);
		tbsObs.setAlignmentY(0.0f);
		tbsObs.setAlignmentX(0.0f);
		tbsObs.setBorder(null);
		tbsObs.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tbpServico.addTab("", new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\document-text_small@1x.png"), tbsObs, null);
		tbsObs.setLayout(null);
		
		spnObs = new JScrollPane();
		spnObs.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		spnObs.setBounds(0, 0, 790, 265);
		tbsObs.add(spnObs);		
		
		txtObs = new JTextPane();
		txtObs.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		txtObs.setBorder(new LineBorder(new Color(0, 0, 0)));
		spnObs.setViewportView(txtObs);
		
		pnlMoeda = new JPanel();
		pnlMoeda.setName("pnlMoeda");
		pnlMoeda.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlMoeda.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		pnlMoeda.setBounds(5, 494, 794, 85);
		pnlForm.add(pnlMoeda);
		pnlMoeda.setLayout(null);
						
		txtSubtotal = new JFormattedTextField(Global.funcao.getNumero().numberFormat(new NumeroFormato("#.00"), new NumeroLimite(0,999999999)));
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setEditable(false);
		txtSubtotal.setToolTipText("Subtotal do servi\u00E7o");
		txtSubtotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtSubtotal.setBounds(10, 28, 116, 25);
		pnlMoeda.add(txtSubtotal);
		
		lblSubtotal = new JLabel("Subtotal ($)");
		lblSubtotal.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSubtotal.setBounds(10, 5, 116, 20);
		pnlMoeda.add(lblSubtotal);
		
		lblAcrescimoPerc = new JLabel("Acr\u00E9scimo (%)");
		lblAcrescimoPerc.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAcrescimoPerc.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrescimoPerc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAcrescimoPerc.setBounds(130, 5, 116, 20);
		pnlMoeda.add(lblAcrescimoPerc);
		
		txtAcrescimo = new JFormattedTextField(Global.funcao.getNumero().numberFormat(new NumeroFormato("#.00"), new NumeroLimite(0,100)));
		txtAcrescimo.setHorizontalAlignment(SwingConstants.CENTER);
		txtAcrescimo.setToolTipText("Acr\u00E9scimo %");
		txtAcrescimo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtAcrescimo.setBounds(130, 28, 116, 25);
		pnlMoeda.add(txtAcrescimo);
		
		lblAcrescimo = new JLabel("Acr\u00E9scimo R$");
		lblAcrescimo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAcrescimo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrescimo.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblAcrescimo.setBounds(130, 60, 116, 14);
		pnlMoeda.add(lblAcrescimo);
		
		lblDescontoPerc = new JLabel("Desconto (%)");
		lblDescontoPerc.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescontoPerc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescontoPerc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescontoPerc.setBounds(250, 5, 116, 20);
		pnlMoeda.add(lblDescontoPerc);
				 
		txtDesconto = new JFormattedTextField(Global.funcao.getNumero().numberFormat(new NumeroFormato("#.00"), new NumeroLimite(0,99.99)));
		txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesconto.setToolTipText("Desconto %");
		txtDesconto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDesconto.setBounds(250, 28, 116, 25);
		pnlMoeda.add(txtDesconto);
		
		lblDesconto = new JLabel("Desconto R$");
		lblDesconto.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesconto.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblDesconto.setBounds(250, 60, 116, 14);
		pnlMoeda.add(lblDesconto);
		
		lblTotal = new JLabel("Total ($)");
		lblTotal.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTotal.setBounds(370, 5, 116, 20);
		pnlMoeda.add(lblTotal);
		
		txtTotal = new JFormattedTextField(Global.funcao.getNumero().numberFormat(new NumeroFormato("#.00"), new NumeroLimite(0,999999999)));
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setToolTipText("Total do servi\u00E7o");
		txtTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTotal.setBounds(370, 28, 116, 25);
		pnlMoeda.add(txtTotal);
		
		cbbMoeda = new JComboBox();
		cbbMoeda.setToolTipText("Moeda do pagamento");
		cbbMoeda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbMoeda.setModel(new DefaultComboBoxModel(bsnMoeda.ColunaMoeda(1)));
		cbbMoeda.setBounds(490, 28, 175, 25);
		pnlMoeda.add(cbbMoeda);
		
		lblMoeda = new JLabel("Moeda");
		lblMoeda.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoeda.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMoeda.setBounds(490, 5, 165, 20);
		pnlMoeda.add(lblMoeda);
		
		btnInserir = new JButton("");
		btnInserir.setToolTipText("Inserir OS");
		btnInserir.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\symbol-add_small_red@1x.png"));
		btnInserir.setName("");
		btnInserir.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnInserir.setBounds(728, 28, 25, 24);
		btnInserir.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormForm(true, null);
				FormAction(2);
				sttServico.setState(1);
				
			}
		});
		
		btnVoltar = new JButton("");
		btnVoltar.setToolTipText("Voltar");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\command-undo-gray_small@1x.png"));
		btnVoltar.setName("");
		btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnVoltar.setBounds(670, 28, 25, 24);
		btnVoltar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FormForm(false, null);
				FormClear(pnlOs);
				FormClear(pnlServico);
				FormClear(pnlMoeda);				
				
				getProdutosSer(false);
				FormAction(0);	
				sttServico.setState(0);					
			}
		});
		pnlMoeda.add(btnVoltar);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setToolTipText("Pesquisar OS");
		btnPesquisar.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\find_small@1x.png"));
		btnPesquisar.setName("");
		btnPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnPesquisar.setBounds(699, 28, 25, 24);
		btnPesquisar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (btnPesquisar.isEnabled()) {
					PesquisaView frmPesquisa = new PesquisaView();
					frmPesquisa.setOwner(frmServico);
					frmPesquisa.setAssembly(clsServico);	
					frmPesquisa.setSearch(psqServico);
					frmPesquisa.setVisible(true);					
				}
			}
		});
		pnlMoeda.add(btnPesquisar);
		pnlMoeda.add(btnInserir);
		
		btnSalvar = new JButton("");
		btnSalvar.setToolTipText("Salvar OS");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\symbol-ok_small@1x.png"));
		btnSalvar.setName("");
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSalvar.setBounds(757, 28, 25, 24);
		pnlMoeda.add(btnSalvar);
		
		FormForm(false, null);
		FormClear(pnlOs);
		FormClear(pnlServico);
		FormClear(pnlMoeda);		
		FormAction(0);							
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void show() { 
		super.show();		
		if (getOwner() != null) {
			getOwner().setVisible(false);
		}		
		getCliente();
		getProduto();
		getVeiculo();	
		getServico();
	}	
	
	@Override
	public void dispose() { 
		super.dispose();		
		if (getOwner() != null) {
			getOwner().setVisible(true);
		}					
	}	

	@Override
	public void FormAction(int action) {
		super.FormAction(action);
		switch (action) {
			case 0: //Voltar
				btnVoltar.setEnabled(true);
				btnPesquisar.setEnabled(true);
				btnInserir.setEnabled(true);
				btnSalvar.setEnabled(false);
				break;		
			case 1: //Pesquisar
				btnVoltar.setEnabled(true);
				btnPesquisar.setEnabled(false);
				btnInserir.setEnabled(false);
				btnSalvar.setEnabled(true);
				break;
			case 2: //Inserir
				btnVoltar.setEnabled(true);
				btnPesquisar.setEnabled(false);
				btnInserir.setEnabled(false);
				btnSalvar.setEnabled(true);
				break;				
			case 3: //Salvar
				btnVoltar.setEnabled(true);
				btnPesquisar.setEnabled(true);
				btnInserir.setEnabled(true);
				btnSalvar.setEnabled(false);
				break;
		}
	}
	
	
	public void getCliente() {		
		if ((psqCliente.getRetorno() != null) && (psqCliente.getRetorno() != "")) {
			clsCliente = bsnCliente.SetarCliente(Integer.parseInt(psqCliente.getRetorno()));	
			if (clsCliente.getIdcliente() > 0) { 
				txtFone.setText(clsCliente.getFone());			
				txtCliente.setText(clsCliente.getNome().toUpperCase());
			} else {
				txtFone.setText("");
				txtCliente.setText("");
			}
			psqCliente.setRetorno(null);
		}		
	}

	public void getProduto() {
		if ((psqProduto.getRetorno() != null) && (psqProduto.getRetorno() != "")) {
			clsProduto = bsnProduto.SetarProduto(Integer.parseInt(psqProduto.getRetorno()));
			if (clsProduto.getIdproduto() > 0) { 
				
			} else {
				
			}
			psqProduto.setRetorno(null);
		}
	}
	
	public void getVeiculo() {
		if ((psqVeiculo.getRetorno() != null) && (psqVeiculo.getRetorno() != "")) {
			clsVeiculo = bsnVeiculo.SetarVeiculo(Integer.parseInt(psqVeiculo.getRetorno()));
			if (clsVeiculo.getIdveiculo() > 0) { 
				txtPlaca.setText(clsVeiculo.getPlaca());
			} else {
				txtPlaca.setText("");
			}
			psqVeiculo.setRetorno(null);
		}
	}
	
	public void getServico() {
		if ((psqServico.getRetorno() != null) && (psqServico.getRetorno() != "")) {
			clsServico = bsnServico.SetarServico(Integer.parseInt(psqServico.getRetorno()));
			if (clsServico.getIdveiculo() > 0) { 
				lblOS.setText(String.valueOf(clsServico.getIdservico()));
				
				if (clsServico.getDtservico() != null) {							
//					txtData.setText(dtFormat.format(clsServico.getDtservico()));					
				}
				
				if (clsServico.getIdcliente() > 0) {
					psqCliente.setRetorno(String.valueOf(clsServico.getIdcliente()));
					getCliente();
				}

				if (clsServico.getIdveiculo() > 0) {
					psqVeiculo.setRetorno(String.valueOf(clsServico.getIdveiculo()));
					getVeiculo();
				}	
				
//				txtSubtotal.setText(flFormat.format(clsServico.getValor()));
//				txtAcrescimo.setText(dlFormat.format(clsServico.getAcrescimoperc()));				
//				lblAcrescimo.setText(flFormat.format(clsServico.getAcrescimo()));
//				txtDesconto.setText(dlFormat.format(clsServico.getDescontoperc()));
//				lblDesconto.setText(flFormat.format(clsServico.getDesconto()));
//				txtTotal.setText(flFormat.format(clsServico.getTotal()));								
//				cbbMoeda.setSelectedItem(clsServico.getMoeda());
				
				if (clsServico.getServicoProduto().size() > 0) {					
					clsServicoProduto = clsServico.getServicoProduto().get(0);
					getProdutosSer(true);
				} // Lembrar de inserir registro novo! 								
				
				FormForm(true, pnlMoeda);							
				FormAction(1);
				sttServico.setState(2);
			} else {
				lblOS.setText("");		
				
				FormAction(0);	
				sttServico.setState(0);	
			}
			psqServico.setRetorno(null);
		}				
	}
	
	public String[] getProdutosCol() {
		Field[] fields = clsServicoProduto.getClass().getDeclaredFields();					
		String[] colunas = new String[fields.length];					
		
		for (int i = 0; i < fields.length; i++) {												
			Annotation annotation = fields[i].getAnnotation(TitleNote.class);			
			if(annotation instanceof TitleNote) {			
				colunas[i] = ((TitleNote) annotation).value();						
			} else {			
				colunas[i] = fields[i].getName();			
			}
	    }		
		return colunas;
	}
	
	public ArrayList<Object[]> getProdutosLin(boolean clear) {		
		ArrayList<Object[]> linhas = clear == true ? bsnServico.TabelaServicoProduto() : new ArrayList<Object[]>();;
		return linhas;
	}
	
	public void getProdutosSer(boolean clear) {					
		if (clsServicoProduto != null) {
			tbmProduto.setColunas(getProdutosCol());
			tbmProduto.setLinhas(getProdutosLin(clear));
		
			tblProduto.setModel(tbmProduto);		
			tblProduto.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblProduto.getColumnModel().getColumn(0).setResizable(false);
			tblProduto.getColumnModel().getColumn(0).setCellRenderer(tbmProduto.CellRight);		
			tblProduto.getColumnModel().getColumn(0).setHeaderRenderer(tbmProduto.ColLeft);		
			
			tblProduto.getColumnModel().getColumn(1).setPreferredWidth(0);
			tblProduto.getColumnModel().getColumn(1).setResizable(false);
			tblProduto.getColumnModel().getColumn(1).setCellRenderer(tbmProduto.CellRight);
			tblProduto.getColumnModel().getColumn(1).setHeaderRenderer(tbmProduto.ColLeft);
			tblProduto.getColumnModel().getColumn(1).setMaxWidth(0);
			
			tblProduto.getColumnModel().getColumn(2).setPreferredWidth(40);
			tblProduto.getColumnModel().getColumn(2).setResizable(false);
			tblProduto.getColumnModel().getColumn(2).setCellRenderer(tbmProduto.CellRight);
			tblProduto.getColumnModel().getColumn(2).setHeaderRenderer(tbmProduto.ColLeft);
					
			tblProduto.getColumnModel().getColumn(3).setPreferredWidth(400);
			tblProduto.getColumnModel().getColumn(3).setResizable(false);
			tblProduto.getColumnModel().getColumn(3).setCellRenderer(tbmProduto.CellLeft);
			tblProduto.getColumnModel().getColumn(3).setHeaderRenderer(tbmProduto.ColLeft);
					
			tblProduto.getColumnModel().getColumn(4).setPreferredWidth(0);
			tblProduto.getColumnModel().getColumn(4).setResizable(false);
			tblProduto.getColumnModel().getColumn(4).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(4).setHeaderRenderer(tbmProduto.ColCenter);		
			tblProduto.getColumnModel().getColumn(4).setMaxWidth(0);
			
			tblProduto.getColumnModel().getColumn(5).setPreferredWidth(0);
			tblProduto.getColumnModel().getColumn(5).setResizable(false);
			tblProduto.getColumnModel().getColumn(5).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(5).setHeaderRenderer(tbmProduto.ColCenter);			
			tblProduto.getColumnModel().getColumn(5).setMaxWidth(0);
			
			tblProduto.getColumnModel().getColumn(6).setPreferredWidth(40);
			tblProduto.getColumnModel().getColumn(6).setResizable(false);
			tblProduto.getColumnModel().getColumn(6).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(6).setHeaderRenderer(tbmProduto.ColCenter);		
			
			tblProduto.getColumnModel().getColumn(7).setPreferredWidth(80);
			tblProduto.getColumnModel().getColumn(7).setResizable(false);
			tblProduto.getColumnModel().getColumn(7).setCellRenderer(tbmProduto.CellRight);
			tblProduto.getColumnModel().getColumn(7).setHeaderRenderer(tbmProduto.ColLeft);		
			
			tblProduto.getColumnModel().getColumn(8).setPreferredWidth(80);		
			tblProduto.getColumnModel().getColumn(8).setResizable(false);
			tblProduto.getColumnModel().getColumn(8).setCellRenderer(tbmProduto.CellRight);
			tblProduto.getColumnModel().getColumn(8).setHeaderRenderer(tbmProduto.ColCenter);		
					
			tblProduto.getColumnModel().getColumn(9).setPreferredWidth(80);
			tblProduto.getColumnModel().getColumn(9).setResizable(false);
			tblProduto.getColumnModel().getColumn(9).setCellRenderer(tbmProduto.CellRight);
			tblProduto.getColumnModel().getColumn(9).setHeaderRenderer(tbmProduto.ColCenter);			
			
			tblProduto.getColumnModel().getColumn(10).setPreferredWidth(100);		
			tblProduto.getColumnModel().getColumn(10).setResizable(false);
			tblProduto.getColumnModel().getColumn(10).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(10).setHeaderRenderer(tbmProduto.ColCenter);		
					
			tblProduto.getColumnModel().getColumn(11).setPreferredWidth(130);
			tblProduto.getColumnModel().getColumn(11).setResizable(false);
			tblProduto.getColumnModel().getColumn(11).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(11).setHeaderRenderer(tbmProduto.ColCenter);	
					
			tblProduto.getColumnModel().getColumn(12).setPreferredWidth(100);
			tblProduto.getColumnModel().getColumn(12).setResizable(false);
			tblProduto.getColumnModel().getColumn(12).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(12).setHeaderRenderer(tbmProduto.ColCenter);				
			
			tblProduto.getColumnModel().getColumn(13).setPreferredWidth(130);
			tblProduto.getColumnModel().getColumn(13).setResizable(false);
			tblProduto.getColumnModel().getColumn(13).setCellRenderer(tbmProduto.CellCenter);
			tblProduto.getColumnModel().getColumn(13).setHeaderRenderer(tbmProduto.ColCenter);
			
			tblProduto.getTableHeader().setReorderingAllowed(false);
			tblProduto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			tblProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tblProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
			tblProduto.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			tblProduto.setBorder(new LineBorder(new Color(0, 0, 0)));
			tblProduto.setFillsViewportHeight(true);						
			tblProduto.setCellSelectionEnabled(false); 		
			tblProduto.setRowSelectionAllowed(true);
			
			sorProduto.setModel(tblProduto.getModel());						
			sorProduto.setRowFilter(null);
			tblProduto.setRowSorter(sorProduto);
			
			if (tbmProduto.getLinhas().size() > 0) {
				tblProduto.setRowSelectionInterval(0, 0);
					
			}
		
		}
	}

	
	
}

