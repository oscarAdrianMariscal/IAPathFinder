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
        
        private String transformaCoordenadaJ(int coordenadaJ)
    {
        String cI = new String();
        
        switch(coordenadaI)
        {
            case 0:
                cI = "A";
            case 1:
                cI = "B";
                break;
            case 2:
                cI = "C";
                break;
            case 3:
                cI = "D";
                break;
            case 4:
                cI = "E";
                break;
            case 5:
                cI = "F";
                break;
            case 6:
                cI = "G";
                break;
            case 7:
                cI = "H";
                break;
            case 8:
                cI = "I";
                break;
            case 9:
                cI = "J";
                break;
            case 10:
                cI = "K";
                break;
            case 11:
                cI = "L";
                break;
            case 12:
                cI = "M";
                break;
            case 13:
                cI = "N";
                break;
            case 14:
                cI = "O";
                break;
            default:
                cI = "ERROR";
                break;
        }
        return cI;
    }

    @Override
    public String toString() {
        int i = getCoordenadaI() + 1;
        int j = getCoordenadaJ();
        return  "(" + i + ", " + transformaCoordenadaJ(j) + ")";
    }
}
