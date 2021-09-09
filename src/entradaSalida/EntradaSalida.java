package entradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
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

	public static void guardarEnArchivo(String ruta, ArrayList<String> datosDeItinerario) {
		// TODO Auto-generated method stub
		
	}
}