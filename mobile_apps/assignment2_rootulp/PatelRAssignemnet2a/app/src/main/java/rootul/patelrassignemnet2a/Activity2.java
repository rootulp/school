package rootul.patelrassignemnet2a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends Activity implements View.OnClickListener {
    EditText data;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        data = (EditText) findViewById(R.id.etdata);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        Intent myLocalIntent = getIntent();
        Bundle myBundle =  myLocalIntent.getExtras();
        Double v1 = myBundle.getDouble("val1");
        Double v2 = myBundle.getDouble("val2");
        Double tan =  v2 / v1;
        Double h = (Math.pow(v1, 2)) + (Math.pow(v2, 2));
        Double hyp = Math.sqrt(h);
        Double sin = v2 / hyp;
        Double cos = v1 / hyp;

        data.setText("sin= " + sin + "\n" +
                     "cos= " + cos + "\n" +
                     "tan= " + tan + "\n");

        // add to the bundle the computed result
        myBundle.putDouble("vresult", tan);

        // attach updated bumble to invoking intent
        myLocalIntent.putExtras(myBundle);

        // return sending an OK signal to calling activity
        setResult(Activity.RESULT_OK, myLocalIntent);

    }  //onCreate

    @Override
    public void onClick(View v) {
        // close current screen - terminate Activity2
        finish();
    }

}
