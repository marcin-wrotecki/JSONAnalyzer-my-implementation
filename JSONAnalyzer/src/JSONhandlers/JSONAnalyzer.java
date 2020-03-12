package JSONhandlers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JSONAnalyzer {

        /*public ArrayList<StringBuilder> countNumberOfPosts(ArrayList<StringBuilder>[] s){
            List formated= Arrays.stream(s).collect(Collectors.toList());
            ArrayList<StringBuilder> a = new ArrayList<>(formated) ;
        }*/
        public static StringBuilder[] formatUserResponse(StringBuilder response){
            String[] temp = response.toString().split("}  },");
            return formatResponse(temp);
        }

        public static StringBuilder[] formatPostsReponse(StringBuilder response){
            String[] temp = response.toString().split("},");
            return formatResponse(temp);
        }

        private static StringBuilder[] formatResponse(String[] temp) {
            StringBuilder[] formatted = new StringBuilder[temp.length];
            for (int i = 0; i < temp.length; i++) {
                formatted[i] = new StringBuilder(temp[i] + "}  },");
            }
            return formatted;
        }
    public static int createIntAttribute(String attribute, StringBuilder source){
        StringBuilder temp=new StringBuilder(source);
        int indexOfAttribute=temp.indexOf(attribute);
        int indexOfComa,result;

        indexOfAttribute=temp.indexOf(":",indexOfAttribute)+1;
        indexOfComa=temp.indexOf(",",indexOfAttribute);

        if(indexOfComa!=-1 && indexOfAttribute!=-1){
            try{
                result=Integer.parseInt(temp.substring(indexOfAttribute,indexOfComa).trim());
            }catch(NumberFormatException e){
                result=-1;
                System.out.println("Can't convert value - createIntAttribute");
            }
        }
        else
            result=-1;
        return result;
    }

    public static String createStringAttribute(String attribute, StringBuilder source){
        StringBuilder temp=new StringBuilder(source);
        int indexOfAttribute=temp.indexOf(attribute);
        int indexOfComa,result;
        indexOfAttribute=temp.indexOf(":",indexOfAttribute)+1;
        indexOfComa=temp.indexOf(",",indexOfAttribute);
        if(indexOfComa!=-1 && indexOfAttribute!=-1)
            return temp.substring(indexOfAttribute,indexOfComa).trim().replaceAll(".*:","").replaceAll("\"","");
        else
            return "";
    }

    public static double createDoubleAttribute(String attribute, StringBuilder source){
        StringBuilder temp=new StringBuilder(source);
        int indexOfAttribute=temp.indexOf(attribute);
        int indexOfComa;
        double result;

        indexOfAttribute=temp.indexOf(":",indexOfAttribute)+1;
        indexOfComa=temp.indexOf(",",indexOfAttribute);

        if(indexOfComa!=-1 && indexOfAttribute!=-1){
            try{
                result=Double.parseDouble(temp.substring(indexOfAttribute,indexOfComa).replace('\"',' ').replace('}',' ').trim());
            }catch(NumberFormatException e){
                result=-1;
                System.out.println("Can't convert value - createDoubleAttribute");
            }
        }
        else
            result=-1;
        return result;
    }


}
