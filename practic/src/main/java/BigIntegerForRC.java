
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

/**
 * класс оболочка для BigInteger, для дополнения его нужными для реализации
 * гостов методами
 */

public class BigIntegerForRC implements Cloneable, Comparable<BigIntegerForRC> {

    private BigInteger ch;
    
    public static final BigIntegerForRC ZERO =valueOf(0);
    public static final BigIntegerForRC ONE =valueOf(1);
    public static final BigIntegerForRC TWO =valueOf(2);

    /**
     * конструкторы класса(они в основнов такие же как и в классле BigIteger
     */


    public BigIntegerForRC(@NotNull String val) {
        ch=new BigInteger(val);
    }
    public BigIntegerForRC(Byte[] val,int byte_len) {
        ch=new BigInteger(ArrayUtils.toPrimitive(val));
        ch=ch.shiftLeft(byte_len*8-this.getBytesLen()*8);
    }

    private BigIntegerForRC(BigInteger chq) {
        ch=chq;
    }

    /**
     * методы класса(они в основнов такие же как и в классе BigIteger(некоторые из них немного переделанны
     */

    public BigIntegerForRC subtract(BigIntegerForRC radix) {
        return new BigIntegerForRC(ch.subtract(radix.ch));
    }

    public BigIntegerForRC multiply(BigIntegerForRC radix) {

        return new BigIntegerForRC(ch.multiply(radix.ch));
    }

    public int compareTo(BigIntegerForRC radix) {
        return ch.compareTo(radix.ch);
    }

    public BigIntegerForRC clone(){
        return new BigIntegerForRC(this.ch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigIntegerForRC that = (BigIntegerForRC) o;
        return Objects.equals(ch, that.ch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }

    @NotNull
    public BigIntegerForRC xor(BigIntegerForRC val) {
        return  new BigIntegerForRC(this.ch.xor(val.ch));
    }

    @NotNull
    public static BigIntegerForRC valueOf(long i){
        return new BigIntegerForRC(Long.toString(i));
    }

    @NotNull
    public BigIntegerForRC add(BigIntegerForRC val) {
        return  new BigIntegerForRC(this.ch.add(val.ch));
    }

    @NotNull
    public BigIntegerForRC pow(int exponent) {
        return  new BigIntegerForRC(this.ch.pow(exponent));
    }

    public BigIntegerForRC rotateLeft(int c, int size){
        if(size!=this.getBytesLen())ch=ch.shiftLeft(size*8-this.getBytesLen()*8);
        return new BigIntegerForRC(ch.shiftLeft(c).mod(BigInteger.TWO.pow(size*8)).xor(ch.shiftRight(size*8-c)));
    }


    public BigIntegerForRC rotateRight(int c, int size){
        if(size!=this.getBytesLen())ch=ch.shiftLeft(size*8-this.getBytesLen()*8);
        return new BigIntegerForRC(ch.shiftLeft(size*8-c).mod(BigInteger.TWO.pow(size*8)).xor(ch.shiftRight(c)));
    }
    @NotNull
    public BigIntegerForRC mod(BigIntegerForRC m) {
        return  new BigIntegerForRC(this.ch.mod(m.ch));
    }


    @NotNull
    public Byte[] toByteArray(int size) {

        Byte[] ch = ArrayUtils.toObject(this.ch.toByteArray());
        Byte[] ch2= Arrays.stream(new Byte[size]).map(s->s=(byte) 0).toArray(Byte[]::new);

        if(Math.ceil((double)(this.ch.bitLength()+1)/8.0)==Math.ceil((double)(this.ch.bitLength())/8.0)) {

            System.arraycopy(ch, 0, ch2, Math.max((size - ch.length), 0), Math.min(ch.length, size));
        }
        else
            System.arraycopy(ch,1,ch2, Math.max((size- ch.length+1), 0),Math.min(ch.length-1,size));
        return ch2;
    }

    public int getBytesLen(){
        return (int) Math.ceil((double) (this.ch.bitLength())/8.);
    }

    public static BigIntegerForRC P_w(int w)
    {
        BigInteger ch=BigDecimal.valueOf(Math.E-2.).multiply(BigDecimal.valueOf(2).pow(w*8)).setScale(0,
                RoundingMode.HALF_EVEN).toBigInteger();
        if(ch.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)==0)return new BigIntegerForRC(ch.add(BigInteger.ONE));
        return new BigIntegerForRC(ch);
    }

    public static BigIntegerForRC Q_w(int w)
    {
        BigInteger ch=(new BigDecimal("0.61803398874989484820458683436")).multiply(BigDecimal.valueOf(2).pow(w*8)).
                setScale(0, RoundingMode.HALF_EVEN).toBigInteger();
        if(ch.mod(BigInteger.TWO).compareTo(BigInteger.ZERO)==0)return new BigIntegerForRC(ch.add(BigInteger.ONE));
        return new BigIntegerForRC(ch);
    }

    public Byte[] toByteArray() {
        return this.toByteArray(this.getBytesLen());
    }

    public long longValue() {
        return this.ch.longValue();
    }

    @Override
    public String toString() {
        return ch.toString();
    }
}
