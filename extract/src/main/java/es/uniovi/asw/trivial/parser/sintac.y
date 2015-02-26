%{
package es.uniovi.asw.trivial.parser;
import es.uniovi.asw.trivial.pregunta.*;
import java.util.*;
 @SuppressWarnings("all")
%}

%TOKEN TEXTO
%TOKEN DOBLEPUNTO

%%

preguntas
	:	preguntas pregunta
	|	pregunta
	;
		
pregunta
	:	identificador TEXTO '{' respuestas '}'
	;
		
identificador
	:	DOBLEPUNTO TEXTO DOBLEPUNTO
	|
	;
	
respuestas
	:	respuestas respuesta
	|	respuesta
	;
		
respuesta
	:	simbolo TEXTO comentario
	;
		
comentario
	:	'#'	TEXTO
	|
	;
		
simbolo
	:	'='
	|	'~'
	;

%%

private Yylex lex;
private ArrayList<JSonable> preguntas;
private int token;

public Parser(Yylex lex, boolean debug) {
  this(debug);
  this.lex = lex;
}


public int parse() { return yyparse(); }



// Funciones requeridas por el parser

void yyerror(String s)
{
 System.out.println("Error sint�ctico en " + lex.line() + ":" + lex.column() + " Token = " + token + " lexema = \"" + lex.lexeme()+"\"");
}

int yylex() {
  try {
	token = lex.yylex();
	yylval = lex.lexeme();
	return token;
  } catch (Exception e) {
    return -1;
  }
}

public ArrayList<JSonable> getPreguntas() {
	return preguntas;
}



public Parser(Yylex lexico) {
	this.lex = lexico;
	lex.setParser(this);
}