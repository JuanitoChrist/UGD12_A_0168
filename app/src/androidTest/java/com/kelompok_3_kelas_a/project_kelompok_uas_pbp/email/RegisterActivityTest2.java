package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest2 {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void registerActivityTest2() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.et_nama),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1)));
        textInputEditText.perform(scrollTo(), replaceText("j"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.et_nama), withText("j"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1)));
        textInputEditText2.perform(scrollTo(), replaceText("ju"));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.et_nama), withText("ju"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.et_nama), withText("ju"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1)));
        textInputEditText4.perform(scrollTo(), replaceText("juan"));

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.et_nama), withText("juan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.et_umur),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText6.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.et_umur), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText7.perform(scrollTo(), replaceText("12"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.et_umur), withText("12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.et_umur), withText("12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText9.perform(scrollTo(), replaceText("1234"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.et_umur), withText("1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.et_umur), withText("1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText11.perform(scrollTo(), replaceText("123"));

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.et_umur), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText12.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.et_umur), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText13.perform(scrollTo(), replaceText("12"));

        ViewInteraction textInputEditText14 = onView(
                allOf(withId(R.id.et_umur), withText("12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText14.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText15 = onView(
                allOf(withId(R.id.et_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText15.perform(scrollTo(), replaceText("j"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText16 = onView(
                allOf(withId(R.id.et_email), withText("j"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText16.perform(scrollTo(), replaceText("ju"));

        ViewInteraction textInputEditText17 = onView(
                allOf(withId(R.id.et_email), withText("ju"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText17.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText18 = onView(
                allOf(withId(R.id.et_email), withText("ju"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText18.perform(scrollTo(), replaceText("jua"));

        ViewInteraction textInputEditText19 = onView(
                allOf(withId(R.id.et_email), withText("jua"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText19.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText20 = onView(
                allOf(withId(R.id.et_email), withText("jua"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText20.perform(scrollTo(), replaceText("juan"));

        ViewInteraction textInputEditText21 = onView(
                allOf(withId(R.id.et_email), withText("juan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText21.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText22 = onView(
                allOf(withId(R.id.et_email), withText("juan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText22.perform(scrollTo(), replaceText("juanit"));

        ViewInteraction textInputEditText23 = onView(
                allOf(withId(R.id.et_email), withText("juanit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText23.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText24 = onView(
                allOf(withId(R.id.et_email), withText("juanit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText24.perform(scrollTo(), replaceText("juanito"));

        ViewInteraction textInputEditText25 = onView(
                allOf(withId(R.id.et_email), withText("juanito"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText25.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText26 = onView(
                allOf(withId(R.id.et_jenis_kelamin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText26.perform(scrollTo(), replaceText("l"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText27 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("l"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText27.perform(scrollTo(), replaceText("lak"));

        ViewInteraction textInputEditText28 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("lak"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText28.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText29 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("lak"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText29.perform(scrollTo(), replaceText("laki-"));

        ViewInteraction textInputEditText30 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("laki-"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText30.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText31 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("laki-"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText31.perform(scrollTo(), replaceText("laki-l"));

        ViewInteraction textInputEditText32 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("laki-l"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText32.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText33 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("laki-la"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText33.perform(scrollTo(), replaceText("laki-laki"));

        ViewInteraction textInputEditText34 = onView(
                allOf(withId(R.id.et_jenis_kelamin), withText("laki-laki"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText34.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText35 = onView(
                allOf(withId(R.id.et_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1)));
        textInputEditText35.perform(scrollTo(), replaceText("j"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText36 = onView(
                allOf(withId(R.id.et_password), withText("j"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1)));
        textInputEditText36.perform(scrollTo(), replaceText("jua"));

        ViewInteraction textInputEditText37 = onView(
                allOf(withId(R.id.et_password), withText("jua"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText37.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText38 = onView(
                allOf(withId(R.id.et_password), withText("jua"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1)));
        textInputEditText38.perform(scrollTo(), replaceText("juan"));

        ViewInteraction textInputEditText39 = onView(
                allOf(withId(R.id.et_password), withText("juan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText39.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText40 = onView(
                allOf(withId(R.id.et_email), withText("juanito"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText40.perform(scrollTo(), replaceText("juanito@"));

        ViewInteraction textInputEditText41 = onView(
                allOf(withId(R.id.et_email), withText("juanito@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText41.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText42 = onView(
                allOf(withId(R.id.et_email), withText("juanito@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText42.perform(scrollTo(), replaceText("juanito"));

        ViewInteraction textInputEditText43 = onView(
                allOf(withId(R.id.et_email), withText("juanito"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText43.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText44 = onView(
                allOf(withId(R.id.et_email), withText("juanito"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText44.perform(scrollTo(), replaceText("juanitoc"));

        ViewInteraction textInputEditText45 = onView(
                allOf(withId(R.id.et_email), withText("juanitoc"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText45.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText46 = onView(
                allOf(withId(R.id.et_email), withText("juanitoc"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText46.perform(scrollTo(), replaceText("juanitoch"));

        ViewInteraction textInputEditText47 = onView(
                allOf(withId(R.id.et_email), withText("juanitoch"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText47.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText48 = onView(
                allOf(withId(R.id.et_email), withText("juanitoch"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText48.perform(scrollTo(), replaceText("juanitochri"));

        ViewInteraction textInputEditText49 = onView(
                allOf(withId(R.id.et_email), withText("juanitochri"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText49.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText50 = onView(
                allOf(withId(R.id.et_email), withText("juanitochri"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText50.perform(scrollTo(), replaceText("juanitochristi"));

        ViewInteraction textInputEditText51 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText51.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText52 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText52.perform(scrollTo(), replaceText("juanitochristia"));

        ViewInteraction textInputEditText53 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText53.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText54 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText54.perform(scrollTo(), replaceText("juanitochristian"));

        ViewInteraction textInputEditText55 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText55.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText56 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText56.perform(scrollTo(), replaceText("juanitochristian@"));

        ViewInteraction textInputEditText57 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText57.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText58 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText58.perform(scrollTo(), replaceText("juanitochristian@gm"));

        ViewInteraction textInputEditText59 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText59.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText60 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText60.perform(scrollTo(), replaceText("juanitochristian@gmai"));

        ViewInteraction textInputEditText61 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmai"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText61.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText62 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmai"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText62.perform(scrollTo(), replaceText("juanitochristian@gmail.c"));

        ViewInteraction textInputEditText63 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmail.c"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText63.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText64 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmail.c"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText64.perform(scrollTo(), replaceText("juanitochristian@gmail.co"));

        ViewInteraction textInputEditText65 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmail.co"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText65.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textInputEditText66 = onView(
                allOf(withId(R.id.et_email), withText("juanitochristian@gmail.co"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText66.perform(scrollTo(), replaceText("juan.makan.mendoan@gmail.com"));

        ViewInteraction textInputEditText67 = onView(
                allOf(withId(R.id.et_email), withText("juan.makan.mendoan@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText67.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btn_save), withText("simpan"),
                        childAtPosition(
                                allOf(withId(R.id.ll_button),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
