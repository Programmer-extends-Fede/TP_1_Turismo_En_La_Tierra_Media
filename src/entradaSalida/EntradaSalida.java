package entradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public static void guardarEnArchivo(ArrayList<String> datosAGuardar, String nombreDeRuta) {
		PrintWriter salida = null;
		
		try {
			for(String dato : datosAGuardar) {
				salida = new PrintWriter(new FileWriter("Salida/"+ nombreDeRuta +".csv"));
				salida.print(dato);
				salida.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			 try{                    
	               if(salida != null){   
	                    salida.close();     
	                }                  
	            }catch (Exception e2){ 
	                e2.printStackTrace();
	            }
		}		
	}

}
