package api.apispring.repository;

import api.apispring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        /*
        Java의 람다
        - values().stream() : 루프 돌리기
        -  member에서 member의 getName()이 파라미터로 넘어온 name과 같은지 filter()한다.
         - 값이 같은 경우에만 필터링된다.
        - findAny() : 하나라도 찾으면 값이 반환된다. 끝까지 돌려도 값이 없으면 null을 반환한다.
        */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 value들이 반환된다.
    }

    public void clearStore(){
        store.clear();  // clear() : store를 비우기
    }
}
