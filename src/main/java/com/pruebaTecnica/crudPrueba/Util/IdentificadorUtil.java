
package com.pruebaTecnica.crudPrueba.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class IdentificadorUtil {
        public static String generarIdentificador(String prefijo) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        return prefijo + "-" + LocalDateTime.now().format(formatter);
    }
}
