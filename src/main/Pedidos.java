package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Pedidos {

	private int posx,posy;
	private PImage burguer;
	private PApplet app;
	private String string;
	private String formateado;
	
	
	public Pedidos(int i, int j, PImage burguer, PApplet app, String string, String formateado) {
		
		this.posx = i;
		this.posy = j;
		this.burguer = burguer;
		this.app = app;
		this.setString(string);
		this.setFormateado(formateado);
	}


	public int getPosx() {
		return posx;
	}


	public void setPosx(int posx) {
		this.posx = posx;
	}


	public int getPosy() {
		return posy;
	}


	public void setPosy(int posy) {
		this.posy = posy;
	}


	public PImage getBurguer() {
		return burguer;
	}


	public void setBurguer(PImage burguer) {
		this.burguer = burguer;
	}


	public PApplet getApp() {
		return app;
	}


	public void setApp(PApplet app) {
		this.app = app;
	}


	public void pintar(int i) {
	
		app.image(burguer,posx,(20)+posy*(i));
		app.fill(0);
		app.text("Pedido #"+(i+1),posx+120,(40)+posy*(i));
		app.text("Hora"+formateado,posx+120,(70)+posy*(i));
		
		
	}


	public String getString() {
		return string;
	}


	public void setString(String string) {
		this.string = string;
	}


	public String getFormateado() {
		return formateado;
	}


	public void setFormateado(String formateado) {
		this.formateado = formateado;
	}

	
	
	
}
