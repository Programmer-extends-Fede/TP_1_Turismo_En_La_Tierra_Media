package marco;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Lamina extends JPanel {

	JButton botonSi;
	JButton botonNo;
	JTextArea miArea;

	public Lamina(ConsolaNueva consola, JTextArea areaImpresion) {
		setLayout(new BorderLayout());
		miArea = areaImpresion;
		miArea.setEditable(false);

		botonSi = new JButton("COMPRAR");
		botonNo = new JButton("NO COMPRAR");
		JPanel laminaBotones = new JPanel();
		miArea.setLineWrap(true);
		JScrollPane miPanel = new JScrollPane(miArea);
		this.add(miPanel, BorderLayout.CENTER);

		botonSi.addActionListener(new Eventos(consola));
		botonNo.addActionListener(new Eventos(consola));
		laminaBotones.add(botonSi);
		laminaBotones.add(botonNo);
		this.add(laminaBotones, BorderLayout.SOUTH);
	}

	public JTextArea getMiArea() {
		return this.miArea;
	}

	private class Eventos implements ActionListener {
		private ConsolaNueva consola;

		public Eventos(ConsolaNueva consola) {
			this.consola = consola;
		}

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			String botonPresionado = e.getActionCommand();
			if (botonPresionado.equals("COMPRAR")) {
				consola.setEntrada("s");
				consola.reanudarhilo();
			} else if (botonPresionado.equals("NO COMPRAR")) {
				consola.setEntrada("n");
				consola.reanudarhilo();
			}
		}
	}
}