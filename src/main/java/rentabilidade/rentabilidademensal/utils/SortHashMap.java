package rentabilidade.rentabilidademensal.utils;

import java.util.*;

interface ISortHashMap{
    public HashMap<String, Float> sortByValueDesc(HashMap<String, Float> hm);
}

public final class SortHashMap implements ISortHashMap {
    public HashMap<String, Float> sortByValueDesc(HashMap<String, Float> hashMap)
    {
        List<Map.Entry<String, Float> > list =
                new LinkedList<>(hashMap.entrySet());

        list.sort(new Comparator<>() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
