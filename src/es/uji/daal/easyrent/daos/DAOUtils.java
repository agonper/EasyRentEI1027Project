package es.uji.daal.easyrent.daos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alberto on 25/04/2016.
 */
public class DAOUtils {

    private final String[] columns;
    private String[] selectFields;

    public DAOUtils(String[] columns) {
        this.columns = columns;
    }

    public String getFieldNames() {
        return arrayFieldsToString(getFields());
    }

    private String arrayFieldsToString(String[] fields) {
        return Arrays.stream(fields).collect(Collectors.joining(", "));
    }

    public String[] getFields() {
        if (selectFields != null) {
            return selectFields;
        }

        generateFieldsList();
        return selectFields;
    }

    private void generateFieldsList() {
        List<String> selectFields = new LinkedList<>();
        for (String column : columns) {
            selectFields.add(column);
        }
        this.selectFields = (String[]) selectFields.toArray();
    }

    public String getInsertWildcards() {
        String[] wildcards = new String[getFields().length];
        Arrays.fill(wildcards, "?");
        return arrayFieldsToString(wildcards);
    }

    public String getUpdatePlaceholders() {
        List<String> placeholders = new LinkedList<>();
        for (String field : this.getFields()) {
            String placeholder = String.format("%s = ?", field);
            placeholders.add(placeholder);
        }
        return arrayFieldsToString((String[]) placeholders.toArray());
    }
}
