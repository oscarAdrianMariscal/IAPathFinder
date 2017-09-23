package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Casilla;
import modelo.Terreno;

public class ParsearArchivo {
	public int tamanioI;
	public int tamanioJ;
	private boolean esValido;
	private ArrayList<ArrayList<Integer>> matrix;


	public ParsearArchivo(String direccion){
		try{
			esValido=true;
			matrix = new ArrayList<ArrayList<Integer>>();
			File file = new File(direccion);
			Scanner input = new Scanner(file);		
			while(input.hasNextLine())
			{

				Scanner colReader = new Scanner(input.nextLine());
				colReader.useDelimiter(",");
				ArrayList<Integer> col = new ArrayList<Integer>();
				while(colReader.hasNextInt())
				{
					int number = colReader.nextInt();
					System.out.println();
					col.add(number);
				}
				matrix.add(col);
				colReader.close();
			}
			input.close();
			tamanioI = matrix.size();
			tamanioJ = matrix.get(0).size();
		}catch(FileNotFoundException ex){
			esValido=false;
		}
	}
	
	public boolean esValido(){
		return esValido;
	}
	
	public Casilla[][] getMapa(){
		Casilla [][] casillas= new Casilla[tamanioI][tamanioJ];
		for (int i = 0; i < tamanioI; i++){
			for ( int j = 0; j < tamanioJ ; j++){
				Coordenada cor = new Coordenada(i,j);
				Terreno ter = new Terreno (matrix.get(i).get(j),"","",0 );
				casillas[i][j] = new Casilla(false,true,null,cor,ter);
			}
		}
		return casillas;
	}

	public static void main(String [ ] args)
	{
		ParsearArchivo parseador = new ParsearArchivo("helo.txt");
		Casilla [][] a= parseador.getMapa();
				
	}
}
