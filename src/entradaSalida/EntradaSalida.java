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

	public static void guardarEnArchivo( String Ruta,ArrayList<String> misDatos){
		BufferedWriter bufferDeGuardado = null;
		
		try {
			bufferDeGuardado = new BufferedWriter(new FileWriter("Salida/Itinerario de " + Ruta +".csv"));
			
			bufferDeGuardado.write("ESTE ES EL DETALLE DE TU ITINERARIO\n");
			bufferDeGuardado.write(misDatos.get(0));
			misDatos.remove(0);
			bufferDeGuardado.write("\n\n\nPromocion / Atraccion Comprada;Tipo;Costo;Duracion");
			bufferDeGuardado.newLine();
		
			for (String dato : misDatos) {
				bufferDeGuardado.newLine();
				bufferDeGuardado.write(dato);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bufferDeGuardado != null) {
				try {
					bufferDeGuardado.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
