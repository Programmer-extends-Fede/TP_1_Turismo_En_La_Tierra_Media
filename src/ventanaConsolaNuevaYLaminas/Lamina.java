package ventanaConsolaNuevaYLaminas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Lamina extends JPanel {

	JButton botonSi;
	JButton botonNo;
	JTextPane miArea;

	public Lamina(ConsolaNueva consola, JTextPane areaImpresion) {
		setLayout(new BorderLayout());
		miArea = areaImpresion;

		// para que no se pueda escribir en miArea y usarlo como display.
		miArea.setEditable(false);
		// se le agrega borde y margen a miArea
		miArea.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.DARK_GRAY));
		miArea.setMargin(new Insets(10, 10, 10, 10));

		// se crea objeto de la clase que maneja los eventos de click.
		Evento miEvento = new Evento(consola);

		botonSi = new JButton("COMPRAR");
		botonNo = new JButton("NO COMPRAR");
		//se pone a los botones a la escucha de cualquier click en ellos. Para al clickearlos desencadenar las acciones.
		botonSi.addActionListener(miEvento);
		botonNo.addActionListener(miEvento);
		JPanel laminaBotones = new JPanel();
		laminaBotones.add(botonSi);
		laminaBotones.add(botonNo);
		//se agrega miArea a un JScrollPane para tener slider y poder deslizar hacia arriba y abajo.
		JScrollPane miPanel = new JScrollPane(miArea);
		// se agrega la lamina que contiene botones al sur del marco y al centro miPanel que contiene a miArea.
		this.add(miPanel, BorderLayout.CENTER);
		this.add(laminaBotones, BorderLayout.SOUTH);
	}

//clase interna que gestiona los eventos.
	private class Evento implements ActionListener {
		private ConsolaNueva consola;

		public Evento(ConsolaNueva consola) {
			this.consola = consola;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// obtengo el nombre del boton que fue presionado.
			String botonPresionado = e.getActionCommand();

			if (botonPresionado.equals("COMPRAR")) {
				consola.setEntrada("s");
				consola.reanudarHilo();
			} else if (botonPresionado.equals("NO COMPRAR")) {
				consola.setEntrada("n");
				consola.reanudarHilo();
			}
		}
	}
}