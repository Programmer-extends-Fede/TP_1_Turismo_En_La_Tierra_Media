package marco;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Marco extends JFrame {

	Lamina miLamina;

	public Marco(ConsolaNueva consola, JTextArea areaImpresion) {
		setLayout(new BorderLayout());
		setTitle("TierraMedia");
		setBounds(400, 400, 900, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		miLamina = new Lamina(consola, areaImpresion);
		this.add(miLamina, BorderLayout.CENTER);
		setVisible(true);
	}

	public JTextArea getLamina() {
		return miLamina.getMiArea();
	}
}
