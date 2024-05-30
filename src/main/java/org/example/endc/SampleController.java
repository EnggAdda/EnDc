package org.example.endc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController
{
    @GetMapping("/getSecretMessage")
    public String getMessage(){
        String message = "TheXCoders";

        EncryptionManager manager = new EncryptionManager();
        manager.initFromStrings();

        try{
            return manager.encrypt(message);

        }catch (Exception ignored){}

        return "null";
    }
}
