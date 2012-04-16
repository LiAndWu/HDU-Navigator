package net.mindlee.android.mabbage;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;

public class AboutActivity extends Activity {
	private Button BackButton;
	private TextView AboutTextView;
	int start, end;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		BackButton = (Button) findViewById(R.id.back_button);
		BackButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AboutActivity.this,
						MainActivity.class);
				startActivity(intent);
				AboutActivity.this.finish();
			}
		});

		AboutTextView = (TextView) findViewById(R.id.about_textView);

		SpannableStringBuilder word = new SpannableStringBuilder();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (dm.heightPixels > 600) {
			final String zero = "       \n";
			final String one = "\t\tHDUNav\n";
			final String two = "\t\tVersion 1.0\n";
			final String three = "\t\t吴旭东:\n\t\t\t\t  http://tikiet.blog.163.com\n"
					+ "\t\t\t\t  wuxd@me.com\n"
					+ "\t\t李伟:\n\t\t\t\t  http://mindlee.net\n"
					+ "\t\t\t\t  chinawelon@gmail.com\n";

			final String four = "\n\t\t\tCrabium & Mabbage Workshop\n\t\t\t\t\t\tLiWei and WuXudong\n\t\t\t\t\t\t\t Copyleft 2011.";
			word.append(zero);// 调整用空行
			start = 0;
			end = zero.length();
			word.setSpan(new AbsoluteSizeSpan(8), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(one);
			start = end;
			end += one.length();
			word.setSpan(new AbsoluteSizeSpan(40), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(4), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(two);
			start = end;
			end += two.length();
			word.setSpan(new AbsoluteSizeSpan(36), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(4), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(three);
			start = end;
			end += three.length();
			word.setSpan(new AbsoluteSizeSpan(32), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(6), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(four);
			start = end;
			end += four.length();
			word.setSpan(new AbsoluteSizeSpan(30), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			AboutTextView.setText(word);

		} else {
			final String zero = "       \n";
			final String one = "\tHDUNav\n";
			final String two = "\tVersion 1.0\n";
			final String three = "\t吴旭东:\n\t\t\t  http://tikiet.blog.163.com\n"
					+ "\t\t\t  wuxd@me.com\n"
					+ "\t李伟:\n\t\t\t  http://mindlee.net\n"
					+ "\t\t\t  chinawelon@gmail.com\n";

			final String four = "\n\t\tCrabium & Mabbage Workshop\n\t\t\t\tLiWei and WuXudong\n\t\t\t\t\t Copyleft 2011.";
			word.append(zero);// 调整用空行
			start = 0;
			end = zero.length();
			word.setSpan(new AbsoluteSizeSpan(4), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(one);
			start = end;
			end += one.length();
			word.setSpan(new AbsoluteSizeSpan(20), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(2), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(two);
			start = end;
			end += two.length();
			word.setSpan(new AbsoluteSizeSpan(18), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(2), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(three);
			start = end;
			end += three.length();
			word.setSpan(new AbsoluteSizeSpan(16), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(zero);
			start = end;
			end += zero.length();
			word.setSpan(new AbsoluteSizeSpan(3), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			word.append(four);
			start = end;
			end += four.length();
			word.setSpan(new AbsoluteSizeSpan(15), start, end,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			AboutTextView.setText(word);

		}

	}

}
