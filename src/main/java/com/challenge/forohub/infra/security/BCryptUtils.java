package com.challenge.forohub.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Metodo para generar un hash a partir de una contraseña
    public static String generarHash(String passwordPlano) {
        return encoder.encode(passwordPlano);
    }

    // Metodo para verificar si la contraseña en texto plano coincide con el hash
    public static boolean verificarPassword(String passwordPlano, String passwordHasheado) {
        return encoder.matches(passwordPlano, passwordHasheado);
    }

    // Metodo main para hacer pruebas manuales
    public static void main(String[] args) {
        // Generar hash
        String password = "adminPass321";
        String hash = generarHash(password);
        System.out.println("Hash generado: " + hash);

//        // Verificar password
//        String passwordParaVerificar = "123456";
//        String hashExistente = "$2a$12$EvgDZX3.Rnl6O5YTdNtVHOwrVEqhD1s8tVAXuGT6h1rygeA4shyD6"; // Reemplaza por un hash real
//        boolean resultado = verificarPassword(passwordParaVerificar, hashExistente);
//        System.out.println("¿Coincide la contraseña? " + resultado);
    }
}
