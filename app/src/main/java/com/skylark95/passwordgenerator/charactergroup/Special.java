package com.skylark95.passwordgenerator.charactergroup;

public class Special implements CharacterGroup {

    private static final String CHARACTERS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    @Override
    public char[] getChars() {
        return CHARACTERS.toCharArray();
    }

}
