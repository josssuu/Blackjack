import java.util.ArrayList;
import java.util.List;

public class Mängija {
    String nimi;
    int raha;
    List<Kaart> käsi;

    public Mängija() {
        this.nimi = "";                 // hiljem võib nime ka muuta
        this.raha = 100;            // algselt 100 peaks okei olema
        this.käsi = new ArrayList<>();
    }

    public void võtaKaart(Kaart kaart) {
        this.käsi.add(kaart);
    }

    public void näitaKaarte() {
        StringBuilder tulemus = new StringBuilder();
        for (Kaart kaart : käsi) {
            tulemus.append(kaart + " ");
        }
        System.out.println(tulemus.toString());
    }

    public void uuendaRahakotti(int summa) {
        this.raha += summa;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return this.nimi;
    }

    public List<Kaart> getKäsi() {
        return this.käsi;
    }

    public int käe_väärtus() {
        int summa = 0;
        for (Kaart kaart : käsi) {
            summa += kaart.getVäärtus();
        }
        return summa;
    }
}
