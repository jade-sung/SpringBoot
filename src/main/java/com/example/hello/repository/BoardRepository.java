package com.example.hello.repository;

import com.example.hello.data.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
                Board findByTitle (String title);
                Board findByTitleAndContent(String title, String content);
                List<Board> findByTitleLike (String title);
                Page<Board> findAll(Pageable pageable);
                @Query("select "
                    + "distinct q "
                    + "from Board q "
                    + "left outer join User u1 on q.author=u1 "
                    + "left outer join Reply a on a.board=q "
                    + "left outer join User u2 on a.author=u2 "
                    + "where "
                    + "   q.title like %:kw% "
                    + "   or q.content like %:kw% "
                    + "   or u1.username like %:kw% "
                    + "   or a.content like %:kw% "
                    + "   or u2.username like %:kw% ")
                Page<Board> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
        }
