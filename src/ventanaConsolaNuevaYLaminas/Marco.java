package ventanaConsolaNuevaYLaminas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Marco extends JFrame {

	Lamina miLamina;

	public Marco(ConsolaNueva consola, JTextPane areaImpresion) {
		setLayout(new BorderLayout());
		setTitle("TierraMedia");
		//se le da dimension y posicion inicial al marco
		setBounds(200, 200, 1150, 550);
		//se le indica que hacer cuando se cierra el marco. finalizar la ejecucion del programa.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//se crea un objeto de tipo Lamina que tiene botones y areade texto.
		miLamina = new Lamina(consola, areaImpresion);
		//se agrega al marco
		this.add(miLamina, BorderLayout.CENTER);
		//por ultimo se hace visible el marco.
		setVisible(true);
	}
}
