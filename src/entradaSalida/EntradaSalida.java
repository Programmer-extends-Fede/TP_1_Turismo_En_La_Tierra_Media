package entradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import tierraMedia.TierraMedia;
import usuario.Usuario;

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
	
	public static void guardarEnArchivo(ArrayList<Usuario> usuarios) throws IOException {
		PrintWriter salida = null;
		
		String nombreUsuario= null;
		String datosAGuardar= null;
		for(Usuario cadaUsuario : usuarios) {
			nombreUsuario= cadaUsuario.getNombre();
			datosAGuardar= TierraMedia.informacionParaGuardar();
			salida = new PrintWriter(new FileWriter("Salida/"+ nombreUsuario +".csv"));
			
			salida.print(datosAGuardar);
			salida.close();
		}
		
		salida.close();
	}
}
