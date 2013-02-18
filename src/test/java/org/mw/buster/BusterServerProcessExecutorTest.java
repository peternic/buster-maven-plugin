package org.mw.buster;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusterServerProcessExecutorTest {

    private BusterServerProcessExecutor busterServer;

    private PluginProcess mockProcess;
    private PhantomJsBrowser mockBrowser;


    @Before
    public void setUp(){
        mockProcess = mock(PluginProcess.class);
        mockBrowser = mock(PhantomJsBrowser.class);
        busterServer = new BusterServerProcessExecutor(mockProcess, mockBrowser);
    }

    @Test
    public void shouldStartBusterProcess(){
        busterServer.start();
        verify(mockProcess).start();

    }

    @Test
    public void shouldStartAndStopBusterProcess(){
        busterServer.start();
        verify(mockProcess).start();
        busterServer.stop();
        verify(mockProcess).stop();
        verify(mockBrowser).stop();
    }

    @Test
    public void shouldCaptureBrowser() throws IOException {
        when(mockProcess.getPort()).thenReturn("1111");
        busterServer.captureBrowser();
        verify(mockBrowser).capturePhantomBrowser(String.format("http://localhost:1111"));
    }
}