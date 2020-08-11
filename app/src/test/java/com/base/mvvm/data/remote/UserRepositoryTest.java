package com.base.mvvm.data.remote;

import com.base.mvvm.domain.entities.requests.LoginRequest;
import com.base.mvvm.domain.entities.response.LoginResponse;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    RemoteAPI remoteAPI;

    @InjectMocks
    UserRepository repository;

    private UserEntity vEntity;

    private UserEntity unvEntity;

    @Before
    public void setUp() throws Exception {
        vEntity = new UserEntity();
        vEntity.setUsername("username");
        vEntity.setPassword("password");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoginSuccessful() {
        LoginResponse response = new LoginResponse("jwt", vEntity);
        LoginRequest request = new LoginRequest("username", "password");
        when(remoteAPI.login(request)).thenReturn(Single.just(response));
        TestObserver<LoginResponse> observer = repository.login(request).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors().assertValue(r -> r.getUser().getUsername().equals("username")
                && r.getJwt().equals("jwt"));
    }
}