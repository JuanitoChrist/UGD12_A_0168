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
public class RegisterActivityTest4 {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void registerActivityTest4() {
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

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.et_nama),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1)));
        textInputEditText.perform(scrollTo(), replaceText("a"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.et_nama), withText("a"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_nama),
                                        0),
                                1)));
        textInputEditText2.perform(scrollTo(), click());

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

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.et_umur),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText3.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.et_umur),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText4.perform(scrollTo(), replaceText("123"), closeSoftKeyboard());

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

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.et_umur), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1)));
        textInputEditText5.perform(scrollTo(), replaceText("12"));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.et_umur), withText("12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_umur),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

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

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.et_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText7.perform(scrollTo(), replaceText("juan"), closeSoftKeyboard());

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

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.et_jenis_kelamin),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_jenis_kelamin),
                                        0),
                                1)));
        textInputEditText8.perform(scrollTo(), replaceText("laki-laki"), closeSoftKeyboard());

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

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.et_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_password),
                                        0),
                                1)));
        textInputEditText9.perform(scrollTo(), replaceText("juanito123"), closeSoftKeyboard());

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

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.et_email), withText("juan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1)));
        textInputEditText10.perform(scrollTo(), replaceText("juan.makan.mendoan@gmail.com"));

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.et_email), withText("juan.makan.mendoan@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_email),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText11.perform(closeSoftKeyboard());

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
