package com.kelompok_3_kelas_a.project_kelompok_uas_pbp.UnitTesting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.android.volley.RequestQueue;
import com.kelompok_3_kelas_a.project_kelompok_uas_pbp.email.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)


public class RegisterPresenterTest {

    @Mock
    private RegisterView view;
    @Mock
    private RegisterService service;
    @Mock
    private RegisterPresenter presenter;
    @Mock
    private RegisterActivity activity;
    @Mock
    private RequestQueue queue;


    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenter(view, service,activity,queue);
    }

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws
            Exception {
        when(view.getNama()).thenReturn("");
        System.out.println("Testing Pertama: Inputan Nama Kosong");
        System.out.println( view.getNama());
        presenter.onRegisterClicked();
        verify(view).showNamaError("Nama tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenUmurIsLess3() throws
            Exception {
        System.out.println("Testing Kedua: Inputan Nama Depan Kosong");
            when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("1235");
        System.out.println(view.getUmur());
        presenter.onRegisterClicked();
        verify(view).showUmurError("Umur tidak lebih dari 3 digit");
    }

    @Test
    public void shouldShowErrorMessageWhenUmurIsEmpty() throws
            Exception {
        System.out.println("Testing Ketiga: Inputan Umur Kosong");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("");
        System.out.println(view.getUmur());
        presenter.onRegisterClicked();
        verify(view).showUmurError("Umur tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenUmurIsNumberButNotInRange() throws
            Exception {
        System.out.println("Testing Keempat: Inputan Keempat angka dan tidak sesuai dengan format");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("133");
        System.out.println(view.getUmur());
        presenter.onRegisterClicked();
        verify(view).showUmurError("Umur harus merupakan angka");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws
            Exception {
        System.out.println("Testing Kelima: Inputan Kelima email kosong");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("13");
        System.out.println(view.getUmur());
        when(view.getEmail()).thenReturn("");
        System.out.println(view.getEmail());
        presenter.onRegisterClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsNotInFormat() throws
            Exception {
        System.out.println("Testing Keenam: Inputan Keenam Format tidak sesuai");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("13");
        System.out.println(view.getUmur());
        when(view.getEmail()).thenReturn("kosong123");
        System.out.println(view.getEmail());
        presenter.onRegisterClicked();
        verify(view).showEmailError("Email harus berformat email");
    }

    @Test
    public void shouldShowErrorMessageWhenJenisKelaminIsEmpty() throws
            Exception {
        System.out.println("Testing Ketujuh: Jenis Kelamin  kosong");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("13");
        System.out.println(view.getUmur());
        when(view.getEmail()).thenReturn("juanitochristian@gmail.com");
        System.out.println(view.getEmail());
        when(view.getJenisKelamin()).thenReturn("");
        System.out.println(view.getJenisKelamin());
        presenter.onRegisterClicked();
        verify(view).showJenisKelaminError("Jenis Kelamin tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws
            Exception {
        System.out.println("Testing Kedelepan: Password kosong");
        when(view.getNama()).thenReturn("Juan");
        System.out.println( view.getNama());
        when(view.getUmur()).thenReturn("13");
        System.out.println(view.getUmur());
        when(view.getEmail()).thenReturn("juanitochristian@gmail.com");
        System.out.println(view.getEmail());
        when(view.getJenisKelamin()).thenReturn("Laki-laki");
        System.out.println(view.getJenisKelamin());
        when(view.getPassword()).thenReturn("");
        System.out.println(view.getPassword());
        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }











}

