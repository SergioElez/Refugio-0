import java.util.Scanner;
import java.util.ArrayList;

public class Metodos
{
	static int entrada = 0;
	static String sentrada = "";
	static String sdia, smes, sano, fecha;

	static int vidaMinimaSoldado;
	static String nombreVidaMinimaSoldado;

	static int vidaMinimaEnemigo;
	static String nombreVidaMinimaEnemigo;

	static int sumVidaTotalSoldados = 1;
	static int sumVidaTotalEnemigos = 1;


	static String espaciado = "\n\n\n\n\n\n\n\n\n\n\n";
	static String espaciadoH = "                                                                                                     ";

	//METODOS
	static void menu(String mensaje)
	{
		limpiar();
		hud();
		mensaje(mensaje);
		scanner();
		if(entrada<=0)
		{
			mensaje(" Escribe un numero mayor a 0", true);
		}
		limpiar();
		hud();
	}

	static void presentacion()
	{
		limpiar();
		mensaje(
			"\n\n" + ANSI_YELLOW + "               8888888b.  8888888888 8888888888 888     888  .d8888b.  8888888 .d88888b. " + ANSI_RESET + ANSI_RED +"       .d8888b."+ ANSI_RESET + "  \n" +
			ANSI_GREEN + "               888   Y88b 888        888        888     888 d88P  Y88b   888  d88P' 'Y88b" + ANSI_RESET + ANSI_RED +"      d88P  Y88b \n" +
			ANSI_YELLOW + "               888    888 888        888        888     888 888    888   888  888     888" + ANSI_RESET + ANSI_RED +"      888    888 \n" +
			ANSI_GREEN + "               888   d88P 8888888    8888888    888     888 888          888  888     888" + ANSI_RESET + ANSI_RED +"      888    888 \n" +
			ANSI_YELLOW + "               8888888P'  888        888        888     888 888  88888   888  888     888" + ANSI_RESET + ANSI_RED +"      888    888 \n" +
			ANSI_GREEN + "               888 T88b   888        888        888     888 888    888   888  888     888" + ANSI_RESET + ANSI_RED +"      888    888 \n" +
			ANSI_YELLOW + "               888  T88b  888        888        Y88b. .d88P Y88b  d88P   888  Y88b. .d88P" + ANSI_RESET + ANSI_RED +"      Y88b  d88P \n" +
			ANSI_GREEN + "               888   T88b 8888888888 888         'Y88888P'   'Y8888P88 8888888 'Y88888P' " + ANSI_RESET + ANSI_RED +"       'Y8888P'  \n" + ANSI_RESET +



			"\n\n\n" +

			"       Bienvenido " + ANSI_RED + "LIDER" + ANSI_RESET + ".\n       Justo antes de que la " + ANSI_GREEN + "destrucción nuclear" + ANSI_RESET + " aniquilase un 95% de la poblacion del planeta,\n" +
			"       un porcentaje de la sociedad se refugiaron en bunkeres suvencionados gracias al gobierno de los " + ANSI_CYAN + "Estados Unidos" + ANSI_RESET + ".\n\n" +
			"       Ahora " + ANSI_YELLOW + "TÚ" + ANSI_RESET + " eres el lider de un refugio.\n\n" + ANSI_CYAN +
			"       SOBREVIVE LIDER" + ANSI_RESET + "."

			, true, true);

	}

	//metodo acciones de momento no cuando sea un nuevo dia hay posibnilidad de que aparezcan enemigos
	static void hoyHayCombate()
	{
		//Hay un 10% de que en cada dia haya un combate
		if(generarNumAleatorio(0,10) == 1)
		{
			comenzarCombate();
		}
	}

	//Calcula la vida todal de los soldados
	static void actualizarVidaTotalSoldados()
	{
		sumVidaTotalSoldados = 0;
		for(int i = 0; i < Principal.ejercito.size(); i++)
		{
			sumVidaTotalSoldados = sumVidaTotalSoldados + Principal.ejercito.get(i).getVida();
		}
	}
	
	//Calcula la vida todal de los enemigos
	static void actualizarVidaTotalEnemigos()
	{
		sumVidaTotalEnemigos = 0;
		for(int i = 0; i < Principal.enemigos.size(); i++)
		{
			sumVidaTotalEnemigos = sumVidaTotalEnemigos + Principal.enemigos.get(i).getVida();
		}
	}


