package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.ProjectDao;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.EProjectChannel;
import com.supergenius.xo.tpi.enums.EProjectState;
import com.supergenius.xo.tpi.enums.EWishType;
import com.supergenius.xo.tpi.service.ProjectSO;
import com.supergenius.xo.tpi.service.TypeSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * ProjectDao项目SOImpl
 * @author liushaomin
 */
@Service
public class ProjectSOImpl extends BaseSOImpl<Project> implements ProjectSO{

	@Autowired
	private ProjectDao dao;

	@Override
	protected ProjectDao getDao() {
		return dao;
	}
	
	@Autowired
	private TypeSO typeSO;
	
	@Autowired
	private WishSO wishSO;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getList()
	 */
	@Override
	public List<Project> getList(Map<String, Object> map) {
		List<Project> projects = dao.getList(map);
		if (projects.size() != 0) {
			for (Project item : projects) {
				Type type = typeSO.get(item.getTypeuid());
				if (type != null) {
					item.setTypeName(type.getName());
				}
				
			}
		}
		return projects;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#deleteByUids(java.lang.String[])
	 */
	@Override
	public boolean deleteByUids(String[] ids) {
		for (String id : ids) {
			dao.delete(id);
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#update(com.genius.model.base.enums.EStatus, java.lang.String[])
	 */
	@Override
	public boolean update(EStatus eStatus, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.status, eStatus);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#setTop(java.lang.String[], boolean)
	 */
	@Override
	public boolean setTop(String[] ids, boolean istop) {
		if (!checkIsPublish(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.istop, istop);
			dao.updateFields(map);
		}
		
		return true;
	}
	
	/*
	 * 在操作之前校验数据是否都是已经发布
	 * @param ids
	 * @return 若都已经发布则返回true，否则返回false
	 * @author ShangJianguo
	 */
	private boolean checkIsPublish(String[] ids) {
		Map<String, Object> querymap = new HashMap<>();
		querymap.put(MapperDict.ids, ids);
		List<Project> list = dao.getList(querymap);
		for (Project item : list) {
			if (item.getStatus() != EStatus.enable) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#setRecommend(java.lang.String[], boolean)
	 */
	@Override
	public boolean setRecommend(String[] ids, boolean isrecommend) {
		if (!checkIsPublish(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.isrecommend, isrecommend);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#setPublic(java.lang.String[], boolean)
	 */
	@Override
	public boolean setPublic(String[] ids, boolean ispublic) {
		if (!checkIsPublish(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.ispublic, ispublic);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#setCherished(java.lang.String[], boolean)
	 */
	@Override
	public boolean setCherished(String[] ids, boolean ischerished) {
		if (!checkIsPublish(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.uid, id);
			map.put(MapperDict.ischerished, ischerished);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#update(com.supergenius.xo.tpi.enums.EProjectState, java.lang.String)
	 */
	@Override
	public boolean update(EProjectState state, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String id : ids) {
			map.put(MapperDict.state, state);
			map.put(MapperDict.uid, id);
			dao.updateFields(map);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getListByTop()
	 */
	@Override
	public List<Project> getListByCherished(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.ischerished, true);
		List<Project> list = dao.getList(map);
		for (Project item : list) {
			item.setWantinum(wishSO.getCount(null, item.getUid(), EWishType.wantInvestment, EChannel.project));
			item.setWantmnum(wishSO.getCount(null, item.getUid(), EWishType.wantMergers, EChannel.project));
			item.setCountnum(wishSO.getCount(null, item.getUid(), EWishType.attention, EChannel.project));
			try {
				item.setTypeName(typeSO.get(item.getTypeuid()).getName());
			} catch (Exception e) {
				continue;
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getListByRecommend()
	 */
	@Override
	public List<Project> getListByRecommend(Pager pager, EProjectChannel channel) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.isrecommend, true);
		map.put(MapperDict.channel, channel);
		List<Project> list = dao.getList(map);
		for (Project item : list) {
			item.setWantinum(wishSO.getCount(null, item.getUid(), EWishType.wantInvestment, EChannel.project));
			item.setWantmnum(wishSO.getCount(null, item.getUid(), EWishType.wantMergers, EChannel.project));
			item.setCountnum(wishSO.getCount(null, item.getUid(), EWishType.attention, EChannel.project));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getOneRecommend(com.supergenius.xo.tpi.enums.EProjectChannel)
	 */
	@Override
	public Project getOneRecommend(EProjectChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.isrecommend, true);
		Project project = dao.getOne(map);
		if (project != null) {
			project.setWantinum(wishSO.getCount(null, project.getUid(), EWishType.wantInvestment, EChannel.project));
			project.setWantmnum(wishSO.getCount(null, project.getUid(), EWishType.wantMergers, EChannel.project));
			project.setCountnum(wishSO.getCount(null, project.getUid(), EWishType.attention, EChannel.project));
		}
		return project;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getList(com.genius.model.base.entity.Pager, com.supergenius.xo.tpi.entity.Type)
	 */
	@Override
	public List<Project> getList(Pager pager, EProjectChannel channel, String typeuid) {
		Map<String, Object> map = getParamMap(pager);
		if (channel != null) {
			map.put(MapperDict.channel, channel);
		}
		if (StringUtils.isNotEmpty(typeuid)) {
			map.put(MapperDict.typeuid, typeuid);
		}
		List<Project> list = dao.getList(map);
		for (Project item : list) {
			item.setWantinum(wishSO.getCount(null, item.getUid(), EWishType.wantInvestment, EChannel.project));
			item.setWantmnum(wishSO.getCount(null, item.getUid(), EWishType.wantMergers, EChannel.project));
			item.setCountnum(wishSO.getCount(null, item.getUid(), EWishType.attention, EChannel.project));
			try {
				item.setTypeName(typeSO.get(item.getTypeuid()).getName());
			} catch (Exception e) {
				continue;
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getCount(com.supergenius.xo.tpi.enums.EProjectChannel)
	 */
	@Override
	public int getCount(EProjectChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.channel, channel);
		return dao.getCount(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getList(java.util.List)
	 * @author: ShangJianguo
	 * 2015-1-15 下午12:26:42
	 */
	@Override
	public List<Project> getList(List<String> uids) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid + MapperDict.suffix_in_key, uids);
		List<Project> list = dao.getList(map);
		for (Project item : list) {
			item.setTypeName(typeSO.get(item.getTypeuid()).getName());
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getList(java.util.List)
	 * @author: ShangJianguo
	 * 2015-1-15 下午12:26:42
	 */
	@Override
	public List<Project> getList(Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		List<Project> list = dao.getList(map);
		for (Project item : list) {
			item.setWantinum(wishSO.getCount(null, item.getUid(), EWishType.wantInvestment, EChannel.project));
			item.setWantmnum(wishSO.getCount(null, item.getUid(), EWishType.wantMergers, EChannel.project));
			item.setCountnum(wishSO.getCount(null, item.getUid(), EWishType.attention, EChannel.project));
		}
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String typeuid, EProjectChannel channel) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.typeuid, typeuid);
		map.put(MapperDict.channel, channel);
		return dao.getCount(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getCount(java.lang.String)
	 */
	public List<Project> getList(String uid, Pager pager) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.fromuid, uid);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getList(java.lang.String)
	 */
	public List<Project> getList(String uid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.fromuid, uid);
		return dao.getList(map);
	}

	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getList(boolean, int)
	 * @author: ShangJianguo
	 * 2015-2-12 下午12:28:52
	 */
	@Override
	public List<Project> getList(EProjectChannel channel, boolean istop, int num) {
		Map<String, Object> map = getParamMap(num);
		map.put(MapperDict.channel, channel);
		map.put(MapperDict.istop, istop);
		return dao.getList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#get(java.lang.String)
	 * @author: ShangJianguo
	 * 2015-2-12 下午3:26:47
	 */
	@Override
	public Project get(String id) {
		Project project = super.get(id);
		try {
			project.setTypeName(typeSO.get(project.getTypeuid()).getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return project;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#getCount(java.lang.String)
	 * @author: ShangJianguo
	 * 2015-3-4 上午11:54:52
	 */
	@Override
	public int getCount(String fromuid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.fromuid, fromuid);
		return dao.getCount(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.ProjectSO#update(java.lang.String, com.genius.model.base.enums.EStatus, com.supergenius.xo.tpi.enums.EProjectState)
	 */
	@Override
	public boolean update(String uid, EStatus status, EProjectState state) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.uid, uid);
		map.put(MapperDict.status, status);
		map.put(MapperDict.state, state);
		return dao.updateFields(map);
	}

}

