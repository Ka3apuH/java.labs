import java.util.Arrays;
import java.util.stream.Stream;

public class RC6 {

    public static Byte[] Encryption(int number_of_rounds, int size_of_word, Byte[] mess, Byte[] key){
        Byte[] mass=Arrays.stream(new Byte[size_of_word*4*(int) Math.ceil(mess.length/(double)(size_of_word*4))]).
                map(s->(byte)0x00).toArray(Byte[]::new);

        System.arraycopy(mess,0,mass,0,mess.length);

        for (int i = 0;i <mass.length/(size_of_word*4) ; i++) {
            Byte[] ch=Arrays.copyOfRange(mass,i*(size_of_word*4),(i+1)*(size_of_word*4));
            System.arraycopy(
                    RC6_encrypt(number_of_rounds,
                            size_of_word,
                            ch,
                            key,
                            s->s.multiply(s.multiply(BigIntegerForRC.TWO).add(BigIntegerForRC.ONE))),
                    0,mass,i*(size_of_word*4),size_of_word*4);
        }
        return mass;
    }

    public static Byte[] Decryption(int number_of_rounds, int size_of_word, Byte[] mess, Byte[] key){
        Byte[] mass=Arrays.stream(new Byte[size_of_word*4*(int) Math.ceil(mess.length/(double)(size_of_word*4))]).
                map(s->(byte)0x00).toArray(Byte[]::new);

        System.arraycopy(mess,0,mass,0,mess.length);

        for (int i = 0;i <mass.length/(size_of_word*4) ; i++) {
            Byte[] ch=Arrays.copyOfRange(mass,i*(size_of_word*4),(i+1)*(size_of_word*4));
            System.arraycopy(
                    RC6_decrypt(number_of_rounds,
                            size_of_word,
                            ch,
                            key,
                            s->s.multiply(s.multiply(BigIntegerForRC.TWO).add(BigIntegerForRC.ONE))),
                    0,mass,i*(size_of_word*4),size_of_word*4);
        }
        return mass;
    }
     static Byte[] RC6_encrypt(int number_of_rounds,
                               int size_of_word,
                               Byte[] block_of_mess,
                               Byte[] key, function_f function){

        BigIntegerForRC regiser_A= new BigIntegerForRC(Arrays.copyOfRange(block_of_mess,
                0,size_of_word),size_of_word);
        BigIntegerForRC regiser_B= new BigIntegerForRC(Arrays.copyOfRange(block_of_mess,
                size_of_word,2*size_of_word),size_of_word);
        BigIntegerForRC regiser_C= new BigIntegerForRC(Arrays.copyOfRange(block_of_mess,
                2*size_of_word,3*size_of_word),size_of_word);
        BigIntegerForRC regiser_D= new BigIntegerForRC(Arrays.copyOfRange(block_of_mess,
                3*size_of_word,block_of_mess.length),size_of_word);

        int lg_w= (int) (Math.log(size_of_word*8));
        BigIntegerForRC[] S=key_prod(size_of_word,number_of_rounds,key);

        regiser_B=regiser_B.add(S[0]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        regiser_D=regiser_D.add(S[1]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));

        BigIntegerForRC t,u;

        for (int i = 1; i <= number_of_rounds; i++) {
            t=(function.fun(regiser_B).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).rotateLeft(lg_w,size_of_word);
            u=(function.fun(regiser_D).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).rotateLeft(lg_w,size_of_word);

            regiser_A=regiser_A.xor(t).mod(BigIntegerForRC.TWO.pow(size_of_word*8)).rotateLeft((int)u.
                    mod(BigIntegerForRC.valueOf(lg_w)).
                    longValue(),size_of_word).add(S[2*i]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
            regiser_C=regiser_C.xor(u).mod(BigIntegerForRC.TWO.pow(size_of_word*8)).rotateLeft((int)t.
                    mod(BigIntegerForRC.valueOf(lg_w)).
                    longValue(),size_of_word).add(S[2*i+1]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));

            BigIntegerForRC help_reg_A=regiser_A;
            regiser_A=regiser_B;
            regiser_B=regiser_C;
            regiser_C=regiser_D;
            regiser_D=help_reg_A;
        }
        regiser_A=regiser_A.add(S[2*number_of_rounds+2]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        regiser_C=regiser_C.add(S[2*number_of_rounds+3]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));

         return Stream.of(regiser_A.toByteArray(),
                 regiser_B.toByteArray(),
                 regiser_C.toByteArray(),
                 regiser_D.toByteArray()).flatMap(Stream::of).toArray(Byte[]::new);
    }

