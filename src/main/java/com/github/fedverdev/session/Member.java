package com.github.fedverdev.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private InetAddress address;
    private int portUDP;
    private int portTCP;
    private String name;

    private MemberType memberType;

    public enum MemberType {
        member,
        host
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (this.address.getHostName().equals(member.address.getHostName())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address);
    }
}
