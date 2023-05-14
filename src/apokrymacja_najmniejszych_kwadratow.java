import java.util.Scanner;

public class apokrymacja_najmniejszych_kwadratow {
    private static double fx(double x) {
        return Math.sqrt(x+2);
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

    public static void main(String[] args) {
        double Wx = 0;
        int px = 1;
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Przedział dolny: ");
//        double a = scan.nextDouble();//ograniczenie dolne
//        System.out.print("Przedział górny: ");
//        double b = scan.nextDouble();//ograniczenie gorne
//        System.out.print("Dokładność całkowania: ");
//        int n = scan.nextInt();//dokładność całkowania
//        System.out.print("Podaj wartość x: ");
//        int x = scan.nextInt();//dokładność całkowania

        //tablica Aij, np. dla n=3 jest 4x4, i,j=0,1,...,n
        int n = 5;
        double[][] Skj = new double[n-1][n-1];
        double[] Xi = {-1,-0.5,0,0.5, 1};
        double[] Yi = new double [n];
        for(int i=0; i<n-1; i++){
            Yi[i] = fx(Xi[i]);
            System.out.println(Yi[i]);
        }
        double A;
        double[] Tk = new double [n-1];
    }
}
