package com.example.realworld.domain.user.passwordencoder;

import org.springframework.stereotype.Component;
public interface PasswordEncryption {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

    @Component
    class FakePasswordEncryption implements PasswordEncryption {

        static class PasswordEncryptionHolder {

            private final static FakePasswordEncryption INSTANCE = new FakePasswordEncryption();

        }

        public static FakePasswordEncryption instance() {
            return PasswordEncryptionHolder.INSTANCE;
        }

//        public static PasswordEncryption instance() {
//            return PasswordEncryptionHolder.INSTANCE;
//        }

        private FakePasswordEncryption() {
        }

        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            ;
            return encodedPassword.contentEquals(rawPassword);
        }
    }


}
