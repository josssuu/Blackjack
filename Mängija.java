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

    public Mängija(String nimi, int raha) {
        this.nimi = nimi;
        this.raha = raha;
        this.käsi = new ArrayList<>();
    }

    public boolean omabVõiduKätt() {
        boolean ässKäes = false;
        for (Kaart kaart : this.käsi) {
            if (kaart.getNumber().equals("A")) ässKäes = true;
        }
        if (käe_väärtus() == 21 || (käe_väärtus() == 11 && ässKäes)) {
            return true;
        }
        return false;
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

    public int käe_väärtus() {
        int summa = 0;
        for (Kaart kaart : käsi) {
            summa += kaart.getVäärtus();
        }
        return summa;
    }

    public void uuendaRahakotti(int summa) {
        this.raha += summa;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setRaha(int raha) {
        this.raha = raha;
    }

    public void setKäsi(List<Kaart> käsi) {
        this.käsi = käsi;
    }

    public String getNimi() {
        return this.nimi;
    }

    public List<Kaart> getKäsi() {
        return this.käsi;
    }

    public int getRaha() {
        return raha;
    }

    @Override
    public String toString() {
        return "Mängija{" +
                "nimi='" + nimi + '\'' +
                ", raha=" + raha +
                ", käsi=" + käsi +
                '}';
    }
}
