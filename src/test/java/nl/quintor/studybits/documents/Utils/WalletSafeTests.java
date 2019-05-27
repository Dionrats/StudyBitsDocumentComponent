//package nl.quintor.studybits.documents.Utils;
//
//import nl.quintor.studybits.documents.config.WalletConfig;
//import org.hyperledger.indy.sdk.IndyException;
//import org.hyperledger.indy.sdk.wallet.Wallet;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.ExecutionException;
//
//import static junit.framework.TestCase.assertFalse;
//import static junit.framework.TestCase.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class WalletSafeTests {
//
//    private static WalletSafe sut;
//
//    @BeforeClass
//    public static void setup() {
//        WalletConfig walletConfig = new WalletConfig();
//        walletConfig.setName("John");
//        walletConfig.setSeed("0000000000000000000000000000000");
//
//        sut = new WalletSafe(walletConfig);
//    }
//
//    @Test
//    public void startLockedTest() {
//        assertTrue(sut.isLocked());
//    }
//
//    @Test
//    public void unlockTest() throws InterruptedException, ExecutionException, IndyException {
//
//        Wallet wallet = sut.unlock();
//
//
//        assertFalse(sut.isLocked());
//    }
//
//    @Test
//    public void lockTest() throws IndyException {
//
//        sut.lock();
//
//
//        assertTrue(sut.isLocked());
//    }
//}
