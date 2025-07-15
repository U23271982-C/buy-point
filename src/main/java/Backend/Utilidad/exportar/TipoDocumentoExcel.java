package Backend.Utilidad.exportar;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TipoDocumentoExcel implements TipoDocumento<XSSFWorkbook> {

    public TipoDocumentoExcel() {}

    @Override
    public Exportador getExportador() {
        return new ExportarExcel();
    }

    @Override
    public XSSFWorkbook crearDocumento() {
        return new XSSFWorkbook();
    }

    @Override
    public String getExtension() {
        return ".xlsx";
    }
}
