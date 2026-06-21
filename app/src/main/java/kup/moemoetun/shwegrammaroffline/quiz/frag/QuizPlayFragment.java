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
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
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
    private List<QuizQuestion> quizQuestions;
    private List<Integer> selectedButtonPositions;

    private int currentQuestionIndex = 0;
    private String selectedCategory;

    private int correctCount = 0;
    private int incorrectCount = 0;

    private Drawable correctIcon;
    private Drawable wrongIcon;

    private int totalQuestions;
    private String statusText;

    // 🔥 AdMob
    private InterstitialAd interstitialAd;

    public QuizPlayFragment() {}

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

        loadInterstitialAd();
    }

    private void loadInterstitialAd() {
        if (!AppConstant.isAdOn) return;
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                requireContext(),
                AppConstant.OFFLINE_ONBACK, // ✅ TEST ID
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        interstitialAd = null;
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_player_tab, container, false);

        optionsContainer = view.findViewById(R.id.optionsContainer);
        nextButton = view.findViewById(R.id.showNext);
        questionTextView = view.findViewById(R.id.questionTextView);
        explanationTextView = view.findViewById(R.id.explanationTextView);
        progressBar = view.findViewById(R.id.progressBar);

        correctIcon = ContextCompat.getDrawable(requireContext(), R.drawable.accept);
        wrongIcon = ContextCompat.getDrawable(requireContext(), R.drawable.error);

        nextButton.setOnClickListener(v -> displayNextQuestion());

        quizQuestions = QuizQuestionLoader.loadQuizQuestions(requireContext());

        filteredQuestions = new ArrayList<>();
        for (QuizQuestion q : quizQuestions) {
            if (q.getCategory().equals(selectedCategory)) {
                filteredQuestions.add(q);
            }
        }

        totalQuestions = filteredQuestions.size();

        displayQuestion();

        return view;
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= filteredQuestions.size()) return;

        QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestionText());

        optionsContainer.removeAllViews();

        for (int i = 0; i < question.getOptions().length; i++) {
            Button btn = new Button(requireContext());
            btn.setText(question.getOptions()[i]);

            btn.setOnClickListener(v -> {
                deselectAllButtons();
                btn.setSelected(true);
                submitAnswer();
            });

            optionsContainer.addView(btn);
        }

        nextButton.setEnabled(false);
        explanationTextView.setVisibility(View.GONE);
    }

    private void submitAnswer() {
        int selectedPosition = getSelectedButtonPosition();

        if (selectedPosition == -1) {
            Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
        Button selectedButton = (Button) optionsContainer.getChildAt(selectedPosition);
        String selectedOption = selectedButton.getText().toString();

        if (selectedOption.equals(question.getCorrectAnswer())) {
            correctCount++;
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
            playAudio("right_answer.mp3");
        } else {
            incorrectCount++;
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, wrongIcon, null);
            playAudio("wrong_answer.mp3");

            // highlight correct
            for (int i = 0; i < optionsContainer.getChildCount(); i++) {
                Button b = (Button) optionsContainer.getChildAt(i);
                if (b.getText().toString().equals(question.getCorrectAnswer())) {
                    b.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
                }
            }
        }

        if (question.getExplanation() != null) {
            explanationTextView.setText(question.getExplanation());
            explanationTextView.setVisibility(View.VISIBLE);
        }

        nextButton.setEnabled(true);
        selectedButtonPositions.add(selectedPosition);
        updateProgressBar();
    }

    private int getSelectedButtonPosition() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            if (optionsContainer.getChildAt(i).isSelected()) return i;
        }
        return -1;
    }

    private void displayNextQuestion() {
        if (getSelectedButtonPosition() == -1) {
            Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < filteredQuestions.size()) {
            displayQuestion();
        } else {
            handleEndOfQuiz();
        }
    }

    private void handleEndOfQuiz() {
        double percentage = (double) correctCount / filteredQuestions.size() * 100;

        if (percentage >= 90) statusText = "A";
        else if (percentage >= 80) statusText = "B";
        else if (percentage >= 70) statusText = "C";
        else if (percentage >= 60) statusText = "D";
        else statusText = "F";

        if (interstitialAd != null) {

            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    openResult();
                    loadInterstitialAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(
                        com.google.android.gms.ads.AdError adError) {
                    openResult();
                }
            });

            interstitialAd.show(requireActivity());

        } else {
            openResult();
        }
    }

    private void openResult() {
        Intent intent = new Intent(requireContext(), QuizReviewActivity.class);
        intent.putParcelableArrayListExtra("filteredQuestions", new ArrayList<>(filteredQuestions));
        intent.putExtra("correctCount", correctCount);
        intent.putExtra("incorrectCount", incorrectCount);
        intent.putExtra("statusText", statusText);
        startActivity(intent);
        requireActivity().finish();
    }

    private void deselectAllButtons() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            optionsContainer.getChildAt(i).setSelected(false);
        }
    }

    private void updateProgressBar() {
        int progress = (selectedButtonPositions.size() * 100) / totalQuestions;
        progressBar.setProgress(progress);
    }

    private void playAudio(String file) {
        try {
            AssetFileDescriptor afd = requireContext().getAssets().openFd(file);
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}