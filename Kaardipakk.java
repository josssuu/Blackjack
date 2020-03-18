import java.util.List;

public class Kaardipakk {
    List<String> pakk;

    public Kaardipakk() {
        //this.pakk
    }

    public String toString() {
        String kaardid = "";
        for (String kaart : pakk) {
            kaardid += kaart + " ";
        }
        return kaardid;
    }

    public Kaardipakk uus_pakk() {
        // Genereerib uue kaardipaki
        String[] mastid = {"♠","♥","♣","♦"};
        String[] numbrid = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        return new Kaardipakk();
    }

    private void sega_kaardid(Kaardipakk kaardid){
        // Segab suvaliselt paki ära
    }

    public void eemalda_kaart(String kaart) {
        // Eemaldab pakist etteantud kaardi
    }

    public String anna_kaart() {
        // tagastab pakist suvalise kaardi ja eemaldab selle sealt
        return "suvaline kaart";
    }




}
