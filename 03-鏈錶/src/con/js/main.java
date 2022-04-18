package con.js;

import con.js.circleList.CircleSingleLinkList;
import con.js.singeList.SingleLinkList2;

public class main {
    public static void main(String[] args) {
        List<Integer> list = new CircleSingleLinkList<>();
//        list.remove(1);
        list.add(23);
        list.add(0, 10);
        list.add(20);
        list.add(list.size(),40);
        //10,23,20,40
        list.remove(1);
        //10,20,40
        System.out.println(list);
    }
}
