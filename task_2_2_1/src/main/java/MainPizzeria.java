import JSON.PizzeriaConfiguration;
import Production.PizzeriaProduction;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MainPizzeria {
    private static PizzeriaConfiguration configuration;
    public static void main(String[] args) throws IOException {
        if(createIfNotExists()){
            return;
        }
        loadConfig();
        PizzeriaProduction.startOfWorking(configuration);
    }

    public static PizzeriaConfiguration loadConfig() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        configuration = mapper.readValue( new File("./conf.json"), PizzeriaConfiguration.class);
        return configuration;
    }

    public static boolean createIfNotExists() throws IOException {
        var file = new File("./conf.json");
        if(!file.exists()){
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, new PizzeriaConfiguration());
            return true;
        }
        return false;
    }
}
