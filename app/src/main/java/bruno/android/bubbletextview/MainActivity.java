package bruno.android.bubbletextview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView to_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to_input = (TextView) findViewById(R.id.text);

        String[] contactNames = {"Bruno de Lima", "Bruno Lira", "Daniel Amaral", "Édipo Costa", "Klevison", "Leandro Neves", "Sergio Holanda"};

        final SpannableStringBuilder sb = new SpannableStringBuilder();
        for (String contactName : contactNames) {
            TextView tv = createContactTextView(contactName);
            BitmapDrawable bd = (BitmapDrawable) convertViewToDrawable(tv);
            bd.setBounds(0, 0, bd.getIntrinsicWidth(), bd.getIntrinsicHeight());

            sb.append(contactName + " ");
            sb.setSpan(new ImageSpan(bd), sb.length() - (contactName.length() + 1), sb.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        to_input.setText(sb);
    }

    public TextView createContactTextView(String text) {
        //creating textview dynamically
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(40);
        tv.setTextColor(getResources().getColor(android.R.color.white));
        tv.setBackgroundResource(R.drawable.oval);
//        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear_search_api_holo_light, 0);
        return tv;
    }

    public static Object convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();
        return new BitmapDrawable(viewBmp);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
