package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.UsuarioBusiness;
import util.Funcao;
import util.Global;
import util.Sessao;

@SuppressWarnings("serial")
public class LoginView extends JFrame {		
	private LoginView frmLogin;
	private MenuView frmPrincipal;
	private JPanel pnlLogin;
	private JTextField txtSenha;
	private JTextField txtLogin;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private UsuarioBusiness bsnLogin;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setAlwaysOnTop(true);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Acessar sistema");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\r.de.lavor.rodrigues\\eclipse\\workspace\\Andromeda\\doc\\png\\key_small@1x.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 205);
		setLocationRelativeTo(null);				
		frmLogin = this;												
		bsnLogin = new UsuarioBusiness();	
		
		Global.funcao = new Funcao();
		Global.sessao = new Sessao();
		
		pnlLogin = new JPanel();
		pnlLogin.setBorder(new EmptyBorder(5, 5, 5, 5));		
		pnlLogin.setLayout(null);				       
		setContentPane(pnlLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtSenha.setToolTipText("Senha do usu\u00E1rio");
		txtSenha.setBounds(10, 90, 221, 25);
		pnlLogin.add(txtSenha);
		txtSenha.setColumns(10);			
		
		JButton btnConfirmar = new JButton("Confimar");
		btnConfirmar.addActionListener(new ActionListener() {									
			public void actionPerformed(ActionEvent e) {																																				
				Global.sessao.setUsuario(bsnLogin.AutenticarUsuario(txtLogin.getText().trim(), txtSenha.getText().trim()));
				
				if ((Global.sessao.getUsuario() != null) && (Global.sessao.getUsuario().getIdusuario() > 0)) { 																														
					frmPrincipal = new MenuView();
					frmPrincipal.setVisible(true);
					frmLogin.setVisible(false);
				} else {																									
					Global.funcao.getMsg().setComponent(frmLogin);
					Global.funcao.getMsg().setMessage("Falha na autenticação do usuário!");
					Global.funcao.getMsg().setTitle("Erro");
					Global.funcao.getMsg().setType(0);					
					Global.funcao.getMsg().Show();										
				}
				
			}
		});
		btnConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnConfirmar.setBounds(10, 135, 100, 25);
		pnlLogin.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		
		
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCancelar.setBounds(131, 135, 100, 25);
		pnlLogin.add(btnCancelar);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtLogin.setToolTipText("Login do usu\u00E1rio");
		txtLogin.setColumns(10);
		txtLogin.setBounds(10, 30, 221, 25);
		pnlLogin.add(txtLogin);
		
		lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 10, 46, 14);
		pnlLogin.add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 70, 46, 14);
		pnlLogin.add(lblSenha);
				
		txtLogin.setText("XG891");
		txtSenha.setText("rl7801");		
	}
		

	
}