	static void comenzarCombate()
	{
		//cambiamos el espaciado para que los mensajes quepan en pantalla
		espaciado = "\n\n\n\n\n\n\n\n\n";

		int cada15dias = (Principal.dia * Principal.mes * (Principal.ano - 2999)) / 15;

		//minimo siempre habra 1
		Principal.enemigos.add(new Enemigo() );

		//cada 15 dias se añade un enemigo a la array 
		for(int i = 0; i < cada15dias; i++){
			Principal.enemigos.add(new Enemigo() );
		}
		
		mensaje("Han aparecido " + Principal.enemigos.size() + " enemigos!", true); 

		//genera un numero del 1 al 3 porque math.floor redondea a la baja
		int quienEmpieza = generarNumAleatorio(1,2);

		//Se genera aleatoriamente quien comienza el combate 50% de probabilidades
		if(quienEmpieza==1)
		{
			mensaje(true, "Los soldados empiezan primero", true);

			//El combate acaba cuando todos los enemigos o soldados han muerto
			while(Principal.enemigos.size()>0 | Principal.ejercito.size()>0)
			{
				//Si no hay soldados pierdes
				if(Principal.ejercito.size()==0 | Principal.municion<=0)
				{
					gameOver();
				}
					for(int i = 0; i < Principal.ejercito.size(); i++)
					{
						//Si la vida del enemgigo con menos vida es menor a 0 muere
						if(minVidaEnemigos().getVida() <= 0)
						{
							mensaje("* El enemigo " +  minVidaEnemigos().getNombre()  + " ha muerto. VIDA TOTAL ENEMIGOS: " + sumVidaTotalEnemigos, "amarillo", true );
							Principal.enemigos.remove( minVidaEnemigos() );
							recalcularMinVidaEnemigos();
							actualizarVidaTotalEnemigos();
						}
						else
						{
							recalcularMinVidaEnemigos();
							
							Principal.ejercito.get(i).atacar( minVidaEnemigos() );
							
							mensaje("> " + Principal.ejercito.get(i).getNombre() + " ha inflingido " + Principal.ejercito.get(i).getAtaque() + " de daño al enemigo " + minVidaEnemigos().getNombre(), "verde", true );
							Principal.municion = Principal.municion - 3;

							//Se vuelve a comprobar por que si no luego pega un golpe estando muerto
							if(minVidaEnemigos().getVida() <= 0)
							{
								mensaje("* El enemigo " +  minVidaEnemigos().getNombre() + " ha muerto. VIDA TOTAL ENEMIGOS: " + sumVidaTotalEnemigos, "amarillo", true );
								Principal.enemigos.remove( minVidaEnemigos() );
								recalcularMinVidaEnemigos();
								actualizarVidaTotalEnemigos();
							}
						}
					}
				recalcularMinVidaEnemigos();
				actualizarVidaTotalEnemigos();
				try
				{
					for(int i = 0; i < Principal.ejercito.size(); i++)
					{
						if(minVidaEjercito().getVida() < 0)
						{
							mensaje("* EL ALIADO " +  Principal.ejercito.get(i).getNombre().toUpperCase() + " HA MUERTO", "rojo", true );
							Principal.ejercito.remove( minVidaEjercito() );
							Principal.soldados--;
							recalcularMinVidaEnemigos();
							actualizarVidaTotalEnemigos();
						}
						else
						{

							recalcularMinVidaEjercito();
							
							Principal.enemigos.get(i).atacar( minVidaEjercito() );
							
							mensaje("< " + Principal.enemigos.get(i).getNombre() + " ha inflingido " + Principal.enemigos.get(i).getAtaque() + " de daño al aliado " + minVidaEjercito().getNombre(), "azul", true );

										//Se vuelve a comprobar por que si no luego pega un golpe estando muerto
							if(minVidaEjercito().getVida() < 0)
							{
								mensaje(" * EL ALIADO " +  Principal.ejercito.get(i).getNombre().toUpperCase() + " HA MUERTO", "rojo", true );
								Principal.ejercito.remove( minVidaEjercito() );
								Principal.soldados--;
								recalcularMinVidaEnemigos();
								actualizarVidaTotalEnemigos();
							}
						}
					}
				}
				catch (Exception e)
				{
					sumVidaTotalEnemigos=0;
					break;
				}
			}
		}
		else if (quienEmpieza==2)
		{
			mensaje(true, "Los enemigos empiezan primero", true);
			while(Principal.enemigos.size()>0 | Principal.ejercito.size()>0)
			{
				if(Principal.ejercito.size()==0 | Principal.municion<=0)
				{
					gameOver();
				}
				
				try
				{
					for(int i = 0; i < Principal.ejercito.size(); i++)
					{
						if(minVidaEjercito().getVida() < 0)
						{
							mensaje(" *EL ALIADO " +  Principal.ejercito.get(i).getNombre().toUpperCase() + " HA MUERTO", "rojo", true );
							Principal.ejercito.remove( minVidaEjercito() );
							Principal.soldados--;
							recalcularMinVidaEnemigos();
							actualizarVidaTotalEnemigos();
						}
						else
						{
							
							recalcularMinVidaEjercito();
							
							Principal.enemigos.get(i).atacar( minVidaEjercito() );
							
							mensaje("< " + Principal.enemigos.get(i).getNombre() + " ha inflingido " + Principal.enemigos.get(i).getAtaque() + " de daño al aliado " + minVidaEjercito().getNombre(), "azul", true );

							//Se vuelve a comprobar por que si no luego pega un golpe estando muerto
							if(minVidaEjercito().getVida() < 0)
							{
								mensaje("* EL ALIADO " +  Principal.ejercito.get(i).getNombre().toUpperCase() + " HA MUERTO", "rojo", true );
								Principal.ejercito.remove( minVidaEjercito() );
								Principal.soldados--;
								recalcularMinVidaEnemigos();
								actualizarVidaTotalEnemigos();
							}
						}
					}
				}
				catch (Exception e)
				{
					sumVidaTotalEnemigos=0;
					break;
				}

				recalcularMinVidaEnemigos();
				actualizarVidaTotalEnemigos();

				try
				{
					for(int i = 0; i < Principal.ejercito.size(); i++)
					{
						if(minVidaEnemigos().getVida() < 0)
						{
							mensaje("* El enemigo " +  minVidaEnemigos().getNombre()  + " ha muerto. VIDA TOTAL ENEMIGOS: " + sumVidaTotalEnemigos, "amarillo", true );
							Principal.enemigos.remove( minVidaEnemigos() );
							recalcularMinVidaEnemigos();
							actualizarVidaTotalEnemigos();
						}
						else
						{

							recalcularMinVidaEnemigos();
							
							Principal.ejercito.get(i).atacar( minVidaEnemigos() );
							
							mensaje("> " + Principal.ejercito.get(i).getNombre() + " ha inflingido " + Principal.ejercito.get(i).getAtaque() + " de daño al enemigo " + minVidaEnemigos().getNombre(), "verde", true );
							Principal.municion = Principal.municion - 10;

							//Se vuelve a comprobar por que si no luego pega un golpe estando muerto
							if(minVidaEnemigos().getVida() < 0)
							{
								mensaje("* El enemigo " +  minVidaEnemigos().getNombre()  + " ha muerto. VIDA TOTAL ENEMIGOS: " + sumVidaTotalEnemigos, "amarillo", true );
								Principal.enemigos.remove( minVidaEnemigos() );
								recalcularMinVidaEnemigos();
								actualizarVidaTotalEnemigos();
							}
						}
					}
				}
				catch (Exception e)
				{
					sumVidaTotalEnemigos=0;
					break;
				}
			}
		}
		//reiniciamos el espaciado a como estaba
		espaciado = "\n\n\n\n\n\n\n\n\n\n\n";
		System.out.println();}

