package com.coworking.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.coworking.repository.llavesecreta.ILlaveSecretaRepository;

@Configuration
@EnableScheduling
@EnableAsync
public class TareaSecreta {

    @Autowired
    private ILlaveSecretaRepository llaveSecretaRepository;

    @Scheduled(cron = "${parametro.cron}")
    public void actualizarLLaveSecreta() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        // Inicializar el generador de claves con el tamaño de clave deseado
        keyGenerator.init(128); // Puedes cambiar el tamaño de clave según tus necesidades

        // Generar la clave secreta
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        llaveSecretaRepository.actualizar(bytesToHex(keyBytes), 1);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
