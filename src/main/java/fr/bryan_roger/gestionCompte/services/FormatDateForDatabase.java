package fr.bryan_roger.gestionCompte.services;

import org.springframework.stereotype.Service;

@Service
public class FormatDateForDatabase {

    public String convertDateToCorrectFormatDatabase(String month, String year) {
        String numericMonth = switch (month.toLowerCase()) {
            case "janvier" -> "01";
            case "février" -> "02";
            case "mars" -> "03";
            case "avril" -> "04";
            case "mai" -> "05";
            case "juin" -> "06";
            case "juillet" -> "07";
            case "août" -> "08";
            case "septembre" -> "09";
            case "octobre" -> "10";
            case "novembre" -> "11";
            case "décembre" -> "12";
            default -> throw new IllegalArgumentException("Mois invalide : " + month);
        };

        return numericMonth + "-" + year;
    }
}