		static void recalcularMinVidaEjercito()
		{
		//El - 1 es porque sino se sale de la array
			for(int i = 0; i <Principal.ejercito.size() - 1 ; i++)
			{

			//Si el primero tiene menos vida que el segundo la vida minima del grupo es esa
				if( Principal.ejercito.get(i).getVida() < Principal.ejercito.get(i+1).getVida() )
				{
					vidaMinimaSoldado = Principal.ejercito.get(i).getVida();
					nombreVidaMinimaSoldado = Principal.ejercito.get(i).getNombre();
				}
				else if( Principal.ejercito.get(i).getVida() > Principal.ejercito.get(i+1).getVida() )
				{
					vidaMinimaSoldado = Principal.ejercito.get(i+1).getVida();
				}
			//si tiene la misma vida que vuelva a comprobar
				else if( Principal.ejercito.get(i).getVida() == Principal.ejercito.get(i+1).getVida() )
				{
					if( Principal.ejercito.get(i).getVida() < vidaMinimaSoldado)
					{
						vidaMinimaSoldado = Principal.ejercito.get(i).getVida();
					}
				}
			}
		}

	//Este metodo devuelve el soldado con menos vida del ejercito
	//cada vez que ataque alguien se tiene que ejecutar este metodo para recalcular quien tiene la minima vida
		static Soldado minVidaEjercito()
		{
			actualizarVidaTotalSoldados();
			try
			{
				recalcularMinVidaEjercito();
			}
			catch (Exception e) 
			{
			//Si solo hay uno hace coje el primero
				vidaMinimaSoldado = Principal.ejercito.get(0).getVida();
			}
			int x = 0;
			int posicionSoldadoMenosVida = 0;
			while(x < Principal.ejercito.size() )
			{
			//Si la vida minima coincide con la vida minima de alguien del ejercito devuelve el soldado
				if(vidaMinimaSoldado==Principal.ejercito.get(x).getVida())
				{
					posicionSoldadoMenosVida = Principal.ejercito.indexOf( Principal.ejercito.get(x) );
				}
				x++;
			}
			return Principal.ejercito.get(posicionSoldadoMenosVida);
		}

