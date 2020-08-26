
/**
 * Write a description of class Bord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bord {
    public boolean moetNeem;
    public boolean klaar;
    public boolean geactiveerd;
    private int z;
    private int x, y;
    private boolean witAanZet;
    private Stuk[][] dambord;
    private int hor, ver;
    private int q, p;
    private int v, l;

    public Bord() {
        hor = ver = 10;
        dambord = new Stuk[20][20];
        dambord[1][0] = new Wit();
        dambord[3][0] = new Wit();
        dambord[3][0] = new Wit();
        dambord[5][0] = new Wit();
        dambord[7][0] = new Wit();
        dambord[9][0] = new Wit();
        dambord[0][1] = new Wit();
        dambord[2][1] = new Wit();
        dambord[4][1] = new Wit();
        dambord[6][1] = new Wit();
        dambord[8][1] = new Wit();
        dambord[0][3] = new Wit();
        dambord[2][3] = new Wit();
        dambord[4][3] = new Wit();
        dambord[6][3] = new Wit();
        dambord[8][3] = new Wit();
        dambord[1][2] = new Wit();
        dambord[3][2] = new Wit();
        dambord[3][2] = new Wit();
        dambord[5][2] = new Wit();
        dambord[7][2] = new Wit();
        dambord[9][2] = new Wit();
        dambord[0][9] = new Paars();
        dambord[2][9] = new Paars();
        dambord[4][9] = new Paars();
        dambord[6][9] = new Paars();
        dambord[8][9] = new Paars();
        dambord[1][8] = new Paars();
        dambord[3][8] = new Paars();
        dambord[5][8] = new Paars();
        dambord[7][8] = new Paars();
        dambord[9][8] = new Paars();
        dambord[1][6] = new Paars();
        dambord[3][6] = new Paars();
        dambord[5][6] = new Paars();
        dambord[7][6] = new Paars();
        dambord[9][6] = new Paars();
        dambord[0][7] = new Paars();
        dambord[2][7] = new Paars();
        dambord[4][7] = new Paars();
        dambord[6][7] = new Paars();
        dambord[8][7] = new Paars();

    }

    public int getHor() {
        return hor;
    }

    public int getVer() {
        return ver;
    }

    public Stuk getInhoud(int x, int y) {
        return dambord[x][y];
    }

    /**
     * Methode voor het starten van een activerings-
     * proces. De methode kijkt of de betsemming leeg is, en als dit niet zo is,
     * of het geaactiveerd kan worden.
     * een actief blokje is een blokje waar je een handeling mee kan uitvoeren.
     */
    public void activeren(int x, int y) {
        klaar = false;


        Stuk st = getInhoud(x, y);
        if (st == null) {
        } else if (st.activeerbaar()) {
            st.ActiveerHetNu();
            geactiveerd = true;

        }

    }

    /**
     * deze methode leert ons dat we op een bepaald moment moeten pakken.
     * <p>
     * Het zorgt ervoor dat enkel de objecten die een ander stuk kunnen slaan;
     * geselecteerd kunnen worden. po.stro() verwijst naar een methode die
     * het vakje dat moet pakken beschermd van de onbruikbaarheid.
     */

    public void moetPakken() {
        //habba habba
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                for (int j = 0; j < 10; j++) {
                    for (int i = 0; i < 10; i++) {
                        Stuk st = getInhoud(m, n);
                        Stuk po = getInhoud(i, j);


                        if ((!witAanZet && n == j - 1 && po instanceof Wit) || (!witAanZet && n == j + 1 && po instanceof Wit)) {

                            if ((m == i + 1 && st instanceof Paars) || (m == i - 1 && st instanceof Paars)) {

                                if ((i + (m - i) * 2) < 10 && (i + (m - i) * 2) > -1 && (j + (n - j) * 2) < 10 && (j + (n - j) * 2) > -1) {
                                    Stuk vrij = getInhoud(i + (m - i) * 2, j + (n - j) * 2);
                                    if (vrij == null) {
                                        if (po != null) {
                                            po.stro();

                                            moetNeem = true;
                                        }

                                    }
                                }
                            }
                        } else if ((witAanZet && n == j - 1 && po instanceof Paars) || (witAanZet && n == j + 1 && po instanceof Paars)) {

                            if ((m == i + 1 && st instanceof Wit) || (m == i - 1 && st instanceof Wit)) {

                                if ((i + (m - i) * 2) < 10 && (i + (m - i) * 2) > -1 && (j + (n - j) * 2) < 10 && (j + (n - j) * 2) > -1) {
                                    Stuk vrij = getInhoud(i + (m - i) * 2, j + (n - j) * 2);
                                    if (vrij == null) {
                                        if (po != null) {

                                            po.stro();
                                            moetNeem = true;
                                        }
                                    }
                                }


                            }
                        }

                    }
                }
            }
        }
    }


    /**
     * deze methode wordt gestart om te kunnen bewegen naar een bepaalde locatie.
     * ze is opgedeeld in 2 grote gevallen:
     * -indien er een schijfje geslagen moet worden
     * -indien dit niet het geval is
     */


    public void bewegen(int x, int y) {
        geactiveerd = false;
        if (moetNeem) {

            pakken(x, y);

            moetPakken();
            Stuk st = getInhoud(x, y);

            if (moetNeem) {
                if (st == null) {
                } else {
                    st.ActiveerHetNu();
                    klaar = false;
                }
            } else {
                if (witAanZet) {
                    witAanZet = false;
                } else {
                    witAanZet = true;
                }
            }

        } else {
            doeDeZet(x, y);

        }
    }


    /**
     * de naam is vrij evident,
     * belangrijk hier was het disactiveren en de booleans klaar en witaanzet oppereren.
     * klaar is een essentiele boolean voor de controller.
     * wit aan zet betekend dat bij het wisselen van beurt paars aan zet wordt, en dat paarse
     * schijfjes dus kunnen bewegen.
     */
    public void doeDeZet(int x, int y) {
        Stuk st = dambord[x][y];
        Stuk ac = getInhoud(q, p);
        if (y == p + 1 && x == q + 1 || y == p + 1 && x == q - 1) {
            if (st == null && ac instanceof Wit || st == null && ac.isDam) {

                if (ac.isDam) {
                    ac.Disactiveren();
                    dambord[x][y] = ac;
                } else {
                    dambord[x][y] = new Wit();
                }
                dambord[q][p] = null;
                klaar = true;
                geactiveerd = false;
                if (ac.isDam && ac instanceof Paars) {
                    witAanZet = false;
                } else {
                    witAanZet = true;
                }
            }
        } else if (y == p - 1 && x == q + 1 || y == p - 1 && x == q - 1) {
            if (st == null && ac instanceof Paars || st == null && ac.isDam) {

                ac.Disactiveren();
                dambord[x][y] = ac;
                dambord[q][p] = null;
                klaar = true;
                geactiveerd = false;
                if (ac.isDam && ac instanceof Wit) {
                    witAanZet = true;
                } else {
                    witAanZet = false;
                }
            }
        }
    }


    //scanner
    public void scannen() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (st.geKlikt() && st instanceof Wit) {
                    z = 1;
                    q = m;
                    p = n;
                } else if (st.geKlikt() && st instanceof Paars) {
                    z = 2;
                    q = m;
                    p = n;
                } else if (moetNeem) {
                    st.aanZet = false;
                }
            }
        }

    }

    //beurtwissel
    public void beurtwissel() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (witAanZet) {

                    if (st instanceof Wit) {

                        st.aanZet = false;
                    } else {
                        st.aanZet = true;
                    }
                } else {
                    System.out.println("raheu");
                    if (st instanceof Wit) {
                        st.aanZet = true;
                    } else {
                        st.aanZet = false;
                    }

                }
            }
        }
        // pakken
    }

    public void pakken(int x, int y) {

        int u = (x + q) / 2;
        int h = (y + p) / 2;
        Stuk ac = getInhoud(q, p);
        Stuk bo = getInhoud((x + q) / 2, (y + p) / 2);
        Stuk ga = getInhoud(x, y);


        if (ac instanceof Wit && bo instanceof Paars || bo instanceof Wit && ac instanceof Paars) {
            if (ga == null) {
                if (x == 2 + q && y == 2 + p || x == 2 + q && y == p - 2 || x == q - 2 && y == 2 + p || x == q - 2 && y == p - 2) {

                    dambord[(x - q) / 2 + q][(y - p) / 2 + p] = null;
                    dambord[x][y] = ac;
                    dambord[q][p] = null;
                    klaar = true;
                    Stuk st = getInhoud(x, y);
                    st.Disactiveren();
                    moetNeem = false;
                }
            }
        }
    }                                       //checkitout

    public void checkItOut() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (n == 9 && st instanceof Wit) {
                    st.isDam = true;
                } else if (n == 0 && st instanceof Paars) {
                    st.isDam = true;
                }
            }
        }
    }

}

