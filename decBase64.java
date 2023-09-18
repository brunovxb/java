import java.lang.*;
import java.util.Base64;

public class decBase64 {

    public static void main(String[] args){

        Base64.Decoder b64dec = Base64.getDecoder();

        String t=args[0];
        // byte[] src=t.getBytes();

        // System.out.println(String(b64dec.decode(t),StandardCharsets.UTF_8));

        String str=new String(b64dec.decode(t));

        System.out.println(str);

        System.exit(0);

    }

}