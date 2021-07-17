package com.ruoyi.network.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.network.mapper.ContentMapper;
import com.ruoyi.network.domain.Content;
import com.ruoyi.network.service.IContentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-15
 */
@Service
public class ContentServiceImpl implements IContentService 
{
    @Autowired
    private ContentMapper contentMapper;

    /**
     * 查询内容
     * 
     * @param id 内容ID
     * @return 内容
     */
    @Override
    public Content selectContentById(Long id)
    {
        return contentMapper.selectContentById(id);
    }

    /**
     * 查询内容列表
     * 
     * @param content 内容
     * @return 内容
     */
    @Override
    public List<Content> selectContentList(Content content)
    {
        return contentMapper.selectContentList(content);
    }

    /**
     * 新增内容
     * 
     * @param content 内容
     * @return 结果
     */
    @Override
    public int insertContent(Content content)
    {
        return contentMapper.insertContent(content);
    }

    /**
     * 修改内容
     * 
     * @param content 内容
     * @return 结果
     */
    @Override
    public int updateContent(Content content)
    {
        return contentMapper.updateContent(content);
    }

    /**
     * 删除内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteContentByIds(String ids)
    {
        return contentMapper.deleteContentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除内容信息
     * 
     * @param id 内容ID
     * @return 结果
     */
    @Override
    public int deleteContentById(Long id)
    {
        return contentMapper.deleteContentById(id);
    }

    @Override
    public int getContentNum(HashMap<String, Object> pm) {
        return contentMapper.getContentNum(pm);
    }

    @Override
    public List<Content> getContentList(HashMap<String, Object> pm) {
        return contentMapper.getContentList(pm);
    }
}
