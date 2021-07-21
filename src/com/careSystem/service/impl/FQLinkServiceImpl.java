package com.careSystem.service.impl;

import com.careSystem.dao.FormDao;
import com.careSystem.dao.QuesDao;
import com.careSystem.dao.impl.FQLinkDaoImpl;
import com.careSystem.pojo.FQ_Link;
import com.careSystem.pojo.Question;
import com.careSystem.service.FQLinkService;
import com.careSystem.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enzo Cotter on 2021/7/13.
 */
public class FQLinkServiceImpl implements FQLinkService {

    private static FQLinkServiceImpl fqLinkService = new FQLinkServiceImpl();
    private FormDao formDao = (FormDao) Util.getObject("form.dao");
    private QuesDao quesDao = (QuesDao) Util.getObject("question.dao");

    private FQLinkServiceImpl() {
    }

    public static FQLinkServiceImpl getInstance() {
        return fqLinkService;
    }

    @Override
    public List<FQ_Link> findAll() {
        return FQLinkDaoImpl.getInstance().findAll();
    }

    @Override
    public Boolean add(List<FQ_Link> fqLinks) {
        return FQLinkDaoImpl.getInstance().add(fqLinks);
    }

    @Override
    public Boolean delete(List<FQ_Link> fqLinks) {
        return FQLinkDaoImpl.getInstance().delete(fqLinks);
    }

    @Override
    public List<Question> findQuestion(int formId) {
        List<Question> list = QuesServiceImpl.getInstance().findAll();
        List<FQ_Link> fqLinks = FQLinkServiceImpl.getInstance().findAll();
        List<Integer> match = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
//        System.out.println(list);测试
        if (fqLinks != null && !fqLinks.isEmpty()) {
            for (FQ_Link link : fqLinks) {
                if (formId == link.getFid()) {
                    match.add(link.getQid());
                }
            }
        }
        if (list != null && !list.isEmpty()) {
            for (Integer matchid : match) {
                for (Question q : list) {
                    if (matchid == q.getId()) {
                        questionList.add(q);
                    }
                }
            }
            return questionList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteLinkByQuestionId(List<Integer> questionIds) {
        List<FQ_Link> fqLinks = findAll();
        List<FQ_Link> removeList = new ArrayList<>();//将要被删除的关系
        for (Integer id : questionIds) {
            for (FQ_Link fq : fqLinks) {
                if (id == fq.getFid()) {
                    removeList.add(fq);
                }
            }
        }
        FQLinkDaoImpl.getInstance().delete(removeList);

    }

    @Override
    public void deleteLinkByFormId(List<Integer> FormIds) {
        List<FQ_Link> fqLinks = findAll();
        List<FQ_Link> removeList = new ArrayList<>();//将要被删除的关系
        for (Integer id : FormIds) {
            for (FQ_Link fq : fqLinks) {
                if (id == fq.getQid()) {
                    removeList.add(fq);
                }
            }
        }
        FQLinkDaoImpl.getInstance().delete(removeList);
    }
}
