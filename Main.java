import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Kaardipakk kaardipakk = new Kaardipakk();
        kaardipakk.sega_kaardid();
        Diiler diiler = new Diiler();
        Mängija mängija = new Mängija();




        boolean run = true;
        boolean mäng = false;
        boolean menüü = true;
        boolean reeglidKuvatud = false;

        while (run) {
            Scanner sisendiLugeja = new Scanner(System.in);
            if (menüü) {
                if (!reeglidKuvatud) {
                    System.out.println("See on BlackJack");
                    System.out.println("Sisesta \"R\" kui soovid ekraanil näha reegleid.");
                }
                System.out.println("Sisesta \"V\" kui soovid mängust väljuda.");
                System.out.println("Sisesta oma kasutajanimi, kui soovid mängu alustada.");

                String sisend = sisendiLugeja.nextLine();
                if (sisend.toUpperCase().equals("R")) {
                    //1) väljastatakse BlackJacki mängu reeglid
                    //2) väljastatakse selle programmi reeglid (näiteks mida tuleb tegevuseks sisestada)
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("//Siin on kirjas kõik reeglid");
                    System.out.println("-----------------------------------------------------------------");
                    reeglidKuvatud = true;
                }
                else if (sisend.toUpperCase().equals("V")) {
                    System.out.println();
                    System.out.println("Mäng on lõppenud!");
                    break;
                }
                else {
                    mängija.setNimi(sisend);
                    mäng = true;
                    menüü = false;
                }
            }

            //Terve mängu käik on selle if-i all:
            if (mäng) {
                if (mängija.getKäsi().size() == 0) {
                    System.out.println("Tere tulemast, " + mängija.getNimi() + "! Mäng algab!");
                    //Kui mängija käes on null kaarti, siis mäng on äsja alganud.
                    //Diiler ja mängija võtavad mõlemad kaks kaarti.
                    //Tulemust näidatakse ekraanil.
                    for (int i = 0; i < 2; i++) {
                        mängija.võtaKaart(kaardipakk.anna_kaart());
                        diiler.võtaKaart(kaardipakk.anna_kaart());
                    }
                    System.out.print("Diileri kaardid: ");
                    diiler.näitaAlguseKaarte();
                    System.out.print("Mängija " + mängija.getNimi() + " kaardid: ");
                    mängija.näitaKaarte();

                    System.out.println("Milline on Teie soovitud tegevus? (Hit/Stand)");
                }




            }
        }
    }
}
