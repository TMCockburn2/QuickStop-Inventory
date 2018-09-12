import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tyler.shoppinglistapp.MapsActivity;
import com.example.tyler.shoppinglistapp.R;

import static com.google.android.gms.R.id.button;


/**
 * Created by Tyler on 11/10/2017.
 */

public class title_page_activity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);
        Button button = (Button)findViewById(R.id.start);
        EditText radius = (EditText)findViewById(R.id.editText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(title_page_activity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


    }


}
