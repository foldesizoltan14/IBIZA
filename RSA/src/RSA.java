import java.util.Scanner;

public class RSA {

    protected static boolean primTest(long number) {
        long alapHatar = number - 1;
        long kitevo=number-1;
        long osztasokSzama=0;

        if (number == 2)
            return true;
       

        long alap = 2 + (long) (Math.random() * (alapHatar));

        while(kitevo%2!=1){
            kitevo=kitevo/2;
            osztasokSzama++;
        }


        long reszeredmeny=hatvany(alap,kitevo,number);

            if(reszeredmeny==1 || reszeredmeny==number-1)
            {
                return true;
            }
else {
                for (long i = 0; i < (osztasokSzama - 1); i++) {
                    reszeredmeny = hatvany(reszeredmeny, 2, number);

                    if (reszeredmeny == 1) {
                        return false;
                    }
                    if (reszeredmeny == number - 1) {
                        return true;
                    }

                }
            }

        return false;
    }
    private static long hatvany(long szam, long hatvany, long mod){
        long sz=szam;
            for(int i=2;i<=hatvany;i++)
                sz=(sz*szam)%mod;

        return sz;
    }
    private static long primGenerate(long p){
        p= 2 + (long) (Math.random() * 10000);
        while(!primTest(p)){
            p=2 + (long) (Math.random() * 10000);
        }


        return p;
    }
    private static long kozosOszto(long a, long b){

        if (a == 0)
                return b;

            return kozosOszto(b%a, a);

    }
    private static long kiterjesztettEu(long b,long a )
    {
        long hatvany=-1;
        long ua=0, ub=1, x=0,seged=0,segedFi=a;
        while(hatvany(b,hatvany,a)!=1){

             x=ua- ub*(a/b);

            ua=ub;
            ub=x;
            seged=b;
            b=a%b;
            a=seged;
        }
            if(x>0) {
                return x;
            }
            else{
                while(x<=0) x = x + segedFi;
            }
            return x;
    }

    private static long titkosit(long m,long e,long n){
        long tm;
        tm=hatvany(m,e,n);
        return tm;
    }
    private static long decrypt(long tm,long d,long n){
        long dm;
        dm=hatvany(tm,d,n);
        return dm;
    }


    public static void main(String[] args) {
        long p=0,q=0,n,fiN,e=0, k=0,m,tm,dm;
        System.out.println("Írja be a titkosítani kívánt üzenetet:");
        Scanner in = new Scanner(System.in);
        m=in.nextLong();
        System.out.println("Titkosítani kívánt üzenete: m="+ m);

    while(p*q<m){
             p = primGenerate(p);
             q = primGenerate(q);
                }
        n=p*q;
        fiN=(p-1)*(q-1);

        while(k!=1){
            e=2 + (long) (Math.random() * fiN-1);
            k=kozosOszto(fiN,e);

        }
        long d=kiterjesztettEu(e,fiN);
        tm=titkosit(m,e,n);
        dm=decrypt(tm,d,n);


        System.out.println("p="+ p);
        System.out.println("q="+ q);
        System.out.println("n="+ n);
        System.out.println("fiN="+ fiN);
        System.out.println("e="+ e);
        System.out.println("d="+ d);
        System.out.println("Titkosított üzenet:tm="+ tm);
        System.out.println("Feltört üzenet:dm="+ dm);

    }
}
