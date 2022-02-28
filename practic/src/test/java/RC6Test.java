import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class RC6Test {

    @ParameterizedTest(name = "[RC6(блока данных)]")
    @CsvFileSource(resources = "test_RC6_bloks_Res.csv",numLinesToSkip = 1)
    void RC6_blocks(String str, int numberOfRounds,int sizeOfWord,String key) {

        Byte[] crypt=RC6.RC6_encrypt(numberOfRounds,sizeOfWord, ArrayUtils.toObject(str.getBytes()),
                ArrayUtils.toObject(key.getBytes()),s->
                        s.multiply(s.multiply(BigIntegerForRC.TWO).add(BigIntegerForRC.ONE)));

        Byte[] uncrypt=RC6.RC6_decrypt(numberOfRounds,sizeOfWord, crypt,
                ArrayUtils.toObject(key.getBytes()),s->
                        s.multiply(s.multiply(BigIntegerForRC.TWO).add(BigIntegerForRC.ONE)));

        Assertions.assertArrayEquals(ArrayUtils.toObject(str.getBytes()),uncrypt);
    }

    @ParameterizedTest(name = "[RC6()]")
    @CsvFileSource(resources = "test_RC6_Res.csv",numLinesToSkip = 1)
    void RC6(String str, int numberOfRounds,int sizeOfWord,String key) {

        Byte[] crypt=RC6.Encryption(numberOfRounds,sizeOfWord, ArrayUtils.toObject(str.getBytes()),
                ArrayUtils.toObject(key.getBytes()));

        Byte[] uncrypt=RC6.Decryption(numberOfRounds,sizeOfWord, crypt,
                ArrayUtils.toObject(key.getBytes()));

        StringBuilder str_=new StringBuilder();
        str_.append(str);
        str_.append("\0".repeat(4*sizeOfWord - str.length()%(sizeOfWord * 4)));
        Assertions.assertArrayEquals(ArrayUtils.toObject(str_.toString().getBytes()),uncrypt);
    }

}