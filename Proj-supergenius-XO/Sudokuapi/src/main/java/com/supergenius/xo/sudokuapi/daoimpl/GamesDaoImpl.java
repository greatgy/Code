package com.supergenius.xo.sudokuapi.daoimpl;

import org.springframework.stereotype.Component;

import com.supergenius.xo.sudokuapi.dao.GamesDao;
import com.supergenius.xo.sudokuapi.entity.Games;


/**
 * 游戏Dao实现
 * 
 * @author ChenQi
 */
@Component
public class GamesDaoImpl extends BaseSudokuMongoDaoImpl<Games> implements GamesDao {

}
