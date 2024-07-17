package com.example.movie.Repository;

import com.example.movie.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
}
