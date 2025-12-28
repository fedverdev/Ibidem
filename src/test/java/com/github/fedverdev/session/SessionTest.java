package com.github.fedverdev.session;

import org.junit.jupiter.api.*;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

@DisplayName("Session test")
public class SessionTest {

    Set<Member> members;
    Session session;

    @BeforeEach
    public void setUp() {
        members = new HashSet<>();
        session = new Session(members);
    }

    @DisplayName("adding a member")
    @Test
    public void addingMember() {
        Assertions.assertDoesNotThrow(() -> {
            session.addMember(
                    InetAddress.getByName("1.1.1.1"),
                    200,
                    100,
                    Member.MemberType.member,
                    "Greg");
        });

        Assertions.assertFalse(session.getAllMembers().isEmpty());

    }

    @DisplayName("adding a host")
    @Test
    public void addingHost() {
        Assertions.assertDoesNotThrow(() -> {
            session.addMember(
                    InetAddress.getByName("1.1.1.1"),
                    200,
                    100,
                    Member.MemberType.host,
                    "Greg");
        });

        Assertions.assertFalse(session.getAllMembers().isEmpty());

        Member host = session.getHost();
        Assertions.assertNotNull(host);
        Assertions.assertEquals(Member.MemberType.host, host.getMemberType());

    }
}
