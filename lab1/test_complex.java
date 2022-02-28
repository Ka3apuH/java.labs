import java.util.Scanner;

public class test_complex {

    public static void main(String[] args) {
        complex num1=new complex();
        complex num2=new complex(1,15);
        complex res= new complex();

        Scanner in =new Scanner(System.in);

        System.out.println("напиши число:(веществнная часть) ");
        num1.re_comp=in.nextDouble();
        System.out.println("напиши число:(мнимая часть) ");
        num1.im_comp=in.nextDouble();
        in.close();

        System.out.println( "модуль числа: " + num1.ABC_of_complex());

        System.out.println( "аргумент числа: "  + num1.Agument_of_number());

        res=num1.add_number(num2);
        System.out.println( "сумма 1 числа и 2: " + (res.re_comp) + "+" + (res.im_comp) + "i");
        res=num1.sub_number(num2);
        System.out.println( "разность 1 числа и 2: " + "("+(res.re_comp)+")" + "+" + "("+(res.im_comp)+")" + "i");
    }
}