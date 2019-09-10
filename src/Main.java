import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private FileWriter output;

    public static void main(String[] args) throws java.io.IOException {
        FileReader input = new FileReader("input.txt");
        Scanner input_scan = new Scanner(input);

        int T = Integer.parseInt(input_scan.nextLine());

        Set<String> heads = new HashSet<>();
        Map<String, Integer> edges = new HashMap<>();

        String S;
        String subS1, subS2;

        for(int i=1; i<=T; i++){
            S=input_scan.nextLine();
            subS1=S.substring(0,3);
            heads.add(subS1);
            for(int j=1; j<=S.length()-3; j++){
                subS2=S.substring(j, j+3);
                heads.add(subS2);
                String key = subS1 + " " + subS2;
                if(edges.containsKey(key)){
                    edges.replace(key, edges.get(key)+1);
                }else{
                    edges.put(key, 1);
                }
                subS1=subS2;
            }
        }
        input_scan.close();
        input.close();

        FileWriter output = new FileWriter("output.txt");

        output.write(String.valueOf(heads.size())+"\n");
        output.write(String.valueOf(edges.size())+"\n");

        for(Map.Entry<String, Integer> edge : edges.entrySet()){
            output.write(edge.getKey()+ " " + edge.getValue()+"\n");
        }

        output.close();
    }
}
