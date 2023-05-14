import java.util.Scanner;

public class Metoda_simpsona {

    //funkcja dla ktorej obliczamy calke
    private static double fx(double x) {

        return Math.sin(1.1*x-0.3)/(2.5+x*x);
    }
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        System.out.print("Przedział dolny: ");
        double a = scan.nextDouble();//ograniczenie dolne
        System.out.print("Przedział górny: ");
        double b = scan.nextDouble();//ograniczenie gorne
        System.out.print("Dokładność całkowania: ");
        double n = scan.nextDouble();//dokładność całkowania
        //dlugosc przedzialu a i b na n rownych czesci
        double h = (b - a) / n;

        double fxi = 0;
        double fti = 0;
        double xi;
        //suma f(xi) i f(ti) od i=0 do n-1
        for (int i=1; i<n; i++) {
            xi = a + i*h;
            fti += fx((xi) - h / 2);
            fxi += fx(xi);
        }
        fti += fx(b - h / 2);
        //wynik końcowy
        double wynik = (h/6) * (fx(a) + 2*fxi + 4*fti + fx(b));

        System.out.println(" całka na przedziale ["+ a + ", " + b + "] f(x) dx = " + wynik);

    }

}