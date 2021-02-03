package org.gedstudio.library.bukkit.chat;

public class GText {

    private String text;

    /**
     * To create a new GText
     * @param text the text
     */
    public GText(String text) {
        this.text = text;
    }

    /**
     * To set text
     * @param text text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * To get the text
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * To translate ChatColor
     * @param prefix the prefix of chat color key
     */
    public void translate(String prefix) {
        this.setText(GChatColor.translate(prefix, this.getText()));
    }

}
