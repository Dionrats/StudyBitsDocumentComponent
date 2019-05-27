package nl.quintor.studybits.documents.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
