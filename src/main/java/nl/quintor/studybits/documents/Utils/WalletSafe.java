package nl.quintor.studybits.documents.Utils;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.studybits.documents.config.WalletConfig;
import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.wallet.Wallet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WalletSafe implements AutoCloseable{

    private final WalletConfig walletConfig;
    private Wallet wallet;
    private boolean locked;

    public WalletSafe(WalletConfig walletConfig) {
        this.walletConfig = walletConfig;
        wallet = null;
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lock() throws IndyException {
        CompletableFuture<Void> future = wallet.closeWallet();
        future.thenRun(() -> locked = true);
    }

    public Wallet unlock() throws IndyException, ExecutionException, InterruptedException {
        String config = WalletUtils.composeConfig(walletConfig.getName(), walletConfig.getPath());
        String credentials = WalletUtils.composeUnlockCredentials();

        CompletableFuture<Wallet> future = Wallet.openWallet(config, credentials);
        future.thenRun(() -> locked = false);
        wallet = future.get();
        return wallet;
    }

    @Override
    public void close() throws IndyException {
        lock();
    }
}
