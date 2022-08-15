package com.example.realworld.domain.user.passwordencoder;

public interface PasswordEncryption {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

    class FakePasswordEncryption implements PasswordEncryption {

        private FakePasswordEncryption() {
        }

        public static PasswordEncryption instance() {
            return PasswordEncryptionHolder.INSTANCE;
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

        static class PasswordEncryptionHolder {

            private final static FakePasswordEncryption INSTANCE = new FakePasswordEncryption();

        }

    }

}
