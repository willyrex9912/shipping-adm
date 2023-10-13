package com.modela.shipping.adm.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

/**
 * @author willyrex
 */

@Slf4j
public class KeyLoader {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static PrivateKey loadPrivateKey(String nameFile) throws IOException {
        ClassPathResource resource = new ClassPathResource(nameFile);
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())){
            PEMParser pemParser = new PEMParser(reader);
            JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
            return keyConverter.getPrivateKey((PrivateKeyInfo)pemParser.readObject());
        }
    }

    public static PublicKey loadPublicKey(String nameFile) throws IOException {
        ClassPathResource resource = new ClassPathResource(nameFile);
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())){
            PEMParser pemParser = new PEMParser(reader);
            JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
            return keyConverter.getPublicKey((SubjectPublicKeyInfo)pemParser.readObject());
        }
    }

}
