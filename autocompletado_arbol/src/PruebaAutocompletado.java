

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PruebaAutocompletado {

	public static void main(String[] args) {

		//-----------------------------------------
		String patron = "repa";
		Arbol aPalabras = new Arbol();
		//--------------------------------------
		String ruta = "src/diccionario.txt";
		ArrayList<String> lineas = null;
		try {
			lineas = (ArrayList<String>) Files.readAllLines(Paths.get(ruta), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("no se pudo recuperar el texto");
		}
        for (String linea : lineas) {
			String[] palabras = linea.split("\\s+");
			for(String palabra: palabras)
			{
				Arbol.insertarPalabra(aPalabras, palabra);
			}
        }
		//------------------------------------------------
		long tiempoinicial =System.currentTimeMillis();
        Arbol aPatron = Arbol.obtenerRamaPatron(aPalabras, patron);
        String palabrasrecuperadas = Arbol.obtenerPalabras(aPatron, patron.substring(0, patron.length()-1));
		long tiempofinal = System.currentTimeMillis();
		long tiempobusqueda = tiempofinal - tiempoinicial;
		System.out.println("tiempo empleado en la busqueda (milisegundos): " + tiempobusqueda);

        //------------------------------------------------
		String[] palabras = palabrasrecuperadas.split("\\s+"); //separo la línea en palabras	
		//------------------------------------------------
		System.out.println("palabras encontradas");
		for (int i = 0; i < palabras.length; i++) {
			System.out.println(palabras[i]);
		}

	}

}
