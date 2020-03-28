import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Mängija m = new Mängija();

    static void alustaUutVooru(Kaardipakk k, Diiler d, Mängija m, int raha) {
        Kaardipakk uuspakk = new Kaardipakk();
        k.setPakk(uuspakk.getPakk());
        k.sega_kaardid();
        d.setKäsi(new ArrayList<>());
        m.setRaha(raha);
        m.setKäsi(new ArrayList<>());

        System.out.println("Algab uus voor!");
    }

    public static void main(String[] args) throws Exception {

        Scanner sisendiLugeja = new Scanner(System.in);

        boolean run = true;
        boolean mäng = false;
        boolean menüü = true;
        boolean reeglidKuvatud = false;
        boolean diilerikord = false;

        Kaardipakk kaardipakk = new Kaardipakk();
        kaardipakk.sega_kaardid();
        Diiler diiler = new Diiler();
        Mängija mängija = new Mängija();
        int panus = 0;

        System.out.println("Tere tulemast mängima BlackJacki!");

        while (run) {

            if (mängija.getRaha() == 0) {
                System.out.println("Kahjuks sai sul raha otsa.");
                reeglidKuvatud = false;
                menüü = true;
                mäng = false;
            }

            if (mängija.getRaha() >= 10000) {
                System.out.println("Olete kasiinole juba piisavalt kahju tekitanud. Mäng on nüüd lõppenud.");
                reeglidKuvatud = false;
                menüü = true;
                mäng = false;
            }

            else

            if (menüü) {
                if (!reeglidKuvatud) {
                    System.out.println("Sisesta \"R\" kui soovid ekraanil näha reegleid.");
                }
                System.out.println("Sisesta \"V\" kui soovid mängust väljuda.");
                System.out.println("Sisesta oma kasutajanimi, kui soovid uut mängu alustada.");

                String sisend = sisendiLugeja.nextLine();
                if (sisend.toUpperCase().equals("R")) {
                    //1) väljastatakse BlackJacki mängu reeglid
                    //2) väljastatakse selle programmi reeglid (näiteks mida tuleb tegevuseks sisestada)
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("//Siin on kirjas kõik reeglid");
                    System.out.println("-----------------------------------------------------------------");
                    reeglidKuvatud = true;
                } else if (sisend.toUpperCase().equals("V")) {
                    System.out.println();
                    System.out.println("Mäng on lõppenud!");
                    break;
                } else {
                    mängija.setNimi(sisend);
                    mäng = true;
                    menüü = false;
                }
            }

            //Terve mängu käik on selle if-i all:
            if (mäng) {
                if (mängija.getKäsi().size() == 0) {
                    //Kui mängija käes on null kaarti, siis mäng on äsja alganud.
                    //Mängija teeb panuse
                    System.out.println("Sisesta oma panus (min: 5) või \"Raha\" kui soovite näha oma rahasummat.");
                    String sisend = sisendiLugeja.nextLine();
                    if (sisend.toUpperCase().equals("RAHA")) {
                        System.out.println("Sul on kokku " + mängija.getRaha() + " eurot.");
                    } else if (sisend.length() < 9 && sisend.length() > 0) {
                        panus = 0;
                        if (sisend.chars().allMatch(Character::isDigit)) {
                            if (Integer.parseInt(sisend) >= 5 && Integer.parseInt(sisend) <= mängija.getRaha()) {
                                panus = Integer.parseInt(sisend);
                            } else {
                                System.out.println("Panus ei sobi, proovi uuesti.");
                            }
                        } else {
                            System.out.println("Panus ei sobi, proovi uuesti.");
                        }
                        if (panus != 0) {
                            //Kui panus on paigas, siis diiler ja
                            //mängija võtavad mõlemad kaks kaarti.
                            //Tulemust näidatakse ekraanil.
                            for (int i = 0; i < 2; i++) {
                                mängija.võtaKaart(kaardipakk.anna_kaart());
                                diiler.võtaKaart(kaardipakk.anna_kaart());
                            }
                            System.out.print("Diileri kaardid: ");
                            diiler.näitaAlguseKaarte();
                            System.out.print("Mängija " + mängija.getNimi() + " kaardid: ");
                            mängija.näitaKaarte();

                        }
                    }
                    else {
                        System.out.println("Panus ei sobi, proovi uuesti.");
                    }
                }
                if (mängija.getKäsi().size() >= 2 && !diilerikord) {
                    System.out.println("Milline on Teie soovitud tegevus? (Hit/Stand/Raha)");
                    String sisend = sisendiLugeja.nextLine();
                    if (sisend.toUpperCase().equals("RAHA")) {
                        System.out.println("Sul on kokku " + mängija.getRaha() + " eurot.");
                    }
                    if (sisend.toUpperCase().equals("STAND")) {
                        if (mängija.omabVõiduKätt()) {
                            System.out.println("Vedas, said kohe BlackJacki! Võitsid " + (int) (panus*1.5) + " eurot.");
                            alustaUutVooru(kaardipakk, diiler, mängija, (int) (mängija.getRaha() + panus * 1.5));
                        }
                        else {
                            diilerikord = true;
                        }
                    }
                    else if (sisend.toUpperCase().equals("HIT")) {
                        mängija.võtaKaart(kaardipakk.anna_kaart());

                        System.out.print("Diileri kaardid: ");
                        diiler.näitaAlguseKaarte();
                        System.out.print("Mängija " + mängija.getNimi() + " kaardid: ");
                        mängija.näitaKaarte();

                        if (mängija.käe_väärtus() > 21) {
                            System.out.println("Läksid lõhki!");
                            System.out.println("Kaotasid " + panus + " eurot.");
                            alustaUutVooru(kaardipakk, diiler, mängija, (int)(mängija.getRaha() - panus));
                        }
                        else if (mängija.omabVõiduKätt()) {
                            System.out.println("Palju õnne," + mängija.getNimi() + "! See on BlackJack, oled vooru võitnud!");
                            alustaUutVooru(kaardipakk, diiler, mängija, (int)(mängija.getRaha() + panus));
                        }
                    }
                }
                if (diilerikord) {
                    int diileriKäsi = diiler.diileriKäik(kaardipakk);
                    if (diileriKäsi < mängija.käe_väärtus() || diileriKäsi > 21) {
                        System.out.println("Võitsid selle vooru!");
                        alustaUutVooru(kaardipakk,diiler,mängija,(int)(mängija.getRaha() + panus));
                    }
                    else {
                        System.out.println("Kahjuks kaotasid selle vooru.");
                        alustaUutVooru(kaardipakk,diiler,mängija,(int)(mängija.getRaha() - panus));
                    }

                }

            }
        }
    }
}
