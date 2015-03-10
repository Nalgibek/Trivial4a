package es.uniovi.asw.trivial;

import java.util.ArrayList;
import java.util.List;

public class Juego {
	
	private Tablero tablero;
	private List<Jugador> jugadores;
	private List<String> categorias;
	private List<Categoria> preguntas;
	private Jugador jugadorActivo;
	Pregunta preguntaActual;
	boolean isQuesito = false;
	
	public Juego(int tamañoTablero, List<Jugador> jugadores,List<Categoria> preguntas)
	{
		
		this.jugadores=jugadores;
		this.preguntas=preguntas;
		categorias= new ArrayList<String>();
		
		for(Categoria c: preguntas)
			if(!categorias.contains(c.getCategoria()))
				categorias.add(c.getCategoria());
		
		jugadorActivo = jugadores.get(0);
		
		tablero = new Tablero(tamañoTablero, categorias, jugadores);
		
		preguntaActual=null;
		isQuesito=false;
	}
	
	public void conseguirPregunta()
	{
		Categoria categoriaActual=null;

		
		
		categoriaActual=tablero.getCategoria(jugadorActivo);
		isQuesito = tablero.isQuesito(jugadorActivo.getPosicion());
		
		preguntaActual = conseguirPregunta(categoriaActual);
		//hasta aquí hemos sacado la pregunta de la categoria pertinente y si vale por quesito
		mostrarPregunta(preguntaActual,isQuesito);
		
	}

	private void mostrarPregunta(Pregunta pregunta, boolean isQuesito) {
		// TODO Auto-generated method stub
		
	}

	private Pregunta conseguirPregunta(Categoria categoriaActual) {
		//TODO mostrar pregunta, categoria y si vale por quesito
		return null;
	}

}
