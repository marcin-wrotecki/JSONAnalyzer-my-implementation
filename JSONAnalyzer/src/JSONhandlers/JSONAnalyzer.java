package JSONhandlers;

public class JSONAnalyzer {

    public static StringBuilder[] formatUserResponse(StringBuilder response) {
        String[] temp = response.toString().split("}  },");
        return formatResponse(temp);
    }

    public static StringBuilder[] formatPostsReponse(StringBuilder response) {
        String[] temp = response.toString().split("},");
        return formatResponse(temp);
    }

    private static StringBuilder[] formatResponse(String[] temp) {
        StringBuilder[] formatted = new StringBuilder[temp.length];
        for (int i = 0; i < temp.length; i++) {
            formatted[i] = new StringBuilder(temp[i] + "}  }");
        }
        return formatted;
    }

    public static int createIntAttribute(String attribute, StringBuilder source) {
        StringBuilder temp = new StringBuilder(source);
        int firstIndex = countFirstIndexOfAttribute(attribute,temp);
        int lastIndex, result;

        lastIndex = countLastIndexOfAttribute(firstIndex,temp);

        if (lastIndex != -1 && firstIndex != -1) {
            try {
                result = Integer.parseInt(temp.substring(firstIndex, lastIndex).trim());
            } catch (NumberFormatException e) {
                result = -1;
                System.out.println("Can't convert value - createIntAttribute");
            }
        } else
            result = -1;
            return result;
    }

    public static String createStringAttribute(String attribute, StringBuilder source) {
        StringBuilder temp = new StringBuilder(source);
        int firstIndex=countFirstIndexOfAttribute(attribute,temp);
        int lastIndex;

        lastIndex = countLastIndexOfAttribute(firstIndex,temp);
        if (lastIndex != -1 && firstIndex != -1)
            return temp.substring(firstIndex, lastIndex).replaceAll(".*:", "").replaceAll("\"", "").replaceAll("}+.*", "").trim();
        else
            return "";
    }

    public static double createDoubleAttribute(String attribute, StringBuilder source) {
        StringBuilder temp = new StringBuilder(source);
        int firstIndex = countFirstIndexOfAttribute(attribute,temp);
        int lastIndex;
        double result;

        lastIndex = countLastIndexOfAttribute(firstIndex,temp);

        if (lastIndex != -1 && firstIndex != -1) {
            try {
                result = Double.parseDouble(temp.substring(firstIndex, lastIndex).replace('\"', ' ').replace('}', ' ').trim());
            } catch (NumberFormatException e) {
                result = -1;
                System.out.println("Can't convert value - createDoubleAttribute");
            }
        } else
            result = -1;
        return result;
    }

    private static int countFirstIndexOfAttribute(String attribute,StringBuilder temp){
        StringBuilder[] arr=null;
        int firstIndex;
        if(attribute.indexOf(":")!=-1){
            String[] spliAttribute=attribute.split(":");
            arr=new StringBuilder[spliAttribute.length];
            for(int i=0;i<spliAttribute.length;i++){
                arr[i]=new StringBuilder(spliAttribute[i]);
            }
        }

        if(arr!=null){
            firstIndex=temp.indexOf(arr[0].toString());
            for(int i=1;i<arr.length;i++){
                firstIndex=temp.indexOf(arr[i].toString(),firstIndex);

            }
        }
        else {
            firstIndex = temp.indexOf(attribute);
        }
        firstIndex = temp.indexOf(":", firstIndex) + 1;
        return firstIndex;
    }
    
    private static int countLastIndexOfAttribute(int firstIndex,StringBuilder temp){
        int lastIndex = temp.indexOf(",", firstIndex);
        if(lastIndex==-1){
            lastIndex = temp.indexOf("}", firstIndex);
        }
        return lastIndex;
    }


}
