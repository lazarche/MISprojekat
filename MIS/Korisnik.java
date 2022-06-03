import java.sql.*;
import java.util.Calendar;

import javax.sql.rowset.CachedRowSet;
public class Korisnik {
        //Generise unikatni qr kod na osnovu poslednjeg korisnika
        static private String generateQrCode() {
            CachedRowSet _temp = DBControl.returnSet("SELECT * FROM korisnik ORDER BY id DESC LIMIT 1");
            try {
                String qr =  "";
                while(_temp.next())  
                   qr = _temp.getString(2);
                int broj = Integer.parseInt(qr.substring(2));
                broj += 13;
                qr = "qr" + broj;
                System.out.println("Novi qr napravljen " + qr);
                return qr;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "";
        }
    
        //Vraca qr kod i novoregistrovanog korisnika
        static public String RegistrujKorisnika(String ime, String prezime, String godiste, String email, String sifra) {
            if(ime != "" && prezime != "" && godiste != "" && email != "") {
                String qr = generateQrCode();
                if(qr != "") {
                    boolean d = DBControl.executeSql("INSERT INTO korisnik (qrId, ime, prezime, godiste, email, sifra) VALUES ('" + qr + "','" + ime + "','" + prezime +"','" + godiste + "','" + email +"','" + sifra + "') ");
                    System.out.println("Ubacivanje novog korisnika");
                    if(d) 
                        return qr;
                } else {
                    System.out.println("Problem sa pravljenjem qr koda");
                }
            }
            return "";
        }
    
        //Vraca qr kod
        static public String PrijaviKorisnika(String email, String sifra) {
            String korisnik = "";
            CachedRowSet _temp = DBControl.returnSet("SELECT qrId, ime, prezime, clanarinaDo FROM korisnik WHERE email = '"+ email +"' AND sifra = '" + sifra + "'");
            try {
                while(_temp.next()) {
                    korisnik = _temp.getString(1) + " " +  _temp.getString(2) + " " + _temp.getString(3) + " " + _temp.getDate(4).toString();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return korisnik;
        }
    
        static public boolean UplatiTeretanu(String qrId, int brojMeseci) {
            CachedRowSet _temp = DBControl.returnSet("SELECT clanarinaDo FROM korisnik WHERE qrId = '"+ qrId +"'");
            Date d = null;
            try {
                while(_temp.next()){
                    d = _temp.getDate(1);
                }
    
                Calendar utilDate = Calendar.getInstance();
                Date cur = new Date(utilDate.getTimeInMillis());
    
                System.out.println(d);
                System.out.println(cur);
                if(d == null|| d.compareTo(cur) < 0)
                    d = cur;
    
                    System.out.println(d);
                    DBControl.executeSql("UPDATE korisnik SET clanarinaDo = DATE_ADD('" + d.toString() + "', INTERVAL +" + brojMeseci +" MONTH) WHERE qrId = '" + qrId +"'");
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    
        static public boolean Pretplacen(String qrId) {
            CachedRowSet _temp = DBControl.returnSet("SELECT clanarinaDo FROM korisnik WHERE qrId = '"+ qrId +"'");
            Date d = null;
                try {
                    while(_temp.next()){
                        d = _temp.getDate(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    
                Calendar utilDate = Calendar.getInstance();
                Date cur = new Date(utilDate.getTimeInMillis());

                if(d == null || d.compareTo(cur) < 0)
                    return false;
                    else 
                    return true;

        }
}
