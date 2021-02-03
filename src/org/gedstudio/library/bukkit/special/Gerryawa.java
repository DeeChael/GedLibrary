package org.gedstudio.library.bukkit.special;

import java.util.ArrayList;
import java.util.List;

public class Gerryawa implements Author {

    private final String name = "Gerryawa";
    private List<Human> friends;

    public Gerryawa() {
        this.friends = new ArrayList<>();
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
