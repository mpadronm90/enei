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

import sabadell.grupo18.enei.Utils.ImageUtils;

/**
 * Created by pablo on 21/02/15.
 */
public class AdapterOferta extends RecyclerView.Adapter<AdapterOferta.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public ImageView imagen_estado;
        public TextView titulo;
        public TextView contenido;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen= (ImageView) itemView.findViewById(R.id.imgImagen);
            imagen_estado = (ImageView) itemView.findViewById(R.id.green);
            titulo= (TextView) itemView.findViewById(R.id.txtTitulo);
            contenido= (TextView) itemView.findViewById(R.id.txtContenido);
        }
    }

    private class Oferta{
        long id;
        String titulo;
        String subtitulo;
        int imagen;
        int estado;


        public Oferta(String titulo, String subtitulo, int imagen,int estado) {
            this.titulo = titulo;
            this.subtitulo = subtitulo;
            this.imagen = imagen;
            this.estado = estado;
        }
    }



    private Context contexto;
    private ArrayList<Oferta> ofertas= new ArrayList<>();



    public AdapterOferta(Context contexto){
        this.contexto= contexto;



        ofertas.add(new Oferta("oferta1","contenido1", R.drawable.corazon,0));
        ofertas.add(new Oferta("oferta2","contenido2", R.drawable.ipod,0));
        ofertas.add(new Oferta("oferta3","contenido3", R.drawable.monitor,0));
        ofertas.add(new Oferta("oferta4","contenido4", R.drawable.ordenador,0));
    }



    @Override
    public AdapterOferta.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewOferta=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_oferta,viewGroup,false);
        ViewHolder vh=new ViewHolder(viewOferta);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterOferta.ViewHolder holder, int i) {
        ImageUtils im = new ImageUtils(contexto);
        Drawable drawable=contexto.getResources().getDrawable(ofertas.get(i).imagen);
        drawable = im.resize(drawable);
        Drawable img_estado = contexto.getResources().getDrawable(R.drawable.green_circle);
        holder.imagen.setImageDrawable(drawable);
        holder.titulo.setText(ofertas.get(i).titulo);
        holder.contenido.setText(ofertas.get(i).subtitulo);
        holder.imagen_estado.setImageDrawable(img_estado);
    }



    @Override
    public int getItemCount() {
        return ofertas.size();
    }


}
