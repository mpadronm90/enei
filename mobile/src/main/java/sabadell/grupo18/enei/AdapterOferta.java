package sabadell.grupo18.enei;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pablo on 21/02/15.
 */
public class AdapterOferta extends RecyclerView.Adapter<AdapterOferta.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView titulo;
        public TextView contenido;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen= (ImageView) itemView.findViewById(R.id.imgImagen);
            titulo= (TextView) itemView.findViewById(R.id.txtTitulo);
            contenido= (TextView) itemView.findViewById(R.id.txtContenido);
        }
    }

    private class Oferta{
        long id;
        String titulo;
        String subtitulo;
        int imagen;


        public Oferta(String titulo, String subtitulo, int imagen) {
            this.titulo = titulo;
            this.subtitulo = subtitulo;
            this.imagen = imagen;
        }
    }



    private Context contexto;
    private ArrayList<Oferta> ofertas= new ArrayList<>();



    public AdapterOferta(Context contexto){
        this.contexto= contexto;



        ofertas.add(new Oferta("oferta1","contenido1", R.drawable.corazon));
        ofertas.add(new Oferta("oferta2","contenido2", R.drawable.ipod));
        ofertas.add(new Oferta("oferta3","contenido3", R.drawable.monitor));
        ofertas.add(new Oferta("oferta4","contenido4", R.drawable.ordenador));
    }



    @Override
    public AdapterOferta.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewOferta=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_oferta,viewGroup,false);
        ViewHolder vh=new ViewHolder(viewOferta);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterOferta.ViewHolder holder, int i) {

        Drawable drawable=contexto.getResources().getDrawable(ofertas.get(i).imagen);
        holder.imagen.setImageDrawable(drawable);
        holder.titulo.setText(ofertas.get(i).titulo);
        holder.contenido.setText(ofertas.get(i).subtitulo);

    }



    @Override
    public int getItemCount() {
        return ofertas.size();
    }


}
