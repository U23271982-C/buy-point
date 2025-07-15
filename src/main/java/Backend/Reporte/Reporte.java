package Backend.Reporte;

import Backend.Utilidad.exportar.Exportador;
import Backend.Utilidad.exportar.TipoDocumento;
import Backend.Utilidad.exportar.TipoDocumentoFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Reporte {
    protected final LocalDateTime horaFechaActual = LocalDateTime.now();
    protected DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected String[] encabezado;
    protected List<Object[]> contenido;
    protected Workbook workbook;
    protected Sheet hoja;
    protected List<Object[]> datos;
    protected final TipoDocumentoFactory factoriaDocumentos = new TipoDocumentoFactory();
    protected String direccionGuardado;
    protected String extensionDocumento;
    protected final Exportador exportadorDocumento;

    public Reporte (String tituloHoja, String tipoDocumento, List<Object[]> datos) {
        TipoDocumento e = factoriaDocumentos.getTipoDocumento(tipoDocumento);
        this.exportadorDocumento = e.getExportador();
        this.workbook = (XSSFWorkbook) e.crearDocumento();
        this.extensionDocumento = e.getExtension();

        createSheet(tituloHoja);
        this.datos = datos;
    }
    private void createSheet(String titlteSheet) {
        // Supón que workbook es tu XSSFWorkbook y sheetName es el nombre de la hoja
        if (workbook.getSheet(titlteSheet) != null) {
            // El nombre ya existe, puedes lanzar una excepción, renombrar, o eliminar la hoja
            // Por ejemplo, renombrar:
            int i = 1;
            String newSheetName = titlteSheet;
            while (workbook.getSheet(newSheetName) != null) {
                newSheetName = titlteSheet + "_" + i;
                i++;
            }
            titlteSheet = newSheetName;
        }
        this.hoja = workbook.createSheet(titlteSheet);
    }
    abstract protected void cortarEncabezado();

    abstract protected void cortarCuerpo();
    
    abstract public void exportarReporte();

    public void setDireccionGuardado(String direccionGuardado) {
        this.direccionGuardado = direccionGuardado;
    }
}
