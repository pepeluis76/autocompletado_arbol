

import java.util.ArrayList;

public class Arbol {

	private static char VALORDEFECTO = 0; // valor por defecto de un nodo del arbol
	//--------------------------------
	private char letra ;  // letra guardada en ese nodo
	private ArrayList<Arbol> hijos;
	//--------------------------------
	public Arbol(char letra, ArrayList<Arbol> hijos) {
		this.letra = letra;
		if(hijos == null)
		{ 
			hijos = new ArrayList<Arbol>();
		}
		else {
			this.hijos = hijos;			
		}
	}
	//--------------------------------
	public Arbol() {
		this.letra = VALORDEFECTO;
		hijos = new ArrayList<Arbol>();	
		}
	//--------------------------------
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public ArrayList<Arbol> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Arbol> hijos) {
		this.hijos = hijos;
	}
	//--------------------------------	
	public static boolean insertarPalabra(Arbol a, String palabra) {
		if(palabra.isEmpty())
		{
			return true;
		}
		boolean letraencontrada = false;
		for (int i = 0; i < palabra.length(); i++) {
			char letra = palabra.charAt(i);
			for (Arbol hijo : a.getHijos()) {
				if(hijo.getLetra()==letra)
				{
					letraencontrada = true;
					boolean insertadoOK = insertarPalabra(hijo, palabra.substring(i+1) );
					return insertadoOK;
				}
			}
			if(!letraencontrada)
			{
				Arbol hijo = new Arbol();
				hijo.setLetra(palabra.charAt(0));
				a.getHijos().add(hijo);
				boolean insertadoOK = insertarPalabra(hijo, palabra.substring(i+1) );
				return insertadoOK;
			}
		}
		return false;
	}
	//--------------------------------
	public static String obtenerPalabras(Arbol a, String textoentrada) {
		if(a == null) return "";
		String palabras = "";
		String letra = String.valueOf(a.getLetra());	
		if(a.getHijos().isEmpty())
		{
			return " " + textoentrada + letra;
		}
		
		for(Arbol hijo: a.getHijos())
		{
			String palabrashijo = obtenerPalabras(hijo,textoentrada+letra);
		    palabras += palabrashijo;
		}
       return palabras;   
	}
	//--------------------------------
	public static Arbol obtenerRamaPatron(Arbol a, String patron) {
		int tam_patron = patron.length();
		if(tam_patron == 0) return null;
		int i = 0;
		char letra ='0';
		Arbol b = a;
		while(i< tam_patron)
		{
		if(b.getHijos().size()==0)
		{
			break;
		}
		for(Arbol hijo: b.getHijos())
		{
			letra =patron.charAt(i);
			if(letra == hijo.getLetra())
			{
				b = hijo;
				i++;
				break;
			}
		 }
		}
		if(i == tam_patron) return b;
		else return null;
	}
	
}
