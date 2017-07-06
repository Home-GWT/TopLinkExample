//package com.java8.stream;
//
//
//import java.util.*;
//
///**
// * @see https://m.habrahabr.ru/post/272479/
// */
//public class ForkJoinTest {
//
//    public List<Values> findWithQuery(String query, HashMap map){
//        List<Values> valueses = new ArrayList<>();
//        return valueses;
//    }
//
//    /**
//     *
//     * @param date
//     * @return
//     */
//    public Map<Long, String> getValueInDao(Date date) {
//        Map<Long, String> valueMap = new HashMap<Long, String>();
//
//        HashMap map = new LinkedHashMap();
//        map.put("date", date);
//        List<Values> ValuesList = this.findWithQuery("select c  from Values c where  c.vf < :date and c.vd >= :date", map);
//        if (ValuesList.size() > 0) {
//            ListIterator<Values> iterValues = ValuesList.listIterator();
//            while (iterValues.hasNext()) {
//                Values tmpValues = iterValues.next();
//                valueMap.put(tmpValues.getId, tmpValues.getDescr());
//            }
//        }
//        if (valueMap.size() > 0) {
//            return valueMap;
//        } return Collections.emptyMap();
//    }
//
//    public Map<Long,String> getValue(List<Long> idList,Date date) {
//
//        Map<Long,String> valueMap = new HashMap<Long, String>();
//        HashMap map = new LinkedHashMap();
//
//        List<Value> list = new ArrayList<>();
//        if (idList.size() > 1000) {
//            int j = 0;
//            for (int i = 0; i < idList.size() / 999; i++) {
//                map.put("idList", idList.subList(j, j + 999));
//                map.put("date", date);
//                list.addAll(this.findWithQuery("select c from Value c where  c.id in (" + ":idList" + ")  and c.val < :date and c.val2 >= :date", map));
//                map.clear();
//                j += 999;
//            }
//            if (j <= idList.size()-1) {
//                map.put("idList", idList.subList(j, idList.size()));
//                map.put("date", date);
//                list.addAll(this.findWithQuery("select c from Value c where  c.id in (" + ":idList" + ")  and c.val < :date and c.val2 >= :date", map));
//            }
//        }
//        else {
//            map.put("idList", idList);
//            map.put("date", date);
//            list = this.findWithQuery("select c from Value c where  c.id in (" + ":idList" + ")  and c.val < :date and c.val2 >= :date", map);
//        }
//
//        Iterator<Value> iter = list.iterator();
//        while(iter.hasNext()) {
//            Value tmpValue = iter.next();
//            valueMap.put(tmpValue.getId, tmpValue.getValue());
//        }
//
//        if (valueMap.size() > 0) {
//            return valueMap;
//        } return Collections.emptyMap();
//    }
//
//
//
//    class Values {
//        private int id;
//        private String descr;
//
//        public int getId() {
//            return id;
//        }
//
//        public String getDescr() {
//            return descr;
//        }
//    }
//}
