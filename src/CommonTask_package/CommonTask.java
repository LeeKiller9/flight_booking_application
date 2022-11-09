package CommonTask_package;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonTask<T> {

    public List<T> searchObjectByStringAndReturnList(String str, String nameOfVariable, List<T> list) {
        List<T> searchList = new ArrayList<>();
        for (T obj : list) {
            if (getStringFromObj(obj, nameOfVariable).equals(str)) {
                searchList.add(obj);
            }
        }
        return searchList;
    }

    public T searchObjectByStringAndReturnObject(String str, String nameOfVariable, List<T> list) {
        for (T obj : list) {
            if (getStringFromObj(obj, nameOfVariable).equals(str)) {
                return obj;
            }
        }
        return null;
    }

    public T searchObjectByNumberAndReturnObject(int num, String nameOfVariable, List<T> list) {
        for (T obj : list) {
            if (getNumberFromObj(obj, nameOfVariable) == num) {
                return obj;
            }
        }
        return null;
    }

    public abstract String getStringFromObj(T obj, String nameOfVariable);

    public abstract int getNumberFromObj(T obj, String nameOfVariable);

    public void readFromFile(String FILE_PATH, List<T> lists) {
        try {
            lists.clear();
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                lists.add(handleLine(line));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract T handleLine(String line);

    public void saveToFile(String FILE_PATH, List<T> lists) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (T obj : lists) {
                bufferedWriter.write(obj.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String displayString(List<T> list) {
        String str = "";
        for (T obj : list) {
            str += obj.toString() + "\n";
        }
        return str;
    }
}