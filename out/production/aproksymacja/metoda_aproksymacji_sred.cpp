#include <iostream>
#include <math.h>
#include <stdlib.h>

using namespace std;

long double funkcja(long double x)
{
    return x*x*x + 7*x*x + 2;
}

class Gauss
{
private:
    bool mod;
    int n;
    long double** matrix;
    long double* wynik;
    int* zmienne;
    void wybor_pelny(int);
    void postepowanie_proste(int);
    void postepowanie_odwrotne();
    void bubble_sort();
public:
    Gauss(int, long double**);
    ~Gauss();
    long double* rozwiaz_uklad_rownan();
};

Gauss::Gauss(int n, long double** matrix)
{
    this->mod = false;
    this->n = n;
    this->matrix = matrix;
    this->zmienne = new int[n];
    this->wynik = new long double[n];
    for(int i = 0; i < n; i++)
        zmienne[i] = i;
}

Gauss::~Gauss()
{
    for(int i = 0; i < n; i++)
    {
        delete[] matrix[i];
    }
    delete[] zmienne;
    delete[] wynik;
    delete[] matrix;
}

void Gauss::wybor_pelny(int k)
{
    int max_index_w, max_index_k;
    max_index_w = k;
    max_index_k = k;
    bool zera = true;
    for(int i=k; i<n; i++)
    {
        for(int j=k; j<n; j++)
        {
            if(matrix[i][j] != 0.0)
            {
                zera = false;
            }
            if( fabs(matrix[i][j]) > fabs(matrix[max_index_w][max_index_k]))
            {
                max_index_w = i;
                max_index_k = j;
            }
        }
    }

    if(zera)
    {
        std::cout << "Dany uklad jest sprzeczny lub nie posiada jednoznacznego rozwiazania!" << std::endl;
        exit(0);
    }
    if(k!=max_index_k)
    {
        for(int i=0; i<n; i++)
        {
            std::swap(matrix[i][k], matrix[i][max_index_k]);
        }
        std::swap(zmienne[k], zmienne[max_index_k]);
        mod = true;
    }

    if(k!=max_index_w)
    {
        std::swap(matrix[k], matrix[max_index_w]);
    }
}

void Gauss::postepowanie_proste(int k)
{
    long double temp = matrix[k][k];


    for(int i=k+1; i<n; i++)
    {
        temp = matrix[i][k]/matrix[k][k];
        for(int j=k; j<n+1; j++)
        {
            matrix[i][j] -= matrix[k][j]*temp;
        }
    }

}


void Gauss::postepowanie_odwrotne()
{
    for (int i = n - 1; i >= 0; i--)
    {
        float s = 0;
        for (int j = i + 1; j < n; ++j) s = s + matrix[i][j]*wynik[j];
        wynik[i] = (matrix[i][n] - s)/matrix[i][i];
    }
}


void Gauss::bubble_sort()
{
    for(int i=n-1; i>0; i--)
    {
        for(int j=0; j<i; j++)
            if(zmienne[j]>zmienne[j+1])
            {
                std::swap(zmienne[j], zmienne[j+1]);
                std::swap(wynik[j], wynik[j+1]);
            }
    }
}

long double* Gauss::rozwiaz_uklad_rownan()
{
    for(int k = 0; k < n; k++)
    {
        wybor_pelny(k);
        postepowanie_proste(k);
    }

    postepowanie_odwrotne();

    if(mod)
        bubble_sort();
    return wynik;
}


class Calkowanie
{
private:
    long double a;
    long double b;
    int liczba_przedzialow;
    int n;
    long double px;
    long double h;
    long double simpson_macierz(int, int);
    long double simpson_wektor(int);
    long double (*f)(long double);
public:
    Calkowanie(long double, long double, int, long double, long double (*f)(long double));
    long double** utworz_macierz_wspolczynnikow();
};

Calkowanie::Calkowanie(long double a, long double b, int n, long double px, long double (*f) (long double) )
{
    this->a = a;
    this->b = b;
    this->n = n;
    this->px = px;
    this->liczba_przedzialow = 100;
    this->h = (b - a) / liczba_przedzialow;
    this->f = f;
}

long double Calkowanie::simpson_macierz(int i, int j)
{
    long double wynikSimpson = pow(a, i)*pow(a, j)*px + pow(b, i)*pow(b, j)*px;
    for (int k = 1; k < liczba_przedzialow; k++)
    {
        if(i % 2 == 0){
            wynikSimpson += 2 * pow(a + k * h, i)*pow(a + k * h, j)*px;
        }
        else{
            wynikSimpson += 4 * pow(a + k * h, i)*pow(a + k * h, j)*px;
        }

    }
    wynikSimpson *= (h/3);
    return wynikSimpson;
}

long double Calkowanie::simpson_wektor(int i)
{
    long double wynikSimpson = pow(a, i)*f(a)*px + pow(b, i)*f(b)*px;
    for (int k = 1; k < liczba_przedzialow; k++)
    {
        if(i % 2 == 0){
            wynikSimpson += 2 * pow(a + k * h, i)*f(a + k * h)*px;
        }
        else{
            wynikSimpson += 4 * pow(a + k * h, i)*f(a + k * h)*px;
        }
    }
    wynikSimpson *= (h/3);
    return wynikSimpson;
}

long double** Calkowanie::utworz_macierz_wspolczynnikow()
{
    long double **macierz = new long double*[n];
    for(int i = 0; i < n; i++)
    {
        macierz[i] = new long double[n+1];
        for(int j = 0; j < n; j++)
            macierz[i][j] = simpson_macierz(i, j);
        macierz[i][n] = simpson_wektor(i);
    }
    return macierz;
}

int main()
{
    const long double a = -1;
    const long double b = 1;
    const int n = 7;
    const long double px = 1;
    const long double x = -0.25;

    Calkowanie c(a, b, n, px, &funkcja);
    Gauss g(n, c.utworz_macierz_wspolczynnikow());

    long double* potegi = g.rozwiaz_uklad_rownan();

    long double wynik = 0;
    for(int i = 0; i < n; i++){
        wynik += potegi[i] * pow(x, i);
    }

    cout << "Wynik aproksymacji: " << wynik << endl;
}
