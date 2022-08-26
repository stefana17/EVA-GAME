package Window;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptiiStefana extends IOException {

    public ExceptiiStefana(String message) {
        super(message);
    }
    public ExceptiiStefana(String message, Throwable cause){
        super(message,cause);
    }
}