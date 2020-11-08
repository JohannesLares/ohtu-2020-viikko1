package ohtu.ohtuvarasto;
import static java.lang.Math.*;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {  // tilavuus on annettava
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else {
            this.tilavuus = 0.0;  // => käyttökelvoton varasto
        }
        saldo = 0;     // oletus: varasto on tyhjä
    }

    public Varasto(double tilavuus, double alkuSaldo) {
        this.tilavuus = Math.max(tilavuus, 0.0);
        this.saldo = Math.max(alkuSaldo, 0.0);
        if (this.saldo > tilavuus) {
            this.saldo = tilavuus;
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan my�s laskea
        return tilavuus - saldo;        //  ei tarvita erillist� kentt�� vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return;       // t�llainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // t�yteen ja ylim��r� hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0;   // t�llainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mit� voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;               // ja tyhj�ksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos t�nne p��st��n, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // v�hennet��n annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}