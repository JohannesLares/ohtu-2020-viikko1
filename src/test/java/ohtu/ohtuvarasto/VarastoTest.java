package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pit�isi olla sama kun lis�tty m��r�
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pit�isi viel� olla tilavuus-lis�tt�v� m��r� eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pit�isi olla tilaa 10 - 8 + 2 eli 
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriToimiiAlleNolla() {
    	Varasto uusi = new Varasto(-2);
    	assertEquals(0, uusi.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriToimiiAlleNollaAlkuSaldo() {
    	Varasto uusi = new Varasto(0, 0);
    	assertEquals(0, uusi.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriToimiiAlkuSaldoAlleNolla() {
    	Varasto uusi = new Varasto(10, -2);
    	assertEquals(10, uusi.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriToimiiAlkuSaldoYliTilavuus() {
    	Varasto uusi = new Varasto(10, 20);
    	assertEquals(0, uusi.paljonkoMahtuu(), vertailuTarkkuus);
    }
    

    @Test
    public void konstruktoriToimiiYliNollaAlkuSaldo() {
    	Varasto uusiVarasto = new Varasto(10, 5);
    	assertEquals(5, uusiVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonAlleNolla() {
    	varasto.lisaaVarastoon(-1);
    	assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoOverFlow() {
    	varasto.lisaaVarastoon(100);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenAlleNolla() {
    	varasto.lisaaVarastoon(5);
    	assertEquals(0.0, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }
    
    @Test
    public void kaikkiMitaSaadaan() {
    	varasto.lisaaVarastoon(5);
    	assertEquals(5, varasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void tekstia() {
    	varasto.lisaaVarastoon(2);
    	assertEquals("saldo = 2.0, viela tilaa 8.0", varasto.toString());
    }
}