		static void recalcularMinVidaEnemigos()
		{
			actualizarVidaTotalEnemigos();
			for(int i = 0; i <Principal.enemigos.size() - 1 ; i++)
			{

				if( Principal.enemigos.get(i).getVida() < Principal.enemigos.get(i+1).getVida() )
				{
					vidaMinimaEnemigo = Principal.enemigos.get(i).getVida();
					nombreVidaMinimaEnemigo = Principal.enemigos.get(i).getNombre();
				}
				else if( Principal.enemigos.get(i).getVida() > Principal.enemigos.get(i+1).getVida() )
				{
					vidaMinimaEnemigo = Principal.enemigos.get(i+1).getVida();
				}
				else if( Principal.enemigos.get(i).getVida() == Principal.enemigos.get(i+1).getVida() )
				{
					if( Principal.enemigos.get(i).getVida() < vidaMinimaEnemigo)
					{
						vidaMinimaEnemigo = Principal.enemigos.get(i).getVida();
					}
				}
			}
		}

	//Este metodo muestra el enemigo con menos vida (EN VERDAD SI HUBIESE HECHO UNA CLASE PADRE DE LOS DOS LO HABRIA HECHO FALTA UN METODO)
		static Enemigo minVidaEnemigos()
		{
			actualizarVidaTotalEnemigos();	
			int x1 = 0;
			int posicionEnemigoMenosVida = 0;
			while(x1 < Principal.enemigos.size() )
			{
				if(vidaMinimaEnemigo==Principal.enemigos.get(x1).getVida())
				{
					posicionEnemigoMenosVida = Principal.enemigos.indexOf( Principal.enemigos.get(x1) );
				}
				x1++;
			}
			try
			{
				return Principal.enemigos.get(posicionEnemigoMenosVida);
			}
			catch(Exception e)
			{
				return null;
			}
		}

	//Genera numero aleatorios entre un minimo y un maximo
		static int generarNumAleatorio(int minimo, int maximo)
		{
			return (int)Math.floor(Math.random()*(maximo-minimo)+minimo);
		}

	//metodo generarEnemigo
		static void generarEnemigo()
		{

		}

	//DEVUELVE LA FECHA ACTUAL
		static String returnFecha()
		{
			sdia = Integer.toString(Principal.dia);
			smes = Integer.toString(Principal.mes);
			sano = Integer.toString(Principal.ano);
			fecha = sdia + "/" + smes + "/" + sano;

			return fecha;
		}

	// LA FECHA ACTUAL
		static void setFecha(String sdia, String smes, String sano)
		{
			sdia = Integer.toString(Principal.dia);
			smes = Integer.toString(Principal.mes);
			sano = Integer.toString(Principal.ano);
			fecha = sdia + "/" + smes + "/" + sano;
		}

