package security;

import java.io.IOException;

public interface Password {

    String getStaticSalt() throws IOException;

    byte[] getDynamicSalt32();

    byte[] getDynamicSalt64();

    byte[] getDynamicSalt(final int size);

    byte[] getHashedPassword(final String password, final byte[] salt) throws IOException;

    boolean isPasswordCorrect(final String password, final byte[] salt, final byte[] hash) throws IOException;
}
