//package com.java.chat1;
//
//import com.google.common.base.Joiner;
//import org.junit.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.Random;
//
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.Matchers.contains;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class ServerTest {
//
//    private static final String LN = System.getProperty("line.separator");
//
//    @Test
//    public void whenAskAnswerThenChooseRandom() throws IOException{
//        Socket socket = mock(Socket.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
//        when(socket.getInputStream()).thenReturn(in);
//        when(socket.getOutputStream()).thenReturn(out);
//        Server server = new Server(socket);
//        server.start();
//        assertThat(out.toString(), is(""));
//    }
//
//    @Test
//    public void whenAskHelloThenBeackGreatOracle() throws IOException{
//        Socket socket = mock(Socket.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(
////                String.format(
////                        "Hello oracle%sexit",
////                        LN
////                ).getBytes()
//                Joiner.on(LN).join(
//                        "Hello oracle",
//                        "exit"
//                ).getBytes()
//        );
//        when(socket.getInputStream()).thenReturn(in);
//        when(socket.getOutputStream()).thenReturn(out);
//        Server server = new Server(socket);
//        server.start();
//        assertThat(out.toString(), is(
//                String.format("Hello dear friend, I'm a oracle.%s%s", LN, LN)
//                )
//        );
//    }
//
//    @Test
//    public void whenUnsupportedAskThenDontUnderstand() throws IOException{
//        Socket socket = mock(Socket.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(
////                String.format(
////                        "Hello oracle%sexit",
////                        LN
////                ).getBytes()
//                Joiner.on(LN).join(
//                        "unsupported ask",
//                        "exit"
//                ).getBytes()
//        );
//        when(socket.getInputStream()).thenReturn(in);
//        when(socket.getOutputStream()).thenReturn(out);
//        Server server = new Server(socket);
//        server.start();
//        assertThat(out.toString(), is(
//                        Joiner.on(LN).join(
//                                "I don't understand",
//                                "",
//                                ""
//                        )
//                )
//        );
//    }
//}
