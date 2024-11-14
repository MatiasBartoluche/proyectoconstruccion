package clases;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// creando un adaptador personalizado para LocalDate
// ya que Gson no tiene funciones para serializar y desserializar objetos LocalDate de java
// 

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        //jsonWriter.value(localDate.format(formatter));
        if(localDate == null){
            // si el campo de localDate es nulo, escribe "null"
            jsonWriter.nullValue();
        }
        else{
            jsonWriter.value(localDate.format(formatter));
        }
    }
    
    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        //return LocalDate.parse(jsonReader.nextString(), formatter);
        if(jsonReader.peek() == JsonToken.NULL){
            jsonReader.nextNull();
            return null;
        }
        String fecha = jsonReader.nextString();
        return fecha.isEmpty() ? null : LocalDate.parse(fecha, formatter);
    }
}
