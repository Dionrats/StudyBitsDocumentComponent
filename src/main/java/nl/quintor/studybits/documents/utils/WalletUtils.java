package nl.quintor.studybits.documents.utils;


import lombok.extern.slf4j.Slf4j;
import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.wallet.Wallet;

import java.util.concurrent.ExecutionException;

@Slf4j
public class WalletUtils {

    private static String currentKey;

    static {
        //TODO: this realy shouldn't be static. Has to be determined from either working input or properties...
        currentKey = generatWalletKey("00000000000000000000000000000001", true);
    }

    protected WalletUtils(){}

    public static String generatWalletKey(String seed, boolean updateCurrent) {
        String key = null;
        try {
            key = Wallet.generateWalletKey("{\"seed\": \"" + seed + "\"}").get();
        } catch (ExecutionException | IndyException e) {
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        if(updateCurrent)
            currentKey = key;

        return key;
    }


    public static String composeConfig(String name, String path) {
        return "{  \"id\": \"" + name + "\",\"storage_config\": {\"path\": \"" + path + "\"}}";
    }

    public static String composeUnlockCredentials() {
        return "{\"key\": \"" + currentKey + "\"}";
    }

    public static String composeLockCredentials() {
        return "{\"key\": \"\", \"rekey\": \"" + currentKey + "\"}";
    }

}
