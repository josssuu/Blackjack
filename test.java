import java.util.ArrayList;
import java.util.List;

public class test {

    public static Mängija m = new Mängija();

    static void alustaUutVooru(Kaardipakk k, Diiler d, Mängija m, int raha) {
        k = new Kaardipakk();
        k.sega_kaardid();
        d = new Diiler();
        m = new Mängija(m.getNimi(), raha);

        System.out.println("Algab uus voor!");
    }

    public static void main(String[] args) {
        //Las see klass olla, siin hea testida

        Kaardipakk k = new Kaardipakk();
        k.sega_kaardid();
        Diiler d = new Diiler();
        Mängija m = new Mängija();

        m.võtaKaart(k.anna_kaart());

        alustaUutVooru(k,d,m,100);
        System.out.println(m);
        System.out.println(k);



    }
}
