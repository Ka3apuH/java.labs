/**
 * complex
 */

import java.lang.Math;

public class complex {

    public double im_comp;
    public double re_comp;

    public complex(){
    }

    public complex(double x, double y){
    this.im_comp=y;
    this.re_comp=x;
    }
    
    public double ABC_of_complex() {
        return Math.hypot(this.im_comp,this.re_comp);
    }

    public double Agument_of_number() {
        
        double ague_arc = Math.atan(this.im_comp/this.re_comp);

        if (this.im_comp>0 && this.re_comp<0){
            ague_arc+=Math.PI;
        }
        if (this.im_comp<0 && this.re_comp>0){
            ague_arc-=Math.PI;
        }
        return Math.toDegrees(ague_arc);
    }

    public complex add_number(complex num) {
        
        complex res=new complex(0,0);
        res.im_comp=this.im_comp+num.im_comp;
        res.re_comp=this.re_comp+num.re_comp;
        return res;
    }

    public complex sub_number(complex num) {
        
        complex res=new complex(0,0);
        res.im_comp=this.im_comp-num.im_comp;
        res.re_comp=this.re_comp-num.re_comp;
        return res;
    }
}