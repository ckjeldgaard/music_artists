package com.trifork.ckp.musicartists.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static org.mockito.Mockito.when;

public class ApiResponseInterceptorTest {

    @Mock
    private Interceptor.Chain chain;

    @Mock
    private Request request;

    @Mock
    private Response response;

    @Mock
    private ResponseBody responseBody;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInterceptReturnsNewResponse() throws Exception {
        when(chain.request()).thenReturn(request);
        when(chain.proceed(request)).thenReturn(response);
        when(response.body()).thenReturn(responseBody);

        ApiResponseInterceptor interceptor = new ApiResponseInterceptor();
        interceptor.intercept(chain);
    }
}