package Mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Aplicacion extends JFrame
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Aplicacion n = new Aplicacion();
		n.setVisible(true);
	}
	
	public Aplicacion()
	{
		
	}
	
	public void construirMapaPorLatitudLongitud(String latitud, String longitud) throws Exception
	{
		if(longitud.equals("")||latitud.equals(""))
		{
			throw new Exception("Los datos no estan completos");
		}
		else
		{
			File mapa = new File("./datos/mapaFinal.html");
				PrintWriter escritor = new PrintWriter(mapa);
				BufferedReader lector = new BufferedReader(new FileReader(new File("./datos/mapa.html")));
				String linea = lector.readLine();
				while(linea!=null)
				{
					String texto = linea;
					if(linea.contains("longitud")&& linea.contains("latitud"))
					{
						texto = texto.replace("longitud", longitud);
						texto = texto.replace("latitud", latitud);
					}
					escritor.println(texto);
					linea = lector.readLine();
				}
				escritor.close();
				lector.close();
			} 
		}
	}


