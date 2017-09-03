
import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class EncryptUtils
{
    static String PUBLIC_INDEX = "65537";
    private static final String encoding = "utf-8";
    private static final String iv = "01234567";
    private static final String secretKey = "v3VC7LCaVikLen6IL5KgIglw";


    private static byte charToByte(char paramChar)
    {
        return (byte)"0123456789ABCDEF".indexOf(paramChar);
    }

    public static String convertStringToHex(String paramString)
    {
        char[] charArray = paramString.toCharArray();
        StringBuffer localStringBuffer = new StringBuffer();
        int i = 0;
        while (true)
        {
            if (i >= charArray.length)
                return localStringBuffer.toString();
            localStringBuffer.append(Integer.toHexString(charArray[i]));
            i += 1;
        }
    }


    public static String encode(String paramString)
            throws Exception
    {
        Object localObject = new DESedeKeySpec("v3VC7LCaVikLen6IL5KgIglw".getBytes());
        localObject = SecretKeyFactory.getInstance("desede").generateSecret((KeySpec)localObject);
        Cipher localCipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        localCipher.init(1, (Key)localObject, new IvParameterSpec("01234567".getBytes()));
        return Base64New.encode(localCipher.doFinal(paramString.getBytes("utf-8")));
    }

    public static String getParameterStr(Map<String, String> paramMap)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        Iterator localIterator = paramMap.keySet().iterator();
        while (true)
        {
            if (!localIterator.hasNext())
            {
                localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
                System.out.println(localStringBuffer.toString() + "=====");
                return localStringBuffer.toString();
            }
            String str1 = (String)localIterator.next();
            String str2 = (String)paramMap.get(str1);
            localStringBuffer.append(str1).append("=").append(str2).append("^");
        }
    }

    public static byte[] hexStringToBytes(String paramString)
    {
        if ((paramString == null) || (paramString.equals("")))
        {
            paramString = null;
            return null;
        }
        paramString = paramString.toUpperCase();
        int j = paramString.length() / 2;
        char[] arrayOfChar = paramString.toCharArray();
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        while (true)
        {
            paramString = new String(arrayOfByte);
            if (i >= j)
                break;
            int k = i * 2;
            int m = charToByte(arrayOfChar[k]);
            arrayOfByte[i] = ((byte)(charToByte(arrayOfChar[(k + 1)]) | m << 4));
            i += 1;
        }
        return arrayOfByte;
    }
}