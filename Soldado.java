public class Soldado extends Persona{
	
	int ataque;
	boolean turno;

	//cambiar parametro soldado a enemigo
	void atacar(Enemigo objetivo)
	{
			objetivo.vida = objetivo.vida - this.ataque;
	}
	
	int getAtaque()
	{
		return this.ataque;
	}

	@Override
	String asignarId()
	{
		String id = "sol" + contSol;
		contSol++;
		return id;
	}
	
	Soldado()
	{
		super();
		this.ataque=Metodos.generarNumAleatorio(19,26);
		this.turno=false;
		Principal.desempleados--;
	}

	Soldado(int vida)
	{
		super();
		this.ataque=Metodos.generarNumAleatorio(19,26);
		this.turno = false;
		this.vida = vida;
		Principal.desempleados--;
	}

	Soldado(String nombre)
	{
		this.id = asignarId();
		this.ataque=Metodos.generarNumAleatorio(19,26);
		this.turno = false;
		this.nombre = nombre;

		Principal.desempleados--;
	}
} 