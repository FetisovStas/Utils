import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;

/**
 * Created by stasPC on 14.04.2017.
 */


class Main{

    public static void main(String[] args) {





        if (args.length >= 2 && args.length < 4) {

            FileSearcher x = new FileSearcher();

            FileSearcher.fileSearcher(System.getProperty("user.dir"), args[0], args[1].equals("true"));
            if (args.length == 2 || (!args[2].equals("#") && !args[2].equals("@"))) {
                x.whatFormat();
            } else {
                x.whatFormat(args[2]);
            }
        } else {
            System.out.println("The first argument should be EXPRESSION, which you are going to find," +
                    " the second arg must be 'false'[if you don't use RegEx] and 'true'[if you are about to apply RegEx]," +
                    " the third arg is the KEY [it can be '@' if you want to get byte size of the files...or it can be '#' if you want to get also LastModified info besides byte size]");
        }


}