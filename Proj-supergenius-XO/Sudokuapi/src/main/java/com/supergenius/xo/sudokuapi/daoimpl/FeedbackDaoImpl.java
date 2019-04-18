package com.supergenius.xo.sudokuapi.daoimpl;


import com.supergenius.xo.sudokuapi.dao.FeedbackDao;
import com.supergenius.xo.sudokuapi.entity.Feedbacks;
import org.springframework.stereotype.Component;

/**
 * 反馈信息Dao实现
 * @author yongxuezhen
 */
@Component
public class FeedbackDaoImpl extends BaseSudokuMongoDaoImpl<Feedbacks> implements FeedbackDao {

}
