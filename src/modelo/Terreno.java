package modelo;

public class Terreno {
    private int idTerreno;
    private String nombreTerreno;
    private String color;
    private float costo;

    public Terreno(int idTerreno, String nombreTerreno, String color, float costo) {
        this.idTerreno = idTerreno;
        this.nombreTerreno = nombreTerreno;
        this.color = color;
        this.costo = costo;
    }

    public int getIdTerreno() {
        return idTerreno;
    }

    public String getNombreTerreno() {
        return nombreTerreno;
    }

    public String getColor() {
        return color;
    }

    public float getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return "TERRENO: \n" 
                + "ID: " + idTerreno + "\n"
                + "Nombre: " + nombreTerreno + "\n" 
                + "Color: " + color + "\n" 
                + "Costo: " + costo;
    }

	public void setNombreTerreno(String nombreTerreno) {
		this.nombreTerreno = nombreTerreno;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
}
