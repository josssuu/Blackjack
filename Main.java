import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean run = true;
    static boolean mäng = false;
    static boolean menüü = true;
    static boolean reeglidKuvatud = false;
    static boolean diilerikord = false;
    static Kaardipakk kaardipakk = new Kaardipakk();
    static Diiler diiler = new Diiler();
    static Mängija mängija = new Mängija();
    static Scanner sisendiLugeja = new Scanner(System.in);

    static void alustaUutVooru(int raha) {
        Kaardipakk uuspakk = new Kaardipakk();
        kaardipakk.setPakk(uuspakk.getPakk());
        kaardipakk.sega_kaardid();
        diiler.setKäsi(new ArrayList<>());
        mängija.setRaha(raha);
        mängija.setKäsi(new ArrayList<>());
        diilerikord = false;

        if (mängija.getRaha() > 0 && mängija.getRaha() < 10000) {
            System.out.println("\nAlgab uus voor!");
        }
    }

    static boolean kasOnPiisavaltRaha() {
        if (mängija.getRaha() == 0) {
            System.out.println("Kahjuks sai sul raha otsa. Näeme järgmisel korral.");
            return false;
        }
        else if (mängija.getRaha() >= 10000) {
            System.out.println("Olete kasiinole juba piisavalt kahju tekitanud. Teid visati välja.");
            return false;
        }
        return true;
    }

    static void väljastaReeglid() {
        try {
            Scanner sc = new Scanner(new File("reeglid.txt"), StandardCharsets.UTF_8);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void näitaMenüüd() {
        if (!reeglidKuvatud) {
            System.out.println("Sisesta \"Reeglid\" kui soovid ekraanil näha reegleid.");
        }
        System.out.println("Sisesta \"Välju\" kui soovid mängust väljuda.");
        System.out.println("Sisesta oma kasutajanimi, kui soovid uut mängu alustada.");

        String sisend = sisendiLugeja.nextLine();
        switch (sisend.toUpperCase()) {
            case "REEGLID":
                //1) väljastatakse BlackJacki mängu reeglid
                //2) väljastatakse selle programmi reeglid (näiteks mida tuleb tegevuseks sisestada)
                väljastaReeglid();
                reeglidKuvatud = true;
                break;
            case "VÄLJU":
                System.out.println("\nMäng on lõppenud!");
                System.exit(0);
                break;
            default:
                mängija.setNimi(sisend);
                mäng = true;
                menüü = false;
                break;
        }
    }

    static int küsiPanust() {
        int panus;
        while (true) {
            System.out.println("Sisesta oma panus (min: 5) või \"Raha\" kui soovite näha oma rahasummat.");
            String sisend = sisendiLugeja.nextLine();
            if (sisend.toUpperCase().equals("VÄLJU")) {
                System.out.println("Mäng on lõppenud.");
                System.exit(0);
            } else if (sisend.toUpperCase().equals("RAHA")) {
                System.out.println("Sul on kokku " + mängija.getRaha() + " eurot.");
            } else if (sisend.chars().allMatch(Character::isDigit) && sisend.length() <= 5 && sisend.length() > 0) {
                if (Integer.parseInt(sisend) >= 5 && Integer.parseInt(sisend) <= mängija.getRaha()) {
                    System.out.println("Hästi, alustame!\n");
                    panus = Integer.parseInt(sisend);
                    break;
                }
            } else {
                System.out.println("Panus ei sobi, proovi uuesti.");
            }
        }
        return panus;
    }

    static void mängiEsimesedKaardid() {
        System.out.println("Jagame esimesed kaardid!");
        for (int i = 0; i < 2; i++) {
            mängija.võtaKaart(kaardipakk.anna_kaart());
            diiler.võtaKaart(kaardipakk.anna_kaart());
        }
        System.out.print("Diileri kaardid: ");
        diiler.näitaAlguseKaarte();
        mängija.näitaKaarte();
    }

    static void kontrolliVõitu(int panus) {
        int diileriKäsi = diiler.käeVäärtus();
        int mängijaKäsi = mängija.käeVäärtus();

        System.out.println("diiler: " + diileriKäsi);
        System.out.println("mängija: " + mängijaKäsi);
        if (diileriKäsi < mängijaKäsi || diileriKäsi > 21) {
            System.out.println("Võitsid selle vooru!\n");
            alustaUutVooru(mängija.getRaha() + panus);
        } else {
            System.out.println("Kahjuks kaotasid selle vooru.\n");
            alustaUutVooru(mängija.getRaha() - panus);
        }
    }

    static void väljastaMängijaRaha() {
        System.out.println("Sul on kokku " + mängija.getRaha() + " eurot.");
    }

    static void väljastaMänguSeis() {
        System.out.print("Diileri kaardid: ");
        diiler.näitaAlguseKaarte();
        mängija.näitaKaarte();
    }

    static boolean käsiOnLõhki() {
        return mängija.käeVäärtus() > 21;
    }

    static void kontrolliBlackJacki(int panus) {
        if (mängija.käeVäärtus() == 21) {
            System.out.println("Vedas, said kohe BlackJacki! Võitsid " + panus * 1.5 + " eurot!");
            alustaUutVooru(mängija.getRaha() + (int) (panus * 1.5));
        }
    }

    static void teeKäsiOnLõhki(int panus) {
        System.out.println("Läksid lõhki!");
        System.out.println("Kaotasid " + panus + " eurot.");
        alustaUutVooru(mängija.getRaha() - panus);
    }

    public static void main(String[] args) throws Exception {
        kaardipakk.sega_kaardid();
        int panus = 0;
        System.out.println("Tere tulemast mängima BlackJacki!");

        while (run) {
            if (!kasOnPiisavaltRaha()) {
                break;
            }

            if (menüü) {
                näitaMenüüd();
            }
            else if (mäng) {
                if (mängija.getKäsi().size() == 0) {
                    //Kui mängija käes on null kaarti, siis mäng on äsja alganud.
                    panus = küsiPanust();
                    mängiEsimesedKaardid();
                    kontrolliBlackJacki(panus);
                }
                else if (diilerikord) {
                    diiler.käik(kaardipakk);
                    mängija.näitaKaarte();
                    kontrolliVõitu(panus);
                }
                else {
                    System.out.println("Milline on Teie soovitud tegevus? (Hit/Stand/Raha)");
                    String sisend = sisendiLugeja.nextLine().toUpperCase();

                    switch (sisend) {
                        case "VÄLJU":
                            System.out.println("Mäng on lõppenud!");
                            System.exit(0);
                        case "RAHA":
                            väljastaMängijaRaha();
                            break;
                        case "HIT":
                            mängija.võtaKaart(kaardipakk.anna_kaart());
                            väljastaMänguSeis();
                            break;
                        case "STAND":
                            diilerikord = true;
                            break;
                        default:
                            System.out.println("vigane sisend");
                            break;
                    }

                    if (käsiOnLõhki()) {
                        teeKäsiOnLõhki(panus);
                    }
                }
            }
        }
    }
}
