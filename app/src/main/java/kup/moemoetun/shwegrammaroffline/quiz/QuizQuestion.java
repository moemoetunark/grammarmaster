package kup.moemoetun.shwegrammaroffline.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizQuestion implements Parcelable {
    private String category;
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private String explanation;
    private boolean selected;
    private int selectedOptionIndex;
    private String userAnswer;

    public QuizQuestion(String category, String questionText, String[] options, String explanation, String correctAnswer) {
        this.category = category;
        this.questionText = questionText;
        this.options = options;
        this.explanation = explanation;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters for all fields

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    // Parcelable implementation

    protected QuizQuestion(Parcel in) {
        category = in.readString();
        questionText = in.readString();
        options = in.createStringArray();
        correctAnswer = in.readString();
        explanation = in.readString();
        selected = in.readByte() != 0;
        selectedOptionIndex = in.readInt();
        userAnswer = in.readString();
    }

    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(questionText);
        dest.writeStringArray(options);
        dest.writeString(correctAnswer);
        dest.writeString(explanation);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeInt(selectedOptionIndex);
        dest.writeString(userAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
