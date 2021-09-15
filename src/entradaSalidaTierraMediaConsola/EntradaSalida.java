package entradaSalidaTierraMediaConsola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EntradaSalida {

	public static ArrayList<String> cargarArchivoDe(String rutaRelativa) {
		ArrayList<String> misDatos = new ArrayList<String>();
		BufferedReader bufferDeLectura = null;

		try {
			bufferDeLectura = new BufferedReader(new FileReader("Entrada/" + rutaRelativa));
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
					System.exit(1);
				}
		}
		return misDatos;
	}

	public static void guardarEnArchivo(ArrayList<String> datosAGuardar, String nombreDeRuta) {
		PrintWriter salida = null;

		try {
			salida = new PrintWriter(new FileWriter("Salida/" + nombreDeRuta + ".csv"));

			for (String dato : datosAGuardar) {
				salida.print(dato);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				if (salida != null) {
					salida.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
