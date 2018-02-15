package com.java.chat1;

/**
 * @see https://www.youtube.com/watch?v=YEZx_Bz_OPA
 */

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.Matchers.contains; // pom.xml ?
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskAnswerThenChooseRandom()
            throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenAskHelloThenBeackGreatOracle()
            throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "Hello oracle",
                        "exit"
                ),
                String.format("Hello dear friend, I'm a oracle.%s%s", LN, LN)
        );
    }

    @Test
    public void whenUnsupportedAskThenDontUnderstand()
            throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "unsupported ask",
                        "exit"
                ),
                Joiner.on(LN).join("I don't understand", "", "")
        );
    }

    private void testServer(String input, String excepted) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(excepted));
    }

}
