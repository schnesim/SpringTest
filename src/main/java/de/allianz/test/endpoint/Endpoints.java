package de.allianz.test.endpoint;

import de.allianz.test.model.Address;
import de.allianz.test.model.Person;
import de.allianz.test.repo.PersonRepo;
import de.allianz.test.service.SomeService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Endpoints {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private SomeService someService;

    @GetMapping("/test")
    public String test() {
        Person p = new Person();
        p.setId(UUID.randomUUID().toString());
        Address a = new Address();
        a.setId(UUID.randomUUID().toString());
        a.setCity("München");
        p.setAddress(a);
        personRepo.save(p);
        return "sakkzess";
    }

    @GetMapping("/test2")
    public String test2() {
        Iterable<Person> findAll = personRepo.findAll();
        findAll.forEach(p -> {
            System.out.println(p.getId());
            if (p.getAddress() != null) {
                System.out.println(p.getAddress().getId());
            }
        });
        return "";
    }
    
    @GetMapping("/testCache")
    public String testCache() {
        String something = someService.getSomething("a");
        return something;
    }

    @GetMapping("/makeCall")
    public String makeCall() throws MalformedURLException, IOException {
        URL urlNet = new URL("https://druckservice.apps.test.sbk.de/swagger-ui/index.html");

//        MakeSSLHandshake();

        HttpsURLConnection con = (HttpsURLConnection) urlNet.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder htmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            htmlContent.append(line);
        }

        reader.close();
        con.disconnect();

        return htmlContent.toString();
    }

    public static boolean MakeSSLHandshake() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                for (X509Certificate arg : arg0) {
                    System.out.println(arg.getSubjectX500Principal().getName());
                    System.out.println(arg.getIssuerDN().getName());
                }
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                for (X509Certificate cert : certs) {
                    if (cert.getIssuerDN().getName().startsWith("CN=SBK-ROOT-CA-2023")) {
                        return;
                    }
                }

                throw new CertificateException("Server certificate doesn't match *.sbk.org");
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            return true;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
