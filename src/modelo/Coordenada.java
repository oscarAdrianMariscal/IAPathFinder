package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordenada {
	private int coordenadaI;
	private int coordenadaJ;

	public Coordenada(int coordenadaI, int coordenadaJ)
	{
		this.coordenadaI = coordenadaI;
		this.coordenadaJ = coordenadaJ;
	}

	public int getCoordenadaI() {
		return coordenadaI;
	}

	public int getCoordenadaJ() {
		return coordenadaJ;
	}
	public void  setComoTexto(String cadena) {
		Pattern datePatt = Pattern.compile("([a-zA-Z])+(\\d)+");
		Matcher m = datePatt.matcher(cadena);
		if (m.matches()) {
			String cordenadaX=m.group(1).toUpperCase();
			coordenadaI= cordenadaX.codePointAt(0) - "A".codePointAt(0);
			coordenadaJ = Integer.parseInt(m.group(2)) -1 ;

		}
	}
}
