import java.util.ArrayList;
import java.util.List;

public class Mängija {
    String nimi;
    int rahakott;
    List<String> käsi;

    public Mängija() {
        this.nimi = "";                 // hiljem võib nime ka muuta
        this.rahakott = 100;            // algselt 100 peaks okei olema
        this.käsi = new ArrayList<>();
    }

    public int käe_väärtus() {
        // tagastab käes olevate kaartide summa
        return 0;
    }
}
