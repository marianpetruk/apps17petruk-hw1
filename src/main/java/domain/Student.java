package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 * Changed by Marian Petruk on 17/12/2017
 */
public class Student extends BasicStudent {
    private final int MIN_PASS_MARK = 3;
    protected List<Tuple<String, Integer>> examList;


    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.examList = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject JSONObject = super.toJsonObject();
        int numberOfExams = this.examList.size();
        JsonObject[] JsonExamsArray = new JsonObject[numberOfExams];
        int index = 0;
        for (Tuple<String, Integer> exam : examList) {
            JsonPair examName = new JsonPair("course", new JsonString(exam.key));
            JsonPair examMark = new JsonPair("mark", new JsonNumber(exam.value));
            JsonPair examPassed = new JsonPair("passed", new JsonBoolean(!(exam.value < MIN_PASS_MARK)));
            JsonExamsArray[index] = new JsonObject(examName, examMark, examPassed);
            index++;
        }
        JsonArray examsJsonArray = new JsonArray(JsonExamsArray);
        JsonPair exams = new JsonPair("exams", examsJsonArray);
        JSONObject.add(exams);
        return JSONObject;
    }
}
