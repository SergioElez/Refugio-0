interface Entidad {

	//ESTA INTERFAZ VA A SER HEREDADA POR ENEMIGO Y PERSONA
	String generarNombre();
	String getNombre();
	
	void setVida(int vida);
	int getVida();

	int getMaxVida();
	void setMaxVida(int maxVida);


}