package com.mis.a201835022esraargaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView msQuestion,msScore,msQuestionNo,msTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3;
    private Button btnNext;
    private Button btnPlay;
    private MediaPlayer mediaPlayer;


   int totalQuestions;
   int qCounter= 0;
   int score;


   ColorStateList dfRbColor;
   boolean answered;

   CountDownTimer countDownTimer;

   private QuestionModel currentQuestion;


    private List<QuestionModel> questionslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

       btnPlay = findViewById(R.id.btnPlay);

       btnPlay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (mediaPlayer!= null){
                   mediaPlayer.stop();
               }
               int[] musics = {R.raw.kuzukuzu,R.raw.ayabenzer,R.raw.dinlebenibi,R.raw.babycan,R.raw.anotherlove,
                       R.raw.brunomars,R.raw.ebrugundes,R.raw.serdarortac,R.raw.sezenaksu,R.raw.yalin};
               int i = currentQuestion.getPosition();
               mediaPlayer = MediaPlayer.create(QuizActivity.this, musics[i]);
               if (mediaPlayer != null){
                   mediaPlayer.start();
               } else {
                   Toast.makeText(QuizActivity.this, "Audio file could not be played", Toast.LENGTH_SHORT).show();
               }
           }
       });



        questionslist = new ArrayList<>();


        msQuestion = findViewById(R.id.textQuestion);
        msScore = findViewById(R.id.textScore);
        msQuestionNo = findViewById(R.id.textQuestionNo);
        msTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();



        addQuestions();
        totalQuestions = questionslist.size();
        showNextQuestion();



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();

                    }else{
                        Toast.makeText(QuizActivity.this,"Please select an option",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                    if (mediaPlayer!=null){
                        mediaPlayer.stop();
                    }
                }
            }

            private void checkAnswer() {
                answered = true;
                RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
                int answerNo = radioGroup.indexOfChild(rbSelected)+ 1;
                if (answerNo == currentQuestion.getCorrectAnsNo()){
                    score++;
                    msScore.setText("Score:"+score);

                }
                rb1.setTextColor(Color.RED);
                rb2.setTextColor(Color.RED);
                rb3.setTextColor(Color.RED);
                switch (currentQuestion.getCorrectAnsNo()){
                    case 1:
                        rb1.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        rb2.setTextColor(Color.GREEN);
                        break;
                    case 3:
                        rb3.setTextColor(Color.GREEN);
                        break;


                }
                if (qCounter < totalQuestions){
                    btnNext.setText("Next");
                }else {
                    btnNext.setText("Finish");
                }

            }
        });


    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);

        if (qCounter<totalQuestions){
            timer();
            currentQuestion = questionslist.get(qCounter);
            msQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Submit");
            msQuestionNo.setText("Question:"+qCounter+"/"+totalQuestions);
            answered = false;


        }else {
            finish();
        }
    }

    private void timer() {
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
              public void onTick(long millisUntilFinished) {
                msTimer.setText( "00:" + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                showNextQuestion();


            }
        }.start();
    }
    private void addQuestions()  {
        questionslist.add(new QuestionModel("Who sings this song?","Tarkan","Oguzhan Koc","Murat Boz",1,0));
        questionslist.add(new QuestionModel("Who sings this song?","Hadise","Mustafa Sandal","Ceza",2,1));
        questionslist.add(new QuestionModel("Who sings this song?","Tarkan","Kutsi","Yüzyüzeyken Konuşuruz",3,2));
        questionslist.add(new QuestionModel("Who sings this song?","Tracy Chapman","Lady Gaga","Travis Scott",1,3));
        questionslist.add(new QuestionModel("Who sings this song?","Rihanna","Tom Odel","Selena Gomez",2,4));
        questionslist.add(new QuestionModel("Who sings this song?","Lady Gaga","Justin","Bruno Mars",3,5));
        questionslist.add(new QuestionModel("Who sings this song?","Ebru Gündeş","Demet Akalın","Hadise",1,6));
        questionslist.add(new QuestionModel("Who sings this song?","Ahmet Kaya","Serdar Ortaç","Tarkan",2,7));
        questionslist.add(new QuestionModel("Who sings this song?","Sıla","Hadise","Sezen Aksu",3,8));
        questionslist.add(new QuestionModel("Who sings this song?","Yalın","Murat Boz","Özgün",1,9));
    }


}