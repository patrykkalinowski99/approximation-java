//import java.util.Scanner;
//
//public class apokrymacja_sredniokwadratowa_ciagla {
//    private static double fx(double x) {
//        return Math.sqrt(x);
//    }
//    //x podniesiony do potęgi i
//    private static double Qi(double x, int i) {
//        return Math.pow(x, i);
//    }
//    private static double Px(double x) {
//        x = 1;
//        return x;
//    }
//    private static double simpson(double a, double b, int n, int j){
//        double h = (b - a) / n;
//        double fxi = 0;
//        double fti = 0;
//        double xi;
//        //suma f(xi) i f(ti) od i=0 do n-1
//        for (int i=1; i<n; i++) {
//            xi = a + i*h;
//            fti += fx((xi) - h / 2);
//            fxi += fx(xi);
//        }
//        fti += fx(b - h / 2);
//        //wynik końcowy
//        double wynik = (h/6) * (fx(a) + 2*fxi + 4*fti + fx(b));
//        return wynik;
//    }
//
//    public static void main(String[] args) {
//        double Wx = 0;
//        int px = 1;
////        Scanner scan = new Scanner(System.in);
////        System.out.print("Przedział dolny: ");
////        double a = scan.nextDouble();//ograniczenie dolne
////        System.out.print("Przedział górny: ");
////        double b = scan.nextDouble();//ograniczenie gorne
////        System.out.print("Dokładność całkowania: ");
////        int n = scan.nextInt();//dokładność całkowania
////        System.out.print("Podaj wartość x: ");
////        int x = scan.nextInt();//dokładność całkowania
//        //int n = 50;
//        double a = 1;
//        double b = 3;
//
//
//        //tablica Aij, np. dla n=3 jest 4x4, i,j=0,1,...,n
//        double[][] Aij = new double [n][n];
//        double ai = 0;
//        double[] bi = new double [n];
//        double x = 2;
//        for(int i = 0; i<=n; i++) {
//            ai = bi[i] / Aij[i][i];
//            Wx += ai * Math.pow(x, i);
//        }
//    }
//}
