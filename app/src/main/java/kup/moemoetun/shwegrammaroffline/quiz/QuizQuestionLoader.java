package kup.moemoetun.shwegrammaroffline.quiz;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QuizQuestionLoader {
    public static List<QuizQuestion> loadQuizQuestions(Context context) {
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        try {
            // Read the JSON file from assets folder
            InputStream inputStream = context.getAssets().open("quiz_questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            // Parse the JSON string
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonQuestion = jsonArray.getJSONObject(i);
                String category = jsonQuestion.getString("category");
                String questionText = jsonQuestion.getString("questionText");
                JSONArray jsonOptions = jsonQuestion.getJSONArray("options");

                String[] options = new String[jsonOptions.length()];
                for (int j = 0; j < jsonOptions.length(); j++) {
                    options[j] = jsonOptions.getString(j);
                }

                String correctAnswer = jsonQuestion.getString("correctAnswer");
                String explanation = jsonQuestion.getString("explanation");

                QuizQuestion quizQuestion = new QuizQuestion(category, questionText, options, explanation, correctAnswer);
                quizQuestions.add(quizQuestion);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return quizQuestions;
    }

    public static List<String> getCategories(Context context) {
        List<QuizQuestion> quizQuestions = loadQuizQuestions(context);
        Set<String> categorySet = new LinkedHashSet<>();

        for (QuizQuestion question : quizQuestions) {
            categorySet.add(question.getCategory());
        }

        return new ArrayList<>(categorySet);
    }

}
