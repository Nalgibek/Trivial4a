package es.uniovi.asw.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uniovi.asw.trivial.game.Game;
import es.uniovi.asw.trivial.model.Player;
import es.uniovi.asw.trivial.model.Pregunta;
import es.uniovi.asw.trivial.model.User;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame {

	public static Game juego;
	public static Colores colores;
	
	private static Player[] jugadores;
	private static Player jugadorActual;
	
	private Panel_Quesitos pq;
	
	private JPanel contentPane;
	private JPanel panelCentro;
	private JPanel panelJugador;
	private JTextField txtJugador;
	private JPanel panelNorte;
	private JPanel panelMovimiento;
	private JPanel panelDado;
	private JButton btnDado;
	private JPanel panelFlechas;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JLabel lblDado;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {						//50, new Colores(pruebaCategorias)
					VentanaJuego frame = new VentanaJuego(ClaseParaPruebas.juegoPrueba(20, 2, 1, 6));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaJuego(Game g) {
		inicializarJuego(g);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
	}
	
	/**
	 * Inicializa el juego
	 * @param Game g habiendo ejecutado el metodo start game
	 */
	private void inicializarJuego(Game g){
		juego = g;
		VentanaJuego.colores = new Colores(g.getCategorias());
		jugadores = juego.getPlayers();
		jugadorActual = juego.getCurrentPlayer();
	}
	
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new Panel_TableroCuadrado();
		}
		return panelCentro;
	}
	private JPanel getPanelJugador() {
		if (panelJugador == null) {
			panelJugador = new JPanel();
			panelJugador.setBorder(new TitledBorder(null, "Jugador Actual:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJugador.setLayout(new BoxLayout(panelJugador, BoxLayout.Y_AXIS));
			panelJugador.add(getTxtJugador());
		}
		return panelJugador;
	}
	private JTextField getTxtJugador() {
		if (txtJugador == null) {
			txtJugador = new JTextField();
			txtJugador.setHorizontalAlignment(SwingConstants.CENTER);
			txtJugador.setEditable(false);
			txtJugador.setFont(new Font("Adobe Arabic", Font.PLAIN, 34));
			txtJugador.setText(jugadorActual.getUser().get_id());
			txtJugador.setColumns(10);
			txtJugador.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		}
		return txtJugador;
	}
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setLayout(new GridLayout(0, 3, 0, 0));
			panelNorte.add(getPanelMovimiento());
			panelNorte.add(getQuesitos());
			panelNorte.add(getPanelJugador());
		}
		return panelNorte;
	}
	
	
	private Panel_Quesitos getQuesitos(){
		if(pq==null){
			Player prueba = new Player(new User("Pepe"),0);
			pq = new Panel_Quesitos(prueba,juego.getCategorias());
			pq.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
					"Quesitos ganados:", TitledBorder.LEADING, 
					TitledBorder.TOP, null, new Color(0, 0, 0)));
		}
		return pq;
	}
	
	private JPanel getPanelMovimiento() {
		if (panelMovimiento == null) {
			panelMovimiento = new JPanel();
			panelMovimiento.setLayout(new GridLayout(0, 1, 0, 0));
			panelMovimiento.add(getPanelDado());
			panelMovimiento.add(getPanelFlechas());
		}
		return panelMovimiento;
	}
	private JPanel getPanelDado() {
		if (panelDado == null) {
			panelDado = new JPanel();
			panelDado.setLayout(new GridLayout(0, 2, 5, 0));
			panelDado.add(getBtnDado());
			panelDado.add(getLblDado());
		}
		return panelDado;
	}
	private JLabel getLblDado() {
		if (lblDado == null) {
			lblDado = new JLabel("");
			lblDado.setOpaque(true);
			lblDado.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		}
		return lblDado;
	}
	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton();
			btnDado.setIcon(ajustarImagen("img/dado.png", btnDado));
			btnDado.setContentAreaFilled(false);
			btnDado.setBorderPainted(false);
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num=juego.diceGetNumer();
					lblDado.setText(String.valueOf(num));
					btnDado.setEnabled(false);
				}				
			});
		}		
		return btnDado;
	}
	
	/**
	 * Redimensiona una imagen para un componente dado
	 * @param Direccion
	 * @param Componente
	 * @return Imagen redimensionada
	 */
	protected Icon ajustarImagen(String direccion, Component componente) {
		Image imgOriginal =  new ImageIcon(direccion).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(componente.getWidth()-34,componente.getHeight()-10, Image.SCALE_SMOOTH);
		return new ImageIcon(imgEscalada);
	}
	
	private JPanel getPanelFlechas() {
		if (panelFlechas == null) {
			panelFlechas = new JPanel();
			panelFlechas.setLayout(new GridLayout(0, 2, 5, 0));
			panelFlechas.add(getBtnIzquierda());
			panelFlechas.add(getBtnDerecha());
		}
		return panelFlechas;
	}
	private JButton getBtnDerecha() {
		if (btnDerecha == null) {
			btnDerecha = new JButton();
			btnDerecha.setIcon(ajustarImagen("img/flecha_derecha.png",btnDerecha));
			btnDerecha.setContentAreaFilled(false);
			btnDerecha.setBorderPainted(false);
			btnDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moverse(1);
				}
			});
			
		}
		return btnDerecha;
	}
	private JButton getBtnIzquierda() {
		if (btnIzquierda == null) {
			btnIzquierda = new JButton();
			btnIzquierda.setIcon(ajustarImagen("img/flecha_izquierda.png",btnIzquierda));
			btnIzquierda.setContentAreaFilled(false);
			btnIzquierda.setBorderPainted(false);
			btnIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moverse(-1);
				}
			});
		}
		return btnIzquierda;
	}
	
	/**
	 * Mueve al jugador segun:
	 * 	Orientacion (IZQ -1   DER 1)
	 *  Valor de la tirada del dado
	 * @param orientacion
	 */
	private void moverse(int orientacion){
		if(orientacion!=-1 || orientacion!=1)
			return;
		try{
			int movimientos = Integer.parseInt(getLblDado().getText());
			juego.movePlayer(movimientos*orientacion);
		}
		catch(NumberFormatException e){}
	}
	
	private void mostrarPregunta(){
		Pregunta p = juego.getQuestionSet(juego.getCurrentPlayer().getPosicion());
	}

}