package kup.moemoetun.shwegrammaroffline.quiz.frag;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.quiz.QuizQuestion;
import kup.moemoetun.shwegrammaroffline.quiz.QuizQuestionLoader;
import kup.moemoetun.shwegrammaroffline.quiz.QuizReviewActivity;

public class QuizPlayFragment extends Fragment {

    private LinearLayout optionsContainer;
    private Button nextButton;
    private TextView questionTextView;
    private TextView explanationTextView;
    private ProgressBar progressBar;

    private List<QuizQuestion> filteredQuestions;
    private int currentQuestionIndex;
    private String selectedCategory;
    private List<QuizQuestion> quizQuestions;
    private List<Integer> selectedButtonPositions;
    private int correctCount = 0;
    private int incorrectCount;

    private Drawable correctIcon;
    private Drawable wrongIcon;

    private int totalQuestions;
    private int progress;
    private InterstitialAd mInterstitialAd;
    private String statusText;

    public QuizPlayFragment() {
    }

    public static QuizPlayFragment newInstance(String category) {
        QuizPlayFragment fragment = new QuizPlayFragment();
        Bundle args = new Bundle();
        args.putString("selectedCategory", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCategory = getArguments().getString("selectedCategory");
        }
        selectedButtonPositions = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_player_tab, container, false);
        loadInterstitialAds();
        optionsContainer = view.findViewById(R.id.optionsContainer);
        nextButton = view.findViewById(R.id.showNext);
        questionTextView = view.findViewById(R.id.questionTextView);
        explanationTextView = view.findViewById(R.id.explanationTextView);
        progressBar = view.findViewById(R.id.progressBar);

        correctIcon = ContextCompat.getDrawable(requireContext(), R.drawable.accept);
        wrongIcon = ContextCompat.getDrawable(requireContext(), R.drawable.error);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNextQuestion();
            }
        });

        // Load the quiz questions from JSON file
        quizQuestions = QuizQuestionLoader.loadQuizQuestions(requireContext());

        // Filter the quiz questions based on the selected category
        filteredQuestions = new ArrayList<>();
        for (QuizQuestion question : quizQuestions) {
            if (question.getCategory().equals(selectedCategory)) {
                filteredQuestions.add(question);
            }
        }

        currentQuestionIndex = 0;
        displayQuestion();

        // Calculate the total number of quiz questions
        totalQuestions = filteredQuestions.size();

        // Set the initial progress to 0%
        progress = 0;
        progressBar.setProgress(progress);

        return view;
    }

    private void submitAnswer() {
        int selectedPosition = getSelectedButtonPosition();
        if (selectedPosition == -1) {
            // No option selected
            Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
        Button selectedButton = (Button) optionsContainer.getChildAt(selectedPosition);
        String selectedOption = selectedButton.getText().toString();

        if (selectedOption.equals(question.getCorrectAnswer())) {
            question.setUserAnswer(selectedOption);
            // Correct answer
            Toast.makeText(requireContext(), "Right", Toast.LENGTH_SHORT).show();
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
            playAudioFromAsset("right_answer.mp3");
            correctCount++;
        } else {
            // Incorrect answer
            Toast.makeText(requireContext(), "Wrong", Toast.LENGTH_SHORT).show();
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, wrongIcon, null);
            String correctAnswer = question.getCorrectAnswer();
            for (int i = 0; i < optionsContainer.getChildCount(); i++) {
                Button button = (Button) optionsContainer.getChildAt(i);
                if (button.getText().toString().equals(correctAnswer)) {
                    button.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
                    break;
                }
            }
            playAudioFromAsset("wrong_answer.mp3");
            incorrectCount++;
        }

        String explanation = question.getExplanation();
        if (explanation != null && !explanation.isEmpty()) {
            explanationTextView.setText(explanation);
            explanationTextView.setVisibility(View.VISIBLE);
        } else {
            explanationTextView.setVisibility(View.GONE);
        }

        nextButton.setEnabled(true);
        selectedButtonPositions.add(selectedPosition);

        // Enable the Next button if there are more questions
        updateProgressBar();
    }

    private int getSelectedButtonPosition() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            Button button = (Button) optionsContainer.getChildAt(i);
            if (button.isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < filteredQuestions.size()) {
            QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
            questionTextView.setText(question.getQuestionText());
            String[] options = question.getOptions();

            // Clear existing buttons
            optionsContainer.removeAllViews();

            for (int i = 0; i < options.length; i++) {
                Button button = new Button(requireContext());
                button.setText(options[i]);
                button.setId(i);

                Drawable customDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.options_button_bg);
                button.setBackground(customDrawable);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deselectAllButtons();
                        button.setSelected(true);
                        submitAnswer();
                    }
                });

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 8, 0, 8);
                button.setLayoutParams(layoutParams);

                optionsContainer.addView(button);
            }

            deselectAllButtons();
            nextButton.setEnabled(false);
            explanationTextView.setVisibility(View.GONE);
        }
    }

    private void deselectAllButtons() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            Button button = (Button) optionsContainer.getChildAt(i);
            button.setSelected(false);
        }
    }

    private void displayNextQuestion() {
        if (getSelectedButtonPosition() == -1) {
            // No option selected
            Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < filteredQuestions.size()) {
            displayQuestion();
            nextButton.setEnabled(true);
            explanationTextView.setVisibility(View.GONE);
        } else {
                handleEndOfQuiz();
        }
    }

    private void handleEndOfQuiz() {
        double percentage = (double) correctCount / filteredQuestions.size() * 100;

        if (percentage >= 90) {
            statusText = "A";
        } else if (percentage >= 80) {
            statusText = "B";
        } else if (percentage >= 70) {
            statusText = "C";
        } else if (percentage >= 60) {
            statusText = "D";
        } else {
            statusText = "F";
        }

        if (!filteredQuestions.isEmpty() && selectedCategoryHasQuestions()) {
            if(mInterstitialAd !=null){
                showInterstitialAds();
            }else {
                // Start the ReviewActivity and pass the filteredQuestions as an extra
                Intent intent = new Intent(requireContext(), QuizReviewActivity.class);
                intent.putParcelableArrayListExtra("filteredQuestions", new ArrayList<>(filteredQuestions));
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("incorrectCount", incorrectCount);
                intent.putExtra("statusText", statusText);
                startActivity(intent);
                requireActivity().finish();
            }
        } else {
            // Handle the case when filteredQuestions is empty
            Toast.makeText(requireContext(), "No questions available for review", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean selectedCategoryHasQuestions() {
        for (QuizQuestion question : quizQuestions) {
            if (question.getCategory().equals(selectedCategory)) {
                return true;
            }
        }
        return false;
    }

    private void playAudioFromAsset(String fileName) {
        try {
            AssetFileDescriptor afd = requireContext().getAssets().openFd(fileName);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProgressBar() {
        progress = (selectedButtonPositions.size() * 100) / totalQuestions;
        progressBar.setProgress(progress);
    }


    private void loadInterstitialAds(){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(), getString(R.string.quiz_complete), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent intent = new Intent(requireContext(), QuizReviewActivity.class);
                                intent.putParcelableArrayListExtra("filteredQuestions", new ArrayList<>(filteredQuestions));
                                intent.putExtra("correctCount", correctCount);
                                intent.putExtra("incorrectCount", incorrectCount);
                                intent.putExtra("statusText", statusText);
                                startActivity(intent);
                                //requireActivity().finish();

                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }
    private void showInterstitialAds(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(requireActivity());
        }
    }
}
