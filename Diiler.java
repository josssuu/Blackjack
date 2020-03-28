import java.util.ArrayList;
import java.util.List;

public class Diiler {
    private List<Kaart> käsi;

    public Diiler() {
        this.käsi = new ArrayList<>();
    }

    public void võtaKaart(Kaart kaart) {
        this.käsi.add(kaart);
    }

    public void näitaAlguseKaarte() {
        System.out.println(käsi.get(0) + "|*| ");
    }

    public void näitaKõikiKaarte() {
        StringBuilder tulemus = new StringBuilder();
        for (Kaart kaart : käsi) {
            tulemus.append(kaart + " ");
        }
        System.out.print(tulemus.toString());
    }

    public int käe_väärtus() {
        int summa = 0;
        for (Kaart kaart : this.käsi) {
            summa += kaart.getVäärtus();
        }
        return summa;
    }

    public int diileriKäik (Kaardipakk k) throws Exception {
        System.out.println("Diiler teeb oma käigu...");

        System.out.print("Diileri kaardid: ");
        näitaKõikiKaarte();
        Kaart võetav;

        while (käe_väärtus() < 17) {
            Thread.sleep(1000);
            võetav = k.anna_kaart();
            võtaKaart(k.anna_kaart());
            System.out.print(võetav + " ");
        }

        System.out.println();

        return käe_väärtus();
    }

    public List<Kaart> getKäsi() {
        return käsi;
    }

    public void setKäsi(List<Kaart> käsi) {
        this.käsi = käsi;
    }
}
