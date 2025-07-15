package Backend.Utilidad.exportar;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ExportarExcel implements Exportador<XSSFWorkbook> {

    @Override
    public void exportar(XSSFWorkbook workbook, String rutaArchivo) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            workbook.write(fos);
            workbook.close();
            //NotificacionUtil.mostrarMensajeAfirmacion("✅ Se guardo correctamente el archivo exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //NotificacionUtil.mostrarError(e);
        }
    }

    @Override
    public String toString() {
        return "ExportarExcel{}";
    }
}
