package org.example.javaspringbootbasic2.repository;

import lombok.extern.log4j.Log4j2;
import org.example.javaspringbootbasic2.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    // 1. insert 기능 테스트
    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("title1...")
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO: {}", result.getBno());

        }
    }

    // 2. select 기능 테스트
    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno); // 반환형이 Optional <-- 컬렉션의 일종
        Board board = result.orElseThrow();
        // 사용하는 메서드는 findById이고 반환형은 Optional 이다.
        log.info("board select 테스트 : " + board);
    }

    // 3. update 기능 테스트
    @Test
    public void testUpdate() {
        // update 기능은 insert와 동일하게 save() 를 통해서 처리.
        // 동일한 @Id값을 가지는 객체를 생성해서 처리가능.
        // 근데 mabatis보다 비 효율적인 동작이 있다. 조금 복잡하기도 하고

        // 테스트 코드에서 change를 이용해서 update 를 수행
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        board.change("update... title 100", "update content 100");
        boardRepository.save(board);
    }


    // JDBC나 MyBatis 방식으로 처리하면 에러
    @Test
    public void testUpdate2() {
        Long bno = 100L;
        Board board = Board.builder()
                .bno(bno)
                .title("title...")
                .content("content... update2")
                .build();
        boardRepository.save(board);
        // writer 가 not-null 인데 빠져서 안됨.
    }


    // 없는 bno를 지정한 경우
    @Test
    public void testUpdate3() {
        Long bno = 1000L;
        Board board = Board.builder()
                .bno(bno)
                .title("title...")
                .content("content... update2")
                .writer("user...update")
                .build();
        boardRepository.save(board);
        // 된다.

        // 없는 bno라서 insert가 되었다.
    }

    // delete 기능 테스트
    @Test
    public void testDelete() {
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }

    // 개발자들이 처음에 JPA를 사용안했던 이유가
    // 간다한 업데이트를 하는데도 select를 2번이나 실행하는등 리소스를 쓸데없이 잡아먹어 부하면에서 불리해서 안썼는데
    // 클라우드 나오고 MSA로 추세가 갈아타지면서 조금 더 유연한? 방식이 선호하게 되었다.

    // 부하를 더 잡아먹더라도 지금은 쓴다.


}