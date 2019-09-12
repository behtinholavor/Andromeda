package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.UsuarioBusiness;
import util.Global;

@SuppressWarnings("serial")
public class MenuView extends JFrame {
	public JFrame frmMenu;
	private JPanel pnlPrincipal;
	private JLabel lblUsuario;
	private JLabel lblAcesso;
	private JLabel lblLogin;
	private UsuarioBusiness bsnMenu;
	private JPanel pnlMenu;
	private JMenuBar mnBar;
	private JMenu mnUsuario;
	private JMenuItem mniUsuarioSenha;
	private JMenu mnCadastro;
	private JMenuItem mniCadastroCliente;
	private JMenuItem mniCadastroProduto;
	private JMenuItem mniCadastroVeiculo;
	private JMenu mnMovimentacao;
	private JMenuItem mniAgenda;
	private JMenuItem mniCaixa;
	private JMenuItem mniTempos;
	private JMenu mnRelatorio;
	private JMenuItem mniRelatorioCliente;
	private JMenuItem mniRelatorioProduto;
	private JMenu mnSistema;
	private JMenuItem mniSobre;
	private JMenuItem mniSair;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {															
					LoginView view = new LoginView();
					view.setVisible(true);							
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MenuView() {
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Andr\u00F4meda - Sistema para Lava Jato");						
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 190);
		setIconImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\tool-blur_large@1x.png");
		setScreen(0);
		
		frmMenu = this;	
		bsnMenu = new UsuarioBusiness();
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlPrincipal.setFocusCycleRoot(true);
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		pnlMenu = new JPanel();
		pnlMenu.setBounds(0, 0, 654, 52);
		pnlMenu.setBorder(new LineBorder(SystemColor.controlShadow));
		pnlMenu.setBackground(SystemColor.control);
		pnlPrincipal.add(pnlMenu);
		pnlMenu.setLayout(null);
		
		mnBar = new JMenuBar();
		mnBar.setOpaque(false);
		mnBar.setBounds(2, 2, 650, 49);
		pnlMenu.add(mnBar);
		
		mnUsuario = new JMenu("Usu\u00E1rio");
		mnUsuario.setIconTextGap(5);
		mnUsuario.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\user_large@1x.png"));
		mnBar.add(mnUsuario);
		
		JMenuItem mniUsuarioCadastro = new JMenuItem("Cadastro");
		mniUsuarioCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								 												
				UsuarioView frmUsuario = new UsuarioView();
				frmUsuario.setOwner(frmMenu);
				frmUsuario.setVisible(true);				
			}
		});
		mniUsuarioCadastro.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\user-id-card_small@1x.png"));
		mnUsuario.add(mniUsuarioCadastro);
		
		mniUsuarioSenha = new JMenuItem("Senha");
		mniUsuarioSenha.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\password_small@1x.png"));
		mniUsuarioSenha.addActionListener(new ActionListener() {						
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {								
				Global.funcao.getMsg().setComponent(frmMenu);
				Global.funcao.getMsg().setMessage("Deseja resetar a senha do usuário logado?");
				Global.funcao.getMsg().setTitle("Pergunta");
				Global.funcao.getMsg().setType(3);
				String login = Global.sessao.getUsuario().getLogin();					
				
				if (Global.funcao.getMsg().Show() == 0) {															
					bsnMenu.ResetartUsuario(Global.sessao.getUsuario());
					Global.sessao.setUsuario(bsnMenu.AutenticarUsuario(login, Global.sessao.system_pswd));					
					if ((Global.sessao.getUsuario() != null) && (Global.sessao.getUsuario().getIdusuario() > 0)) { 																
						lblUsuario.setText("Usuário: "+Global.sessao.getUsuario().getUsuario().toString());
						lblAcesso.setText("Acesso: "+Global.sessao.getUsuario().getAcesso().toString());
						lblLogin.setText("Login: "+Global.sessao.getUsuario().getLogin().toString());
					}
				}
				
			}
		});
		mnUsuario.add(mniUsuarioSenha);
		
		mnCadastro = new JMenu("Cadastros");
		mnCadastro.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\database_large@1x.png"));		
		mnBar.add(mnCadastro);
		
		mniCadastroCliente = new JMenuItem("Clientes");
		mniCadastroCliente.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\users-men_small@1x.png"));
		mniCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								 												
				ClienteView frmCliente = new ClienteView();			
				frmCliente.setOwner(frmMenu);
				frmCliente.setVisible(true);				
			}
		});	
		mnCadastro.add(mniCadastroCliente);
		
		mniCadastroProduto = new JMenuItem("Produtos");
		mniCadastroProduto.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\tag-blue_small@1x.png"));
		mniCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								 												
				ProdutoView frmProduto = new ProdutoView();			
				frmProduto.setOwner(frmMenu);
				frmProduto.setVisible(true);				
			}
		});
		mnCadastro.add(mniCadastroProduto);
		
		mniCadastroVeiculo = new JMenuItem("Ve\u00EDculos");
		mniCadastroVeiculo.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\vehicle-car_small@1x.png"));
		mniCadastroVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								 												
				VeiculoView frmVeiculo = new VeiculoView();
				frmVeiculo.setOwner(frmMenu);
				frmVeiculo.setVisible(true);				
			}
		});		
		mnCadastro.add(mniCadastroVeiculo);
		
		mnMovimentacao = new JMenu("Movimenta\u00E7\u00E3o");
		mnMovimentacao.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\data-storage_large@1x.png"));
		mnBar.add(mnMovimentacao);
		
		JMenuItem mniLavar = new JMenuItem("Lavagem");
		mniLavar.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\tool-blur_small@1x.png"));
		mniLavar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								 												
			 	ServicoView frmServico = new ServicoView();
				frmServico.setOwner(frmMenu);
				frmServico.setVisible(true);								
			}
		});
		mnMovimentacao.add(mniLavar);
		
		mniAgenda = new JMenuItem("Agenda");
		mniAgenda.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\calendar2_small@1x.png"));
		mnMovimentacao.add(mniAgenda);
		
		mniCaixa = new JMenuItem("Caixa");
		mniCaixa.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\money-banknotes_small@1x.png"));
		mnMovimentacao.add(mniCaixa);
		
		mniTempos = new JMenuItem("Tempos");
		mniTempos.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\clock_small@1x.png"));
		mnMovimentacao.add(mniTempos);
		
		mnRelatorio = new JMenu("Relat\u00F3rios");
		mnRelatorio.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\report-open_large@1x.png"));
		mnBar.add(mnRelatorio);
		
		mniRelatorioCliente = new JMenuItem("Relat\u00F3rio de clientes");
		mniRelatorioCliente.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\report-open-user_small@1x.png"));
		mnRelatorio.add(mniRelatorioCliente);
		
		mniRelatorioProduto = new JMenuItem("Relat\u00F3rio de produtos");
		mniRelatorioProduto.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\report-open-tag_small@1x.png"));
		mnRelatorio.add(mniRelatorioProduto);
		
		mnSistema = new JMenu("Sistema");		
		mnSistema.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\gear_large@1x.png"));
		mnBar.add(mnSistema);
		
		mniSobre = new JMenuItem("Sobre");
		mniSobre.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\button-info_small@1x.png"));
		mniSobre.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {								
				Global.funcao.getMsg().setComponent(frmMenu);
				Global.funcao.getMsg().setMessage("Versão do Sistema 1.0.87");
				Global.funcao.getMsg().setTitle("Sobre");
				Global.funcao.getMsg().setType(1);					
				Global.funcao.getMsg().Show();	
			}
		});
		mnSistema.add(mniSobre);
		
		mniSair = new JMenuItem("Sair");
		mniSair.setIcon(new ImageIcon("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\exit_small@1x.png"));
		mniSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		mnSistema.add(mniSair);
		
		lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBounds(10, 136, 329, 14);
		pnlPrincipal.add(lblUsuario);
		
		lblAcesso = new JLabel("Acesso:");
		lblAcesso.setBounds(349, 136, 179, 14);
		pnlPrincipal.add(lblAcesso);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(538, 136, 106, 14);
		pnlPrincipal.add(lblLogin);
		
		lblUsuario.setText("Usuário: "+Global.sessao.getUsuario().getUsuario().toString());
		lblAcesso.setText("Acesso: "+Global.sessao.getUsuario().getAcesso().toString());
		lblLogin.setText("Login: "+Global.sessao.getUsuario().getLogin().toString());
	}
	
	public void setIconImage(String fileName){
	    //ImageIcon ico = new ImageIcon(fileName);
	    this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\tool-blur_large@1x.png"));
	}
	
	public void setScreen(int screenType){
	    Toolkit tk = Toolkit.getDefaultToolkit(); 
	    Dimension scrSize = tk.getScreenSize();
	    switch(screenType){
	        case 0: // Normal
	            this.setSize(this.getWidth(),this.getHeight());
	            int scrWidth = scrSize.width - this.getWidth(); 
	            int scrHeight = scrSize.height - this.getHeight();
	            this.setLocation(scrWidth/2,scrHeight/2);
	            break;
	        case 1: // Maximizada
	            this.setLocation(0,0);
	            this.setSize(scrSize.width, scrSize.height);
	            break;
	    }
	}
}