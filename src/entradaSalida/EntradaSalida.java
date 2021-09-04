package entradaSalida;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EntradaSalida {

	public static ArrayList<String> cargarArchivoDe(String ruta) {
		ArrayList<String> misDatos = new ArrayList<String>();
		BufferedReader bufferDeLectura = null;

		try {
			bufferDeLectura = new BufferedReader(new FileReader(ruta));
			String linea;

			while ((linea = bufferDeLectura.readLine()) != null) {
				misDatos.add(linea);
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

	public static void guardarEnArchivoDe(String[] misDatos) throws IOException {
		BufferedWriter bufferDeGuardado = null;
		
		try {
			bufferDeGuardado = new BufferedWriter(new FileWriter("Salida/DatosGuardados.csv"));
			String encabezados = "Usuario;Costo del Itinerario;Duracion del Itinerario;Promociones y Atracciones Compradas";
			bufferDeGuardado.write(encabezados);
			bufferDeGuardado.newLine();

			for (int i = 0; i < misDatos.length; i++) {
				if (misDatos[i] != null) {
					bufferDeGuardado.write(misDatos[i]);
					bufferDeGuardado.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferDeGuardado != null) {
				bufferDeGuardado.close();
			}
		}
	}
}
