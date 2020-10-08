import java.util.Scanner;
import java.util.ArrayList;

public class Principal extends Metodos{

	//Estas variables van a almacenar la cantidad que hay de soldados, medicos etc 
	static int soldados, medicos, cuidadores, exploradores, agricultores, numNinos, desempleados;

	static int comida, municion, sabiduria = 1;
	static double suministros;
	static int ninMax = 3;

	static int nivelMed = 1, nivelSol = 1, nivelCui = 1, nivelExp = 1, nivelAgr = 1;

	static int dia, mes, ano;

	// Creo las arrays que me van a hacer falta para el juego
	static ArrayList<Persona> personas = new ArrayList<Persona>();
	static ArrayList<Persona> expIdos = new ArrayList<Persona>();
	static ArrayList<Nino> ninos = new ArrayList<Nino>();
	static ArrayList<Soldado> ejercito = new ArrayList<Soldado>();

	//Esta array esta vacia, cuando aparece una pelea simplemente se añaden enemigos
	static ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>(); 

	static boolean running, idEncontrado;


//METODO MENU (ES EL UNICO METODO QUE TIENE EL MAIN)
	static void mostrarMenu(String opcion1)
	{
		menu(
			returnTextColor("1. ", "azul")
			);
	}
	static void mostrarMenu(String opcion1, String opcion2)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2
			);
	}
	static void mostrarMenu(String opcion1, String opcion2, String opcion3)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3
			);
	}
	static void mostrarMenu(String opcion1, String opcion2, String opcion3, String opcion4)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3 + "\n" +
			returnTextColor(" 4. ", "azul") + opcion4
			);
	}
	static void mostrarMenu(String opcion1, String opcion2, String opcion3, String opcion4, String opcion5)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3 + "\n" +
			returnTextColor(" 4. ", "azul") + opcion4 + "\n" +
			returnTextColor(" 5. ", "azul") + opcion5
			);
	}
	static void mostrarMenu(boolean verde, String opcion1, String opcion2, String opcion3, String opcion4, String opcion5)
	{
		menu(
			returnTextColor("1. ", "verde") + opcion1 + "\n" +
			returnTextColor(" 2. ", "verde") + opcion2 + "\n" +
			returnTextColor(" 3. ", "verde") + opcion3 + "\n" +
			returnTextColor(" 4. ", "verde") + opcion4 + "\n" +
			returnTextColor(" 5. ", "verde") + opcion5
			);
	}

	static void mostrarMenu(String opcion1, String opcion2, String opcion3, String opcion4, String opcion5, String opcion6)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "         " +returnTextColor(" 6. ", "azul") + opcion6 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3 + "\n" +
			returnTextColor(" 4. ", "azul") + opcion4 + "\n" +
			returnTextColor(" 5. ", "azul") + opcion5 + "\n" 

			);
	}

	static void mostrarMenu(boolean verde, String opcion1, String opcion2, String opcion3, String opcion4, String opcion5, String opcion6)
	{
		menu(
			returnTextColor("1. ", "verde") + opcion1 + "         " +returnTextColor(" 6. ", "verde") + opcion6 + "\n" +
			returnTextColor(" 2. ", "verde") + opcion2 + "\n" +
			returnTextColor(" 3. ", "verde") + opcion3 + "\n" +
			returnTextColor(" 4. ", "verde") + opcion4 + "\n" +
			returnTextColor(" 5. ", "verde") + opcion5
			);
	}
	static void mostrarMenu(String opcion1, String opcion2, String opcion3, String opcion4, String opcion5, String opcion6, String opcion7)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "         " + returnTextColor(" 6. ", "azul") + opcion6 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + "  " + returnTextColor(" 7. ", "azul") + opcion7 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3 + "\n" +
			returnTextColor(" 4. ", "azul") + opcion4 + "\n" +
			returnTextColor(" 5. ", "azul") + opcion5 + "\n" 

			);
	}
	static void mostrarMenu(String opcion1, String opcion2, String opcion3, String opcion4, String opcion5, String opcion6, String opcion7, String opcion8)
	{
		menu(
			returnTextColor("1. ", "azul") + opcion1 + "        " + returnTextColor(" 6. ", "azul") + opcion6 + "\n" +
			returnTextColor(" 2. ", "azul") + opcion2 + " " + returnTextColor(" 7. ", "azul") + opcion7 + "\n" +
			returnTextColor(" 3. ", "azul") + opcion3 + "                    " + returnTextColor(" 8. ", "azul") + opcion8 + "\n" +
			returnTextColor(" 4. ", "azul") + opcion4 + "\n" +
			returnTextColor(" 5. ", "azul") + opcion5 + "\n" 

			);
	}
	
	// MAIN
	public static void main (String[] args){
		
		idEncontrado=false;

		//Empezamos en el 1/1/3000
		dia=1;
		mes=1;
		ano=3000;

		//Esto lo hice para que en el debug no saliera null
		setFecha("1", "1", "3000");

		//EMPEZAMOS EL JUEGO CON 1 SOLDADO, 1 MEDICO, 1 CUIDADOR, 1 EXPLORADOR Y 2 AGRICULTORES Y 2 NIÑOS
		ejercito.add(new Soldado());
		personas.add(new Medico());
		personas.add(new Cuidador());
		personas.add(new Explorador());
		personas.add(new Agricultor());
		personas.add(new Agricultor());
		ninos.add(new Nino());
		ninos.add(new Nino());

		comida=200;
		suministros=1;
		municion=100;

		soldados=1;
		medicos=1;
		cuidadores=1;
		exploradores=1;
		agricultores=2;
		numNinos=2;
		desempleados=0;
		running = true;
		
		presentacion();

		//Comienza el loop jugable
		while(running){ 

			hud();

			mostrarMenu("Seleccionar unidad", "Criar nuevo hijo y dormir", "Dormir", "Curar Soldado", "Mandar Explorador", "Subir de nivel", "Ayuda", "Salir del juego");

			switch(entrada)
			{
				case 1:
				mostrarMenu("Escribir el id de la unidad", "Mostrar lista");
				if(entrada==1)
				{
					mensaje("Escribe el id");
					scanner(true);
					for(int i = 0; i < ejercito.size(); i++){
						if(sentrada.equals(ejercito.get(i).getId()))
						{
							idEncontrado=true;

							mensaje(true,
								"Nombre: " + ejercito.get(i).getNombre() + "\n" +
								" Id: " + ejercito.get(i).getId() + "\n" +
								" Oficio: " + ejercito.get(i).getClass().getName() + "\n" +
								" Vida: " + Principal.ejercito.get(i).getVida()+ "/" + Principal.ejercito.get(i).getMaxVida() + "\n" +
								" Fecha Nacimiento: " + Principal.ejercito.get(i).fechaNaz
								, true, true);
						}
					}
					for(int i = 0; i < personas.size(); i++){
						if(sentrada.equals(personas.get(i).getId()))
						{
							idEncontrado=true;

							mensaje(true,
								"Nombre: " + personas.get(i).getNombre() + "\n" +
								" Id: " + personas.get(i).getId() + "\n" +
								" Oficio: " + personas.get(i).getClass().getName() + "\n" +
								" Vida: " + Principal.personas.get(i).getVida()+ "/" + Principal.personas.get(i).getMaxVida() + "\n" +
								" Fecha Nacimiento: " + Principal.personas.get(i).fechaNaz
								, true, true);
						}
					}

					if(idEncontrado==false)
					{
						mensaje("id no encontrado", true, true);
					}
				}
				else if(entrada==2)
				{
					debug();
				}
				break;

				case 2:
				if(cuidadores>0)
				{
					if(numNinos<ninMax)
					{
						ninos.add(new Nino());
						mensaje("Ha nacido un nuevo niño en el refugio", "verde",  true);
						avanzarDia();
						limpiar();
						hud();
					}
					else
					{
						mensaje("Se necesitan cuidadores", "amarillo",  true);
						limpiar();
						hud();
					}
				}
				break;

				case 3:
				limpiar();
				hud();
				avanzarDia();
				limpiar();
				hud();
				break;
				
				case 4:

				mostrarMenu("Curar al soldado con menos vida", "Curar por id", "Mostrar lista de soldados");
				switch(entrada)
				{
					case 1:
					if(suministros<=0)
					{
						mensaje("No tienes suministros suficientes", true);
					}
					else
					{
						if( Metodos.minVidaEjercito().getVida() == Metodos.minVidaEjercito().getMaxVida() )
						{
							mensaje("No te hace falta esto", true);
						}
						else
						{
							if(nivelMed==1)
							{
								suministros--;
							}
							else if(nivelMed==2)
							{
								suministros -= 0.5;
							}

							Metodos.minVidaEjercito().setVida( Metodos.minVidaEjercito().getMaxVida() );
							mensaje("Se ha curado a " + Metodos.minVidaEjercito().getNombre(), true);

						}
					}
					break;

					case 2:

					mensaje("Escribe el id");
					scanner(true);
					idEncontrado=false;
					for(int i = 0; i < ejercito.size(); i++){
						if(sentrada.equals(ejercito.get(i).getId()))
						{
							idEncontrado=true;

							if(suministros<=0)
							{
								mensaje("No tienes suministros suficientes", true);
							}
							else
							{
								if( ejercito.get(i).getVida() == ejercito.get(i).getMaxVida() )
								{
									mensaje("No te hace falta esto", true);
								}
								else
								{
									if(nivelMed==1)
									{
										suministros--;
									}
									else if(nivelMed==2)
									{
										suministros -= 0.5;
									}

									ejercito.get(i).setVida( ejercito.get(i).getMaxVida() );
									mensaje("Se ha curado a " + ejercito.get(i).getNombre(), true);
								}
							}


						}
					}
					if(idEncontrado==false)
					{
						mensaje("id no encontrado", true, true);
					}
					break;

					case 3:
					for(int i = 0; i < Principal.ejercito.size(); i++){
						mensaje(" -" + Principal.ejercito.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.ejercito.get(i).getId() + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.ejercito.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.ejercito.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.ejercito.get(i).fechaNaz + ANSI_RESET, true);
					}
					break;
				}

				break;

				case 5:
					mensaje("A que explorador quieres mandar fuera del refugio", true);
					mostrarMenu("Mandar a todos", "Escribe el id del explorador", "Mostrar exploradores", "Mostrar exploradores fuera del refugio");

					switch(entrada)
					{
						case 1:

							for(int i = 0; i < personas.size(); i++)
							{
								if(Principal.personas.get(i).getClass().getName()=="Explorador")
								{
										mensaje("Has mandado a " + personas.get(i).getNombre() + " a explorar", true);

										personas.get(i).expSeHaIdo = true;
										expIdos.add( personas.get(i) );
										personas.remove( personas.get(i) );
										exploradores--;
								}
							}

						break;

						case 2:
						mensaje("Escribe el id");
						scanner(true);
						idEncontrado=false;
						for(int i = 0; i < personas.size(); i++)
						{
							if(Principal.personas.get(i).getClass().getName()=="Explorador")
							{
								if(sentrada.equals(personas.get(i).getId()))
								{
									idEncontrado=true;

									mensaje("Has mandado a " + personas.get(i).getNombre() + " a explorar", true);

									personas.get(i).expSeHaIdo = true;
									expIdos.add( personas.get(i) );
									personas.remove( personas.get(i) );
									exploradores--;
								}
							}
						}
						if(idEncontrado==false)
						{
							mensaje("id no encontrado", true, true);
						}
						break;

						case 3:
						for(int i = 0; i < Principal.personas.size(); i++){
							if(Principal.personas.get(i).getClass().getName()=="Explorador")
							{
								mensaje("-" + Principal.personas.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.personas.get(i).getId() + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.personas.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.personas.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.personas.get(i).fechaNaz + ANSI_RESET, true);
							}
						}
						break;

						case 4:
						for(int i = 0; i < Principal.expIdos.size(); i++){
							if(Principal.expIdos.get(i).getClass().getName()=="Explorador")
							{
								mensaje("-" + Principal.expIdos.get(i).getNombre() + ANSI_CYAN + "  id: " + Principal.expIdos.get(i).getId() + ANSI_RESET + "  "+ ANSI_BLUE_BACKGROUND + ANSI_CYAN + "Dias fuera Total: " + Principal.expIdos.get(i).contDiasExp + ANSI_RESET + ANSI_GREEN + "  Vida: " + Principal.expIdos.get(i).getVida() + ANSI_RESET + "/" + ANSI_GREEN + Principal.expIdos.get(i).getMaxVida() + ANSI_RESET + ANSI_YELLOW + "  Fecha Naz.: " + Principal.expIdos.get(i).fechaNaz + ANSI_RESET, true);
							}
						}
						break;
					}

				break;

				case 6: 
					mensaje("A que profesion quieres subir de nivel?", true);
					mostrarMenu(true,"Soldado", "Medico", "Explorador", "Cuidador", "Agricultor");

					switch(entrada)
					{
						case 1:
						if(sabiduria>0)
						{
							nivelSol++;
							sabiduria--;
						}
						else
						{
							mensaje("No tienes suficiente puntos de sabiduria", true);
						}
						break;

						case 2:
						if(sabiduria>0)
						{
							nivelMed++;
							sabiduria--;
						}
						else
						{
							mensaje("No tienes suficiente puntos de sabiduria", true);
						}
						break;

						case 3:
						if(sabiduria>0)
						{
							nivelExp++;
							
							sabiduria--;
						}
						else
						{
							mensaje("No tienes suficiente puntos de sabiduria", true);
						}
						break;

						case 4:
						if(sabiduria>0)
						{
							nivelCui++;
							sabiduria--;
						}
						else
						{
							mensaje("No tienes suficiente puntos de sabiduria", true);
						}
						break;

						case 5:
						if(sabiduria>0)
						{
							nivelAgr++;
							sabiduria--;
						}
						else
						{
							mensaje("No tienes suficiente puntos de sabiduria", true);
						}
					break;
					}
				break;
				
				case 7:
					mostrarMenu("Explicación oficios", "Obtencion de recursos", "Nivel");
					switch(entrada)
					{
						case 1:
							mensaje(
								"-Soldados: Los soldados protegen el refugio de los enemigos que pueden aparecer cada dia." + "\n" +

								" -Medicos: Curan a los soldados gastando suministros, tambien pueden generar suministros cada dia." + "\n" +

								" -Exploradores: Al mandar un explorador fuera del refugio vuelve a la semana con municion y suministros." + "\n" +

								" -Cuidadores: Los cuidadores son necesarios para cuidar niños" + "\n" +

								" -Agricultores: Los agricultores generan comida cada dia"

								, true);
						break;
						case 2:
							mensaje(
								"-Comida: Los agricultores generan comida cada dia." + "\n" +

								" -Municion: Al mandar un explorador fuera del refugio vuelve a la semana con municion." + "\n" +

								" -Suministros: Los medicos pueden generar suministros cada dia, tambien al mandar un explorador fuera del refugio vuelve a la semana con suministros." + "\n" +

								" -Sabiduria: Al final de mes se genera 1 de sabiduria."

								, true);

						break;

						case 3:
							mensaje(
								"-Soldados: Al subir de nivel a los soldados hacen mas daño y gastan menos municion." + "\n" +

								" -Medicos: Cuando subes de nivel a los medicos gastan menos recursos al curar." + "\n" +

								" -Exploradores: Si subes de nivel a los exploradores traen mas recursos cuando vuelven al refugio." + "\n" +

								" -Cuidadores: Al subir a los cuidadores pueden cuidar a mas niños" + "\n" +

								" -Agricultores: Si subes a los agricultores generan mas comida por dia"

								, true);
						break;

					}
				break;

				case 8:
					mensaje("Estas Seguro de que quieres salir?\n", true);
					mostrarMenu("Si", "No");
					switch(entrada)
					{
						case 1:
							running = false;
						break;

						case 2:
						break;
					}
				break;
			}
		}
	}
}