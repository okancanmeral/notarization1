package com.cashonledger.sha;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileChecksumSHA {
    Path filepath = Paths.get("/home/okan/Notarization1/notarization1/data/notarization187496806-6c0f-47c4-b853-a4ddf061566aDOWNLOAD.txt");

    public static void sha256(String[] args) throws NoSuchFieldException, IOException, NoSuchAlgorithmException {

        String dataFolder = System.getenv("data");
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA, MD2, MD5, SHA-256, SHA-384
        String hex = checksum("/home/okan/Notarization1/notarization1/data/notarization187496806-6c0f-47c4-b853-a4ddf061566aDOWNLOAD.txt", md);
        System.out.println(hex);
    }

    private static String checksum(String filepath, MessageDigest md) throws IOException {

        // file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1)
                ; // empty loop tp clear the data
            md = dis.getMessageDigest();

        }
        // byte to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));

        }
        return result.toString();
    }
}