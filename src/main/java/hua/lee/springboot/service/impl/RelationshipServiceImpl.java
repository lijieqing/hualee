package hua.lee.springboot.service.impl;

import org.springframework.stereotype.Service;
import hua.lee.springboot.dao.RelationshipVoMapper;
import hua.lee.springboot.modal.vo.RelationshipVoExample;
import hua.lee.springboot.modal.vo.RelationshipVoKey;
import hua.lee.springboot.service.IRelationshipService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author psvm
 * @date 2018/1/24 21:33
 */
@Service
public class RelationshipServiceImpl implements IRelationshipService {

    @Resource
    private RelationshipVoMapper relationshipVoDao;

    @Override
    public void deleteById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        relationshipVoDao.deleteByExample(relationshipVoExample);
    }

    @Override
    public Long countById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        long num = relationshipVoDao.countByExample(relationshipVoExample);
        return num;
    }

    @Override
    public void insertVo(RelationshipVoKey relationshipVoKey) {
        relationshipVoDao.insert(relationshipVoKey);
    }

    @Override
    public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        return relationshipVoDao.selectByExample(relationshipVoExample);
    }
}
