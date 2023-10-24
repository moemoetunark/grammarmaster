package kup.moemoetun.shwegrammaroffline.quiz;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kup.moemoetun.shwegrammaroffline.R;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public List<QuizQuestion> questions;
    private Context context;

    public ReviewAdapter(List<QuizQuestion> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizQuestion question = questions.get(position);
        holder.bind(question);

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView questionTextView;
        private  ImageView statusImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTextView = itemView.findViewById(R.id.questionTextView);
            statusImageView = itemView.findViewById(R.id.statusImageView);
        }

        public void bind(QuizQuestion question) {
            questionTextView.setText(question.getQuestionText());
            String userAnswer = question.getUserAnswer();
            String correctAnswer = question.getCorrectAnswer();
            Drawable drawable;

            if (userAnswer != null && correctAnswer != null && userAnswer.equals(correctAnswer)) {
                drawable = ContextCompat.getDrawable(context, R.drawable.accept);
            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.error);
            }

            statusImageView.setImageDrawable(drawable);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }



    }

    public interface OnItemClickListener {
        void onItemClick(QuizQuestion question);
    }
}
