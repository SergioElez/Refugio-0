public class Nino extends Persona{

	//Los dias que tiene el niño desde que nació
	int diasNino;

	@Override
	String asignarId()
	{
		String id = "nin" + contNin;
		contNin++;
		return id;
	}

	Nino()
	{
		this.maxVida=100;
		this.vida=maxVida;
		this.nivel=0;
		this.nombre=generarNombre();
		this.fechaNaz = Principal.fecha;
		this.id=asignarId();

		this.numComidas=2;

		Principal.numNinos++;
		Principal.numNinos++;
		Principal.desempleados--;
	}
} 