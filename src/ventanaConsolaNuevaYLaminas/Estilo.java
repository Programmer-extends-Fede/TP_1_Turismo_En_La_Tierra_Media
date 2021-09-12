package ventanaConsolaNuevaYLaminas;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Estilo {

	//Esta clase brinda los estilos de letras, alineados y demas para usar en el display "JTextPane"
	
	public SimpleAttributeSet alinearIzquierda() {
		SimpleAttributeSet alineadoIzq = new SimpleAttributeSet();
		StyleConstants.setAlignment(alineadoIzq, StyleConstants.ALIGN_JUSTIFIED);
		StyleConstants.setLeftIndent(alineadoIzq, 50);
		StyleConstants.setSpaceBelow(alineadoIzq, 10);
		return alineadoIzq;
	}

	public SimpleAttributeSet alinearCentro() {
		SimpleAttributeSet alineadoCentro = new SimpleAttributeSet();
		StyleConstants.setAlignment(alineadoCentro, StyleConstants.ALIGN_CENTER);
		StyleConstants.setLeftIndent(alineadoCentro, 0);
		StyleConstants.setSpaceBelow(alineadoCentro, 0);
		return alineadoCentro;
	}

	public SimpleAttributeSet letraColorVerde() {
		SimpleAttributeSet estilo = new SimpleAttributeSet();
		StyleConstants.setForeground(estilo, Color.GREEN.darker());
		return estilo;
	}

	public SimpleAttributeSet letraColorRojo() {
		SimpleAttributeSet estilo = new SimpleAttributeSet();
		StyleConstants.setForeground(estilo, Color.RED);
		return estilo;
	}

	public SimpleAttributeSet tipografiaPorDefecto() {
		SimpleAttributeSet estilo = new SimpleAttributeSet();
		StyleConstants.setForeground(estilo, Color.BLACK);
		StyleConstants.setFontFamily(estilo, "Consolas");
		StyleConstants.setFontSize(estilo, 14);
		StyleConstants.setBold(estilo, true);
		return estilo;
	}

}
