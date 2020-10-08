public class Explorador extends Persona{

	@Override
	String asignarId()
	{
		String id = "exp" + contExp;
		contExp++;
		return id;
	}
	Explorador()
	{
		super();
		Principal.desempleados--;
	}

	Explorador(String nombre)
	{
		super();
		this.nombre=nombre;
		Principal.desempleados--;
	}
} 