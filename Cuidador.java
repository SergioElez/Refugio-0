public class Cuidador extends Persona{
	
	@Override
	String asignarId()
	{
		String id = "cui" + contCui;
		contCui++;
		return id;
	}
	Cuidador()
	{
		super();
		Principal.desempleados--;
	}
	Cuidador(String nombre)
	{
		super();
		this.nombre=nombre;
		Principal.desempleados--;
	}
} 