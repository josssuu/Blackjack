import java.util.ArrayList;
import java.util.List;

public class Diiler {
    List<String> käsi;

    public Diiler() {
        this.käsi = new ArrayList<>();
    }

    public String käik() {
        // tagastab mis käigu diiler teeb
        return "";
    }

    private boolean tomba_uus_kaart() {
        // Diiler võtab kaardi kui summa on 16 või väiksem
        // kui summa on 17 või suurem siis ei võta kaarti

        return false;
    }
}
