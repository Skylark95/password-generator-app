package com.skylark95.passwordgenerator.generator;

import com.skylark95.passwordgenerator.charactergroup.CharacterGroup;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class PasswordGeneratorImpl implements PasswordGenerator {

    @Override
    public String generatePassword(int length, Collection<CharacterGroup> characterGroups) {
        String password = "";
        if (!characterGroups.isEmpty()) {
            char[] chars = flatten(characterGroups);
            password = generatePasswordFromCharacters(chars, length);
        }
        return password;
    }

    /*
     * https://en.wikipedia.org/wiki/Random_password_generator#Java
     */
    private String generatePasswordFromCharacters(char[] chars, int length) {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int idx = random.nextInt(chars.length);
            sb.append(chars[idx]);
        }
        return sb.toString();
    }

    private char[] flatten(Collection<CharacterGroup> characterGroups) {
        Iterator<CharacterGroup> iterator = characterGroups.iterator();
        char[] array = iterator.next().getChars();
        while (iterator.hasNext()) {
            array = concat(array, iterator.next().getChars());
        }
        return array;
    }

    /*
     * http://stackoverflow.com/a/80503
     */
    private char[] concat(char[] a, char[] b) {
        char[] c = new char[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

}
