package com.skylark95.passwordgenerator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.skylark95.passwordgenerator.charactergroup.CharacterGroup;
import com.skylark95.passwordgenerator.charactergroup.Lowercase;
import com.skylark95.passwordgenerator.charactergroup.Numbers;
import com.skylark95.passwordgenerator.charactergroup.Special;
import com.skylark95.passwordgenerator.charactergroup.Uppercase;
import com.skylark95.passwordgenerator.generator.PasswordGenerator;
import com.skylark95.passwordgenerator.generator.PasswordGeneratorImpl;

import java.util.ArrayList;
import java.util.List;


public class PasswordGeneratorActivity extends ActionBarActivity {

    private PasswordGenerator passwordGenerator;
    private EditText passwordLengthEditText;
    private EditText generatedPasswordEditText;

    public PasswordGeneratorActivity() {
        this(new PasswordGeneratorImpl());
    }

    public PasswordGeneratorActivity(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
        findViewById(R.id.button_generate_password).requestFocus();

        passwordLengthEditText = (EditText) findViewById(R.id.password_length);
        generatedPasswordEditText = (EditText) findViewById(R.id.generated_password);

        createClickListeners();
    }

    private void createClickListeners() {
        createMinusButtonClickListener();
        createPlusButtonClickListener();
        createGeneratePasswordButtonClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_generator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createMinusButtonClickListener() {
        Button minusButton = (Button) findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int passwordLength = getPasswordLength();
                setPasswordLength(--passwordLength);
            }
        });
    }

    private void createPlusButtonClickListener() {
        Button minusButton = (Button) findViewById(R.id.button_plus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int passwordLength = getPasswordLength();
                setPasswordLength(++passwordLength);
            }
        });
    }

    private void createGeneratePasswordButtonClickListener() {
        Button generatePasswordButton = (Button) findViewById(R.id.button_generate_password);
        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = getPasswordLength();
                List<CharacterGroup> characterGroups = new ArrayList<>();
                addCharacterGroup(characterGroups, R.id.checkbox_uppercase, Uppercase.class);
                addCharacterGroup(characterGroups, R.id.checkbox_lowercase, Lowercase.class);
                addCharacterGroup(characterGroups, R.id.checkbox_numbers, Numbers.class);
                addCharacterGroup(characterGroups, R.id.checkbox_special, Special.class);
                String generatedPassword = getPasswordGenerator().generatePassword(length, characterGroups);
                getGeneratedPasswordEditText().setText(generatedPassword);
            }
        });
    }

    private void addCharacterGroup(List<CharacterGroup> characterGroups, int id, Class<? extends CharacterGroup> characterGroupClass) {
        if (isChecked(id)) {
            try {
                characterGroups.add(characterGroupClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new PasswordGeneratorException(e);
            }
        }
    }

    private boolean isChecked(int id) {
        return ((CheckBox) findViewById(id)).isChecked();
    }

    int getPasswordLength() {
        return Integer.parseInt(getPasswordLengthEditText().getText().toString());
    }

    private void setPasswordLength(int passwordLength) {
        getPasswordLengthEditText().setText(String.valueOf(passwordLength));
    }

    PasswordGenerator getPasswordGenerator() {
        return passwordGenerator;
    }

    EditText getPasswordLengthEditText() {
        return passwordLengthEditText;
    }

    EditText getGeneratedPasswordEditText() {
        return generatedPasswordEditText;
    }

}
