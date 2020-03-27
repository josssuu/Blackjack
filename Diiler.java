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
        System.out.println(tulemus.toString());
    }

    private boolean tomba_uus_kaart() {
        // Diiler võtab kaardi kui summa on 16 või väiksem
        // kui summa on 17 või suurem siis ei võta kaarti

        return false;
    }
}
