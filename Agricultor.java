public class Agricultor extends Persona{
	
	//Esta variable muestra la cantidad de comida que genera un agricultor

	@Override
	String asignarId()
	{
		String id = "agr" + contAgr;
		contAgr++;
		return id;
	}

	void subirNivel()
	{
		nivel++;
		numComidaGenerada+=4;
	}

	Agricultor()
	{
		super();
		this.id=asignarId();
		numComidaGenerada=5;

		Principal.desempleados--;
	}
	Agricultor(String nombre)
	{
		super();
		this.id=asignarId();
		this.nombre=nombre;
		Principal.desempleados--;
		numComidaGenerada=5;
	}
} 