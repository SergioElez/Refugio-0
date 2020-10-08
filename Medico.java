public class Medico extends Persona{

	@Override
	String asignarId()
	{
		String id = "med" + contMed;
		contMed++;
		return id;
	}
	Medico()
	{
		super();
		Principal.desempleados--;
	}
	Medico(String nombre)
	{
		super();
		this.nombre=nombre;
		Principal.desempleados--;
	}
} 