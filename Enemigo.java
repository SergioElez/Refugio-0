public class Enemigo implements Entidad{

	int vida, maxVida, ataque;
	String nombre;

	public String generarNombre() {
		String nombresAleatorios;
		String[] nombres = { "Necrofago", "Araña mutante", "CucaRaton", "Escorpion radioactivo", "Avispa acida", "Perro rabioso", "Planta carnivora", "Mercenario agresivo",
			"Caiman de cloaca", "Cuervo gigante", "El CANCER", "Gigante", "Loco kamikaze", "Necrofago potenciado", "Oso agresivo", "Tu exnovia cabreada", "Topo cabreado",
			"Rufian de clase baja", "Aprendiz de tirador", "Gran tirador"};
		nombresAleatorios = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))];
		
		return  nombresAleatorios;
	}

	void atacar(Persona objetivo)
	{
			objetivo.vida = objetivo.vida - this.ataque;
	}

	int getAtaque()
	{
		return this.ataque;
	}
	
	//TODO ESTO LO HEREDA DE LA INTERFAZ ENTIDAD
	@Override
	public String getNombre()
	{
		return this.nombre;
	}

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

	Enemigo()
	{
		this.maxVida = Metodos.generarNumAleatorio(50, 120);
		this.vida = maxVida;
		this.nombre = generarNombre();
		this.ataque = Metodos.generarNumAleatorio(13, 22);
	}
}