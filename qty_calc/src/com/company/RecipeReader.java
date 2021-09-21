package com.company;

import java.util.ArrayList;

public class RecipeReader {

    String filePath;     // taken from DB

    /*




     */

    public RecipeReader(String filePath) {
        this.filePath = filePath;
    }







    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
