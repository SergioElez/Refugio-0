public class Persona implements Entidad{
	int vida, maxVida, numComidas, nivel;
	String id, fechaNaz;
	
	int numComidaGenerada;

	//Esta variable cuenta los dias que han pasado desde que el explorador se fue del refugio
	int contDiasExp = 0;

	//Esta variable indica si el explorador se ha ido del refugio
	boolean expSeHaIdo = false;

	//El nombre es una array porque el metodo generar nombre devuelve una array y como copie ese codigo de internet paso de cambiarlo
	String nombre;

	//Cuenta cuantos niños, agricultores, cuidadores, etc (ESTO ES PARA ASIGNAR UN NUMERO EN LOS IDs)
	static int contDes = 0;
	static int contNin = 0;
	static int contAgr = 0;
	static int contCui = 0;
	static int contExp = 0;
	static int contMed = 0;
	static int contSol = 0;

	//METODOS
	String asignarId()
	{
		String id = "des" + contDes;
		contDes++;
		return id;
	}

	@Override
	public String generarNombre() {
		String nombresAleatorios;
		String[] nombres = { "Andrea", "David", "Juanito", "Carlos", "Adrian", "Gabriel", "Rafa", "Miguel",
			"Jose", "Pepe", "Antonio", "Sergio", "Guillermo", "Cristian", "Rosa", "Sara", "Laura",
			"Esperanza", "Cristina", "Maria"};
		String[] apellidos = { "Gomez", "Cruz", "Garcia", "Elez", "Ruiz", "González", "Rodríguez", "Fernández",
			"López", "Martínez", "Sánchez", "Pérez", "Gómez", "Martin", "Jiménez", "Hernández", "Diaz",
			"Moreno" };
		nombresAleatorios = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) + 1)))] + " "
			+ apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) + 1)))];

		return  nombresAleatorios;
	}
	//FIN DE METODOS

	//GETTERS Y SETTERS
	
	String getId()
	{
		return this.id;
	}

	//TODO ESTO LO HEREDA DE LA INTERFAZ ENTIDAD
	@Override
	public int getVida()
	{
		return this.vida;
	}
	@Override
	public void setVida(int vida)
	{
		this.vida = vida;
	}

	@Override
	public int getMaxVida()
	{
		return this.maxVida;
	}

	@Override
	public void setMaxVida(int maxVida)
	{
		this.maxVida = maxVida;
	}

	@Override
	public String getNombre()
	{
		return this.nombre;
	}
	//FIN DE GETTERS Y SETTERS

	//CONSTRUCTORES
	
	//ADULTO
	Persona()
	{
		this.id = asignarId();
		this.maxVida=300;
		this.vida=maxVida;
		this.nivel=1;
		this.nombre=generarNombre();
		this.fechaNaz = Principal.fecha;
		this.numComidas=3;

		Principal.desempleados++;
		Principal.numNinos--;
	}
	
	Persona(String nombre)
	{
		this.id = asignarId();
		this.maxVida=300;
		this.vida=maxVida;
		this.nivel=1;
		this.nombre=nombre;
		this.fechaNaz = Principal.fecha;
		this.numComidas=3;

		Principal.desempleados++;
		Principal.numNinos--;
	}
	
	//FIN DE CONSTRUCTORES
} 