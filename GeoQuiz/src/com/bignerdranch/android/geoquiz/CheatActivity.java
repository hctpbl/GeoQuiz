package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

	private static final String TAG = "CheatActivity";
	private static final String KEY_ANSWER_SHOWN = "answershown";
	
	private boolean mAnswerIsTrue;
	private boolean mIsAnswerShown;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private TextView mAPILevelTextView;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		mIsAnswerShown = isAnswerShown;
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		// Answer will not be shown until the user
		// presses the button
		setAnswerShownResult(false);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		mAPILevelTextView = (TextView)findViewById(R.id.apiLevelTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
		
		mAPILevelTextView.setText("API level "+Build.VERSION.SDK_INT);
        
        if (savedInstanceState != null) {
        	setAnswerShownResult(savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false));
        }
	}
	
	@Override
	public void onSaveInstanceState (Bundle savedInstanceState) {
    	super.onSaveInstanceState(savedInstanceState);
    	Log.i(TAG, "onSavedInstanceState");
    	savedInstanceState.putBoolean(KEY_ANSWER_SHOWN, mIsAnswerShown);
    }

}