	//PRINTEA UN MENSAJE
		static void mensaje(String mensaje)
		{
			System.out.println(" " + mensaje);
		}

	//PRINTEA UN MENSAJE Y ESPERA A QUE EL USUARIO PULSE UNA TECLA
		static void mensaje(String mensaje, boolean readkey)
		{
			System.out.println(" " + mensaje);
			System.console().readLine();
		}
	//PRINTEA UN MENSAJE EN UN COLOR PERO SIN RETORNO DE CARRO
		static void mensaje(String mensaje, String color)
		{
			textColor(" " + mensaje, color, true);
		}
	//PRINTEA UN MENSAJE EN UN COLOR Y ESPERA A QUE EL USUARIO PULSE UNA TECLA
		static void mensaje(String mensaje, String color, boolean readkey)
		{
			textColor(" " +mensaje, color);
			System.console().readLine();
		}
	//PRINTEA UN MENSAJE, ESPERA UNA TECLA Y LIMPIA PANTALLA
		static void mensaje(String mensaje, boolean readkey, boolean limpiar_refrescar)
		{
			System.out.println(" " +mensaje);
			System.console().readLine();
			limpiar();
			hud();
		}

	//LIMPIA PANTALLA, PRINTEA UN MENSAJE, ESPERA UNA TECLA Y LIMPIA PANTALLA
		static void mensaje(boolean limpiar_refrescar_atras, String mensaje, boolean readkey, boolean limpiar_refrescar)
		{
			limpiar();
			hud();
			System.out.println(" " + mensaje);
			System.console().readLine();
			limpiar();
			hud();
		}

	//LIMPIA PANTALLA, PRINTEA UN MENSAJE Y ESPERA UNA TECLA
		static void mensaje(boolean limpiar_refrescar_atras, String mensaje, boolean readkey)
		{
			limpiar();
			hud();
			System.out.println(" " + mensaje);
			System.console().readLine();
		}

	//LIMPIA PANTALLA Y PRINTEA UN MENSAJE
		static void mensaje(boolean limpiar_refrescar_atras, String mensaje)
		{
			limpiar();
			hud();
			System.out.println(" " + mensaje);
		}

	//LIMPIA LA PANTALLA
		static void limpiar()
		{
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (Exception e) {}
		}

	//MUESTRA TODOS LOS IDS Y NOMBRES DE TODAS LAS PERSONAS
		static void debug()
		{
			for(int i = 0; i < Principal.ejercito.size(); i++){
				mensaje(" -" + Principal.ejercito.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.ejercito.get(i).getId() + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.ejercito.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.ejercito.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.ejercito.get(i).fechaNaz + ANSI_RESET, true);
			}

			for(int i = 0; i < Principal.personas.size(); i++){
				mensaje(" -" + Principal.personas.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.personas.get(i).getId() + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.personas.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.personas.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.personas.get(i).fechaNaz + ANSI_RESET, true);
			}
			for(int i = 0; i < Principal.ninos.size(); i++){
				mensaje(" -" + Principal.ninos.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.ninos.get(i).getId() + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.ninos.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.ninos.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.ninos.get(i).fechaNaz + ANSI_RESET, true);
			}			
			limpiar();
			hud();
		}

	//SIRVE PARA ACORTAR EL SCANNER (int)
		static void scanner()
		{
			try
			{
				mensaje(">", "amarillo");
				Scanner sc = new Scanner(System.in);
				entrada = sc.nextInt();				
			}
			catch(Exception e)
			{
				limpiar();
				hud();
				mensaje("Introduce un valor correcto", true);
				limpiar();
				hud();
			}
		}

	//SIRVE PARA ACORTAR EL SCANNER (String)
		static void scanner(boolean isString)
		{
			try
			{
				mensaje(">", "amarillo");
				Scanner sc = new Scanner(System.in);
				sentrada = sc.nextLine();				
			}
			catch(Exception e)
			{
				mensaje("Introduce un valor correcto", true);
				limpiar();
				hud();
			}
		}

