import java.util.Scanner;

public class apokrymacja_wielomiany_ortogonalne {
    private static double fx(double x) {
        return Math.sqrt(x);
    }
    private static double simpson(double a, double b, int n, double qi, double px){
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
        return wynik;
    }
    private static double Pnx(double n, double x){
        double wynik = 0;
        if (n == 0) {
            System.out.println("n = 0");
            wynik = 1;
        }
        else if (n == 1.0){
            System.out.println("n = 1");
            wynik = x;
        }
        else if (n == 2){
            System.out.println("n = 2 -> "+ ((3*x*x)-1)/2);
            wynik = (1/2)*((3*x*x)-1);
        }
        else if (n == 3){
            System.out.println("n =  3 -> " + (5*x*x*x-3*x)/2);
            wynik = (1/2)*(5*x*x*x-3*x);
        }
        else if (n > 3){
            System.out.println("n > 3 -> " +((1/(n+1))*(2*n+1)*x*Pnx(n-1, x) - (n/(n+1))*Pnx(n-2, x)));
            wynik = (1/(n+1))*(2*n+1)*x*Pnx(n-1, x) - (n/(n+1))*Pnx(n-2, x);
        }
        else if (n < 0) wynik = 0;
        return wynik;
    }
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Przedział dolny: ");
//        double a = scan.nextDouble();//ograniczenie dolne
//        System.out.print("Przedział górny: ");
//        double b = scan.nextDouble();//ograniczenie gorne
//        System.out.print("Dokładność całkowania: ");
//        int n = scan.nextInt();//dokładność całkowania
//        System.out.print("Podaj wartość x: ");
//        int x = scan.nextInt();//dokładność całkowania
        int n = 1;
        double[] tabgx = new double [n];
        double gx = 0; //suma wartosci z tabgx
        double[] Ci = new double [n];
        double[] Deltai = new double [n];
        System.out.println(Pnx(0,2.34));
        System.out.println(Pnx(1,2.34));
        System.out.println(Pnx(2,-2.34));
        System.out.println(Pnx(3,7.34));

    }
}
