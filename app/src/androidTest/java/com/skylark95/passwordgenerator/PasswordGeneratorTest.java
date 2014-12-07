package com.skylark95.passwordgenerator;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.ContextThemeWrapper;
import android.widget.EditText;

import com.skylark95.passwordgenerator.generator.PasswordGeneratorImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.android.api.Assertions.assertThat;


public class PasswordGeneratorTest extends ActivityUnitTestCase<PasswordGeneratorActivity> {

    private PasswordGeneratorActivity activity;
    private EditText generatedPasswordEditText;

    public PasswordGeneratorTest() {
        super(PasswordGeneratorActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);
        Intent intent = new Intent(getInstrumentation().getTargetContext(), PasswordGeneratorActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
        generatedPasswordEditText = (EditText) activity.findViewById(R.id.generated_password);
    }

    public void testDoesUsePasswordGeneratorImpl() {
        assertThat(activity.getPasswordGenerator()).isInstanceOf(PasswordGeneratorImpl.class);
    }

    public void testDoesStartWithEmptyText() {
        assertThat(generatedPasswordEditText).isVisible();
    }
}
