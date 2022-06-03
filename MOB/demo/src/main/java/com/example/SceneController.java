package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SceneController {
    @FXML
    private void PrebaciNaReg() throws IOException {
        App.setRoot("registracijaForma");
    }

    @FXML
    private void PrijaviKorisnika() throws IOException {
        TextField tfEmail = (TextField) App.getScene().lookup("#txtPrijavaEmail");
        TextField tfSifra = (TextField) App.getScene().lookup("#txtPrijavaSifra");
        
        String email = tfEmail.getText();
        String sifra = tfSifra.getText();

        String s = (String)ConnectionHandler.PosaljiPaketIPrimiOdgovor("LOG " + email + " " + sifra);
        System.out.println(s.isEmpty());
        if(!s.isEmpty())
            App.setRoot("aplikacijaMain");
    }
}
