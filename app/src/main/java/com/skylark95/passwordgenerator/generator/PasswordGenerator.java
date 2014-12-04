package com.skylark95.passwordgenerator.generator;

import com.skylark95.passwordgenerator.charactergroup.CharacterGroup;

import java.util.Collection;

public interface PasswordGenerator {

    String generatePassword(int length, Collection<CharacterGroup> characterGroups);

}
