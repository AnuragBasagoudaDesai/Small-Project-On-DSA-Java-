import java.util.LinkedList;

public class Int_to_english
{
    String[] smalls = 
    {
        "Zero" , "One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
        "Eleven","twelve","thirteen","Fourteen","fifteen","Sixteen","seventeen","eighteen",
        "Ninteen"
    };

    String[] tens = {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","eighty","Ninty"};

    String hundred = "hundred";

    String[] bigs = {""," Thousand "," Million "," billion "};
    String negative = "Negative";


    public   String convert(int number)
    {
        if(number == 0)
            return smalls[0];

        else if(number < 0)  
            return negative + " " + convert( number * -1);
        
        LinkedList<String>  parts = new LinkedList<String>();
        int chunkCount = 0;

        while(number > 0)
        {
            if(number % 1000 != 0)
            {
                String chunk = convertchunk(number%1000) + ""+bigs[chunkCount];
                parts.addFirst(chunk);
            }
 
            number /= 1000;
            chunkCount++;
        }

        return listToString(parts);


    }

    String  convertchunk(int number)
    {
        LinkedList<String> parts = new LinkedList<>();

        if(number >= 100)
        {
            parts.addLast(smalls[number/100]);
            parts.addLast(hundred);
            number %= 100;
        }

        if(number >= 10 && number <= 19)
            parts.addLast(smalls[number]);
        else if(number >= 20)
        {
            parts.addLast(tens[number/10]);
            number %= 10;
        }
        
        if(number >= 1 && number <= 9)
            parts.addLast(smalls[number]);
        

            return  listToString(parts);
    }

    String  listToString(LinkedList<String> list)
    {
        StringBuilder sb = new StringBuilder();

        while(list.size() > 1)
        {
            sb.append(list.pop());
            sb.append(" ");
        }
        sb.append(list.pop());

        return new  String(sb);
    }


    public static void main(String[] args) {

        Int_to_english number = new Int_to_english();
        System.out.println(number.convert(-1225));
        
    }






}