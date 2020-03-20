import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static java.util.Collections.shuffle;

public class Kaardipakk {
    List<Kaart> pakk;

    public Kaardipakk() {
        this.pakk = uus_pakk();
    }

    public String toString() {
        String kaardid = "";
        for (Kaart kaart : pakk) {
            kaardid += kaart + " ";
        }
        return kaardid;
    }

    public List<Kaart> uus_pakk() {
        List<Kaart> tulemus = new ArrayList<>();
        String[] mastid = {"♠","♥","♣","♦"};
        String[] numbrid = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        for (int i = 0; i < mastid.length; i++) {
            for (int j = 0; j < numbrid.length; j++) {
                tulemus.add(new Kaart(mastid[i],numbrid[j]));
            }
        }
        return tulemus;
    }

    public void sega_kaardid(){
        shuffle(this.pakk);
    }

    public void eemalda_kaart(String kaart) {
        // Eemaldab pakist etteantud kaardi
    }

    public String anna_kaart() {
        // tagastab pakist suvalise kaardi ja eemaldab selle sealt
        return "suvaline kaart";
    }




}
