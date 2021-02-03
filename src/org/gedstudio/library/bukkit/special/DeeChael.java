package org.gedstudio.library.bukkit.special;

import java.util.ArrayList;
import java.util.List;

public class DeeChael implements Author {

    private final String name = "DeeChael";
    private List<Human> friends;

    public DeeChael() {
        this.friends = new ArrayList<>();
        this.friends.add(new Gerryawa());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Human> getFriends() {
        return this.friends;
    }

}
