package nl.quintor.studybits.documents;

import org.hyperledger.indy.sdk.LibIndy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocumentsApplication {

	//TODO Move to properties file
	private static final String LIB_PATH = "/Users/dionrats/Nextcloud/school/Leerjaar_4/Stage/studybits/indy-sdk/libindy/target/debug";


	public static void main(String[] args) {
		LibIndy.init(LIB_PATH);
		SpringApplication.run(DocumentsApplication.class, args);
	}

}