     static Byte[] RC6_decrypt(int number_of_rounds,
                               int size_of_word,
                               Byte[] block_of_mess,
                               Byte[] key,
                               function_f function){

         BigIntegerForRC regiser_A= new BigIntegerForRC(Arrays.
                 copyOfRange(block_of_mess,0,size_of_word),size_of_word);
         BigIntegerForRC regiser_B= new BigIntegerForRC(Arrays.
                 copyOfRange(block_of_mess, size_of_word,2*size_of_word),size_of_word);
         BigIntegerForRC regiser_C= new BigIntegerForRC(Arrays.
                 copyOfRange(block_of_mess,2*size_of_word,3*size_of_word),size_of_word);
         BigIntegerForRC regiser_D= new BigIntegerForRC(Arrays.
                 copyOfRange(block_of_mess,3*size_of_word,block_of_mess.length),size_of_word);

         int lg_w= (int)(Math.log(size_of_word*8));

        BigIntegerForRC[] S=key_prod(size_of_word,number_of_rounds,key);

        regiser_A=regiser_A.subtract(S[2*number_of_rounds+2]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        regiser_C=regiser_C.subtract(S[2*number_of_rounds+3]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));

        BigIntegerForRC t,u;
        for (int i = number_of_rounds; i >0; i--) {
            BigIntegerForRC help_reg_D=regiser_D;
            regiser_D=regiser_C;
            regiser_C=regiser_B;
            regiser_B=regiser_A;
            regiser_A=help_reg_D;

            u=(function.fun(regiser_D).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).rotateLeft(lg_w,size_of_word);

            t=(function.fun(regiser_B).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).rotateLeft(lg_w,size_of_word);

            regiser_C=(regiser_C.subtract(S[2*i+1])).mod(BigIntegerForRC.TWO.pow(size_of_word*8)).
                    rotateRight((int)t.mod(BigIntegerForRC.valueOf(lg_w)).longValue(),size_of_word).xor(u);
            regiser_A=(regiser_A.subtract(S[2*i])).mod(BigIntegerForRC.TWO.pow(size_of_word*8)).
                    rotateRight((int)u.mod(BigIntegerForRC.valueOf(lg_w)).longValue(),size_of_word).xor(t);

        }
        regiser_B=regiser_B.subtract(S[0]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        regiser_D=regiser_D.subtract(S[1]).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        return Stream.of(
                regiser_A.toByteArray(size_of_word),
                regiser_B.toByteArray(size_of_word),
                regiser_C.toByteArray(size_of_word),
                regiser_D.toByteArray(size_of_word)).flatMap(Stream::of).toArray(Byte[]::new);
    }

    static BigIntegerForRC[] key_prod(int size_of_word,int num_rounds,Byte[] key) {
        //генерация констант
        BigIntegerForRC cons_P_w= BigIntegerForRC.P_w(size_of_word);
        BigIntegerForRC cons_Q_w=BigIntegerForRC.Q_w(size_of_word);

        //разбиение ключа на слова

        BigIntegerForRC[] L =new BigIntegerForRC[(int) Math.ceil((key.length/(double)(size_of_word)))];

        for (int i = 0; i < L.length; i++) {
            L[i]=new BigIntegerForRC(Arrays.copyOfRange(key,i*(size_of_word),i==L.length-1?
                    key.length:(i+1)*(size_of_word)),size_of_word);
        }
        //построение таблицы расширенных ключей
        BigIntegerForRC[] S=new BigIntegerForRC[2*num_rounds+4];
        S[0]=cons_P_w;
        for (int i = 1; i <2*num_rounds+4 ; i++) S[i]=S[i-1].add(cons_Q_w).mod(BigIntegerForRC.TWO.pow(size_of_word*8));
        //Перемешивание
        BigIntegerForRC A=BigIntegerForRC.ZERO,B=BigIntegerForRC.ZERO;

        for (int k = 0,i=0 ,j=0; k < 3*Math.max(L.length,2*num_rounds+4); k++) {
            A=S[i]=(S[i].add(A).add(B).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).
                    rotateLeft(3,size_of_word);
            B=L[j]=(L[j].add(A).add(B).mod(BigIntegerForRC.TWO.pow(size_of_word*8))).
                    rotateLeft((int)A.add(B).mod(BigIntegerForRC.valueOf(size_of_word*8L)).longValue(),size_of_word);
            i=(i+1)%(2*num_rounds+4);
            j=(j+1)%(L.length);
        }

        return S;

    }
}
