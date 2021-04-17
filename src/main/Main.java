package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{
	
	private LinkedList <Pedidos> pedido;
	PImage jugo,wrap,burguer,perro;
	int contador=0;
	DatagramSocket socket;
	String 	mensaje;
	String puerto; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
		
		
		
		
		
	/*	
	try {
			DatagramSocket 
			
			String mensaje = "Hola mundo cruel";
			DatagramPacket packet = new DatagramPacket(
					mensaje.getBytes(),
					mensaje.getBytes().length,
					InetAddress.getByName("127.0.0.1"),
					6000
					);
			socket.send(packet);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	*/
		
		
	}
    public void settings() {
        size(600, 400);

    }
	
	public void setup() {
		pedido = new LinkedList<Pedidos>();

				
				
				
				
				
		
		burguer = loadImage("imagenes/burguer.jpg");
		perro = loadImage("imagenes/perro.jpg");
		wrap = loadImage("imagenes/warp.jpg");
		jugo  = loadImage("imagenes/jugos.jpg");
		
		new Thread(
				
				()->{
					
					try {
						socket = new DatagramSocket(6000);
						
						//Recepción 
						while (true) {
						byte[] buff = new byte [256];
						DatagramPacket packet = new DatagramPacket(buff, buff.length);
						
						
						System.out.println("Waiting");
						socket.receive(packet);
						String recibido = new String (packet.getData()).trim();
						System.out.println(recibido+packet.getSocketAddress());
						
						if(contador<5) {
						switch(recibido) {
						
						case "Hamburguesa":
							Calendar c = Calendar.getInstance();
							Date fecha = c.getTime();
							
							String fechaSTR = fecha.toString();
							System.out.println(fechaSTR);
							
							SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
							
							String formateado = sdf.format(fecha);
							pedido.add(new Pedidos(20,70,burguer,this,"Hamburguesa", formateado));
							contador+=1;
							break;
						case "Perro":
							Calendar ca = Calendar.getInstance();
							Date fechas = ca.getTime();
							
							String fechaSTRs = fechas.toString();
							System.out.println(fechaSTRs);
							
							SimpleDateFormat sdfa= new SimpleDateFormat("HH:mm");
							
							String formateados = sdfa.format(fechas);
							pedido.add(new Pedidos(20,70,perro,this,"Perro", formateados));
							contador+=1;
							break;
						case "Wrap":
							Calendar c3 = Calendar.getInstance();
							Date fecha3 = c3.getTime();
							
							String fechaSTR3 = fecha3.toString();
							System.out.println(fechaSTR3);
							
							SimpleDateFormat sdf3= new SimpleDateFormat("HH:mm");
							
							String formateado3 = sdf3.format(fecha3);
							pedido.add(new Pedidos(20,70,wrap,this,"Wrap",formateado3));
							contador+=1;
							break;
						case "Jugos":
							Calendar c4 = Calendar.getInstance();
							Date fecha4 = c4.getTime();
							
							String fechaSTR4 = fecha4.toString();
							System.out.println(fechaSTR4);
							
							SimpleDateFormat sdf4= new SimpleDateFormat("HH:mm");
							
							String formateado4 = sdf4.format(fecha4);
							pedido.add(new Pedidos(20,70,jugo,this,"Jugos",formateado4));
							contador+=1;
							break;
						}
						
						}
						
						
						}
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}).start();	
		
	}
	
	public void draw() {
		background(255,255,255);
		for(int i=0;i<pedido.size();i++) {
			
			pedido.get(i).pintar(i);
			
			
		}
	}
	
	public void mouseClicked() {
		
		
			new Thread(
					()->{
						try {
							
							for(int i=0;i<pedido.size();i++) {
								
							// socket = new DatagramSocket(5000);
							int x = pedido.get(i).getPosx();
							int y = (pedido.get(i).getPosy()*i)+20;
							
							if(mouseX>x && mouseX<x+120 && mouseY>y && mouseY<(y+70)) {
								
							 mensaje = "Listo:pedido"+(i+1);
							 System.out.println(mensaje);
							 pedido.remove(i);
							 contador-=1;
							}
							}
							DatagramPacket packet = new DatagramPacket(
									mensaje.getBytes(),
									mensaje.getBytes().length,
									InetAddress.getByName("127.0.0.1"),
									51850
									);
							socket.send(packet);
							
							
							
						} catch (SocketException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					).start();
			
			
			
		
		
	}
	
	

}
