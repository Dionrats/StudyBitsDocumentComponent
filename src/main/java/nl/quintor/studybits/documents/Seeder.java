package nl.quintor.studybits.documents;

import lombok.extern.slf4j.Slf4j;
import nl.quintor.studybits.documents.utils.WalletUtils;
import nl.quintor.studybits.documents.config.WalletConfig;
import nl.quintor.studybits.documents.utils.WalletSafe;
import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.did.Did;
import org.hyperledger.indy.sdk.did.DidResults;
import org.hyperledger.indy.sdk.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class Seeder {

    @Autowired
    private WalletConfig walletConfig;

    @EventListener
    public void seedWallet(ContextRefreshedEvent event) {
        if(needsSeeding()) {
            log.debug("CREATING WALLET: {}", walletConfig.getName());
            createWallet();
            createDiD();
        } else {
            log.debug("FOUND WALLET: {}", walletConfig.getName());
        }
    }

    private boolean needsSeeding() {
        return !new File(walletConfig.getPath() + walletConfig.getName() + "/sqlite.db").exists();
    }

    private void createWallet() {
        try {
            String config = WalletUtils.composeConfig(walletConfig.getName(), walletConfig.getPath());
            String credentials = WalletUtils.composeUnlockCredentials();
            Wallet.createWallet(config, credentials).get();
        } catch (ExecutionException | IndyException e) {
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void createDiD() {
        try(WalletSafe walletSafe = new WalletSafe(walletConfig)) {
            CompletableFuture<DidResults.CreateAndStoreMyDidResult> future = Did.createAndStoreMyDid(walletSafe.unlock(), "{}");
            DidResults.CreateAndStoreMyDidResult result = future.get();
            log.debug("WALLET DID: {}", result.getDid());
        } catch (IndyException | ExecutionException e) {
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
