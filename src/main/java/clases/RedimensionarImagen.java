package clases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import net.coobird.thumbnailator.Thumbnails;

public class RedimensionarImagen {

    public RedimensionarImagen() {
    }
    
        public byte[] redimensionarImagenBase64(String base64Image, int ancho, int alto) throws IOException {
        // Decodificar la imagen Base64 a bytes
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        // Crear un InputStream a partir de la coonversion a byte
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        // Crear un ByteArrayOutputStream para almacenar la imagen redimensionada
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Usar Thumbnails para redimensionar
        Thumbnails.of(inputStream)
                  .size(ancho, alto) // Dimensiones deseadas
                  .outputFormat("jpg") // Formato de salida
                  .toOutputStream(outputStream);
        // Retornar la imagen redimensionada como byte[]
        return outputStream.toByteArray();
    }
}
