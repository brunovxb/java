import java.util.Base64;

public class encBase64 {

    public static void main(String[] args){

        Base64.Encoder b64enc = Base64.getEncoder();

        String t=args[0];
        byte[] src=t.getBytes();

        System.out.println(b64enc.encodeToString(src));
        System.exit(0);

    }

}