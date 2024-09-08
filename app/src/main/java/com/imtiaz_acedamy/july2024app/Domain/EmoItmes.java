package com.imtiaz_acedamy.july2024app.Domain;

public class EmoItmes {
    private int image;
    String emoText;

    public EmoItmes(int image, String emoText) {
        this.image = image;
        this.emoText = emoText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEmoText() {
        return emoText;
    }

    public void setEmoText(String emoText) {
        this.emoText = emoText;
    }
}
