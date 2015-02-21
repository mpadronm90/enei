package sabadell.grupo18.enei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by pablo on 21/02/15.
 */
public class AdapterOferta extends BaseAdapter {


    private class Oferta{
        long id;
        String titulo;
        String subtitulo;


        public Oferta(String titulo, String subtitulo){
            this.titulo=titulo;
            this.subtitulo=subtitulo;
        }
    }

    private Context contexto;
    private ArrayList<Oferta> ofertas= new ArrayList<>();



    public AdapterOferta(Context contexto){
        this.contexto= contexto;
        ofertas.add(new Oferta("oferta1","contenido1"));
        ofertas.add(new Oferta("oferta2","contenido2"));
        ofertas.add(new Oferta("oferta3","contenido3"));
        ofertas.add(new Oferta("oferta4","contenido4"));
    }

    @Override
    public int getCount() {
        return ofertas.size();
    }

    @Override
    public Object getItem(int position) {
        return ofertas.get(position);

    }

    @Override
    public long getItemId(int position) {
        return ofertas.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View ofertaLayout= inflater.inflate(R.layout.layout_oferta,parent,false);
        Oferta oferta= (Oferta) getItem(position);
        TextView txtTexto= (TextView) ofertaLayout.findViewById(R.id.txtTitulo);
        TextView txtContenido= (TextView) ofertaLayout.findViewById(R.id.txtContenido);

        txtTexto.setText(oferta.titulo);
        txtContenido.setText(oferta.subtitulo);
        return ofertaLayout;
    }
}
