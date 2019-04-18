package com.supergenius.xo.career.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.career.entity.Music;

/**
 * 背景音乐Dao
 * @author ChenQi
 * @date 2017年12月27日19:22:46
 */
@Component("careerMusicDao")
public interface MusicDao extends BaseDao<Music> {
	
}
