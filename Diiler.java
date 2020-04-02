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

    public void näitaKõikiKaarte() throws Exception{
        for (Kaart kaart : käsi) {
            Thread.sleep(1000);
            System.out.print(kaart + " ");
        }
    }

    public int käeVäärtus() {
        int summa = 0;
        int ässadeArv = 0;
        for (Kaart kaart : käsi) {
            if (kaart.getNumber().equals("A")) {
                ässadeArv++;
            }
            else {
                summa += kaart.getVäärtus();
            }
        }

        for (int i = 0; i < ässadeArv; i++) {
            if (summa >= 11) {
                summa++;
            }
            else {
                summa += 11;
            }
        }

        return summa;
    }

    public void käik(Kaardipakk k) throws Exception {
        System.out.println("Diiler teeb oma käigu...");

        System.out.print("Diileri kaardid: ");
        näitaKõikiKaarte();
        Kaart võetav;

        while (käeVäärtus() < 17) {
            Thread.sleep(1000);
            võetav = k.anna_kaart();
            võtaKaart(võetav);
            System.out.print(võetav + " ");
        }
        Thread.sleep(1000);
        System.out.println();

    }

    public void setKäsi(List<Kaart> käsi) {
        this.käsi = käsi;
    }
}
