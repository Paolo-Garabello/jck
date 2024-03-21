package utilities;

import Kalkulator.Expression;
import Kalkulator.Rotaluklak;

public class MessageProcesser {
    
    public static String process(String message) {
        String string = message;
        String result = string.substring(0, (string.indexOf('\\') != -1 ? string.indexOf('\\') : string.length()));
        while(string.indexOf('\\') != -1){
            String str = string.substring(string.indexOf('\\')+1);
            string = str.substring(str.indexOf('\\')+1);
            str = str.substring(0, str.indexOf('\\'));

            switch (str.substring(0, str.indexOf(' '))) {
                case "c":   //pag 194
                case "calc":
                    result += " `" + Rotaluklak.calc(new Expression(str.substring(str.indexOf(' '))).toPolski()) + "` ";
                    break;
                case "k":   
                case "rotaluklak":
                    result += " `" + Rotaluklak.calc(str.substring(str.indexOf(' ')-1)) + "` ";
                    break;
                default:
                    break;
            }
            
        result += string.substring(0, (string.indexOf('\\') != -1 ? string.indexOf('\\') : string.length()));
        }

        return result;
    }
}
