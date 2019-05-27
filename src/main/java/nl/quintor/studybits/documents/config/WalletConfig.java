package nl.quintor.studybits.documents.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
public class WalletConfig {
    @Value("${wallet.name}")
    private String name;
    @Value("${wallet.path}")
    private String path;
    @Value("${wallet.key.seed}")
    private String seed;
}
