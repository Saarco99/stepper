package modules.dataDefinition.impl.relation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationData {
    private List<String> columns;
    private List<SingleRow> rows;

    public RelationData(List<String> columns) {
        this.columns = columns;
        rows = new ArrayList<>();
    }
    public String getValInList(int index){return columns.get(index);}
    public List<String> getRowDataByColumnsOrder(int rowId) {return new ArrayList<>();}
    public int getNumColumns() {return columns.size();}
    public int getNumRows(){return rows.size();}

    public String getValueAt(int index1, int index2) {
        SingleRow t = rows.get(index1);
        Map myMap = t.getData();
        int count = 0;
        String valToReturn = null;
        for (Object key : myMap.keySet()) {
            String value = (String) myMap.get(key);
            if (count == index2) {
                valToReturn = (String) myMap.get(value);
            } else
                count++;
        }
        return valToReturn;
    }

    public List<String> getColumns() {return columns;}

    public void setColumns(List<String> columns) {this.columns = columns;}

    public List<SingleRow> getRows() {return rows;}

    public void setRows(List<SingleRow> rows) {this.rows = rows;}
    /////////////////////////////////////
    public static class SingleRow {
        private Map<String, String> data;

        public Map<String, String> getData() {return data;}

        public void setData(Map<String, String> data) {this.data = data;}

        public SingleRow() {
            data = new HashMap<>();
        }

        public void addData(String columnName, String value) {
            data.put(columnName, value);
        }
        // todo=> add a new line, add a new row
    }
}
