import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PacketHandler extends Thread {
    // Komunikacija
	private final Socket socket;
	private final BufferedReader in;
	private final PrintWriter out;

    public PacketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            out.println("poz");
            boolean run = true;
            while(run) {
                String unos = in.readLine();
                if(unos != null) {
                    String[] args = unos.substring(4).split(" ");
                switch(unos.substring(0,3)) {
                    //Igrac se loguje
                    case "REG":
                        if(args.length == 5)
                            out.println(Korisnik.RegistrujKorisnika(args[0], args[1], args[2], args[3], args[4]));
                            else
                            out.println("Nesto nije dobro");

                    break;
    
                    //Igrac se prijavljuje
                    case "LOG":
                        if(args.length == 2)
                            out.println(Korisnik.PrijaviKorisnika(args[0], args[1]));
                            else
                            out.println("Nesto nije dobro");
                    break;
                    
                    //Igrac uplacuje
                    case "UPL":
                    if(args.length == 2)
                        out.println(Korisnik.UplatiTeretanu(args[0],Integer.parseInt(args[1])));
                        else
                        out.println("Nesto nije dobro");
                    break;
    
                    //Proverava da li igrac sme da prodje na recepciji
                    case "PRV":
                    if(args.length == 1)
                        out.println(Korisnik.Pretplacen(args[0]));
                        else
                        out.println("Nesto nije dobro");
                    break;

                    case "EXT":
                        run = false;
                    break;
                }
            }
        }   
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				System.err.println("Neuspelo zatvaranje konekcije");
			}
		}
    }
}
