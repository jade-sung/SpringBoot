package com.example.hello;

import com.example.hello.data.Board;
import com.example.hello.repository.BoardRepository;
import com.example.hello.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HelloApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Transactional //메서드가 종료될 때까지 DB 세션을 유지함
	@Test
	void testJpa() {
		Optional<Board> ob = boardRepository.findById(1);
		assertTrue(ob.isPresent());
		Board b = ob.get();

		assertEquals("답변내용1", b.getReplyList().get(0).getContent());

	}

}
