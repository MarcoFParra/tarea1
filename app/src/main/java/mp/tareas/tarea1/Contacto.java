package mp.tareas.tarea1;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    int id;
    String nombres, apellidos,direccion,telefono,celular;

    public Contacto(int id, String nombres,String apellidos,String direccion,String telefono,String celular)
    {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
    }

    protected Contacto(Parcel in) {
        id = in.readInt();
        nombres = in.readString();
        apellidos = in.readString();
        direccion = in.readString();
        telefono = in.readString();
        celular = in.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(direccion);
        dest.writeString(telefono);
        dest.writeString(celular);
    }
}
