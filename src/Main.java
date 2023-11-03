import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		// Creación de la baraja y los palos
		final String[] baraja = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
		final ArrayList<Character> palos = new ArrayList<>(Arrays.asList('P', 'D', 'T', 'C'));
		String carta;
		boolean leido, escalera;

		// Bucle infinito para leer las cartas del usuario
		while (true) {

			leido = false;
			escalera = true;
			String[] marcados = Arrays.copyOf(baraja, baraja.length);

			// Leer cuatro cartas del usuario
			for (int i = 0; i < 4; i++) {
				carta = s.next();
				if (carta.equals("0"))
					System.exit(0);
				if (leido) {
					if (palo != s.next().charAt(0) || !palos.contains(palo)) {
						escalera = false;
						s.nextLine();
						break;
					}
				} else {
					palo = s.next().charAt(0);
					leido = true;
				}
				// Marcar la carta en el array de cartas marcadas
				marcar(marcados, carta);
			}

			// Verificar si hay una secuencia válida y imprimir el resultado
			System.out.println(escalera ? valido(marcados) : "NADA");

		}

	}

	// Esta función marca una carta específica como "0" en el array de cartas
	// marcadas
	public static void marcar(String[] marcados, String numero) {
		for (int i = 0; i < marcados.length; i++)
			if (marcados[i].equals(numero))
				marcados[i] = "0";
	}

	// Esta función verifica si hay una secuencia válida en el array de cartas
	// marcadas
	public static String valido(String[] marcados) {
		int cont = 0;
		boolean start = false, hueco = false;
		String output = "";
		for (int i = 0; i < marcados.length; i++) {
			if (marcados[i].equals("0")) {
				start = true;
				cont++;
			} else if (start) { //hem començat a trobar cartes marcades (que tenim, que hem marcat a 0) i aquesta no ho està
				if (hueco) { //teníem un hueco anteriorment, i hem trobat un altre hueco sense haber trobat abans 5 consectives, 
					return "NADA"; //per tant, no podem fer l'escala
				}
				hueco = true; //hem trobat un hueco
				output = marcados[i]; //si té sopluciói la carta ha d'omplir el hueco
			}
			if (cont == 4 && hueco)
				return output + " " + palo; // tenim un hueco, per tant, la carta que falta és la del hueco
			if (cont == 4 && i + 1 < marcados.length)
				return marcados[i + 1] + " " + palo; // tenim 4 cartes consecutives i l'última no és l'As, per tant
														// trierm la carta següent
			else if (cont == 4)
				return marcados[i - 4] + " " + palo; // tenim 4 cartes consecutives, estem al final (As), per tant no
														// podem posar una carta superior i posem l'anterior a la
														// primera
		}
		return "NADA";
	}

	// Variable global para el palo de las cartas
	public static char palo;

}