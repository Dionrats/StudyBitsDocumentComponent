package nl.quintor.studybits.documents.controllers;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.studybits.documents.utils.WalletUtils;
import nl.quintor.studybits.documents.config.WalletConfig;
import nl.quintor.studybits.documents.utils.WalletSafe;
import nl.quintor.studybits.documents.entities.DidMetadata;
import nl.quintor.studybits.documents.models.LoginModel;
import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.did.Did;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller
public class WebController {

    private final WalletConfig walletConfig;

    @Autowired
    public WebController(WalletConfig walletConfig) {
        this.walletConfig = walletConfig;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboardGET() {
        return "login";
    }

    @PostMapping(value = "/dashboard", consumes = "multipart/form-data")
    public String dashboardPOST(@ModelAttribute LoginModel loginModel, Model model) {
        walletConfig.setSeed(WalletUtils.generatWalletKey(loginModel.getPassword(), false));
        walletConfig.setName(loginModel.getName() + "Wallet");

        try(WalletSafe walletSafe = new WalletSafe(walletConfig)) {
            DidMetadata did = new Gson().fromJson(Did.getListMyDidsWithMeta(walletSafe.unlock()).get().replaceAll("^.|.$", ""), DidMetadata.class);

            model.addAttribute("did", did.getDid());
            model.addAttribute("verkey", did.getVerkey());
            model.addAttribute("name", loginModel.getName());
        } catch (IndyException | ExecutionException e) {
            log.error(e.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        return "dashboard";
    }

}
