package nl.quintor.studybits.documents;

import lombok.extern.slf4j.Slf4j;
import org.hyperledger.indy.sdk.LibIndy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DocumentsApplication {

	private static final String LIBPATH = "/Users/dionrats/Nextcloud/school/Leerjaar_4/Stage/studybits/indy-sdk/libindy/target/debug";

	public static void main(String[] args) {
		LibIndy.init(LIBPATH);
		SpringApplication.run(DocumentsApplication.class, args);
	}

}
