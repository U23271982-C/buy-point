package Backend.Utilidad.exportar;

public interface Exportador<Tipo> {
    void exportar(Tipo tipoLibro,String rutaArchivo);
}
