package nl.quintor.studybits.documents;

import org.hyperledger.indy.sdk.LibIndy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocumentsApplication {

	private static String LIB_PATH;

	public static void main(String[] args) {
		SpringApplication.run(DocumentsApplication.class, args);
		LibIndy.init(LIB_PATH);
	}

	@Value("${libindy.path}")
	public void setLibPath(String libPath) {
		LIB_PATH = libPath;
	}

}
