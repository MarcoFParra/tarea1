package mp.tareas.tarea1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    Contacto contacto = null;
    TinyDB tinyDB;
    FloatingActionButton fab;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            contacto = getIntent().getParcelableExtra("CONTACTO");
        }catch (Exception ex){
            contacto = null;
        }

        setContentView(R.layout.activity_contacto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB = new TinyDB(ContactActivity.this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactosAdapter.Items.add(new Contacto(ContactosAdapter.Items.size(), ((EditText)findViewById(R.id.et_Nombres)).getText().toString(),
                        ((EditText)findViewById(R.id.et_Apellidos)).getText().toString(),
                        ((EditText)findViewById(R.id.et_Direccion)).getText().toString(),
                        ((EditText)findViewById(R.id.et_Telefono)).getText().toString(),
                        ((EditText)findViewById(R.id.et_Celular)).getText().toString()));

                tinyDB.clear();
                tinyDB.putListObject("CONTACTOS",ContactosAdapter.Items.toArrayList());

                Toast.makeText(ContactActivity.this,"Contacto Creado correctamente",Toast.LENGTH_SHORT).show();
                ContactActivity.this.finish();
            }

        });

        if (contacto != null)
        {
            fab.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.tv_Nombres)).setText(contacto.getNombres());
            ((TextView)findViewById(R.id.tv_Apellidos)).setText(contacto.getApellidos());
            ((TextView)findViewById(R.id.tv_Direccion)).setText(contacto.getDireccion());
            ((TextView)findViewById(R.id.tv_Telefono)).setText(contacto.getTelefono());
            ((TextView)findViewById(R.id.tv_Celular)).setText(contacto.getCelular());
        }
        else
        {
            ((ViewSwitcher)findViewById(R.id.my_switcher_Nombres)).showNext();
            ((ViewSwitcher)findViewById(R.id.my_switcher_Apellidos)).showNext();
            ((ViewSwitcher)findViewById(R.id.my_switcher_Direccion)).showNext();
            ((ViewSwitcher)findViewById(R.id.my_switcher_telefono)).showNext();
            ((ViewSwitcher)findViewById(R.id.my_switcher_Celular)).showNext();
        }
    }
}
