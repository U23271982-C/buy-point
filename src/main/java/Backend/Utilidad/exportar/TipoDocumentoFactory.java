package Backend.Utilidad.exportar;

import java.util.HashMap;
import java.util.Map;

public class TipoDocumentoFactory {
    private Map<String, TipoDocumento<?>> mapTiposDocumentos = new HashMap<>();

    {
        mapTiposDocumentos.put("excel", new TipoDocumentoExcel());
    }

    public TipoDocumento<?> getTipoDocumento(String extension) {
        return mapTiposDocumentos.get(extension);
    }

    public void registrarTipoDocumento(String extension, TipoDocumento<?> tipoDocumento) {
        mapTiposDocumentos.put(extension, tipoDocumento);
    }
}