		static void avanzarDia()
		{
			if(Principal.dia==30)
			{
				Principal.dia=1;
				if(Principal.mes==12)
				{
					Principal.mes=1;
					Principal.ano++;
				}
				else
				{
					Principal.mes++;
				}
			}
			else
			{
			//NUEVO DIA
				Principal.dia++;

				for(int i = 0; i < Principal.ninos.size(); i++){
					//Aumenta el dia de vida de cada niño
					Principal.ninos.get(i).diasNino++;

					//Si el niño tiene 15 dias se converte en adulto
					if(Principal.ninos.get(i).diasNino == generarNumAleatorio(6,9))
					{
						textColor(Principal.ninos.get(i).getNombre() + " ya es un adulto!", "azul");
						mensaje("Que te gustaria que fuese?", true);

						Principal.mostrarMenu(true, "Soldado", "Medico", "Explorador", "Cuidador", "Agricultor", "Desempleado");

						switch(entrada)
						{
							case 1:
								Principal.ejercito.add( new Soldado( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " ahora es un soldado", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								Principal.soldados++;
								break;

							case 2:
								Principal.personas.add( new Medico( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " ahora es un medico", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								Principal.medicos++;
								break;
							
							case 3:
								Principal.personas.add( new Explorador( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " ahora es un explorador", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								Principal.exploradores++;
								break;
							
							case 4:
								Principal.personas.add( new Cuidador( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " ahora es un cuidador", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								Principal.cuidadores++;

								//Por cada nivel que tengan los cuidadores aumenta el tamaño de niños a 3
								for(int j = 0; j < Principal.nivelCui; j++){
									Principal.ninMax = Principal.ninMax + 3;
								}
								break;
							
							case 5:
								Principal.personas.add( new Agricultor( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " ahora es un agricultor", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								Principal.agricultores++;
								break;
							
							case 6:
								Principal.personas.add( new Persona( Principal.ninos.get(i).getNombre() ) );
								mensaje( Principal.ninos.get(i).getNombre() + " no tiene profesion", true);
								Principal.ninos.remove( Principal.ninos.get(i) );
								break;
						}

						
					}
				}

				//Cada dia se resta la comida que consume cada unidad y se suma la que genera
				for(int i = 0; i<Principal.personas.size() ; i++){
					Principal.comida = Principal.comida - Principal.personas.get(i).numComidas ;
					Principal.comida = Principal.comida + Principal.personas.get(i).numComidaGenerada;	
				}
				
				//Cuando duermes hay un 5% de probabilidad por medico de que generes suministros (CUANTOS MAS MEDICOS MAS PROBABILIDAD)
				for(int i = 0; i<Principal.personas.size() ; i++){
					if(Principal.personas.get(i).getClass().getName()=="Medico")
					{
						if( generarNumAleatorio(1,100)<=5 )
						{
							Principal.suministros+=1;
						}
					}
				}

				//Cada dia suma un dia a los Exploradores que se han ido
				for(int j = 0; j < Principal.expIdos.size(); j++){
					Principal.expIdos.get(j).contDiasExp++;
				}

				//Comprueba si hoy hay combate y si lo hay lo invoca
				hoyHayCombate();

				// Cuando pasan 30 dias ganas 1 de sabiduria
				if(Principal.dia == 30)
				{
					Principal.sabiduria++;
				}

				//Cuando los exploradores pasan 7 dias fuera del refugio vuelven
				for(int i = 0; i < Principal.expIdos.size(); i++){


					if(Principal.expIdos.get(i).contDiasExp == 7 )
					{
						int anadeMunicion = generarNumAleatorio(30,50);
						double anadeSuministros = generarNumAleatorio(0,2);

						if(Principal.nivelExp>1)
						{
							anadeMunicion+=5;
							anadeSuministros+=1;
						}


						Principal.municion = Principal.municion + anadeMunicion;
						Principal.suministros = Principal.suministros + anadeSuministros;

						mensaje(Principal.expIdos.get(i).getNombre() + " ha vuelto al refugio\n Ha vuelto con " + anadeMunicion + " de municion y " + anadeSuministros + " de suministros", true);

						//Añade al explorador a la array de personas y lo borra en la array de exploradores idos
						Principal.exploradores++;
						Principal.personas.add( Principal.expIdos.get(i) );
						Principal.expIdos.remove( Principal.expIdos.get(i) );
					}
				}


				if(Principal.comida<0)
				{
					gameOver();
				}
			}

		}

		static void crecerNino()
		{
			for(int i = 0; i < Principal.ninos.size(); i++){
				if(Principal.ninos.get(i).diasNino==10)
				{
					Principal.personas.add( new Persona( Principal.ninos.get(i).getNombre() ) );
					Principal.ninos.remove( Principal.ninos.get(i) );
				}
			}
		}

		static void actualizarNumHud()
		{
			for(int i = 0; i < Principal.personas.size(); i++){
				if(Principal.personas.get(i).getClass().getName()=="Soldado")
				{
					Principal.soldados++;
				}
				if(Principal.personas.get(i).getClass().getName()=="Medico")
				{
					Principal.medicos++;
				}
				if(Principal.personas.get(i).getClass().getName()=="Agricultor")
				{
					Principal.agricultores++;
				}
				if(Principal.personas.get(i).getClass().getName()=="Cuidador")
				{
					Principal.cuidadores++;
				}
				if(Principal.personas.get(i).getClass().getName()=="Explorador")
				{
					Principal.exploradores++;
				}
				if(Principal.ninos.get(i).getClass().getName()=="Nino")
				{
					Principal.numNinos++;
				}
			}
		}

		static int calcPersonasTotales()
		{
			return Principal.personas.size() + Principal.ejercito.size() + Principal.ninos.size();
		}

	//MUESTRA LA INTERFAZ TODO EL RATO
		static void hud()
		{


			limpiar();		
			System.out.println(
				ANSI_GREEN + " Soldados: "  + 	Principal.soldados + ANSI_RESET + 
				ANSI_CYAN + "  Medicos: " + Principal.medicos + ANSI_RESET + 
				ANSI_RED + "  Exploradores: " +  Principal.exploradores + ANSI_RESET +
				ANSI_PURPLE + "  Cuidadores: " +  Principal.cuidadores + ANSI_RESET +
				ANSI_YELLOW + "  Agricultores: " +  Principal.agricultores + ANSI_RESET +
				"  " + ANSI_CYAN_BACKGROUND + ANSI_BLUE +  "Niños:" +  " "+ Principal.numNinos  + ANSI_RESET+ 
				"  " + ANSI_GREEN_BACKGROUND + ANSI_BLACK + "Desemp:" + " "+ Principal.desempleados + ANSI_RESET+ 
				"  " + ANSI_BLUE_BACKGROUND + ANSI_CYAN + "Refugio:" + " "+ calcPersonasTotales()  + ANSI_RESET+ 
				"  " + ANSI_WHITE_BACKGROUND + ANSI_BLACK + returnFecha() + ANSI_RESET +
				"\n\n" +
				ANSI_WHITE  + " Comida: " + Principal.comida + ANSI_RESET +
				ANSI_WHITE  + "  Municion: " + Principal.municion + ANSI_RESET +
				ANSI_WHITE  + "  Suministros: " + Double.toString(Principal.suministros) + ANSI_RESET +
				ANSI_WHITE  + "  Sabiduria: " + Principal.sabiduria + ANSI_RESET +
				"\n" +
				ANSI_YELLOW + espaciadoH + "+---- Nivel ----+" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "|" + ANSI_RESET + " Soldado:    " + Principal.nivelSol + ANSI_YELLOW + " |" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "|" + ANSI_RESET + " Medico:     " + Principal.nivelMed + ANSI_YELLOW + " |" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "|" + ANSI_RESET + " Explorador: " + Principal.nivelExp + ANSI_YELLOW + " |" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "|" + ANSI_RESET + " Cuidador:   " + Principal.nivelCui + ANSI_YELLOW + " |" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "|" + ANSI_RESET + " Agricultor: " + Principal.nivelAgr + ANSI_YELLOW + " |" + ANSI_RESET + "\n" +
				ANSI_YELLOW + espaciadoH + "+---------------+" + ANSI_RESET + "\n" +

				espaciado
				);
		}

		static void gameOver()
		{
			limpiar();
			hud();
			mensaje(
				"                                    .d8888b.         d8888 888b     d888 8888888888  \n" + 
				"                                    d88P  Y88b       d88888 8888b   d8888 888         \n" +
				"                                    888    888      d88P888 88888b.d88888 888         \n" +
				"                                    888            d88P 888 888Y88888P888 8888888     \n" +
				"                                    888  88888    d88P  888 888 Y888P 888 888         \n" +
				"                                    888    888   d88P   888 888  Y8P  888 888         \n" +
				"                                    Y88b  d88P  d8888888888 888   '   888 888         \n" +
				"                                     Y8888P88  d88P     888 888       888 8888888888  \n" +
				"                                     Y8888P88  d88P     888 888       888 8888888888  \n" +
				"                                                                                      \n" +
				"                                    			                                       \n" +
				"                                                                                      \n" +
				"                                     .d88888b.  888     888 8888888888 8888888b.  888 \n" +
				"                                    d88P'  'Y88 888     888 888        888   Y88b 888 \n" +
				"                                    888     888 888     888 888        888    888 888 \n" +
				"                                    888     888 Y88b   d88P 8888888    888   d88P 888 \n" +
				"                                    888     888  Y88b d88P  888        8888888P'  888 \n" +
				"                                    888     888   Y88o88P   888        888 T88b   Y8P \n" +
				"                                    Y88b. .d88P    Y888P    888        888  T88b      \n" +
				"                                     Y88888P'       Y8P     8888888888 888   T88b 888 \n" +
				"                                                                                      \n" 
				, "rojo", true);
			textColor("                                   ___________________________________________________\n", "azul");
			textColor("                                        Has sobrevivido: " + Principal.dia + " dias, " + (Principal.mes-1) + " meses y " + (3000-Principal.ano) + " anos", "verde");
			textColor("                                   ___________________________________________________\n", "azul");
			mensaje("", true);
			menu("¿Quieres empezar una nueva partida?\n 1. Si\n 2. No");
			if(entrada==1)
			{
				// Sigue el juego
			}
			else if(entrada==2)
			{
				Principal.running=false;
			}
		}

	//PRINTEA UN TEXTO EN UN COLOR
		static void textColor(String texto, String color)
		{
			if(color.equals("verde"))
			{
				mensaje(ANSI_GREEN + texto +  ANSI_RESET);
			}
			else if(color.equals("rojo"))
			{
				mensaje(ANSI_RED + texto +  ANSI_RESET);
			}
			else if(color.equals("amarillo"))
			{
				mensaje(ANSI_YELLOW + texto +  ANSI_RESET);
			}
			else if(color.equals("azul"))
			{
				mensaje(ANSI_CYAN + texto +  ANSI_RESET);
			}
		}
	//PRINTEA UN TEXTO EN UN COLOR SIN RETORNO DE CARROC
		static void textColor(String texto, String color, boolean sinRetornoDeCarro)
		{
			if(color.equals("verde"))
			{
				System.out.print(ANSI_GREEN + texto +  ANSI_RESET);
			}
			else if(color.equals("rojo"))
			{
				System.out.print(ANSI_RED + texto +  ANSI_RESET);
			}
			else if(color.equals("amarillo"))
			{
				System.out.print(ANSI_YELLOW + texto +  ANSI_RESET);
			}
			else if(color.equals("azul"))
			{
				System.out.print(ANSI_CYAN + texto +  ANSI_RESET);
			}
		}

	//PRINTEA UN TEXTO EN UN COLOR SIN RETORNO DE CARROC
		static String returnTextColor(String texto, String color)
		{
			String textoColor = "";
			if(color.equals("verde"))
			{
				textoColor = ANSI_GREEN + texto +  ANSI_RESET;
			}
			else if(color.equals("rojo"))
			{
				textoColor = ANSI_RED + texto +  ANSI_RESET;
			}
			else if(color.equals("amarillo"))
			{
				textoColor = ANSI_YELLOW + texto +  ANSI_RESET;
			}
			else if(color.equals("azul"))
			{
				textoColor = ANSI_CYAN + texto +  ANSI_RESET;
			}
			return textoColor;
		}	

	//FIN DE METODOS

	//COLORES JAVA
		public static final String ANSI_RESET = "\u001B[0m";
		public static final String ANSI_BLACK = "\u001B[30m";
		public static final String ANSI_RED = "\u001B[31m";
		public static final String ANSI_GREEN = "\u001B[32m";
		public static final String ANSI_YELLOW = "\u001B[33m";
		public static final String ANSI_BLUE = "\u001B[34m";
		public static final String ANSI_PURPLE = "\u001B[35m";
		public static final String ANSI_CYAN = "\u001B[36m";
		public static final String ANSI_WHITE = "\u001B[37m";

		public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
		public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
		public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
		public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
		public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
		public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
		public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
		public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//FIN DE COLORES JAVA
	}