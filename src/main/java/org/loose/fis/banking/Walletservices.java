package org.loose.fis.banking;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.loose.fis.introduction.model.User;
import org.loose.fis.introduction.services.FileSystemService;
import org.loose.fis.introduction.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Walletservices {
    private static List<Wallet> wallets;
    private static final Path WALLETS_PATH = FileSystemService.getPathToFile("config", "wallets.json");


    public static void addWallets(String username) {
        wallets.add(new Wallet(username));
        persistWallets();
    }

    public static Wallet getWallet(String username){
        for (Wallet w: wallets) {
            if(w.getUsername().equals(username)){
                return w;
            }
        }

        throw new CouldNotFindWalletException();
    }

    private static void persistWallets() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(WALLETS_PATH.toFile(), wallets);
        } catch (IOException e) {
            throw new CouldNotWriteWalletException();
        }
    }
    public static void loadWalletFromFile() throws IOException {

        if (!Files.exists(WALLETS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("wallets.json")), WALLETS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        wallets = objectMapper.readValue(WALLETS_PATH.toFile(), new TypeReference<List<Wallet>>() {});
    }

    public static void updateWallet(Wallet newWallet){
        for (Wallet w:wallets) {
            if(newWallet.getUsername().equals(w.getUsername())){
                wallets.remove(w);
                wallets.add(newWallet);
                persistWallets();
                return;
            }
        }
        throw new CouldNotFindWalletException();
    }
}


