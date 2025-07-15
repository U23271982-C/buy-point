package Backend.Utilidad.exportar;

public interface TipoDocumento <T> {
    Exportador getExportador();
    T crearDocumento();
    String getExtension();
}
