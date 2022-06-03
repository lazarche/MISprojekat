import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

    public static void main(String[] args) throws IOException {
        DBControl kontrola = new DBControl();
        System.out.println("Uspeno povezan na bazu");
        //System.out.println(kontrola.RegistrujKorisnika("Dusan", "Borovica", "4.12.2003", "dusanborovica@gmail.com", "4234"));
        //System.out.println(kontrola.PrijaviKorisnika("lazarborovica@gmail.com", "1234"));
        //Korisnik.UplatiTeretanu("qr1260", 2);
        //System.out.print(Korisnik.Pretplacen("qr1234"));
        try (ServerSocket ss = new ServerSocket(1234)) {
			System.err.println("Server je pokrenut");

			// Glavna nit prihvata konekcije i za svaku pristiglu konekciju kreira novu igru i pokrece je
			// Igra je nit za sebe, tako da glavna nit moze odmah da se vrati na prihvatanje novih konekcija
			// Svaka nit igre ce nezavisno od ostalih obradjivati svoje zahteve i realizovati svoju komunikaciju
			while (!Thread.interrupted()) {
				Socket klijent = ss.accept();
				PacketHandler ph = new PacketHandler(klijent);
				ph.start();
			}

		}
    }

}
