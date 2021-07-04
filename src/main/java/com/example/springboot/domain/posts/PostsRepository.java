package com.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
* If Entity Repository class extends JpaRepository<Entity class, PK type>, basic CRUD methods would be auto-created.
*
* PostsRepository클래스는 Posts 클래스로 Database에 접근하게 해주는 JpaRepository이다.
* Mybatis에서 Dao라고 불리는 DB Layer접근자이며, Jpa에서는 Repository라고 부른다.
* interface로 생성한 뒤 JpaRepository<Entity 클래스, PK 타입>을 상속하면 CRUD 메서드가 자동으로 생성된다.
* Entity클래스(Posts)와 Entity Repository(PostsRepository)는 같은 곳에 위치해야 한다.
*
* */
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
