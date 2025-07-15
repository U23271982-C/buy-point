package Backend.Reporte;


import java.util.ArrayList;
import java.util.List;

public abstract class ReportePlantilla extends Reporte {

    public ReportePlantilla(String tituloHoja, String tipoDocumento, List<Object[]> listaTabla) {
        super(tituloHoja, tipoDocumento,listaTabla);
        formatearReporte();
    }

    @Override
    protected void cortarEncabezado() {
        int numColumnas = this.datos.getFirst().length;

        this.encabezado = new String[numColumnas];
        // se corta fila encabezados
        for (int i = 0; i < numColumnas; i++) {
            this.encabezado[i] = this.datos.getFirst()[i].toString();
        }
    }

    // corta el cuerpo de la consulta
    @Override
    protected void cortarCuerpo() {
        this.contenido = new ArrayList<>();
        // se corta solo datos cuerpo, pasa contenido
        for (int i = 1; i < datos.size(); i++) {
            this.contenido.add(datos.get(i));
        }
    }
    
    private void formatearReporte() {
        cortarEncabezado();
        cortarCuerpo();
        // Ajustar el tamaño de las columnas
        for(int i = 0; i < encabezado.length; i++){
            hoja.autoSizeColumn(i);
        }
    }

}
