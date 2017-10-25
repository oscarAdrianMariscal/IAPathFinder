package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordenada {
	private int coordenadaI;
	private int coordenadaJ;

	public String dameCoordenadaEnCadena() {
		char x =  (char)("A".codePointAt(0) + coordenadaJ);
		return String.valueOf(x) + String.valueOf(coordenadaI+1);
	}
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
		Pattern datePatt = Pattern.compile("([a-zA-Z])+([\\d]+)");
		Matcher m = datePatt.matcher(cadena);
		if (m.matches()) {
			String cordenadaX=m.group(1).toUpperCase();
			String cordenadaY=m.group(2).toUpperCase();
			coordenadaI= cordenadaX.codePointAt(0) - "A".codePointAt(0);
			
			coordenadaJ = Integer.parseInt(cordenadaY) -1 ;

		}
	}
    
        public String transformaCoordenadaJ(int coordenadaJ)
        {
            String cJ = new String("ERROR");
            if(coordenadaJ == 0)
                cJ = "A";
            if(coordenadaJ == 1)
                cJ = "B";
            if(coordenadaJ == 2)
                cJ = "C";
            if(coordenadaJ == 3)
                cJ = "D";
            if(coordenadaJ == 4)
                cJ = "E";
            if(coordenadaJ == 5)
                cJ = "F";
            if(coordenadaJ == 6)
                cJ = "G";
            if(coordenadaJ == 7)
                cJ = "H";
            if(coordenadaJ == 8)
                cJ = "I";
            if(coordenadaJ == 9)
                cJ = "J";
            if(coordenadaJ == 10)
                cJ = "K";
            if(coordenadaJ == 11)
                cJ = "L";
            if(coordenadaJ == 12)
                cJ = "M";
            if(coordenadaJ == 13)
                cJ = "N";
            if(coordenadaJ == 14)
                cJ = "O";
            return cJ;
        }

        @Override
        public String toString() {
            //COORDENADA Y
            int i = getCoordenadaI() + 1;
            //COORDENADA X
            int j = getCoordenadaJ();
            return  "(" + transformaCoordenadaJ(j) + ", " + i + ")";
        }
}
