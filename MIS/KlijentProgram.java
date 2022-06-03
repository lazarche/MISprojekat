import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class KlijentProgram {

	public static void main(String[] args) throws IOException, NumberFormatException {

		// Prvo otvaramo soket prema serveru
		// a potom i tokove za citanje i pisanje
		// Koristimo try-with-resources kako ne bismo morali da brinemo o zatvaranju soketa
		// Otvaramo usput i tok za citanje sa tastature
		try (
				Socket socket = new Socket("localhost", 1234);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				BufferedReader unos = new BufferedReader(new InputStreamReader(System.in));
		) {
			System.out.println(in.readLine());
// static public String RegistrujKorisnika(String ime, String prezime, String godiste, String email, String sifra) 
            out.println("REG draza manijak 1995 drazamanijak@gmail.com 4321");
            System.out.println("odgovor" + in.readLine());
            out.print("EXT");
		}
	}
}
