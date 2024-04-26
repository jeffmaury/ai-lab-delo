package io.podman.desktop;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> splitString(String inputString, int blockLength) {
        List<String> blocks = new ArrayList<>();
        if (inputString == null || inputString.length() == 0 || blockLength <= 0) {
            return blocks;
        }
    
        for (int i = 0; i < inputString.length(); i += blockLength) {
            if (i + blockLength > inputString.length()) {
                blockLength = inputString.length() - i;
            }
            blocks.add(inputString.substring(i, i + blockLength));
        }
    
        return blocks;
    }
    
}
