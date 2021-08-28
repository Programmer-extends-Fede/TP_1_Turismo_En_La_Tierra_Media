package entradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EntradaSalida {

	public static String[] cargarArchivoDe(String ruta) {
		String[] misDatos = null;
		BufferedReader bufferDeLectura = null;

		try {
			bufferDeLectura = new BufferedReader(new FileReader(ruta));
			misDatos = new String[Integer.parseInt(bufferDeLectura.readLine().split(";")[0])];
			int indice = 0;
			String linea;
			
			while ((linea = bufferDeLectura.readLine()) != null) {
				misDatos[indice] = linea;
				indice++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferDeLectura != null)
				try {
					bufferDeLectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return misDatos;
	}
}
