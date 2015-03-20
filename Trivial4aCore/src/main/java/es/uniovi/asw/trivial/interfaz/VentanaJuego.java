package es.uniovi.asw.trivial.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro;
	private JPanel panelJugador;
	private JLabel lblJugadorActual;
	private JTextField txtJugador;
	private JPanel panelJugar;
	private JPanel panelMovimiento;
	private JPanel panelDado;
	private JButton btnDado;
	private JTextField txtTirada;
	private JPanel panelFlechas;
	private JButton btnDerecha;
	private JButton btnIzquierda;
	private JPanel panelQuesitos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
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
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelCentro());
		contentPane.add(getPanelJugar());
	}
	
	
	/**
	 * Establece el layout para el panel tablero
	 * y el panel de los quesitos de forma dinamica
	 * 
	 * @param nElementos
	 * @param panel quesitos o panel Tablero
	 */
	private void setTablero(int nElementos,JPanel panel){
		double proporcion = nElementos/4;
		int arriba=0, derecha=0, izquierda=0, abajo=0;
		
		if(proporcion - (int)proporcion == 0.25){
			
		}
		else if(proporcion - (int)proporcion == 0.5 && nElementos>=10){
			int laterales = (int)proporcion/2 + proporcion - (int)proporcion;
			
		}
		else if(proporcion - (int)proporcion == 0.75){
			
		}
		else if(proporcion - (int)proporcion == 0 && nElementos>=4){
			//Establecer proporciones iniciales
			arriba = (int)proporcion; derecha = (int)proporcion;
			izquierda = (int)proporcion; abajo = (int)proporcion;
			arriba++; izquierda--; //PASO 1
			abajo++; derecha--; //PASO 2
			if(arriba==abajo && derecha==izquierda)
				panel.setLayout(new GridLayout(derecha, arriba, 5, 0));
			else
				System.err.print("Error al estimar las proporciones "
						+ "(Condicon inicial: p=0 y nElmentos="+nElementos+ ")");
		}
	}
	
	
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBounds(10, 244, 600, 207);
		}
		return panelCentro;
	}
	private JPanel getPanelJugador() {
		if (panelJugador == null) {
			panelJugador = new JPanel();
			panelJugador.setBounds(332, 35, 149, 94);
			panelJugador.setLayout(new BoxLayout(panelJugador, BoxLayout.Y_AXIS));
			panelJugador.add(getLblJugadorActual());
			panelJugador.add(getTxtJugador());
		}
		return panelJugador;
	}
	private JLabel getLblJugadorActual() {
		if (lblJugadorActual == null) {
			lblJugadorActual = new JLabel("Jugador Actual:");
		}
		return lblJugadorActual;
	}
	private JTextField getTxtJugador() {
		if (txtJugador == null) {
			txtJugador = new JTextField();
			txtJugador.setText("Pepe");
			txtJugador.setColumns(10);
		}
		return txtJugador;
	}
	private JPanel getPanelJugar() {
		if (panelJugar == null) {
			panelJugar = new JPanel();
			panelJugar.setBounds(10, 23, 580, 152);
			panelJugar.setLayout(null);
			panelJugar.add(getPanelMovimiento());
			panelJugar.add(getPanelQuesitos());
			panelJugar.add(getPanelJugador());
		}
		return panelJugar;
	}
	private JPanel getPanelMovimiento() {
		if (panelMovimiento == null) {
			panelMovimiento = new JPanel();
			panelMovimiento.setBounds(10, 11, 141, 130);
			GridBagLayout gbl_panelMovimiento = new GridBagLayout();
			gbl_panelMovimiento.columnWidths = new int[]{141, 0};
			gbl_panelMovimiento.rowHeights = new int[]{74, 45, 0};
			gbl_panelMovimiento.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panelMovimiento.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panelMovimiento.setLayout(gbl_panelMovimiento);
			GridBagConstraints gbc_panelDado = new GridBagConstraints();
			gbc_panelDado.fill = GridBagConstraints.BOTH;
			gbc_panelDado.insets = new Insets(0, 0, 5, 0);
			gbc_panelDado.gridx = 0;
			gbc_panelDado.gridy = 0;
			panelMovimiento.add(getPanel_4(), gbc_panelDado);
			GridBagConstraints gbc_panelFlechas = new GridBagConstraints();
			gbc_panelFlechas.fill = GridBagConstraints.BOTH;
			gbc_panelFlechas.gridx = 0;
			gbc_panelFlechas.gridy = 1;
			panelMovimiento.add(getPanelFlechas(), gbc_panelFlechas);
		}
		return panelMovimiento;
	}
	private JPanel getPanel_4() {
		if (panelDado == null) {
			panelDado = new JPanel();
			panelDado.setLayout(new GridLayout(0, 2, 5, 0));
			panelDado.add(getBtnDado());
			panelDado.add(getTxtTirada());
		}
		return panelDado;
	}
	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("Dado");
		}
		return btnDado;
	}
	private JTextField getTxtTirada() {
		if (txtTirada == null) {
			txtTirada = new JTextField();
			txtTirada.setText("Tirada");
			txtTirada.setColumns(10);
		}
		return txtTirada;
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
			btnDerecha = new JButton("->");
		}
		return btnDerecha;
	}
	private JButton getBtnIzquierda() {
		if (btnIzquierda == null) {
			btnIzquierda = new JButton("<-");
		}
		return btnIzquierda;
	}
	private JPanel getPanelQuesitos() {
		if (panelQuesitos == null) {
			panelQuesitos = new JPanel();
			panelQuesitos.setBounds(160, 11, 149, 130);
		}
		return panelQuesitos;
	}
}
