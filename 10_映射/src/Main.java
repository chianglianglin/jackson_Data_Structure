import com.mj.map.Map;
import com.mj.map.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("e",2);
        map.put("r",3);
        map.put("q",88);
        map.put("a",3445);

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key +"_"+value);
                return false;
            }
        });
    }
}
