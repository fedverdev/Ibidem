package com.github.fedverdev.session;

import lombok.AllArgsConstructor;

import java.net.InetAddress;
import java.util.Set;

@AllArgsConstructor
public class Session {
    private final Set<Member> members;

    public void addMember(InetAddress address, int portUDP, int portTCP, Member.MemberType type, String name) {
        members.add(new Member(
                address,
                portUDP,
                portTCP,
                name,
                type
        ));
    }

    public Set<Member> getAllMembers() {
        return members;
    }

    public Member getHost() {
        return members
                .stream()
                .filter((member) -> member.getMemberType() == Member.MemberType.host)
                .findFirst().orElse(null);
    }
}
