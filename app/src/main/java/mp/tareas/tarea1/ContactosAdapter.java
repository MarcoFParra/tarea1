package mp.tareas.tarea1;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactosAdapter extends ArrayAdapter<Contacto> {
    public static MiLista Items = new MiLista();
    private Activity activity;
    private View item;
    private LayoutInflater inflater;

    public ContactosAdapter(@NonNull Activity activity, int resource) {
        super(activity, resource);
        this.activity = activity;

        clear();
        for (int i =0; i< Items.size(); i++)
        {
            add( Items.get(i));
        }

        //for (Object contacto: new TinyDB(activity).getListObject("CONTACTOS",Contacto.class)) {
        //    add((Contacto) contacto);
        //}

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        inflater = activity.getLayoutInflater();

        try {
             item = inflater.inflate(R.layout.item_contacto, parent, false);


            ((TextView) item.findViewById(R.id.tv_nombre)).setText(Items.get(position).getNombres());
            ((TextView) item.findViewById(R.id.tv_telefono)).setText(Items.get(position).getCelular());

            item.setClickable(true);
            item.setFocusable(true);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, ContactActivity.class).putExtra("CONTACTO", Items.get(position)));
                }
            });

            item.findViewById(R.id.image_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Items.remove(position);
                    new TinyDB(activity).clear();
                    new TinyDB(activity).putListObject("CONTACTOS",Items.toArrayList());
                    Toast.makeText(activity,"Contacto Eliminado correctamente",Toast.LENGTH_SHORT).show();
                    activity.recreate();
                }
            });
        }catch (Exception ex){
            Toast.makeText(activity,ex.toString(),Toast.LENGTH_SHORT).show();

        }

            return item;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
