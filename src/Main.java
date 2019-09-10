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
        // Открытие файла input.txt
        FileReader input = new FileReader("input.txt");
        Scanner input_scan = new Scanner(input);

        int T = Integer.parseInt(input_scan.nextLine());

        // Создание коллекций для хранения вершин и ребер графа
        Set<String> heads = new HashSet<>();
        Map<String, Integer> edges = new HashMap<>();

        // Создание переменных для хранения строки S и подстрок-вершин графа subS
        String S;
        String subS1, subS2;

        // Перебираем все исходные строки S, разбивая их на триады subS.
        // Триады добавляются в коллекцию уникальных вершин (Set) heads.
        // Две идущие подряд вершины образуют ребро и добавляются в Map edges, где ключом выступает строка с названиеми
        // обеих вершин, а в качестве значения - их количество (вес).
        for(int i=1; i<=T; i++){
            S=input_scan.nextLine();
            subS1=S.substring(0,3);
            heads.add(subS1);
            for(int j=1; j<=S.length()-3; j++){
                subS2=S.substring(j, j+3);
                heads.add(subS2);
                String key = subS1 + " " + subS2;
                if(edges.containsKey(key)){ // Проверка на наличие такого ключа в Map
                    edges.replace(key, edges.get(key)+1);
                }else{
                    edges.put(key, 1);
                }
                subS1=subS2;
            }
        }

        // Закрываем файл input.txt
        input_scan.close();
        input.close();

        // Открываем файл output.txt на запись
        FileWriter output = new FileWriter("output.txt");

        // Выводим количество вершин, количество ребер графа и все ребра с количеством повторов (весом)
        output.write(heads.size() +"\n");
        output.write(edges.size() +"\n");

        for(Map.Entry<String, Integer> edge : edges.entrySet()){
            output.write(edge.getKey()+ " " + edge.getValue()+"\n");
        }

        // Закрываем файл output.txt
        output.close();
    }
}
