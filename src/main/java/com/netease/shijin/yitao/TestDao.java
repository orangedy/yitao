package com.netease.shijin.yitao;

import org.apache.ibatis.session.SqlSession;

public class TestDao {
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
      this.sqlSession = sqlSession;
    }

    public User getUser(int userId) {
      return (User) sqlSession.selectOne("org.mybatis.example.UserMapper.selectUser", userId);
    }
}
